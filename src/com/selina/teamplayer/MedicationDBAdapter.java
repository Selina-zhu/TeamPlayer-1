package com.selina.teamplayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MedicationDBAdapter {

	static final String DATABASE_NAME = "team_Player.db";
	static final int DATABASE_VERSION = 1;
	public static final int NAME_COLUMN = 1;
	
	

	static final String MEDICATION_CREATE = "create table "
			+ "MEDICATION"
			+ "( "
			+ "ID"
			+ " integer primary key autoincrement,"
			+ "MEDNAME  text,MEDINSTUCTION text,REMAINDER text,STARTDATE text,ALERTTIME text); ";

	public SQLiteDatabase db;
	// Context of the application using the database.
	private final Context context;
	// Database open/upgrade helper
	private DataBaseHelper dbHelper;

	public MedicationDBAdapter(Context _context) {
		System.out.println(" I am here ---------- ");
		context = _context;
		dbHelper = new DataBaseHelper(context, DATABASE_NAME, null,
			DATABASE_VERSION);
				
	}

	public MedicationDBAdapter open() throws SQLException {
		db = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		db.close();
	}

	public SQLiteDatabase getDatabaseInstance() {
		return db;
	}

	/*
	 private int Id;
	private String medName;
	private String medInstrction;
	private int remainder;//sqllite wont support boolean 1- true, 0- false
	private String startDate;
	private String alertTime; 
	 
	 */
	public void insertEntry(String medName, String medInstrction, String remainder,
			String startDate, String alertTime) {
		ContentValues newValues = new ContentValues();
		// Assign values for each row.
		newValues.put("MEDNAME", medName);
		newValues.put("MEDINSTUCTION", medInstrction);
		newValues.put("REMAINDER", remainder);
		newValues.put("STARTDATE", startDate);
		newValues.put("ALERTTIME", alertTime);

		// Insert the row into your table
		db.insert("MEDICATION", null, newValues);
		// /Toast.makeText(context, "Reminder Is Successfully Saved",
		// Toast.LENGTH_LONG).show();
	}

	public int deleteEntry(String medName) {
		// String id=String.valueOf(ID);
		String where = "MEDNAME=?";
		int numberOFEntriesDeleted = db.delete("MEDICATION", where,
				new String[] { medName });
		// Toast.makeText(context,
		// "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted,
		// Toast.LENGTH_LONG).show();
		return numberOFEntriesDeleted;
	}

	public String getSinlgeEntry(String medName) {
		Cursor cursor = db.query("MEDICATION", null, " MEDNAME=?",
				new String[] { medName }, null, null, null);
		if (cursor.getCount() < 1) // UserName Not Exist
		{
			cursor.close();
			return "NOT EXIST";
		}
		cursor.moveToFirst();
		String password = cursor.getString(cursor.getColumnIndex("MEDINSTUCTION"));
		cursor.close();
		return password;
	}

	public void updateEntry(String medName, String medInstrction, String remainder,
			String startDate, String alertTime) {
		// Define the updated row content.
		ContentValues updatedValues = new ContentValues();
		// Assign values for each row.

		updatedValues.put("MEDNAME", medName);
		updatedValues.put("MEDINSTUCTION", medInstrction);
		updatedValues.put("REMAINDER", remainder);
		updatedValues.put("STARTDATE", startDate);
		updatedValues.put("ALERTTIME", alertTime);

		String where = "MEDNAME = ?";
		db.update("MEDICATION", updatedValues, where, new String[] { medName });
	}

}
