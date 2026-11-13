package com.example.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //references for all text fields
    public EditText textField_website = findViewById(R.id.editText_website);
    public EditText textField_location = findViewById(R.id.editText_location);
    public EditText textField_share = findViewById(R.id.editText_share);
    public EditText textField_phone = findViewById(R.id.editText_phone);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void openWebsite(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(String.valueOf(textField_website.getText()));
        String scheme = (uri.getScheme());
        intent.setData(uri);

        if(scheme.equalsIgnoreCase("http") || scheme.equalsIgnoreCase("https")) {
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, getString(R.string.error_invalid_website), Toast.LENGTH_SHORT).show();
        }
    }

    public void openLocation(View view) {

    }
    public void shareText(View view) {

    }
    public void callNumber(View view) {

    }
}