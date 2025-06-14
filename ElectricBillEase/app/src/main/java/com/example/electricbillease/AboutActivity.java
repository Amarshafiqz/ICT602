package com.example.electricbillease;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // GitHub Button logic
        Button githubButton = findViewById(R.id.buttonGithub);
        githubButton.setOnClickListener(v -> {
            String url = "https://github.com/Amarshafiqz";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        // Optional: You can also set profile image dynamically here if needed
        ImageView profileImage = findViewById(R.id.profileImage);
        profileImage.setContentDescription("Muammar Shafiq Bin Raslan Adli");
    }
}
