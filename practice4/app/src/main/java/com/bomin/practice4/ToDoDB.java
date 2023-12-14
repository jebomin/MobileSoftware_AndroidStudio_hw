package com.bomin.practice4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ToDoDB extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    private Context context;
    public static final String DATABASE_NAME = "todoList.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "todo_list";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TASK = "task";
    public static final String COLUMN_STATUS = "status";

    public ToDoDB(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TASK + " TEXT, " +
                COLUMN_STATUS + " INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void openDatabase(){

        db = this.getWritableDatabase();
    }

    //할일 전체 가져오기
    public ArrayList<ToDoModel> getAllTasks(){

        ArrayList<ToDoModel> taskList = new ArrayList<>();

        Cursor cursor  = null;

        String query = "SELECT * FROM " + TABLE_NAME;

        db = this.getReadableDatabase();

        if(db != null){

            cursor = db.rawQuery(query, null);

            while(cursor.moveToNext()){

                ToDoModel task = new ToDoModel();

                task.setId(cursor.getInt(0));
                task.setTask(cursor.getString(1));
                task.setStatus(cursor.getInt(2));
                taskList.add(task);
            }
        }

        return taskList;
    }


    /**
     * 할일 추가
     * @param task 할일
     */
    public void addTask(ToDoModel task){

        openDatabase();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TASK, task.getTask());
        cv.put(COLUMN_STATUS, 0);

        db.insert(TABLE_NAME, null, cv);

    }

    /**
     * 할일 상태 수정
     * @param id 아이디
     * @param status 상태
     */
    public void updateStatus(int id, int status){

        openDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_STATUS, status);

        db.update(TABLE_NAME, cv, "id=?", new String[]{String.valueOf(id)});

    }

    /**
     * 할일 수정
     * @param id 아이디
     * @param task 할일
     */
    public void updateTask(int id, String task){

        openDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TASK, task);
        db.update(TABLE_NAME, cv, "id=?", new String[]{String.valueOf(id)});

    }

    /**
     * 할일 삭제
     * @param id 아이디
     */
    public void deleteTask(int id){

        openDatabase();
        db.delete(TABLE_NAME, "id=?" , new String[]{String.valueOf(id)});
    }
}