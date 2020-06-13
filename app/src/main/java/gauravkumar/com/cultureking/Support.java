package gauravkumar.com.cultureking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Support extends AppCompatActivity {
    ImageView backButton;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        backButton = (ImageView) findViewById(R.id.back_button_profile);

        text=(TextView)findViewById(R.id.t);

        text.setText("AUSTRALIA \n" +
                "1300 CKINGS(1300 254 647) \n" +
                "Monday to Friday 7am - 9am AEST \n" +
                "Saturday to Sunday 9am - 5am AEST\n \n" +
                "\n NEW ZEALAND\n" +
                "0508 CKINGS(0508254647)\n" +
                "Monday to Friday 7am - 9am AEST \n" +
                "Saturday to Sunday 9am - 5am AEST \n\n" +
                "INTERNATIONAL \n" +
                "+61733450511\n" +
                "Monday to Friday 7am - 9am AEST"

        );





        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Support.this,HomePage.class);
                startActivity(i);
                finish();
            }
        });
    }
}