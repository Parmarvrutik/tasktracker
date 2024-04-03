package com.example.tasktracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Date;

public class db extends SQLiteOpenHelper {
    private static final String DB_Name = "TaskDB";
    private static final String Table_Name = "TaskMaster";
    private static final int DB_Version = 1;
    private static final String Track_ID = "id";
    private static final String Activity_Desc = "description";
    private static final String Status = "status";
    private static final String Dead_Line = "deadLine";



  public db (Context context){
      super(context,DB_Name,null,DB_Version);
  }

    @Override
    public void onCreate(SQLiteDatabase sql) {
        String query = "CREATE TABLE " + Table_Name + "(" +
                Track_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Activity_Desc + " TEXT, " +
                Status + " TEXT, " +
                Dead_Line + " TEXT)";
       sql.execSQL(query);

    }
    public void addTask(String TaskDesc,String Stat, String DeadLine){
      SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Activity_Desc,TaskDesc);
        cv.put(Status,Stat);
        cv.put(Dead_Line,DeadLine);
        db.insert(Table_Name,null,cv);
        db.close();
    }
    public void updateTask (String TaskDesc,String Stat, String DeadLine){}

    @Override
    public void onUpgrade(SQLiteDatabase sql, int i, int i1) {
    sql.execSQL ("DROP TABLE IF EXISTS " + Table_Name);
    onCreate(sql);
    }
}
