package com.ravindra.etiffin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;



public class  LoginActivity extends AppCompatActivity {

    TextView tv_NewUser,tv_login;
    EditText et_NewUserMobileNumber,et_loginnumber,et_loginpassword;
    Button btn_signup;
    CardView cv_loginpanel,cv_signuppanel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_loginnumber=(EditText)findViewById(R.id.et_mobilenumber);
        et_loginpassword=(EditText)findViewById(R.id.et_password);
        tv_NewUser=(TextView)findViewById(R.id.tv_newuser);
        et_NewUserMobileNumber=(EditText)findViewById(R.id.et_newuser_mobilenumber);
        btn_signup=(Button)findViewById(R.id.btn_signup);
        cv_loginpanel=(CardView)findViewById(R.id.cv_loginpanel);
        tv_login=(TextView)findViewById(R.id.tv_login);
        cv_signuppanel=(CardView)findViewById(R.id.cv_signuppanel);
        cv_signuppanel.setVisibility(View.GONE);
        tv_login.setVisibility(View.GONE);

    }
    public void login(View v)
    {
        BackgroundTaskForLoginActivity login=new BackgroundTaskForLoginActivity(this);
        login.execute(et_loginnumber.getText().toString(),et_loginpassword.getText().toString());
    }
    public void newuser(View v)
    {
        // show a edit text to input mobile number
        tv_NewUser.setVisibility(View.GONE);
        cv_loginpanel.setVisibility(View.GONE);
        cv_signuppanel.setVisibility(View.VISIBLE);
        tv_login.setVisibility(View.VISIBLE);

    }
    public void setTv_guestmode(View v)
    {
        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
    }
    public void setTv_login(View view)
    {
        cv_loginpanel.setVisibility(View.VISIBLE);
        tv_NewUser.setVisibility(View.VISIBLE);
        cv_signuppanel.setVisibility(View.GONE);
        tv_login.setVisibility(View.GONE);
    }
    public void signup(View v)
    {
        PrefManager prefManager=new PrefManager(getBaseContext());
        prefManager.setRegisterNumber(et_NewUserMobileNumber.getText().toString());
        startActivity(new Intent(LoginActivity.this,NewUserOtpActivity.class));

    }

    /*
    A class to work in background
    async class
    use to communicate server
     */
    public class BackgroundTaskForLoginActivity extends AsyncTask<String,Void,String> {
        Context context;
        String flag;
        String mobilenumber,password;
        AlertDialog alertDialog;
        public BackgroundTaskForLoginActivity(Context ctx)
        {
            context=ctx;

        }

        @Override
        protected String doInBackground(String... params) {

            String loginurl="http://freefoodathome.esy.es/thepakshala/login.php";

                mobilenumber=params[0];
                password=params[1];
                try {
                    URL url=new URL(loginurl);
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    OutputStream outputStream=httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String msg= URLEncoder.encode("mobilenumber","UTF-8")+"="+URLEncoder.encode(mobilenumber,"UTF-8")+"&"
                            +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                    bufferedWriter.write(msg);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream=httpURLConnection.getInputStream();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String line="";
                    String result="";
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            return null;
        }

        @Override
        protected void onPreExecute() {
            alertDialog=new AlertDialog.Builder(context).create();
            alertDialog.setTitle("status");
        }

        @Override
        protected void onPostExecute(String result) {
            if(result.equals("succes")){
                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                finish();
            }
            else {
                alertDialog.setMessage(result);
                alertDialog.show();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

}
