package org.techtown.numbertouch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static java.util.Collections.sort;

public class RankActivity extends AppCompatActivity {
    Button button;
    SQLiteDatabase database;
    public static final String DBNAME = "ranking.db";
    public static final String TBNAME = "rank";
    RankAdapter adapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        button = findViewById(R.id.button_back);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RankActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();

        RecyclerView recyclerView = findViewById(R.id.rankRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // DB 실행, 순위 기록 불러오기
        executeDB();
        getRecords();

        recyclerView.setAdapter(adapter);
    }

    public void executeDB(){
        database = openOrCreateDatabase(DBNAME, MODE_PRIVATE, null);

        database.execSQL("create table if not exists " + TBNAME + " ("
                                                                    + "time text, "
                                                                    + "type text, "
                                                                    + "date text)");
    }

    private void getRecords(){
        String sql = "select time, type, date from " + TBNAME + " order by time asc";
        Cursor cursor = database.rawQuery(sql, null);
        adapter = new RankAdapter();

        int recordCount = cursor.getCount();

        for(int i = 0; i < recordCount; i++){
            cursor.moveToNext();
            String time = cursor.getString(0);
            String type = cursor.getString(1);
            String date = cursor.getString(2);
            adapter.addItem(new Rank(i+1, time, type, date));
        }
        cursor.close();
    }
}

