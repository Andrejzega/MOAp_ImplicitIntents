package com.example.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText textField_website;
    private EditText textField_location;
    private EditText textField_share;
    private EditText textField_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            int marginSide = (int) (24 * getResources().getDisplayMetrics().density);
            int marginTop = (int) (24 * getResources().getDisplayMetrics().density);
            int marginBottom = (int) (24 * getResources().getDisplayMetrics().density);
            v.setPadding(systemBars.left + marginSide, systemBars.top + marginTop, systemBars.right + marginSide, systemBars.bottom + marginBottom);
            return insets;
        });

        textField_website = findViewById(R.id.editText_website);
        textField_location = findViewById(R.id.editText_location);
        textField_share = findViewById(R.id.editText_share);
        textField_phone = findViewById(R.id.editText_phone);
    }

    public void openWebsite(View view) {
        String url = textField_website.getText().toString().trim();
        if (!url.isEmpty() && !url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            try {
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, getString(R.string.error_invalidwebsite), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void openLocation(View view) {
        String loc = textField_location.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            try {
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, getString(R.string.error_invalidlocation), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void shareText(View view) {
        String txt = textField_share.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.button_share)
                .setText(txt)
                .startChooser();
    }

    public void callNumber(View view) {
        String phone = textField_phone.getText().toString().trim();
        Uri uri = Uri.parse("tel:" + phone);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            try {
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, getString(R.string.error_invalidphone), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void fillPlaceholders(View view) {
        textField_website.setText(R.string.edittext_uri);
        textField_location.setText(R.string.edittext_loc);
        textField_share.setText(R.string.edittext_share);
        textField_phone.setText(R.string.edittext_phone);
    }
}
