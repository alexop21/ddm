package com.example.aplicao;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    String nomes[] = new String[] {"Joao","Anna","Hudson","Xibilú"};

    @SuppressLint("WrongViewCast")
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
        lv= findViewById(R.id.listView);
        ArrayAdapter<String> adapter =new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                nomes
        );
        lv.setAdapter(adapter);
        lv.setOnItemClickListener((parent, view, position, id)->{
            Toast.makeText(this,nomes[position],Toast.LENGTH_LONG).show();
            Intent i= new Intent(getApplicationContext(),ActivityExibeDados.class);
            i.putExtra("user",nomes[position]);
            startActivity(i);
        });

    }
}