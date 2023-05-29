package jp.ac.gifu_u.z3033035.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private TextView titleText;
    private Button prevButton, nextButton;
    private CalendarAdapter mCalendarAdapter;
    private GridView calendarGridView;
    public static final String EH="";
    public static final String EM="";
    public static final String SH="";
    public static final String SM="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleText = findViewById(R.id.titleText);
        prevButton = findViewById(R.id.prevButton);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarAdapter.prevMonth();
                titleText.setText(mCalendarAdapter.getTitle());
            }
        });
        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarAdapter.nextMonth();
                titleText.setText(mCalendarAdapter.getTitle());
            }
        });
        calendarGridView = findViewById(R.id.calendarGridView);
        mCalendarAdapter = new CalendarAdapter(this);
        calendarGridView.setAdapter(mCalendarAdapter);
        titleText.setText(mCalendarAdapter.getTitle());
        calendarGridView.setOnItemClickListener(this);
    }
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMd", Locale.US);
        Intent intent = new Intent(getApplicationContext(), Memo.class);//Memo.javaに遷移
        intent.putExtra("date", dateFormat.format(mCalendarAdapter.getItem(position)).toString());//カレンダーの画面で押した日付も引き渡す
        intent.putExtra("id",mCalendarAdapter.getItem(position).toString());
        startActivity(intent);//遷移開始
    }
}