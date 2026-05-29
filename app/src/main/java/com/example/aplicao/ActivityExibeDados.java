package com.example.aplicao;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityExibeDados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_exibe_dados);

        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        String nome= b.getString("user");
        ((TextView)findViewById(R.id.textViewExibeNome)).setText(nome);
    }
}