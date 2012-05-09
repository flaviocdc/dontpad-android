package com.dontpad.android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DontpadLinksOpenHelper extends SQLiteOpenHelper {

	public static final String DONTPAD_LINKS_TABLE_NAME = "dontpad_links";
	
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dontpad";
    private static final String DONTPAD_LINKS_TABLE_CREATE =
    		"CREATE TABLE " + DONTPAD_LINKS_TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, link TEXT);";

    DontpadLinksOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DONTPAD_LINKS_TABLE_CREATE);
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE  " + DONTPAD_LINKS_TABLE_NAME);
		onCreate(db);
	}
}