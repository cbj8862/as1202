package com.example.a2020_1_cp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

public class EWProvider extends ContentProvider {
    static final Uri CONTENT_URI = Uri.parse("content://com.example.myapp1/word");
    static final int ALLWORD = 1;
    static final int ONEWORD = 2;

    static final UriMatcher Matcher;
    static {
        Matcher = new UriMatcher(UriMatcher.NO_MATCH);
        Matcher.addURI("com.example.myapp1", "word", ALLWORD);
        Matcher.addURI("com.example.myapp1", "word/*", ONEWORD);
    }

    SQLiteDatabase mDB;

    public EWProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;

        //*
        switch (Matcher.match(uri)) {
            case ALLWORD:
                count = mDB.delete("dic", selection, selectionArgs);
                break;
            case ONEWORD:
                String where;
                where = "eng = '" + uri.getPathSegments().get(1) + "'";
                if (TextUtils.isEmpty(selection) == false) {
                    where += " AND" + selection;
                }
                count = mDB.delete("dic", where, selectionArgs);
                break;
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
        //*/

        /*
        String sql;

        // 전체에 대한 쿼리 명령
        sql = "DELETE FORM dic";

        // 단어 선택 where절 추가
        if (Matcher.match(uri) == ONEWORD) {
            sql += " where eng = '" + uri.getPathSegments().get(1) + "'";
        }
        mDB.execSQL(sql);
        return 1;
        //*/
    }

    @Override
    public String getType(Uri uri) {
        if (Matcher.match(uri) == ALLWORD) {
            return "vnd.myapp1.example.com.cursor.dir/word";
        }
        if (Matcher.match(uri) == ONEWORD) {
            return "vnd.myapp1.example.com.cursor.item/word";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long row = mDB.insert("dic", null, values);
        if (row > 0) {
            Uri notiuri = ContentUris.withAppendedId(CONTENT_URI, row);
            getContext().getContentResolver().notifyChange(notiuri, null);
            return notiuri;
        }
        return null;
    }

    @Override
    public boolean onCreate() {
        WordDBHelper helper = new WordDBHelper(getContext());
        mDB = helper.getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String sql;

        // 전체에 대한 쿼리 명령
        sql = "SELECT eng, han FROM dic";

        // 단어 선택 where절 추가
        if (Matcher.match(uri) == ONEWORD) {
            sql += " where eng = '" + uri.getPathSegments().get(1) + "'";
        }

        Cursor cursor = mDB.rawQuery(sql, null);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;

        switch (Matcher.match(uri)) {
            case ALLWORD:
                count = mDB.update("dic", values, selection, selectionArgs);
                break;
            case ONEWORD:
                String where;
                where = "eng = '" + uri.getPathSegments().get(1) + "'";
                if (TextUtils.isEmpty(selection) == false) {
                    where += " AND " + selection;
                }
                count = mDB.update("dic", values, where, selectionArgs);
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
