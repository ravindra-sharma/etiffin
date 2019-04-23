package com.ravindra.etiffin;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{


     private ResideMenu resideMenu;
    private ResideMenuItem homeItem;
    private ResideMenuItem shareItem;
    private ResideMenuItem offersItem;
    private ResideMenuItem aboutusItem;
    private ResideMenuItem accountItem;
    private ResideMenuItem orderhistoryItem;
    private ResideMenuItem feedbackItem;
    private ResideMenuItem logoutItem;
    Context context;
    private SliderLayout sliderShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context=this;

        if(savedInstanceState==null)
        {
            HomeFragment fragment=new HomeFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainfragementholder,fragment,"fragement")
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
            fragment.setResidemenu(resideMenu);
        }
        sliderShow = (SliderLayout) findViewById(R.id.slider);

//        RequestQueue requestQueue= Volley.newRequestQueue(this);
//        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, "http://192.168.42.96/thepakshala/get_offer_image_url.php", null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray jsonArray) {
//                for (int i=0;i<jsonArray.length();i++)
//                {
//                    JSONObject object=null;
//                    try {
//                        object=jsonArray.getJSONObject(i);
//                        String s=object.getString("image");
//                        TextSliderView textSliderView = new TextSliderView(getBaseContext());
//                        textSliderView
//                                .description("Game of Thrones")
//                                .image(s);
//
//                        sliderShow.addSlider(textSliderView);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//
//            }
//        });
//        requestQueue.add(jsonArrayRequest);
        //new Background().execute();
        setupmenu();



    }




    private void setupmenu() {
        //attach menu to current activity
        resideMenu=new ResideMenu(this);

        resideMenu.setBackground(R.drawable.menubg);
        resideMenu.attachToActivity(this);

        resideMenu.setMenuListener(menulistner);

        //set width of menu
        resideMenu.setScaleValue(.7f);

        //set ignoredview



        homeItem=new ResideMenuItem(this,R.drawable.ic_home,"Home");
        shareItem=new ResideMenuItem(this,R.drawable.ic_share,"Share");
        offersItem=new ResideMenuItem(this,R.drawable.ic_offer,"Offers");
        aboutusItem=new ResideMenuItem(this,R.drawable.ic_about,"About Us");
        accountItem=new ResideMenuItem(this,R.drawable.ic_balance,"Account Info");
        orderhistoryItem=new ResideMenuItem(this,R.drawable.ic_orderhis,"Order History");
        feedbackItem=new ResideMenuItem(this,R.drawable.ic_feedback,"Feedback");
        logoutItem=new ResideMenuItem(this,R.drawable.ic_logout,"Logout");

        //add on click listener to all of them

        homeItem.setOnClickListener(this);
        shareItem.setOnClickListener(this);
        offersItem.setOnClickListener(this);
        aboutusItem.setOnClickListener(this);
        accountItem.setOnClickListener(this);
        orderhistoryItem.setOnClickListener(this);
        feedbackItem.setOnClickListener(this);
        logoutItem.setOnClickListener(this);

        //add each item to reside menu

        resideMenu.addMenuItem(homeItem,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(shareItem,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(offersItem,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(aboutusItem,ResideMenu.DIRECTION_LEFT);

        resideMenu.addMenuItem(accountItem,ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(orderhistoryItem,ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(feedbackItem,ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(logoutItem,ResideMenu.DIRECTION_RIGHT);


        //residemenu set open and close on button press

        findViewById(R.id.leftmenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        findViewById(R.id.rightmenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            }
        });
    }

    private ResideMenu.OnMenuListener menulistner=new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
//            sliderShow.stopAutoCycle();
        }

        @Override
        public void closeMenu() {
  //          sliderShow.startAutoCycle();
        }
    };
    @Override
    public void onClick(View v) {
        if(v==homeItem)
        {
//            startActivity(new Intent(HomeActivity.this,HomeActivity.class));
//            finish();
            HomeFragment fragment=new HomeFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainfragementholder,fragment,"fragement")
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
            fragment.setResidemenu(resideMenu);
        }
        else if(v==shareItem)
        {
            chagefragement(new ShareFragment());
        }
        else if(v==offersItem){
            chagefragement(new OffersFragment());
        }
        else if(v==aboutusItem){
            chagefragement(new AboutusFragment());
        }
        else if(v==accountItem){
            chagefragement(new AccountFragment());
        }
        else if(v==orderhistoryItem){
            chagefragement(new OrderHistoryFragment());
        }
        else if(v==feedbackItem){
            chagefragement(new FeedbackFragment());
        }
        else if(v==logoutItem){
            chagefragement(new LogoutFragment());
        }
        resideMenu.closeMenu();

    }

    private void chagefragement(Fragment targetFragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainfragementholder,targetFragment,"fragement")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }



    }




