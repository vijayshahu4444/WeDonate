package com.app.wedonate2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;

public class Session {

    SharedPreferences usersSession;
    SharedPreferences.Editor editor;
    Context context;

    private static final String Is_login ="IsLoggedIn";
    public static final String KEY_CITY = "AURANGABAD";
    public static final String KEY_BLOOD = "A +";
    public static final String KEY_DONATE = "YES";
    public static final String KEY_NAME = "null";
    public static final String KEY_lname = "";
    public static final String KEY_Phone = "";


    public Session(Context _context){
        context = _context;
        usersSession = _context.getSharedPreferences("userLoginSession", Context.MODE_PRIVATE);
        editor  = usersSession.edit();

    }

    public  void createLoginSession(String city, String blood, String donate,String fname, String lname,String phone){
        editor.putBoolean(Is_login,true);

        editor.putString(KEY_CITY,city);
        editor.putString(KEY_BLOOD,blood);
        editor.putString(KEY_DONATE,donate);
        editor.putString(KEY_NAME,fname);
        editor.putString(KEY_lname,lname);
        editor.putString(KEY_Phone,phone);

        editor.commit();
    }
    public HashMap<String, String> getUserDetailFromSesion(){
        HashMap<String, String> userData = new HashMap<String, String>();

        userData.put(KEY_CITY,usersSession.getString(KEY_CITY, null));
        userData.put(KEY_BLOOD,usersSession.getString(KEY_BLOOD, null));
        userData.put(KEY_DONATE,usersSession.getString(KEY_DONATE,null));
        userData.put(KEY_NAME,usersSession.getString(KEY_NAME,null));
        userData.put(KEY_lname,usersSession.getString(KEY_lname,null));
        userData.put(KEY_Phone,usersSession.getString(KEY_Phone,null));
        return userData;
    }
    private static final String TAG="SessionManager";
    public boolean checkLogin(){
        if (usersSession.getBoolean(Is_login,true)){
            return true;
        }else
            return false;

    }

    public void logoutUserFromSession(){
        editor.putBoolean(Is_login,false);
        editor.clear();
        editor.commit();
    }

}
