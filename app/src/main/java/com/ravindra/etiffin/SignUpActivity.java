package com.ravindra.etiffin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

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
import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    EditText et_fullname,et_additionaladdressinfo,et_email,et_password_nu;
    RadioGroup rg_gender;
    RadioButton rb_gender_btn;
    Spinner city,area;
    CheckBox cb_tnc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setDisplayShowCustomEnabled(true);


        et_fullname= (EditText) findViewById(R.id.et_fullname);
        et_additionaladdressinfo= (EditText) findViewById(R.id.et_additionaladdressinfo);
        et_email= (EditText) findViewById(R.id.et_email);
        et_password_nu=(EditText)findViewById(R.id.et_password_nu);
        rg_gender=(RadioGroup)findViewById(R.id.rg_gender);
        city=(Spinner)findViewById(R.id.spinner_city);
        area=(Spinner)findViewById(R.id.spinner_area);
        cb_tnc=(CheckBox)findViewById(R.id.cb_tnc);

        //set city spinner
        ArrayList<String> list=new ArrayList<>();
        list.add("jaipur");
        list.add("jodhpur");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        city.setAdapter(adapter);
        //set area spinner
        ArrayList<String> arealist=new ArrayList<>();
        arealist.add("Pratap nagar");
        arealist.add("sitapura");
        arealist.add("lalkothi");
        arealist.add("gopalpura");
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arealist);
        area.setAdapter(adapter1);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void submitnewuserinfo(View v) {
        String fullname=et_fullname.getText().toString();
        int gen_id=rg_gender.getCheckedRadioButtonId();
        rb_gender_btn= (RadioButton) findViewById(gen_id);
        String gender=rb_gender_btn.getText().toString();
        String address=et_additionaladdressinfo.getText().toString();
        String cityname=city.getSelectedItem().toString();
        String areaname=area.getSelectedItem().toString();
        String email=et_email.getText().toString();
        String password=et_password_nu.getText().toString();
        String mobilenumber=new PrefManager(getBaseContext()).getRegisterNumber();
        BackgroundTaskForSignUpActivity backgroundTask=new BackgroundTaskForSignUpActivity(this);
        backgroundTask.execute(fullname,gender,address,areaname,cityname,mobilenumber,password,email);

    }

    /*
       A class to work in background
       async class
       use to communicate server
        */
    private class BackgroundTaskForSignUpActivity extends AsyncTask<String, Void, String> {
        Context context;
        String flag;
        String mobilenumber, password;
        AlertDialog alertDialog;

        public BackgroundTaskForSignUpActivity(Context ctx) {
            context = ctx;

        }

        @Override
        protected String doInBackground(String... params) {
            String signupurl = "http://freefoodathome.esy.es/thepakshala/signup.php";
            try {
                URL url = new URL(signupurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postdata = URLEncoder.encode("fullname", "UTF-8") + "=" + URLEncoder.encode(params[0], "UTF-8") + "&"
                        + URLEncoder.encode("gender", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8") + "&" +
                        URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8") + "&" +
                        URLEncoder.encode("area", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8") + "&" +
                        URLEncoder.encode("city", "UTF-8") + "=" + URLEncoder.encode(params[4], "UTF-8") + "&" +
                        URLEncoder.encode("mobilenumber", "UTF-8") + "=" + URLEncoder.encode(params[5], "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(params[6], "UTF-8")+ "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(params[7], "UTF-8");
                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line = "";
                String result = "";
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
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("status");
        }

        @Override
        protected void onPostExecute(String result) {
            if(result.equals("succes"))
            {
                Toast.makeText(getBaseContext(),"You are registered succesfully!!!",Toast.LENGTH_LONG).show();
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
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
