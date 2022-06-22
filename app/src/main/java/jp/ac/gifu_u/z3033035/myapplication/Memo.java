package jp.ac.gifu_u.z3033035.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Memo extends AppCompatActivity {
    private String currentDate;
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Intent intent = getIntent();
        currentDate=intent.getStringExtra("date");
    }
    public void onBackButtonClick(View view){
        finish();
    }
}
