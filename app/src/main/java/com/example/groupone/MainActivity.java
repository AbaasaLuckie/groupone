package com.example.groupone;

import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    class_db myDb;
    EditText editname, editreg_no, editdetails, editmarks;
    Button btnAddData,btnViewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDb = new class_db(this);
        editname = (EditText)findViewById(R.id.name_editText);
        editreg_no = (EditText)findViewById(R.id.regno_editText2);
        editdetails = (EditText)findViewById(R.id.details_editText3);
        editmarks = (EditText)findViewById(R.id.marks_editText4);
        btnAddData = (Button) findViewById(R.id.send_button);
        btnViewAll = (Button) findViewById(R.id.button3);
        AddData();
        viewAllmethods();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    public void AddData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editname.getText().toString(),
                                editreg_no.getText().toString(),
                                editdetails.getText().toString(),
                                editmarks.getText().toString()
                        );
                        if(isInserted = true){
                            Toast.makeText(MainActivity.this,"Data Inserted", Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(MainActivity.this,"Data not  Inserted", Toast.LENGTH_LONG).show();

                        }


                    }
                }
        );
    }
    public void viewAllmethods(){
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      Cursor res =  myDb.getAlldata();
                      if(res.getCount() == 0 ){
                          //show some message
                          showMessage("Error", "Nothing found");
                          return;
                      }else {
                          StringBuffer buffer = new StringBuffer();
                          while (res.moveToNext()){
                              buffer.append("ID:"+ res.getString(0)+"\n");
                              buffer.append("NAME:"+ res.getString(1)+"\n");
                              buffer.append("REG_NO:"+ res.getString(2)+"\n");
                              buffer.append("DETAILS:"+ res.getString(3)+"\n");
                              buffer.append("MARKS:"+ res.getString(4)+"\n\n");
                          }
                          showMessage("Data", buffer.toString());

//                          show all methods
                      }

                    }
                }
        );
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
