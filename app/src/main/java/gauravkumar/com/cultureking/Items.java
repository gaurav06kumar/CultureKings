package gauravkumar.com.cultureking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import adapters.ImageLoader;
import adapters.ItemArrayClass;
import adapters.ItemsAdapter;

public class Items extends AppCompatActivity {

    String gender = "",itemSelected = "";
    TextView heading;
    ImageView item1,item2,item3,item4,backButton;
    String[] url={"","","",""};
    ItemArrayClass itemArrayClass;
    ArrayList<ItemArrayClass> list;

    protected  MainApplication mainApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        mainApplication = (MainApplication)this.getApplicationContext();
        mainApplication.setCurrentActivity(this);

        gender = mainApplication.selectedGenderIs();
        itemSelected = mainApplication.selectedItemIs();

        list = new ArrayList<>();

        list = ItemsAdapter.getObject(getApplicationContext()).getItems(gender,itemSelected);

        for(int i = 0;i<list.size();i++)
        {
            itemArrayClass = list.get(i);
            url[i] = itemArrayClass.imageName+".png";
        }

        heading = (TextView) findViewById(R.id.item_heading_textview);
        heading.setText(itemSelected);

        backButton = (ImageView) findViewById(R.id.back_button_items);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Items.this,Collection.class);
                startActivity(i);
                finish();
            }
        });


        item1  = (ImageView) findViewById(R.id.item1_imageview);
        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainApplication.setChoosenItem(list.get(0));
                Intent i = new Intent(Items.this,ItemDetail.class);
                startActivity(i);
                finish();
            }
        });

         item2  = (ImageView) findViewById(R.id.item2_imageview);
        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainApplication.setChoosenItem(list.get(1));
                Intent i = new Intent(Items.this,ItemDetail.class);
                startActivity(i);
                finish();
            }
        });
  item3  = (ImageView) findViewById(R.id.item3_imageview);
        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainApplication.setChoosenItem(list.get(2));
                Intent i = new Intent(Items.this,ItemDetail.class);
                startActivity(i);
                finish();
            }
        });

        item4  = (ImageView) findViewById(R.id.item4_imageview);
        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainApplication.setChoosenItem(list.get(3));
                Intent i = new Intent(Items.this,ItemDetail.class);
                startActivity(i);
                finish();
            }
        });


        try
        {
            ImageLoader.getObject(getApplicationContext()).LoadImageFromUrl(item1,url[0]);
            ImageLoader.getObject(getApplicationContext()).LoadImageFromUrl(item2,url[1]);
            ImageLoader.getObject(getApplicationContext()).LoadImageFromUrl(item3,url[2]);
            ImageLoader.getObject(getApplicationContext()).LoadImageFromUrl(item4,url[3]);
        }catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),""+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
