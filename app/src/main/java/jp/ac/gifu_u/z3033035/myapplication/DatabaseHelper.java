package jp.ac.gifu_u.z3033035.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="memo.db";
    private static final  int DATABASE_VERSION=1;
    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE memo(");
        sb.append("_id INTEGER PRIMARY KEY,");
        sb.append("date TEXT,");
        sb.append("sh TEXT,");
        sb.append("sm TEXT,");
        sb.append("eh TEXT,");
        sb.append("em TEXT,");
        sb.append("bt TEXT,");
        sb.append("sl TEXT,");
        sb.append(");");
        String sql = sb.toString();
        db.execSQL(sql);
    }


}
