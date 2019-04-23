package com.ravindra.etiffin;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.special.ResideMenu.ResideMenu;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private SliderLayout sliderShow;
    private ResideMenu residemenu;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        sliderShow = (SliderLayout) v.findViewById(R.id.slider);
        new Background().execute();
        ResideMenu.OnMenuListener menulistner = new ResideMenu.OnMenuListener() {
            @Override
            public void openMenu() {
                sliderShow.stopAutoCycle();
            }

            @Override
            public void closeMenu() {
                sliderShow.startAutoCycle();
            }
        };
         /*
    A method to open thali system activity
     */
        TextView thaliSystem = (TextView) v.findViewById(R.id.tv_thali_system);
        thaliSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), ThaliSystemActivity.class));
            }
        });
        return v;
    }


    public ResideMenu getResidemenu() {
        return this.residemenu;
    }

    public void setResidemenu(ResideMenu residemenu) {
        this.residemenu = residemenu;
    }

    /*
            this i a class use for get images from server.
            here we use async task.

     */
    private class Background extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL("http://freefoodathome.esy.es/thepakshala/get_offer_image_url.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line = "";
                String result = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = null;

                    object = jsonArray.getJSONObject(i);
                    String imurl = object.getString("image");
                    String des = object.getString("des");

                    TextSliderView textSliderView = new TextSliderView(getActivity());
                    textSliderView
                            .description(des)
                            .image(imurl);

                    sliderShow.addSlider(textSliderView);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}
