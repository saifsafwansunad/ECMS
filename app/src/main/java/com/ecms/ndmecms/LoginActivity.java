package com.ecms.ndmecms;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {



Button buttonLogin,buttonReset;
TextView textViewForgotPassword;
    public static UserLoginResponse userLoginResponse;

EditText editTextLoginEmail,editTextLoginPassword;
    public static String data,username;
    private String Uid;
    private ProgressDialog progressDialog;
    private List<UserLoginResponse> dataArrayList=new ArrayList<>();

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private static boolean IsCouncillor;


    public static CheckBox checkBox;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
    buttonLogin=(Button) findViewById(R.id.login_btn);
    checkBox=findViewById(R.id.login_checkbox);
    editTextLoginEmail=findViewById(R.id.emailId);
    editTextLoginPassword=findViewById(R.id.password);
        textViewForgotPassword=findViewById(R.id.forgot_password_textview);

        buttonReset=(Button) findViewById(R.id.reset_btn);
    getSupportActionBar().hide();
        PreferenceUtils utils = new PreferenceUtils();

        if (utils.getUid(this) != null ){
                MainActivity.stopService = false;
                IsCouncillor=PreferenceUtils.getCouncilor(LoginActivity.this);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

              }else{
        }

    textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
ForgotPasswordDialog forgotPasswordDialog=new ForgotPasswordDialog();
forgotPasswordDialog.showDialog(LoginActivity.this,"Forgot Password");
        }
    });
    buttonReset.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            editTextLoginEmail.getText().clear();
            editTextLoginPassword.getText().clear();
            editTextLoginEmail.requestFocus();
        }
    });

    buttonLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (editTextLoginEmail.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Enter Username", Toast.LENGTH_SHORT).show();
            } else {
                    if (editTextLoginPassword.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_SHORT).show();
                    }else{

                        login();


                        // emptyInputEditText();

                    }
                }


          /*  if(checkBox.isChecked()) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }else
            {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }*/
        }
    });
    }
    public static boolean checkValue(){

        return IsCouncillor;
    }



    public final void login() {

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
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(LoginActivity.this);
        progressDoalog.setMessage("Logging In....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();

        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUname(editTextLoginEmail.getText().toString());
        loginRequest.setUpwd(editTextLoginPassword.getText().toString());


        PreferenceUtils.saveEmail(loginRequest.getUname(),this);
        PreferenceUtils.savePassword(loginRequest.getUpwd(),this);
        View contextView = findViewById(android.R.id.content);

        if (!DetectConnection.checkInternetConnection(this)) {
            progressDoalog.dismiss();
            Snackbar snackbar =Snackbar.make(contextView, "Sorry, No Internet", Snackbar.LENGTH_LONG);
            snackbar.setAction("Retry", new TryAgainListener());

            snackbar.show();
        } else {
            Call<List<UserLoginResponse>> userloginResponseCall = ApiClient.getUserService().userLogin(loginRequest.getUname(), loginRequest.getUpwd());
            try {

                username=loginRequest.getUname();
                userloginResponseCall.enqueue(new Callback<List<UserLoginResponse>>() {
                    @Override
                    public void onResponse(Call<List<UserLoginResponse>> call, Response<List<UserLoginResponse>> response) {
                        progressDoalog.dismiss();
                        if (response.isSuccessful()) {

                            dataArrayList = response.body();
                            for (int i = 0; i < dataArrayList.size(); i++) {
                                userLoginResponse = dataArrayList.get(i);

                                data = dataArrayList.get(i).getId();
                                PreferenceUtils.saveUid(data,LoginActivity.this);
                                PreferenceUtils.saveUserName(userLoginResponse.getName(),LoginActivity.this);
                                PreferenceUtils.saveMobile(userLoginResponse.getMobile(),LoginActivity.this);
                                PreferenceUtils.saveEmail(userLoginResponse.getEmail(),LoginActivity.this);


                                Log.d("key of the message", "The message " + data);

                            }
                            if (data == null) {

                                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();

                            } else {
                              //  userLoginResponse.setPassword(loginRequest.getUpwd());
                               // userLoginResponse.setUsername(loginRequest.getUname());
                                        if(userLoginResponse.getIsCouncillor().equals("True")){
                                            PreferenceUtils.saveCouncilor(true,LoginActivity.this);
                                            boolean save=PreferenceUtils.getCouncilor(LoginActivity.this);
                                            IsCouncillor = true;
                                        }else {
                                            PreferenceUtils.saveCouncilor(false,LoginActivity.this);

                                            IsCouncillor = false;

                                        }
                                MainActivity.stopService = false;

                                Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();








                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<UserLoginResponse>> call, Throwable t) {
                        progressDoalog.dismiss();

                        Toast.makeText(LoginActivity.this, "Server Error", Toast.LENGTH_LONG).show();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    public void setUid(String data) {
        Uid=data;
    }


    public String  getUid(){
        return Uid;
    }

    public class TryAgainListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // Code to undo the user's last action
            View contextView = findViewById(android.R.id.content);
            // Make and display Snackbar
            if (!DetectConnection.checkInternetConnection(LoginActivity.this)) {
                Snackbar snackbar =Snackbar.make(contextView, "Sorry, you're offline", Snackbar.LENGTH_LONG);
                snackbar.setAction("Retry", new TryAgainListener());
                snackbar.show();
            }
            else {

                Snackbar.make(contextView, "Internet Connected", Snackbar.LENGTH_SHORT)
                        .show();
            }

        }
    }
    private void emptyInputEditText(){
        editTextLoginEmail.setText(null);
        editTextLoginPassword.setText(null);
    }


    public class ForgotPasswordDialog {

        public void showDialog(Activity activity, String msg){
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

//            dialog.setCancelable(false);
            dialog.setContentView(R.layout.password_dialog_layout);
            Button buttonOk=(Button) dialog.findViewById(R.id.dialog_ok_btn);
            buttonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });


            dialog.show();


        }
    }
}