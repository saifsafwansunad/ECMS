package com.example.ecms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    TextView userName, userEmail, userMobile,textViewHeading;
ImageView imageViewChangePassword;
RelativeLayout relativeLayoutChangePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        userName = findViewById(R.id.user_name_tv);
        userEmail = findViewById(R.id.user_email_tv);
        userMobile = findViewById(R.id.user_mobile_tv);
        textViewHeading=findViewById(R.id.profile_heading_textview);

relativeLayoutChangePassword=findViewById(R.id.change_password_layout);
        relativeLayoutChangePassword.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        ChangePasswordDialog alert = new ChangePasswordDialog();
        alert.showDialog(ProfileActivity.this, "Change Password");
    }
});
        userName.setText(PreferenceUtils.getUserName(this));
        userEmail.setText(PreferenceUtils.getEmail(this));
        userMobile.setText(PreferenceUtils.getMobile(this));
        textViewHeading.setText(PreferenceUtils.getUserName(this));




    }
    public class ChangePasswordDialog {
        TextInputEditText newPassword;

        public void showDialog(Activity activity, String msg){
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

//            dialog.setCancelable(false);
            dialog.setContentView(R.layout.change_password_layout);
Button buttonSave=(Button) dialog.findViewById(R.id.save_btn);
            TextInputEditText currentPassword=dialog.findViewById(R.id.oldPassword);
            newPassword=dialog.findViewById(R.id.newpassword);
            TextInputEditText confirmPassword=dialog.findViewById(R.id.confirmpassword);




            buttonSave.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         //validations for updating password

             if(currentPassword.getText().toString().equals(PreferenceUtils.getPassword(getApplicationContext()))){
                 if(newPassword.getText().toString().equals(confirmPassword.getText().toString())){

                     PasswordChange();
                     dialog.dismiss();


                 }
                 else{
                     Toast.makeText(activity, "Fields not matching please check", Toast.LENGTH_SHORT).show();
                 }
             }
             else{
                 Toast.makeText(activity, "current password wrong", Toast.LENGTH_SHORT).show();
             }


        // PasswordChange();
        // dialog.dismiss();
     }
 });

            Button cancelButton = (Button) dialog.findViewById(R.id.cancel_btn);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int displayWidth = displayMetrics.widthPixels;
            int displayHeight = displayMetrics.heightPixels;
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(dialog.getWindow().getAttributes());
            int dialogWindowWidth = (int) (displayWidth * 1f);
//            int dialogWindowHeight = (int) (displayHeight * 0.5f);
            layoutParams.width = dialogWindowWidth;
//            layoutParams.height = dialogWindowHeight;
            dialog.getWindow().setAttributes(layoutParams);

        }

        public final void PasswordChange() {

//        if (editTextLoginEmail.getText().toString().isEmpty()) {
//            Toast.makeText(getApplicationContext(), "enter email address", Toast.LENGTH_SHORT).show();
//        } else {
//            if (editTextLoginEmail.getText().toString().trim().matches(emailPattern)) {
//                if (editTextLoginPassword.getText().toString().isEmpty()) {
//                    Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_SHORT).show();
//                } else
//                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//            } else {
//                Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
//            }
//
//        }





            View contextView = findViewById(android.R.id.content);

            if (!DetectConnection.checkInternetConnection(getApplicationContext())) {
                Snackbar snackbar =Snackbar.make(contextView, "Sorry, No Internet", Snackbar.LENGTH_LONG);
                snackbar.setAction("Retry", new ChangePasswordDialog.TryAgainListener());

                snackbar.show();
            } else {
                try {
                    Call<List<PasswordResp>> userloginResponseCall = ApiClient.getUserService().changePassword(PreferenceUtils.getUid(getApplicationContext()),newPassword.getText().toString());

                    userloginResponseCall.enqueue(new Callback<List<PasswordResp>>() {
                        @Override
                        public void onResponse(Call<List<PasswordResp>> call, Response<List<PasswordResp>> response) {
                            PreferenceUtils.savePassword(newPassword.getText().toString(),getApplicationContext());
                            Toast.makeText(ProfileActivity.this, "Saved", Toast.LENGTH_SHORT).show();


                        }

                        @Override
                        public void onFailure(Call<List<PasswordResp>> call, Throwable t) {
                            Log.d("key of the message", "onfailure : data not coming" );

                         // Toast.makeText(getApplicationContext(), "on failure called"+t, Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "failed ", Toast.LENGTH_LONG).show();

                }

            }

        }
        public class TryAgainListener implements View.OnClickListener {
            @Override
            public void onClick(View v) {
                // Code to undo the user's last action
                View contextView = findViewById(android.R.id.content);
                // Make and display Snackbar
                if (!DetectConnection.checkInternetConnection(getApplicationContext()) ){
                    Snackbar snackbar =Snackbar.make(contextView, "Sorry, you're offline", Snackbar.LENGTH_LONG);
                    snackbar.setAction("Retry", new ChangePasswordDialog.TryAgainListener());
                    snackbar.show();
                }
                else {

                    Snackbar.make(contextView, "Internet Connected", Snackbar.LENGTH_SHORT)
                            .show();
                }

            }
        }


    }
}