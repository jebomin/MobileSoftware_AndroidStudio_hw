package com.bomin.practice3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PhoneBookDB extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "address.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME   = "phone_info";
    public static final String COLUMN_ID    = "_id";
    public static final String COLUMN_NAME  = "phone_name";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_PHONE_PHOTO = "user_image";


    /**
     * DB 생성
     * @param context 컨텍스트
     */
    public PhoneBookDB(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    /**
     * 테이블 생성
     * @param db 데이터베이스
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PHONE_NUMBER + " TEXT, " +
                COLUMN_PHONE_PHOTO + " BLOP);";
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * 주소록 전체 가져오기
     * @return
     */
    public Cursor readAllData(){

        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }
}