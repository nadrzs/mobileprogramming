package com.example.lockinapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.lockinapp.Model.AnimItem;

import org.json.JSONException;

import java.io.IOException;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "animalist.db";
    private static final String TABLE_ANIME = "ANIME_TABLE";
    private static final String ANIME_RANK = "rank";
    private static final String ANIME_TITLE = "title";
    private static final String ANIME_TYPE = "type";
    private static final String ANIME_EPISODES = "episodes";
    private static final String ANIME_START_DATE = "start_date";
    private static final String ANIME_END_DATE = "end_date";
    private static final String ANIME_SCORE = "score";
    private static final String ANIME_MEMBER = "members";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_ANIME_TABLE = "CREATE TABLE " + TABLE_ANIME + " (" + ANIME_RANK + " INTEGER PRIMARY KEY, " + ANIME_TITLE + " TEXT, "
                + ANIME_TYPE + " TEXT, " + ANIME_EPISODES + " INTEGER, " + ANIME_START_DATE + " TEXT, " + ANIME_END_DATE + " TEXT, "
                + ANIME_SCORE + " REAL, " + ANIME_MEMBER + " INTEGER)";

        db.execSQL(CREATE_ANIME_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(AnimItem animItem){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ANIME_RANK, animItem.getRank());
        cv.put(ANIME_TITLE, animItem.getTitle());
        cv.put(ANIME_TYPE, animItem.getType());
        cv.put(ANIME_EPISODES, animItem.getEpisodes());
        cv.put(ANIME_START_DATE, animItem.getStart_date());
        cv.put(ANIME_END_DATE, animItem.getEnd_date());
        cv.put(ANIME_SCORE, animItem.getScore());
        cv.put(ANIME_MEMBER, animItem.getMembers());

        long insert = db.insert(TABLE_ANIME, null, cv);

        if(insert == -1){
            return false;
        }else{
            return true;
        }

    }

    public Cursor getListContent(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_ANIME, null);
        return data;
    }

}
