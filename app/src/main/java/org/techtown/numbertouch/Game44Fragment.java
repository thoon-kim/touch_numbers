package org.techtown.numbertouch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Game44Fragment extends Fragment {
    private static final String TAG = "Fragment44";
    FragmentCallback callback;
    RelativeLayout[] button = new RelativeLayout[16];
    TextView[] button_text = new TextView[16];
    int[] rand_nums = new int[16];
    int now_num = 1;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentCallback) {
            callback = (FragmentCallback) context;
        } else {
            Log.d(TAG, "Activity is not FragmentCallback.");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_game44, container, false);

        allView(rootView);
        rand(rand_nums);

        for(int i = 0; i < 16; i++) {
            button_text[i].setText(Integer.toString(rand_nums[i]));
        }

        button_clicked(container);

        return rootView;
    }

    private void rand(int[] rand_nums){
        Random random = new Random();

        for(int i = 0; i < 16; i++) {
            rand_nums[i] = random.nextInt(16)+1;
            for(int j = 0; j < i; j++) {
                if(rand_nums[i] == rand_nums[j]) {
                    i--;
                    break;
                }
            }
        }
    }

    private void allView(ViewGroup view){
        button[0] = (RelativeLayout)view.findViewById(R.id.button_1);
        button[1] = (RelativeLayout)view.findViewById(R.id.button_2);
        button[2] = (RelativeLayout)view.findViewById(R.id.button_3);
        button[3] = (RelativeLayout)view.findViewById(R.id.button_4);
        button[4] = (RelativeLayout)view.findViewById(R.id.button_5);
        button[5] = (RelativeLayout)view.findViewById(R.id.button_6);
        button[6] = (RelativeLayout)view.findViewById(R.id.button_7);
        button[7] = (RelativeLayout)view.findViewById(R.id.button_8);
        button[8] = (RelativeLayout)view.findViewById(R.id.button_9);
        button[9] = (RelativeLayout)view.findViewById(R.id.button_10);
        button[10] = (RelativeLayout)view.findViewById(R.id.button_11);
        button[11] = (RelativeLayout)view.findViewById(R.id.button_12);
        button[12] = (RelativeLayout)view.findViewById(R.id.button_13);
        button[13] = (RelativeLayout)view.findViewById(R.id.button_14);
        button[14] = (RelativeLayout)view.findViewById(R.id.button_15);
        button[15] = (RelativeLayout)view.findViewById(R.id.button_16);

        button_text[0] = (TextView)view.findViewById(R.id.button_text_1);
        button_text[1] = (TextView)view.findViewById(R.id.button_text_2);
        button_text[2] = (TextView)view.findViewById(R.id.button_text_3);
        button_text[3] = (TextView)view.findViewById(R.id.button_text_4);
        button_text[4] = (TextView)view.findViewById(R.id.button_text_5);
        button_text[5] = (TextView)view.findViewById(R.id.button_text_6);
        button_text[6] = (TextView)view.findViewById(R.id.button_text_7);
        button_text[7] = (TextView)view.findViewById(R.id.button_text_8);
        button_text[8] = (TextView)view.findViewById(R.id.button_text_9);
        button_text[9] = (TextView)view.findViewById(R.id.button_text_10);
        button_text[10] = (TextView)view.findViewById(R.id.button_text_11);
        button_text[11] = (TextView)view.findViewById(R.id.button_text_12);
        button_text[12] = (TextView)view.findViewById(R.id.button_text_13);
        button_text[13] = (TextView)view.findViewById(R.id.button_text_14);
        button_text[14] = (TextView)view.findViewById(R.id.button_text_15);
        button_text[15] = (TextView)view.findViewById(R.id.button_text_16);
    }

    private void button_clicked(ViewGroup container){
        final Context context;
        context = container.getContext();
        for (int i = 0; i < 16; i++) {
            final int j = i;
            button[j].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int number = j;
                    if(now_num == rand_nums[number]) {
                        if(now_num == 1){
//                          시작
                            callback.start();
                            Toast.makeText(context, "Start", Toast.LENGTH_LONG).show();
                        }
                        now_num += 1;
                        button[number].setVisibility(View.INVISIBLE);
                    }
                    if(now_num == 17){
//                      성공 메시지
                        callback.success();
                    }
                }
            });
        }
    }
}
