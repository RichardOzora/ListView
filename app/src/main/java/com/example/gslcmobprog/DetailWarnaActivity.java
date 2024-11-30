package com.example.gslcmobprog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailWarnaActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_warna);

        db = new DatabaseHelper(this);

        ImageView imageDataDetail = findViewById(R.id.imageDetail);
        TextView namaDataDetail = findViewById(R.id.namaDetail);

        Intent intent = getIntent();
        String namaWarna = intent.getStringExtra("namaWarna");
        int imageWarna = intent.getIntExtra("imageWarna", 0);

        imageDataDetail.setImageResource(imageWarna);
        namaDataDetail.setText(namaWarna);

        Button btnBack = findViewById(R.id.backBtn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btnCart = findViewById(R.id.cartBtn);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isAdded = db.insertCartItem(namaWarna, imageWarna);
                if (isAdded) {
                    Toast.makeText(DetailWarnaActivity.this, "Added to Cart!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DetailWarnaActivity.this, "Failed to Add!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}