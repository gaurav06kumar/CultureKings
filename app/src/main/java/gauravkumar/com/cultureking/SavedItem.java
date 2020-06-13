package gauravkumar.com.cultureking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import adapters.AlertAdapter;
import adapters.CustomlistAdapter;
import adapters.ItemsAdapter;
import adapters.SavedItemArrayclass;
import adapters.WebApiAdapter;

public class SavedItem extends AppCompatActivity {

    protected MainApplication mainApplication;

    ImageView backButton;
    ListView listView;
    ProgressBar progressBar;
    ArrayList<SavedItemArrayclass> savedItems;

    CustomlistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_item);

        mainApplication = (MainApplication)this.getApplicationContext();
        mainApplication.setCurrentActivity(this);

        progressBar = (ProgressBar) findViewById(R.id.saved_items_load_bar);
        progressBar.setVisibility(View.VISIBLE);

        listView = (ListView) findViewById(R.id.saved_listview);
        listView.setVisibility(View.GONE);

        backButton = (ImageView) findViewById(R.id.back_button_saved_menu);
        backButton.setEnabled(false);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SavedItem.this,HomePage.class);
                startActivity(intent);
                finish();
            }
        });

        WebApiAdapter.getObject(SavedItem.this).fireServerApi(11,null);

    }

    public void onResponse(String response)
    {
        if(response.contains("error"))
        {
            AlertAdapter.getObject(SavedItem.this).createMessageAlert("No Items in Cart");
            backButton.setEnabled(true);
            progressBar.setVisibility(View.GONE);
        }
        else {
            ItemsAdapter.getObject(SavedItem.this).saveCartItems(response);
            savedItems = ItemsAdapter.getObject(SavedItem.this).getSavedItemsList();
            adapter = new CustomlistAdapter(SavedItem.this,savedItems);
            listView.setAdapter(adapter);
            listView.setVisibility(View.VISIBLE);

//*  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                            @Override
//                                            public void onItemClick (AdapterView< ? > adapter, View view, int position, long arg){
//                                                // TODO Auto-generated method stub
//
//                                                Intent intent = new Intent(SavedItem.this,Cart.class);
//                                                startActivity(intent);
//                                                finish();
//                                            }
//                                        }
//            );
// */


            progressBar.setVisibility(View.GONE);
            backButton.setEnabled(true);
        }
    }
}
