package com.example.syedinkisarahmed.sqllitesample;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by Syed Inkisar Ahmed on 9/22/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String db_Name="Mydb";
    public static final String table_Name="MyTable";

    public DataBaseHelper(Context context) {
        super(context, db_Name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+table_Name+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, FATHERNAME TEXT, MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+table_Name);
        onCreate(db);
    }
    public boolean insertData(String name,String fname, int marks){
        
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cont = new ContentValues();
        cont.put("NAME",name);
        cont.put("FATHERNAME",fname);
        cont.put("MARKS",marks);
        long result=db.insert(table_Name,null,cont);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor readData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+table_Name,null);
        return res;
    }
}
