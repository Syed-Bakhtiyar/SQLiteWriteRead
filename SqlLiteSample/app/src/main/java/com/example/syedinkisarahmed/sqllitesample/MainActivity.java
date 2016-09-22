package com.example.syedinkisarahmed.sqllitesample;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper MyDb;
    EditText name,fname,marks;
    Button btn,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDb = new DataBaseHelper(this);
        name= (EditText) findViewById(R.id.name);
        fname =(EditText) findViewById(R.id.fname);
        marks =(EditText) findViewById(R.id.marks);
        btn = (Button) findViewById(R.id.btn);
        btn2=(Button) findViewById(R.id.btn2);
        addData();
    }
    public void addData(){
        btn.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                    String names=name.getText().toString();
                        String fnames = fname.getText().toString();
                        int markss =Integer.parseInt( marks.getText().toString());
                        boolean ins= MyDb.insertData(names,fnames,markss);
                        if(ins)
                            Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "Not  Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
    public void viewAll(View v){
        Cursor res = MyDb.readData();
        if(res.getCount()==0){
           ShowMessage("Error","NothingFound");
            return;
        }
        StringBuffer stb = new StringBuffer();
        while(res.moveToNext()){
        stb.append("ID : "+ res.getString(0)+"\n");
            stb.append("NAME : "+ res.getString(1)+"\n");
            stb.append("FATHERNAME : "+ res.getString(2)+"\n");
            stb.append("MARKS : "+ res.getString(3)+"\n");
        }
        ShowMessage("Data",stb.toString());
               
    }
    public void ShowMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
}
