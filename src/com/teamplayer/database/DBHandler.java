package com.teamplayer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

	protected static final int DATABASE_VERSION = 1;
	protected static final String DATABASE_NAME = "teamplayerDB.db";
	public static final String TABLE_MEDICATION = "medication";

	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_MEDNAME = "MEDNAME";
	public static final String COLUMN_MEDINSTRUCTION = "MEDINSTRUCTION";
	public static final String COLUMN_REMAINDER = "REMAINDER";
	
	public String queryString = "";

	public DBHandler(Context context, String name, CursorFactory factory,
			int version) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		  db.execSQL(queryString);
	}
	
	public void setQueryString(String qery) {
		
	      this.queryString=qery;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICATION);
	      onCreate(db);

	}

}
