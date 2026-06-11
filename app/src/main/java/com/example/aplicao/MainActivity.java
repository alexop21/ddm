package com.example.aplicao;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
    ListView listView;
    EditText editText;
    Button button;
    ArrayAdapter<String> adapter;
    ArrayList<String> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        listView=findViewById(R.id.listView);
        button=findViewById(R.id.button);
        editText=findViewById(R.id.editTextTitle);

        button.setOnClickListener(v -> {
            String string = editText.getText().toString();
            ContentValues contentValues = new ContentValues();
            contentValues.put("id",1);
            contentValues.put("titulo","Nota 1");
            sqLiteDatabase.insert("notas",null,contentValues);
            adapter.notifyDataSetChanged();
            ListarDB();
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sqLiteDatabase=openOrCreateDatabase("banco.db",MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS notas(id INTEGER PRIMARY KEY AUTOINCREMENT,titulo TEXT, conteudo TEXT)");
        ListarDB();
    }
    public void ListarDB(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM notas",null);
        cursor.moveToFirst();
        lista = new ArrayList<>();
        while(!cursor.isAfterLast()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String titulo= cursor.getString(cursor.getColumnIndex("titulo"));
            String conteudo= cursor.getString(cursor.getColumnIndex("conteudo"));
            lista.add("id: "+Integer.toString(id)+" titulo: "+titulo);
            cursor.moveToNext();
            Log.d("SELECT notas","id: "+id+" titulo: "+titulo+" conteudo : "+conteudo);
        }
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,lista);
        listView.setAdapter(adapter);
    }
}