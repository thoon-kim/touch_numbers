package org.techtown.numbertouch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static java.util.Collections.sort;

public class RankActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        RecyclerView recyclerView = findViewById(R.id.rankRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        RankAdapter adapter = new RankAdapter();

        adapter.addItem(new Rank("1", "00:04:09", "4X4", "2019.10.15"));
        adapter.addItem(new Rank("2", "00:14:09", "4X4", "2019.10.15"));
        adapter.addItem(new Rank("3", "00:44:09", "4X4", "2019.10.15"));
        adapter.addItem(new Rank("4", "00:54:09", "4X4", "2019.10.15"));
        adapter.addItem(new Rank("5", "00:64:09", "4X4", "2019.10.15"));
        adapter.addItem(new Rank("6", "00:74:09", "4X4", "2019.10.15"));
        adapter.addItem(new Rank("7", "00:84:09", "4X4", "2019.10.15"));
        adapter.addItem(new Rank("8", "00:14:09", "4X4", "2019.10.15"));
        adapter.addItem(new Rank("9", "00:24:09", "4X4", "2019.10.15"));
        adapter.addItem(new Rank("10", "00:34:09", "4X4", "2019.10.15"));
        adapter.addItem(new Rank("11", "00:22:09", "4X4", "2019.10.15"));

        recyclerView.setAdapter(adapter);

        button = findViewById(R.id.button_back);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RankActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}

