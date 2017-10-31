package com.example.user.dbexample;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.dbexample.database.University;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Database myDb;
    EditText name, mark;
    Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            //myDb = new Database(this);

            name = (EditText) findViewById(R.id.editTextName);
            mark = (EditText) findViewById(R.id.editTextMark);
            buttonAdd = (Button) findViewById(R.id.buttonAdd);
            //InsertData();

            University uni = new University();
            uni.name = "DH TN";
            uni.address = "TP HMC";
            uni.save(); //insert

            University university = Select.from(University.class)
                    .where(Condition.prop("NAME").eq("DH CNTT")).first(); //select
            if (university != null)
            {
                university.address = "BINH DUONG";
                university.save(); //update

                university.delete(University.class); //
            }
            //List<University> list = Select.from(University.class).where(Condition.prop("NAME").eq(1)).list();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void InsertData()
    {
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //boolean result = myDb.InsertDatabase(name.getText().toString(), mark.getText().toString());
                boolean result = myDb.InsertSinhVien(name.getText().toString(), mark.getText().toString());
                if (result == true)
                {
                    Toast.makeText(MainActivity.this, "OK!!!!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "NO!!!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        int color = ContextCompat.getColor(this, R.color.colorAccent);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        try
        {
            switch (id)
            {
                case R.id.idDatabase:
                    Intent intent = new Intent(this, AndroidDatabaseManager.class);
                    startActivity(intent);
                    break;

                default:
                    return super.onOptionsItemSelected(item);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return super.onOptionsItemSelected(item);
    }
}
