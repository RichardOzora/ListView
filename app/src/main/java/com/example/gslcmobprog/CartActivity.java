package com.example.gslcmobprog;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    DatabaseHelper db;
    ListView cartListView;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<Integer> image = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);

        db = new DatabaseHelper(this);
        cartListView = findViewById(R.id.cartListView);

        Button btnBack = findViewById(R.id.backBtn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btnClear = findViewById(R.id.clearBtn);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.clearCart();
                Toast.makeText(CartActivity.this, "Cart has been cleared!", Toast.LENGTH_SHORT).show();
                reloadData();
            }
        });

        reloadData();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void reloadData() {
        name.clear();
        image.clear();

        Cursor cursor = db.getAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(CartActivity.this, "Cart is Empty right now!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        while (cursor.moveToNext()) {
            name.add(cursor.getString(1));
            image.add(cursor.getInt(3));
        }

        ContentAdapter adapter = new ContentAdapter(CartActivity.this, name.toArray(new String[0]), image.stream().mapToInt(i -> i).toArray());
        cartListView.setAdapter(adapter);
    }
}