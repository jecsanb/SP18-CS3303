package com.example.jecsan.phonebook;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class MyContentProvider extends ContentProvider {

    private static final String AUTHORITY = "com.example.jecsan.phonebook.MyContentProvider";
    private static final String DATABASE_NAME = "contactDB.db";
    private static final String TABLE_CONTACT = "contacts";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRST_NAME = "firstName";
    public static final String COLUMN_LAST_NAME =  "lastName";
    public static final String COLUMN_PHONE = "phoneNumber";
    private static final int DATABASE_VERSION = 1;
    public static final int CONTACTS = 1;
    public static final int CONTACTS_ID = 2;
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_CONTACT);
    private SQLiteDatabase sqlDB;
    private UriMatcher uriMatcher;

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = uriMatcher.match(uri);
        int rowsDeleted = 0;
        Log.i(TAG, "delete: table" + selection);
        switch (uriType){
            case CONTACTS: rowsDeleted = sqlDB.delete(TABLE_CONTACT,selection, selectionArgs);
                break;
            default: throw  new  UnsupportedOperationException("Unkonow URI: " + uri +
                    " is not supported");
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriTtpe = uriMatcher.match(uri);
        long id = 0;
        switch (uriTtpe){
            case CONTACTS: sqlDB.insert(TABLE_CONTACT,null, values);
                break;
            default: throw  new  UnsupportedOperationException("Unkonow URI: " + uri +
                    " is not supported");
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return uri.parse(TABLE_CONTACT + "/" + id);
    }

    @Override
    public boolean onCreate() {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,TABLE_CONTACT,CONTACTS);
        uriMatcher.addURI(AUTHORITY, TABLE_CONTACT + "/#", CONTACTS_ID);
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        sqlDB = dbHelper.getWritableDatabase();
        if (sqlDB != null)
            return true;
        else
            return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE_CONTACT);
        int uriType = uriMatcher.match(uri);
        switch (uriType){
            case CONTACTS: break;
            default:throw  new  UnsupportedOperationException("Unknown URI: " + uri +
                    " is not supported");
        }
        Cursor cursor = queryBuilder.query(sqlDB,projection,selection,selectionArgs,
                null,null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return  cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int rowsUpdated = 0;
        int uriTtpe = uriMatcher.match(uri);
        switch (uriTtpe){
            case CONTACTS: rowsUpdated =
                    sqlDB.update(TABLE_CONTACT, values,selection,selectionArgs);
                break;
            default: throw  new  UnsupportedOperationException("Unkonow URI: " + uri +
                    " is not supported");
        }

        return rowsUpdated;
    }
    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context c){
            super(c, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            String create_product_table = "CREATE TABLE " + TABLE_CONTACT +"(" +
                    COLUMN_ID +" INTEGER PRIMARY KEY," + COLUMN_FIRST_NAME + " TEXT," + COLUMN_LAST_NAME + " TEXT," +
                    COLUMN_PHONE + " INTEGER )";
            db.execSQL(create_product_table);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.d("MyDBHandler", "Updating db from version " + oldVersion + " to " + newVersion);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);
            onCreate(db);
        }

    }
}
