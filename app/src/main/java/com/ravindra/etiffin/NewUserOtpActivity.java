package com.ravindra.etiffin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewUserOtpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_otp);
    }
    /*

     */
    public void submitOtp(View view)
    {

        startActivity(new Intent(NewUserOtpActivity.this,SignUpActivity.class));
        finish();
    }
}
