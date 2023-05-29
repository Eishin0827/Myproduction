package jp.ac.gifu_u.z3033035.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class Memo extends AppCompatActivity {
    private String currentDate;
    static int _Id;
    static String x="";
    float Y;
    float T;
    private Handler mHandler = new Handler();
    private Runnable updateText;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);//activity_contetntの画面を表示
        Intent intent = getIntent();
        currentDate = intent.getStringExtra("date");
        _Id = Integer.valueOf(currentDate);
        EditText bt =(EditText)findViewById(R.id.bitename);
        EditText sl =(EditText)findViewById(R.id.salaly);
        TextView textView = findViewById(R.id.gettext);
        TextView aaa = findViewById(R.id.textView);
        textView.setText(currentDate);
        EditText sh=(EditText) findViewById(R.id.starthour);//ここから
        sh.setFilters(new InputFilter[]{new InputFilterMinMax("0","23")});//数値の入力制限
        EditText sm=(EditText) findViewById(R.id.startmin);
        sm.setFilters(new InputFilter[]{new InputFilterMinMax("0","59")});
        EditText eh=(EditText) findViewById(R.id.endhour);
        eh.setFilters(new InputFilter[]{new InputFilterMinMax("0","23")});
        EditText em=(EditText) findViewById(R.id.endmin);
        em.setFilters(new InputFilter[]{new InputFilterMinMax("0","59")});//ここまで
        Button button = findViewById(R.id.backbutton);
        Button cbutton=findViewById(R.id.cbutton);
        cbutton.setOnClickListener(v-> {
            finish();
        });

        button.setOnClickListener(v->{
            float Eh= Integer.valueOf(eh.getText().toString());
            float Em= Integer.valueOf(em.getText().toString());
            float Sh= Integer.valueOf(sh.getText().toString());
            float Sm= Integer.valueOf(sm.getText().toString());
            int EH= Integer.valueOf(eh.getText().toString());
            int EM= Integer.valueOf(em.getText().toString());
            int SH= Integer.valueOf(sh.getText().toString());
            int SM= Integer.valueOf(sm.getText().toString());
            if(eh.length()!=0&&em.length()!=0&&sh.length()!=0&&sm.length()!=0&&bt.length()!=0&&sl.length()!=0&&(EH>SH||(EH==SH&&EM>SM))){
                x="";

                String Bt= bt.getText().toString();
                float Sl= Integer.valueOf(sl.getText().toString());
                if(Em>=Sh){ T=(Eh-Sh)+(Em-Sm)/60; }//勤務時間計算
                else{ T=(Eh-Sh-1)+(60-(Sm-Em))/60; }
                Y=Sl*T;//時給計算
                if(Em>=Sh){ T=(Eh-Sh)+(Em-Sm)/60; }
                else { T = (Eh - Sh - 1) + (60 - (Sm - Em)) / 60; };
                aaa.setText("勤務時間は"+T+"時間"+" 給料は"+Y+"円");
                int y = (int)Y;
                x=" "+SH+":"+SM+"~"+EH+":"+ EM+" "+Bt;



                updateText = new Runnable(){
                    public void run(){
                        mHandler.removeCallbacks(updateText);
                        mHandler.postDelayed(updateText,2000);
                        finish();
                    }
                };
                mHandler.postDelayed(updateText,2000);
            }
            else{
                Toast t = Toast.makeText(Memo.this,"入力されていない箇所があるか、勤務開始時刻が勤務終了時刻より遅いです",Toast.LENGTH_LONG);
                t.show();
            }
        });
    }

}