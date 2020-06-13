package gauravkumar.com.cultureking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import adapters.AlertAdapter;
import adapters.ImageLoader;
import adapters.ItemArrayClass;
import adapters.SharedPreps;
import adapters.WebApiAdapter;

public class ItemDetail extends AppCompatActivity {


    MainApplication mainApplication;
    ImageView itemImage,backButton;
    TextView price,itemName,addToBagButton,colorr,sizes,description;
    ItemArrayClass selectedItem;
    ProgressBar addCartLoadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        mainApplication = (MainApplication)this.getApplicationContext();
        mainApplication.setCurrentActivity(this);

        selectedItem = mainApplication.getChoosenItem();

        description = (TextView) findViewById(R.id.item_description);
        description.setMovementMethod(new ScrollingMovementMethod());
        description.setText(selectedItem.description);

        price = (TextView) findViewById(R.id.price_textview);
        price.setText("NZD $"+selectedItem.price);
        colorr = (TextView) findViewById(R.id.color_textview);
        colorr.setText(selectedItem.colors.toUpperCase());
        sizes = (TextView) findViewById(R.id.size_textview);
        sizes.setText(selectedItem.sizes.toUpperCase());
        addCartLoadingBar = (ProgressBar) findViewById(R.id.addToCart_loading_view);
        itemImage = (ImageView) findViewById(R.id.item_image_detail);
        ImageLoader.getObject(getApplicationContext()).LoadImageFromUrl(itemImage,selectedItem.imageName+".png");
        backButton = (ImageView) findViewById(R.id.back_button_detail);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ItemDetail.this,Items.class);
                startActivity(i);
                finish();
            }
        });

        itemName = (TextView) findViewById(R.id.item_name_detail);
        itemName.setText(selectedItem.itemName.toUpperCase());

        addToBagButton = (TextView) findViewById(R.id.add_Button_textview);
        addToBagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addToBagButton.setVisibility(View.GONE);
                addCartLoadingBar.setVisibility(View.VISIBLE);
                backButton.setEnabled(false);

                Bundle bundle = new Bundle();
                bundle.putString("itemID",mainApplication.getChoosenItem().itemID);
                bundle.putString("UID", SharedPreps.getStaticObject(ItemDetail.this).getUID());
                WebApiAdapter.getObject(ItemDetail.this).fireServerApi(7,bundle);


            }
        });


    }

    public void onResponseReceive(String response) {
        if(response.contains("error"))
        {
            AlertAdapter.getObject(ItemDetail.this).createMessageAlert(response);
            backButton.setEnabled(true);
            addCartLoadingBar.setVisibility(View.GONE);
            addToBagButton.setVisibility(View.VISIBLE);
        }
        else {

            Intent i = new Intent(ItemDetail.this,Cart.class);
            startActivity(i);
            finish();
        }
    }
}
