package com.example.gslcmobprog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    String[] namaWarna = {"biru", "hitam", "kuning", "merah", "putih"};
    int[] imageWarna = {R.drawable.biru, R.drawable.hitam, R.drawable.kuning, R.drawable.merah, R.drawable.putih};
    ListView listView;

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

        listView =findViewById(R.id.listViewData);
        ContentAdapter contentAdapter = new ContentAdapter(getApplicationContext(), namaWarna, imageWarna);
        listView.setAdapter(contentAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Warna: " + namaWarna[i], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, DetailWarnaActivity.class);
                intent.putExtra("namaWarna", namaWarna[i]);
                intent.putExtra("imageWarna", imageWarna[i]);
                startActivity(intent);
            }
        });

        Button viewCartBtn = findViewById(R.id.viewCartBtn);
        viewCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }
}