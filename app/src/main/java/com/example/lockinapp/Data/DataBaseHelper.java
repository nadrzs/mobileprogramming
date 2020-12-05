package com.example.lockinapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.lockinapp.Model.AnimItem;
import com.example.lockinapp.Model.MangaItem;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "animalist.db";

    //Anime
    private static final String TABLE_ANIME = "ANIME_TABLE";
    private static final String ANIME_RANK = "rank";
    private static final String ANIME_TITLE = "title";
    private static final String ANIME_TYPE = "type";
    private static final String ANIME_EPISODES = "episodes";
    private static final String ANIME_START_DATE = "start_date";
    private static final String ANIME_END_DATE = "end_date";
    private static final String ANIME_SCORE = "score";
    private static final String ANIME_MEMBER = "members";

    //Manga
    private static final String TABLE_MANGA = "MANGA_TABLE";
    private static final String MANGA_RANK = "rank";
    private static final String MANGA_TITLE = "title";
    private static final String MANGA_TYPE = "type";
    private static final String MANGA_VOLUME = "manga_volumes";
    private static final String MANGA_START_DATE = "manga_start_date";
    private static final String MANGA_END_DATE = "manga_end_date";
    private static final String MANGA_SCORE = "score";
    private static final String MANGA_MEMBER = "member";




    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_ANIME_TABLE = "CREATE TABLE " + TABLE_ANIME + " (" + ANIME_RANK + " INTEGER PRIMARY KEY, " + ANIME_TITLE + " TEXT, "
                + ANIME_TYPE + " TEXT, " + ANIME_EPISODES + " INTEGER, " + ANIME_START_DATE + " TEXT, " + ANIME_END_DATE + " TEXT, "
                + ANIME_SCORE + " REAL, " + ANIME_MEMBER + " INTEGER)";

        String CREATE_MANGA_TABLE = "CREATE TABLE " + TABLE_MANGA + " (" + MANGA_RANK + " INTEGER PRIMARY KEY, " + MANGA_TITLE + " TEXT, "
                + MANGA_TYPE + " TEXT, " + MANGA_VOLUME + " INTEGER, " + MANGA_START_DATE + " TEXT, " + MANGA_END_DATE + " TEXT, "
                + MANGA_SCORE + " REAL, " + MANGA_MEMBER + " INTEGER)";

        db.execSQL(CREATE_ANIME_TABLE);
        db.execSQL(CREATE_MANGA_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addRecord(AnimItem animItem){

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


        db.insert(TABLE_ANIME, null, cv);
        db.close();

    }

    public void addMangaRecord(MangaItem mangaItem){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(MANGA_RANK, mangaItem.getManga_Rank());
        cv.put(MANGA_TITLE, mangaItem.getManga_Title());
        cv.put(MANGA_TYPE, mangaItem.getManga_Type());
        cv.put(MANGA_VOLUME, mangaItem.getManga_Volumes());
        cv.put(MANGA_START_DATE, mangaItem.getManga_Start_date());
        cv.put(MANGA_END_DATE, mangaItem.getManga_End_date());
        cv.put(MANGA_SCORE, mangaItem.getManga_Score());
        cv.put(MANGA_MEMBER, mangaItem.getManga_Members());

        db.insert(TABLE_MANGA, null, cv);
        db.insert(TABLE_MANGA, null, cv);
        db.close();

    }

    public List<AnimItem> getAllRecord(){

        List<AnimItem> animItemList = new ArrayList<AnimItem>();

        String selectQuery = "SELECT  * FROM " + TABLE_ANIME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do {
                AnimItem animItem = new AnimItem();

                animItem.setRank(Integer.parseInt(cursor.getString(0)));
                animItem.setTitle(cursor.getString(1));
                animItem.setType(cursor.getString(2));
                animItem.setEpisodes(Integer.parseInt(cursor.getString(3)));
                animItem.setStart_date(cursor.getString(4));
                animItem.setEnd_date(cursor.getString(5));
                animItem.setScore(Double.parseDouble(cursor.getString(6)));
                animItem.setMembers(Integer.parseInt(cursor.getString(7)));

                animItemList.add(animItem);
            }while (cursor.moveToNext());
        }
        return animItemList;
    }

    public List<MangaItem> getMangaAllRecord(){

        List<MangaItem> mangaItemList = new ArrayList<MangaItem>();

        String selectQuery = "SELECT  * FROM " + TABLE_MANGA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do {
                MangaItem mangaItem = new MangaItem();

                mangaItem.setManga_rank(Integer.parseInt(cursor.getString(0)));
                mangaItem.setManga_title(cursor.getString(1));
                mangaItem.setManga_type(cursor.getString(2));
                mangaItem.setManga_volumes(Integer.parseInt(cursor.getString(3)));
                mangaItem.setManga_start_date(cursor.getString(4));
                mangaItem.setManga_end_date(cursor.getString(5));
                mangaItem.setManga_score(Double.parseDouble(cursor.getString(6)));
                mangaItem.setManga_members(Integer.parseInt(cursor.getString(7)));

                mangaItemList.add(mangaItem);
            }while (cursor.moveToNext());
        }
        return mangaItemList;
    }

}
