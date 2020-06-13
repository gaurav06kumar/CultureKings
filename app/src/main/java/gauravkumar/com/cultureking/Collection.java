package gauravkumar.com.cultureking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import adapters.ImageLoader;

public class Collection extends AppCompatActivity {

    MainApplication mainApplication;

    String gender="Men";
    TextView heading,shirtsButton,jeansButton,shoesButton,accessoriesButton;
    ImageView backButton,searchButton,pageImage;
    EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        mainApplication = (MainApplication)this.getApplicationContext();
        mainApplication.setCurrentActivity(this);

        gender = mainApplication.selectedGenderIs();

        heading = (TextView) findViewById(R.id.collection_heading_textview);
        heading.setText(gender+" Collections");
        shirtsButton = (TextView) findViewById(R.id.shirts_button_tv);
        shoesButton = (TextView) findViewById(R.id.shoes_button_tv);
        jeansButton = (TextView) findViewById(R.id.jeans_button_tv);
        accessoriesButton = (TextView) findViewById(R.id.accessories_tv);

        backButton = (ImageView) findViewById(R.id.back_button_imageview);
        searchButton = (ImageView) findViewById(R.id.search_collection);
        pageImage = (ImageView) findViewById(R.id.collection_page_image);
        if(gender == "Men")
        {
            ImageLoader.getObject(getApplicationContext()).LoadImageFromUrl(pageImage,"men_clothings.png");

        }
        else
        {
            ImageLoader.getObject(getApplicationContext()).LoadImageFromUrl(pageImage,"women_clothing.png");

        }

        searchBar = (EditText) findViewById(R.id.search_edittext_collection);


        shirtsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainApplication.setSelctedItem("Shirts");
                Intent i = new Intent(Collection.this,Items.class);
                startActivity(i);
                finish();
            }
        });
        shoesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainApplication.setSelctedItem("Shoes");
                Intent i = new Intent(Collection.this,Items.class);
                startActivity(i);
                finish();
            }
        });
        jeansButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainApplication.setSelctedItem("Jeans");
                Intent i = new Intent(Collection.this,Items.class);
                startActivity(i);
                finish();
            }
        });
        accessoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainApplication.setSelctedItem("Accessories");
                Intent i = new Intent(Collection.this,Items.class);
                startActivity(i);
                finish();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Collection.this,HomePage.class);
                startActivity(i);
                finish();
            }
        });
    }
}
