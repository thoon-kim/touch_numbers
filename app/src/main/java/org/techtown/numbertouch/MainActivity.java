package org.techtown.numbertouch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements FragmentCallback{
    public static final String DBNAME = "ranking.db";
    public static final String TBNAME = "rank";

    Button button_44, button_55, button_66, button_rank;
    TextView timer_textView;
    Thread timeThread = null;
    Boolean isRunning = false;
    LinearLayout container;
    Fragment fragment44, fragment55, fragment66, game_finish;
    String result, date; // 게임 종료 시간, 게임한 날짜
    String type = "4X4"; // 게임 타입
    SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_44 = findViewById(R.id.button4_4); // Game 4X4
        button_55 = findViewById(R.id.button5_5); // Game 5X5
        button_66 = findViewById(R.id.button6_6); // Game 6X6
        button_rank = findViewById(R.id.button_rank); // 순위 버튼
        timer_textView = findViewById(R.id.timerTextView); // 타이머
        container = findViewById(R.id.gameContainer); // 게임 화면 부분

        long now = System.currentTimeMillis();
        Date today = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = sdf.format(today);

//       초기화면 설정(4X4)
        fragment44 = new Game44Fragment();
        getSupportFragmentManager().beginTransaction().add(R.id.gameContainer, fragment44).commit();

        button_44.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(isRunning){
                    timeThread.interrupt();
                    timer_textView.setText("00:00:00");
                    isRunning = !isRunning;
                }
                type = "4X4";
                timer_textView.setText("00:00:00");
                fragment44 = new Game44Fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.gameContainer, fragment44).commit();
            }
        });

        button_55.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(isRunning){
                    timeThread.interrupt();
                    timer_textView.setText("00:00:00");
                    isRunning = !isRunning;
                }
                type = "5X5";
                timer_textView.setText("00:00:00");
                fragment55 = new Game55Fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.gameContainer, fragment55).commit();
            }
        });

        button_66.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(isRunning){
                    timeThread.interrupt();
                    timer_textView.setText("00:00:00");
                    isRunning = !isRunning;
                }
                type = "6X6";
                timer_textView.setText("00:00:00");
                fragment66 = new Game66Fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.gameContainer, fragment66).commit();
            }
        });

        button_rank.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(isRunning){
                    // 알림 상자 뜨게
                    isRunning = !isRunning;
                    showMessage();
                } else {
                    Intent intent = new Intent(MainActivity.this, RankActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onFragmentSelected(int position, Bundle bundle) {
        Fragment curFragment = null;
        if (position == 0) {
            curFragment = new Game44Fragment();
            type = "4X4";
        } else if (position == 1) {
            curFragment = new Game55Fragment();
            type = "5X5";
        } else if (position == 2) {
            curFragment = new Game66Fragment();
            type = "6X6";
        } else if (position == 3) {
            curFragment = new GameFinish();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.gameContainer, curFragment).commit();
    }

    // 게임 시작
    @Override
    public void start() {
        isRunning = true;
        timeThread = new Thread(new timeThread());
        timeThread.start();
    }

    // 게임 종료(성공)
    @Override
    public void success() {
        timeThread.interrupt();
        isRunning = false;
        result = timer_textView.getText().toString();

        insertRecord(result, type, date);

        game_finish = new GameFinish();
        getSupportFragmentManager().beginTransaction().replace(R.id.gameContainer, game_finish).commit();
    }

    // 게임 종료 시 마다 DB에 기록을 저장
    public void insertRecord(String time, String type, String date) {
        database = openOrCreateDatabase(DBNAME, MODE_PRIVATE, null);

        database.execSQL("create table if not exists " + TBNAME + " ("
                + "time text, "
                + "type text, "
                + "date text)");

        ContentValues contentValues = new ContentValues();
        contentValues.put("TIME", time);
        contentValues.put("TYPE", type);
        contentValues.put("DATE", date);

        database.insert(TBNAME, null, contentValues);
    }

    // 시간을 재기 위한 쓰레드와 핸들러
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // 시간 format :
            int mSec = msg.arg1 % 100;
            int sec = (msg.arg1 / 100) % 60;
            int min = (msg.arg1 /100) / 60;
            String result = String.format("%02d:%02d:%02d", min, sec, mSec);
            timer_textView.setText(result);
        }
    };

    public class timeThread implements Runnable {
        @Override
        public void run() {
            int i = 0;

            while(true) {
                while(isRunning) { // 일시정지를 누르면 멈추도록
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        return; // 인터럽트 받을 경우 return됨
                    }

                    Message msg = new Message();
                    msg.arg1 = i++;
                    handler.sendMessage(msg);
                }
            }
        }
    }

    private void showMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("진행하던 게임이 종료됩니다. \n 순위화면으로 이동하시겠습니까?");

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainActivity.this, RankActivity.class);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                isRunning = !isRunning;
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
