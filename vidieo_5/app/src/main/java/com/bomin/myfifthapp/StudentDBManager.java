package com.bomin.myfifthapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//얘는 직접적으로 데이터베이스를 다루고
public class StudentDBManager extends SQLiteOpenHelper {
    static final String STUDENT_DB = "Students.db"; //데이터베이스 이름
    static final String STUDENT_TABLE = "Students"; //데이터베이스 안의 표 이름
    Context context = null;
    private static StudentDBManager dbManager = null;

    //데이터베이스의 표 생성 명령어, 빈 테이블 만드는 명령어
    //STUDENT_TABLE이라는 이름을 가진 테이블을 create하고 STUDENT_TABLE 뒤에 있는게 테이블에 들어 가는 항목들
    static final String CREATE_DB = " CREATE TABLE "+STUDENT_TABLE+"(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            +" student_id TEXT NOT NULL, name TEXT NOT NULL, phone_number TEXT);";

    public static StudentDBManager getInstance(Context context) {
        if(dbManager == null) {
            dbManager = new StudentDBManager(context, STUDENT_DB, null, 1);
        }
        return dbManager;
    }

    public StudentDBManager(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, version);
        this.context = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
    }

    public long insert(ContentValues addValue) {
        return getWritableDatabase().insert(STUDENT_TABLE, null, addValue);
    }
    public Cursor query(String [] columns, String selection, String[] selectionArgs, String groupBy,
                        String having, String orderBy){
        return getReadableDatabase().query(STUDENT_TABLE, columns, selection, selectionArgs,
                groupBy, having, orderBy);
    }
    public int delete(String whereClause, String[] whereArgs) {
        return getWritableDatabase().delete(STUDENT_TABLE, whereClause,
                whereArgs);
    }
}
