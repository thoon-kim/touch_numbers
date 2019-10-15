package org.techtown.numbertouch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentCallback{
    Button button_44, button_55, button_66, button_rank;
    TextView timer_textView;
    Thread timeThread = null;
    Boolean isRunning = true;
    LinearLayout container;
    Fragment fragment44, fragment55, fragment66;

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

//       초기화면 설정(4X4)
        fragment44 = new Game44Fragment();
        getSupportFragmentManager().beginTransaction().add(R.id.gameContainer, fragment44).commit();

        button_44.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                fragment44 = new Game44Fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.gameContainer, fragment44).commit();
            }
        });

        button_55.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                fragment55 = new Game55Fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.gameContainer, fragment55).commit();
            }
        });

        button_66.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                fragment66 = new Game66Fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.gameContainer, fragment66).commit();
            }
        });

        button_rank.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RankActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onFragmentSelected(int position, Bundle bundle) {
        Fragment curFragment = null;
        if (position == 0) {
            curFragment = new Game44Fragment();
        } else if (position == 1) {
            curFragment = new Game55Fragment();
        } else if (position == 2) {
            curFragment = new Game66Fragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.gameContainer, curFragment).commit();
    }

    @Override
    public void start() {
//      시간 측정
        timeThread = new Thread(new timeThread());
        timeThread.start();
    }

    @Override
    public void success() {
//      종료
        timeThread.interrupt();
        Toast.makeText(getApplicationContext(), timer_textView.getText(), Toast.LENGTH_SHORT).show();
//        timer_textView.setText("00:00:00");
    }


    // 시간을 재기 위한 쓰레드와 핸들러
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // 시간 format :
            int mSec = msg.arg1 % 100;
            int sec = (msg.arg1 / 100) % 60;
            int min = (msg.arg1 /100) / 60;
            String result = String.format("%02d:%02d:%02d", min,sec,mSec);
            timer_textView.setText(result);
        }
    };

    public class timeThread implements Runnable{
        @Override
        public void run() {
            int i = 0;

            while(true) {
                while(isRunning) { //일시정지를 누르면 멈추도록
                    Message msg = new Message();
                    msg.arg1=i++;
                    handler.sendMessage(msg);

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return; // 인터럽트 받을 경우 return됨
                    }
                }
            }
        }
    }
}
