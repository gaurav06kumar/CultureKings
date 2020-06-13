package adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SharedPreps {

    Context context;
    static SharedPreps sharedPreps;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SharedPreps(Context context)
    {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("props",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static synchronized SharedPreps getStaticObject(Context context)
    {
        if(sharedPreps==null)
        {
            sharedPreps = new SharedPreps(context);
        }
        return sharedPreps;
    }


    public void setLoginStatus(boolean value)
    {
        editor.putBoolean("LoginStatus",value);
        editor.apply();
    }
    public boolean getLoginStatus()
    {
        return sharedPreferences.getBoolean("LoginStatus",false);
    }
    public void setConnectivity(boolean value)
    {
        editor.putBoolean("netStatus",value);
        editor.apply();
    }
    public boolean getConnectivity()
    {
        return sharedPreferences.getBoolean("netStatus",true);
    }

    public void setUserLoginStatus(boolean status)
    {
        editor.putBoolean("status",status);
        editor.apply();
    }
    public boolean getUserLoginStatus()
    {
        return  sharedPreferences.getBoolean("status",false);
    }
    public void setUserFName(String fname)
    {
        editor.putString("fname",fname);
        editor.apply();
    }
    public String getUserFName()
    {
        return sharedPreferences.getString("fname","Gaurav");
    }
    public void setUserLName(String lname)
    {
        editor.putString("lname",lname);
        editor.apply();
    }
    public String getUserLName()
    {
        return sharedPreferences.getString("lname","kumar");
    }
    public void setUserEmail(String email)
    {
        editor.putString("email",email);
        editor.apply();
    }
    public String getUserEmail()
    {
        return sharedPreferences.getString("email","abc@gmail.com");
    }
    public void setPassword(String pass)
    {
        editor.putString("pass",pass);
        editor.apply();
    }
    public String getPassword()
    {
        return sharedPreferences.getString("pass","12345678");
    }

    public void setAddress(String address)
    {
        editor.putString("address",address);
        editor.apply();
    }
    public String getAddress()
    {
        return  sharedPreferences.getString("address","abcde new zealand");
    }

    public void setSavedItemCount(int count)
    {
        editor.putInt("count",count);
        editor.apply();
    }
    public int getSavedItemCount()
    {
        return sharedPreferences.getInt("count",0);
    }
    public void signout()
    {
        editor.clear();
        editor.apply();
    }
    public void saveItem(int itemCount,String itemName,String price,String size,String color)
    {
        editor.putString("itemName"+itemCount,itemName);
        editor.apply();
        editor.putString("itemPrice"+itemCount,price);
        editor.apply();
        editor.putString("itemSize"+itemCount,size);
        editor.apply();
        editor.putString("itemColor"+itemCount,color);
        editor.apply();
    }
    public Bundle getSavedItem(int itemCount)
    {
        Bundle b = new Bundle();
        b.putString("itemName",sharedPreferences.getString("itemName"+itemCount,"abc"));
        b.putString("itemPrice",sharedPreferences.getString("itemPrice"+itemCount,"$0"));
        b.putString("itemSize",sharedPreferences.getString("itemSize"+itemCount,"S"));
        b.putString("itemColor",sharedPreferences.getString("itemColor"+itemCount,"black"));

        return b;
    }

    public void setCountry(String country) {
        editor.putString("country",country);
        editor.apply();
    }
    public String getCountry()
    {
        return sharedPreferences.getString("country","vatican city");
    }

    public void setUID(String UID) {
        editor.putString("UID",UID);
        editor.apply();
    }
    public String getUID()
    {
        return sharedPreferences.getString("UID","1");
    }

    public String getUserInitials()
    {
        String initials = "";

        initials = sharedPreferences.getString("fname","G").charAt(0)+" "+sharedPreferences.getString("lname","K").charAt(0);
        return initials;
    }
}
