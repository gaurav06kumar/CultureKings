package adapters;

import android.content.Context;

public class Validation {

    static Context con;
    static Validation obj;

    public Validation(Context context)
    {
        con = context;
    }

    public static synchronized Validation getObject(Context context)
    {
        con = context;
        if(obj==null)
        {
            obj = new Validation(con);
        }
        return obj;
    }
    public boolean validateString(String input)
    {
        if(input.isEmpty())
        {
            return false;
        }
        else if(input.length()<3 || input.length()>15)
        {
            return false;
        }
        return true;
    }

    public boolean validateEmail(String email)
    {
        if(email.length()>12) {
            if (email.contains("@gmail.com") || email.contains("@yahoo.com") || email.contains("@hotmail.com") || email.contains("@live.com")) {
                return true;
            }
        }
        return false;
    }
}
