package com.sqb.implicit_intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mWebsiteEditText, mLocationEditText, mShareEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteEditText = findViewById(R.id.website_edittext);
        mLocationEditText = findViewById(R.id.location_edittext);
        mShareEditText = findViewById(R.id.share_edittext);

    }

    public void openWebsite(View view) {
        //to get the url text
        String url= mWebsiteEditText.getText().toString();
        //parse the uri and create intent
        Uri webpage = Uri.parse(url);
        Intent intent= new Intent(Intent.ACTION_VIEW,webpage);

        //finding an activity to hand the intent and start the activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents","Can't handle this!");
        }
    }

    public void openLocation(View view) {
        String loc = mLocationEditText.getText().toString();
        Uri location = Uri.parse("geo:0,0?q="+loc);
        Intent intent = new Intent(Intent.ACTION_VIEW,location);

        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else{
            Log.d("ImplicitIntents","Can't handle this!");
        }
    }

    public void shareText(View view) {
        String share = mShareEditText.getText().toString();
        String mimetype = "text/plain";
        ShareCompat.IntentBuilder.from(this).setType(mimetype).setChooserTitle(R.string.share_text_with).setText(share).startChooser();
    }
}