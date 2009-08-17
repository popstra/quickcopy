package be.vbsteven.quickcopy;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DBHelper {
	private static DBHelper instance;
	
	private static final String DATABASE_NAME = "/data/data/be.vbsteven.quickcopy/databases/quickcopy.db";
	private static final String TABLE_NAME = "entries";
	private static final String COLUMN_NAME = "value";
	private static final String CREATE_TABLE = "create table " + TABLE_NAME + " (_id integer primary key autoincrement, " + COLUMN_NAME + " text not null);";
	
	private SQLiteDatabase db;
	
	public static DBHelper get(Context context) {
		
		if (instance == null) {
			instance = new DBHelper(context);
		} 
		return instance;
	}
	
	private DBHelper(Context context) {
        db = context.openOrCreateDatabase("quickcopy", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        try {
        	db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
        	// table already exists
        }
	}
	
	public ArrayList<String> getEntries() {
		Cursor c = db.query(TABLE_NAME, new String[] {COLUMN_NAME}, null, null, null, null, null);
        ArrayList<String> values = new ArrayList<String>();
        int columnId = c.getColumnIndex(COLUMN_NAME);
        if (c.moveToFirst()) {
        	do {
        		values.add(c.getString(columnId));
        	} while (c.moveToNext());
        }
        return values;
	}
	
	public void addEntry(String entry) {
		ContentValues cv = new ContentValues();
		cv.put("value", entry);
		db.insert(TABLE_NAME, null, cv);
	}
	
	public void updateEntry(String oldValue, String newValue) {
		String query = "UPDATE " + TABLE_NAME + " SET value = '" + newValue + "' WHERE value = '" + oldValue + "';";
		db.execSQL(query);
	}

	public void deleteEntry(String oldValue) {
		String query = "DELETE FROM " + TABLE_NAME + " WHERE value = '" + oldValue + "';";
		db.execSQL(query);
	}
}
