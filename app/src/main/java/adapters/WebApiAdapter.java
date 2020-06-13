package adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gauravkumar.com.cultureking.AddressBook;
import gauravkumar.com.cultureking.Cart;
import gauravkumar.com.cultureking.Checkout;
import gauravkumar.com.cultureking.HomePage;
import gauravkumar.com.cultureking.ItemDetail;
import gauravkumar.com.cultureking.Login;
import gauravkumar.com.cultureking.MainApplication;
import gauravkumar.com.cultureking.Orders;
import gauravkumar.com.cultureking.SavedItem;
import gauravkumar.com.cultureking.SignUp;
import gauravkumar.com.cultureking.UserDetail;

public class WebApiAdapter {

    StringRequest request;
    static Map map;
    String url;
    MainApplication mainApplication;
    SignUp signUp;
    Login login;
    UserDetail userDetail;
    AddressBook addressBook;
    int requestCode =0;
    int position = 0;
    boolean ifFirst = true;

    static Context con;
    static WebApiAdapter apiAdapter;
    String sid;

    public WebApiAdapter (Context con)
    {
        this.con = con;
        mainApplication = (MainApplication)con.getApplicationContext();
    }

    public static synchronized WebApiAdapter getObject(Context context)
    {
        con = context;
        if(apiAdapter==null)
        {
            apiAdapter = new WebApiAdapter(con);
        }
        map = new HashMap<>();
        return apiAdapter;
    }

