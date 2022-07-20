package jp.ac.gifu_u.z3033035.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Memo extends AppCompatActivity {
    private String currentDate;
    private DatabaseHelper _helper;
    private String _memoId;
    private String currentDateint;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);//activity_contetntの画面を表示
        Intent intent = getIntent();
        currentDate = intent.getStringExtra("date");
        EditText bt =(EditText)findViewById(R.id.bitename);
        EditText sl =(EditText)findViewById(R.id.salaly);
        _helper = new DatabaseHelper(Memo.this);
        TextView textView = findViewById(R.id.gettext);
        textView.setText(currentDate);
        EditText sh=(EditText) findViewById(R.id.starthour);
        sh.setFilters(new InputFilter[]{new InputFilterMinMax("0","23")});
        EditText sm=(EditText) findViewById(R.id.startmin);
        sm.setFilters(new InputFilter[]{new InputFilterMinMax("0","59")});
        EditText eh=(EditText) findViewById(R.id.endhour);
        eh.setFilters(new InputFilter[]{new InputFilterMinMax("0","23")});
        EditText em=(EditText) findViewById(R.id.endmin);
        em.setFilters(new InputFilter[]{new InputFilterMinMax("0","59")});
        Button button = findViewById(R.id.backbutton);
        Button cbutton=findViewById(R.id.cbutton);
        cbutton.setOnClickListener(v->{
            if(eh.length()!=0&&em.length()!=0&&sh.length()!=0&&sm.length()!=0&&bt.length()!=0&&sl.length()!=0){
                _memoId = currentDate;
                String Eh= eh.getText().toString();//入力したデータを取得
                String Em= em.getText().toString();
                String Sh= sh.getText().toString();
                String Sm= sm.getText().toString();
                String Bt= bt.getText().toString();
                String Sl= sl.getText().toString();
                SQLiteDatabase db =_helper.getWritableDatabase();//データベースヘルパーオブジェクトからデータベース接続オブジェクトを取得
                String sqlDelete="DELETE FROM memo WHERE _id =?";//削除用SQL文字列の準備
                SQLiteStatement stmt = db.compileStatement(sqlDelete);//SQL文字列を元にプリペアドステートメント取得
                stmt.bindString(1,_memoId);//変数のバインド
                stmt.executeUpdateDelete();//削除SQL実行
                String sqlInsert ="INSERT INTO memo(_id,date,sh,sm,eh,em,bt,sl)VALUES(?,?,?,?,?,?,?,?)";
                stmt.bindString(1,_memoId);
                stmt.bindString(2, currentDate);
                stmt.bindString(3,Sh);
                stmt.bindString(4,Sm);
                stmt.bindString(5,Eh);
                stmt.bindString(6,Em);
                stmt.bindString(7,Bt);
                stmt.bindString(8,Sl);
                stmt.executeInsert();
                sh.setText("");
                sm.setText("");
                em.setText("");
                eh.setText("");
                bt.setText("");
                sl.setText("");
                finish();
            }
            else{
                Toast t = Toast.makeText(Memo.this,"全ての空欄を埋めてください",Toast.LENGTH_LONG);
                t.show();
            }
        });
    }
    protected void onDestroy(){
        _helper.close();
        super.onDestroy();
    }

}