package gauravkumar.com.cultureking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapters.AlertAdapter;
import adapters.OrderArrayClass;
import adapters.OrderListAdapter;
import adapters.SavedItemArrayclass;
import adapters.SharedPreps;
import adapters.WebApiAdapter;

public class Orders extends AppCompatActivity {

    protected  MainApplication mainApplication;

    ListView orderListView;
    ImageView backButton;
    ProgressBar progressBar;
    List<OrderArrayClass> orderList;
    OrderArrayClass order;
    OrderListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        orderList = new ArrayList<>();
        mainApplication = (MainApplication)this.getApplicationContext();
        mainApplication.setCurrentActivity(this);



        progressBar = (ProgressBar) findViewById(R.id.order_loadingBar);

        backButton = (ImageView) findViewById(R.id.back_button_order_layout);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Orders.this,Profile.class);
                startActivity(i);
                finish();
            }
        });


        orderListView = (ListView) findViewById(R.id.order_listview);

        Bundle bundle = new Bundle();
        bundle.putString("UID", SharedPreps.getStaticObject(Orders.this).getUID());
        WebApiAdapter.getObject(Orders.this).fireServerApi(12,bundle);
    }


    public void onResponseMethode(String response)
    {

    try {


        JSONArray jsonArray = new JSONArray(response);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            order = new OrderArrayClass();
            order.orderNumber =  jsonObject.getString("orderNumber");
            order.oid = jsonObject.getString("oid");
            order.totalPrice = jsonObject.getString("TotalPrice");
            orderList.add(order);
        }

        adapter = new OrderListAdapter(Orders.this,orderList);
        orderListView.setAdapter(adapter);
        orderListView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

    }
        catch(Exception e)
        {
           // AlertAdapter.getObject(Orders.this).createMessageAlert("try again!! "+e.getMessage());
            Toast.makeText(getApplicationContext(), ""+e.getMessage(), Toast.LENGTH_LONG).show();
        }
        }
    }