    public void fireServerApi(int requestCode,Bundle bundle)
    {
        this.requestCode = requestCode;
        switch(requestCode)
        {
            case 1 : {
                    map.put("fname",bundle.getString("fname"));
                    map.put("lname",bundle.getString("lname"));
                    map.put("password",bundle.getString("password"));
                    map.put("email",bundle.getString("email"));
                    url = "https://shoppie808.000webhostapp.com/CultureKings/RegisterUser.php";
            }
            break;
            case 2 :{
                map.put("password",bundle.getString("password"));
                map.put("email",bundle.getString("email"));
                url = "https://shoppie808.000webhostapp.com/CultureKings/SignIn.php";
            }
            break;
            case 3 :
            {
                map.put("UID",bundle.getString("UID"));
                map.put("fname",bundle.getString("fname"));
                map.put("lname",bundle.getString("lname"));
                map.put("password",bundle.getString("password"));
                map.put("email",bundle.getString("email"));
               // Toast.makeText(con, "email = "+bundle.getString("email"), Toast.LENGTH_SHORT).show();
                url = "https://shoppie808.000webhostapp.com/CultureKings/UpdateUserDetail.php";
            }
            break;
            case 4 :
            {

                map.put("UID",bundle.getString("UID"));
                map.put("address",bundle.getString("address"));
                map.put("country",bundle.getString("country"));
                //Toast.makeText(con, "email = "+bundle.getString("country"), Toast.LENGTH_SHORT).show();
                url = "https://shoppie808.000webhostapp.com/CultureKings/UpdateAddress.php";

            }break;
            case 5 :
            {
                url = "https://shoppie808.000webhostapp.com/CultureKings/GetAllItems.php";
            }
            break;
            case 6 :
            {
                map.put("UID", SharedPreps.getStaticObject(con).getUID());
                url = "https://shoppie808.000webhostapp.com/CultureKings/GetSavedItemIds.php";
            }break;
            case 7 :
            {
                map.put("UID",bundle.getString("UID"));
                map.put("itemID",bundle.getString("itemID"));
                url = "https://shoppie808.000webhostapp.com/CultureKings/saveItem.php";
            }
            break;
            case 8 :
            {
                map.put("sid",bundle.getString("sid"));
                sid = bundle.getString("sid");
                url = "https://shoppie808.000webhostapp.com/CultureKings/deletSavedItem.php";
            }break;
            case 9 :
            {
                map.put("UID",bundle.getString("UID"));
                map.put("orderNumber",bundle.getString("orderNumber"));
                map.put("totalPrice",bundle.getString("totalPrice"));
                url="https://shoppie808.000webhostapp.com/CultureKings/placeOrder.php";
            }break;
            case 10 :
            {
                position = bundle.getInt("position");
                sid = bundle.getString("sid");
                map.put("sid",bundle.getString("sid"));
                map.put("orderNumber",bundle.getString("orderNumber"));
                map.put("iid",bundle.getString("iid"));
                url = "https://shoppie808.000webhostapp.com/CultureKings/orderSavedItem.php";
            }
            break;
            case 11 :
            {
                map.put("UID", SharedPreps.getStaticObject(con).getUID());
                url = "https://shoppie808.000webhostapp.com/CultureKings/GetSavedItemIds.php";
            }break;
            case 12 :
            {
                map.put("UID",SharedPreps.getStaticObject(con).getUID());
                url = "https://shoppie808.000webhostapp.com/CultureKings/getOrders.php";
            }
            break;
        }
        performWebOperation();

    }
    public void sendBroadcastToSpecificClass(String response)
    {
     switch (requestCode)
     {
         case 1 :
         {
             signUp=(SignUp)con;
             signUp.onResponseReceive(response);
         }
         break;
         case 2 :
         {
             login = (Login)con;
             login.onResponseReceive(response);
         }
         break;
         case 3 :
         {
             userDetail = (UserDetail)con;
             userDetail.onResponseReceive(response);
         }
         break;
         case 4 :
         {
             addressBook = (AddressBook)con;
             addressBook.onResponseReceive(response);
         }
         break;
         case 5 :
         {
              HomePage homePage = (HomePage)con;
             homePage.onResponseReceive(response);
         }break;
         case 6 :
         {
             Cart cart = (Cart)con;
             cart.onResponseReceive(response);
         }
         break;
         case 7 :
         {
             ItemDetail itemDetail = (ItemDetail)con;
             itemDetail.onResponseReceive(response);
         }
         break;
         case 8 :
         {
             if(mainApplication.getCurrentActivity().getLocalClassName().equals("Cart")==true) {
                 Cart cart = (Cart) con;
                 cart.refreshCart(response, sid);
             }
             else{
                 ItemsAdapter.getObject(con).deleteSavedItem(sid);
             }
         }
         break;
         case 9 :
         {
             Checkout checkout= (Checkout)con;
             checkout.OnResponseByOrderTable(response);
         }break;
         case 10 :
         {
             if(response.contains("error"))
             {
                 Toast.makeText(con, "response "+response, Toast.LENGTH_SHORT).show();
             }
             else {
                 Checkout checkout = (Checkout) con;
                 checkout.OnItemDeleted(null, position + 1);
              //   Toast.makeText(con, "response "+response, Toast.LENGTH_SHORT).show();
             }
         }
         break;
         case 11 :
         {
             SavedItem item = (SavedItem)con;
             item.onResponse(response);
         }break;
         case 12 :
         {
             Orders orders = (Orders) con;
             orders.onResponseMethode(response);
         }
         break;
     }
    }

    public void performWebOperation()
    {
        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if(response!=null)
                {
                    mainApplication.setVolleyResponse(response);
                    sendBroadcastToSpecificClass(response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              /* if(requestCode!=5 || requestCode!=6 || requestCode!=7){ AlertAdapter.getObject(con).dismissAlert();}
              */  Log.e("\n\nerror: ",error.toString()+"\n\n\n");
                try {
                    AlertAdapter.getObject(con).createMessageAlert(error.getMessage().toUpperCase());
                    sendBroadcastToSpecificClass("error");
                }catch (Exception e)
                {
                    AlertAdapter.getObject(con).createMessageAlert("Response error ");
                    sendBroadcastToSpecificClass("error");
                }

                 }

        }
        )
        {
            @Override
            protected Map getParams() throws AuthFailureError {
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(con);
        queue.add(request);

    }

}
