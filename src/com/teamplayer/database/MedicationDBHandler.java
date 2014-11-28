package com.teamplayer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MedicationDBHandler extends DBHandler {
	
	public static final String TABLE_MEDICATION = "medication";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_MEDNAME = "MEDNAME";
	public static final String COLUMN_MEDINSTRUCTION = "MEDINSTRUCTION";
	public static final String COLUMN_REMAINDER = "REMAINDER";
	
	//Create
	private String CREATE_MEDICATION_TABLE = "CREATE TABLE " +
			TABLE_MEDICATION + "("
             + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_MEDNAME 
             + " TEXT," + COLUMN_MEDINSTRUCTION + " TEXT" + COLUMN_REMAINDER+" INTEGER"+")";
	
	
	public MedicationDBHandler(Context context, String name, CursorFactory factory,
			int version) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		this.queryString = CREATE_MEDICATION_TABLE;
		db.execSQL(queryString);
	}
	
	
	public void addMedication(Medication medication) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_MEDNAME, medication.getMedName());
        values.put(COLUMN_MEDINSTRUCTION, medication.getMedInstrction());
        values.put(COLUMN_MEDINSTRUCTION, medication.getRemainder());
 
        SQLiteDatabase db = this.getWritableDatabase();
        
        db.insert(TABLE_MEDICATION, null, values);
        db.close();
	}

}
