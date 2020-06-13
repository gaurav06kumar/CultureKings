package gauravkumar.com.cultureking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import adapters.AlertAdapter;
import adapters.CustomlistAdapter;
import adapters.ItemArrayClass;
import adapters.ItemsAdapter;
import adapters.SavedItemArrayclass;
import adapters.WebApiAdapter;

public class Cart extends AppCompatActivity {

    MainApplication mainApplication;
    TextView buyButton;
    ImageView backButton;
    ListView savedItemsList;
    CustomlistAdapter adapter;
    ArrayList<ItemArrayClass> sItemList;
    ArrayList<SavedItemArrayclass> savedItems;
    ProgressBar cartLoadingBar;
    int itemCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mainApplication = (MainApplication) this.getApplicationContext();
        mainApplication.setCurrentActivity(this);


        buyButton = (TextView) findViewById(R.id.buy_Button_textview);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Cart.this,Checkout.class);
                startActivity(i);
                finish();
            }
        });
        buyButton.setEnabled(false);
        backButton = (ImageView) findViewById(R.id.back_button_cart);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Cart.this,ItemDetail.class);
                startActivity(i);
                finish();
            }
        });
        backButton.setEnabled(false);

        cartLoadingBar = (ProgressBar)findViewById(R.id.cart_loading_bar);
        WebApiAdapter.getObject(Cart.this).fireServerApi(6,null);

        savedItemsList = (ListView) findViewById(R.id.saved_items_list);

    }



    public void onResponseReceive(String response) {
        if(response.contains("error"))
        {
            AlertAdapter.getObject(Cart.this).createMessageAlert(response);
            backButton.setEnabled(true);
        }
        else {
                ItemsAdapter.getObject(Cart.this).saveCartItems(response);
                savedItems = ItemsAdapter.getObject(Cart.this).getSavedItemsList();
                adapter = new CustomlistAdapter(Cart.this,savedItems);
                savedItemsList.setAdapter(adapter);
                cartLoadingBar.setVisibility(View.GONE);
                savedItemsList.setVisibility(View.VISIBLE);



                buyButton.setEnabled(true);
                backButton.setEnabled(true);
        }
    }

    public void refreshCart(String response,String sid)
    {
        if(response.contains("error"))
        {
            AlertAdapter.getObject(Cart.this).createMessageAlert(response);
            adapter.notifyDataSetChanged();
        }
        else{
           ItemsAdapter.getObject(Cart.this).deleteSavedItem(sid);
            //savedItems = ItemsAdapter.getObject(Cart.this).getSavedItemsList();
           // adapter.notifyDataSetChanged();
        }
    }

}
