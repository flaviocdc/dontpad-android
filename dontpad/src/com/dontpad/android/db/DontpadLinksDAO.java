package com.dontpad.android.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DontpadLinksDAO {

	
	public static Cursor getAllLinks(Context ctx) {
		DontpadLinksOpenHelper helper = new DontpadLinksOpenHelper(ctx);
		SQLiteDatabase db = helper.getReadableDatabase();
		
		return db.query(DontpadLinksOpenHelper.DONTPAD_LINKS_TABLE_NAME, null, null, null, null, null, null);
	}
	
	public static void insertLink(Context ctx, String link) {
		DontpadLinksOpenHelper helper = new DontpadLinksOpenHelper(ctx);
		SQLiteDatabase db = helper.getReadableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("link", link);
		
		db.insert(DontpadLinksOpenHelper.DONTPAD_LINKS_TABLE_NAME, null, values);
	}
	
}
