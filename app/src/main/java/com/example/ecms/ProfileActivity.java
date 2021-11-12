package com.example.ecms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    TextView userName, userEmail, userMobile,textViewHeading;
ImageView imageViewChangePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        userName = findViewById(R.id.user_name_tv);
        userEmail = findViewById(R.id.user_email_tv);
        userMobile = findViewById(R.id.user_mobile_tv);
        textViewHeading=findViewById(R.id.profile_heading_textview);

imageViewChangePassword=findViewById(R.id.change_password_imageview);
imageViewChangePassword.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ViewDialog alert = new ViewDialog();
        alert.showDialog(ProfileActivity.this, "Change Password");
    }
});
        userName.setText(PreferenceUtils.getUserName(this));
        userEmail.setText(PreferenceUtils.getEmail(this));
        userMobile.setText(PreferenceUtils.getMobile(this));
        textViewHeading.setText(PreferenceUtils.getUserName(this));


    }
    public class ViewDialog {

        public void showDialog(Activity activity, String msg){
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

//            dialog.setCancelable(false);
            dialog.setContentView(R.layout.password_dialog_layout);
Button buttonSave=(Button) dialog.findViewById(R.id.save_btn);
 buttonSave.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         dialog.dismiss();
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
    }
}