package com.example.will.exp6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

/**
 * Created by Will on 2018/6/24.
 */

public class DBAdapter {
        private static final String DB_NAME = "people.db";
        private static final String DB_TABLE = "peopleinfo";
	    private static final int DB_VERSION = 1;

        public static final String KEY_ID = "_id";
		public static final String KEY_NAME = "name";
		public static final String KEY_CLASS = "class";
		public static final String KEY_NUMBER = "number";

        private SQLiteDatabase db;
        private final Context context;
		private DBOpenHelper dbOpenHelper;

        private static class DBOpenHelper extends SQLiteOpenHelper {
                public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
                        super(context, name, factory, version);
                }
		        private static final String DB_CREATE = "create table " +
                        DB_TABLE + " (" + KEY_ID + " integer primary key autoincrement, " +
                        KEY_NAME+ " text not null, " + KEY_CLASS+ " text," + KEY_NUMBER + " text);";

        		@Override   //根据命令创建新表
                public void onCreate(SQLiteDatabase _db) {
        		        _db.execSQL(DB_CREATE);
                }

        		@Override   //删除旧数据库表并将数据转移到新版本数据库表中
		        public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
                        _db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
                        onCreate(_db);
                }

        }

        public DBAdapter(Context _context) {
                context = _context;
        }

        //打开（建立）数据库
        public void open() throws SQLiteException {
                dbOpenHelper = new DBOpenHelper(context, DB_NAME, null, DB_VERSION);
                try {
                        db = dbOpenHelper.getWritableDatabase();

                }catch (SQLiteException ex) {
            			db = dbOpenHelper.getReadableDatabase();
            	}
        }

        //关闭数据库
        public void close() {
                if (db != null){
                        db.close();
            			db = null;
                }
        }

        //插入一条数据
        public long insert(People people) {
                ContentValues newValues = new ContentValues();

                newValues.put(KEY_NAME, people.Name);
                newValues.put(KEY_CLASS, people.Class);
                newValues.put(KEY_NUMBER, people.Number);

                return db.insert(DB_TABLE, null, newValues);
        }

        //删除全部数据
		public long deleteAllData() {
            return db.delete(DB_TABLE, null, null);
        }

        //根据ID删除一条数据
		public long deleteOneData(long id) {
            return db.delete(DB_TABLE,  KEY_ID + "=" + id, null);
        }

        //查询全部数据
		public People[] queryAllData() {
                Cursor results = db.query(DB_TABLE, new String[] { KEY_ID, KEY_NAME, KEY_CLASS, KEY_NUMBER},
                        null, null, null, null, null);
                return ConvertToPeople(results);
        }

        //根据ID查询数据
		public People[] queryOneData(long id) {
                Cursor results =  db.query(DB_TABLE, new String[] { KEY_ID, KEY_NAME, KEY_CLASS, KEY_NUMBER},
                        KEY_ID + "=" + id, null, null, null, null);
                return ConvertToPeople(results);
        }

        //根据ID更新一条数据
		public long updateOneData(long id , People people){
                ContentValues updateValues = new ContentValues();
                updateValues.put(KEY_NAME, people.Name);
                updateValues.put(KEY_CLASS, people.Class);
                updateValues.put(KEY_NUMBER, people.Number);

                return db.update(DB_TABLE, updateValues,  KEY_ID + "=" + id, null);
        }

        @Nullable   //将查询结果转换为自定义的People类实例
        private People[] ConvertToPeople(Cursor cursor){
            int resultCounts = cursor.getCount();
            if (resultCounts == 0 || !cursor.moveToFirst()){
                return null;
            }
            People[] peoples = new People[resultCounts];
            for (int i = 0 ; i<resultCounts; i++){
                peoples[i] = new People();
                peoples[i].ID = cursor.getInt(0);
                peoples[i].Name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                peoples[i].Class = cursor.getString(cursor.getColumnIndex(KEY_CLASS));
                peoples[i].Number = cursor.getString(cursor.getColumnIndex(KEY_NUMBER));
                cursor.moveToNext();
            }
            return peoples;

        }

}