package com.example.aplicao;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicao.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ListView listView;

        listView=findViewById(R.id.listView);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sqLiteDatabase=openOrCreateDatabase("banco.db",MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS notas(id INTEGER PRIMARY KEY AUTOINCREMENT,titulo TEXT, conteudo TEXT)");
        //sqLiteDatabase.execSQL("INSERT INTO notas (id,titulo,conteudo) VALUES (1,'Nota 1', 'Conteudo da nota 1')");
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",1);
        contentValues.put("titulo","Nota 1");
        contentValues.put("conteudo","Conteudo da nota 1");
        sqLiteDatabase.insert("notas",null,contentValues);

        //RECUPERAR DADOS DO BANCO
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM notas",null);
        cursor.moveToFirst();

        ArrayList<String> lista = new ArrayList<>();
        while(!cursor.isAfterLast()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String titulo= cursor.getString(cursor.getColumnIndex(("titulo"));
            String conteudo= cursor.getString(cursor.getColumnIndex("conteudo"));
            lista.add("id: "+Integer.toString(id)+" titulo: "+titulo);
            cursor.moveToNext();
            Log.d("SELECT notas","id: "+id+" titulo: "+titulo+" conteudo : "+conteudo);
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,lista);
        listView.setAdapter(adapter);
    }
}