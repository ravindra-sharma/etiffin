package com.ravindra.etiffin;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Cod_PlYr on 1/12/2017.
 */
public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "thepakshala";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String REGISTER_NUMBER="registernumber";
    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void setRegisterNumber(String number)
    {
        editor.putString(REGISTER_NUMBER,number);
        editor.commit();
    }
    public String getRegisterNumber()
    {
        return pref.getString(REGISTER_NUMBER,"");
    }
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}
