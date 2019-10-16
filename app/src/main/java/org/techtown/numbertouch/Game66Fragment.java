package org.techtown.numbertouch;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Game66Fragment extends Fragment {
    private static final String TAG = "Fragment66";
    FragmentCallback callback;
    RelativeLayout[] button = new RelativeLayout[36];
    TextView[] button_text = new TextView[36];
    int[] rand_nums = new int[36];
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_game66, container, false);

        allView(rootView);
        rand(rand_nums);

        for(int i = 0; i < 36; i++) {
            button_text[i].setText(Integer.toString(rand_nums[i]));
        }

        button_clicked(container);

        return rootView;
    }

    public void rand(int[] rand_nums){
        Random random = new Random();

        for(int i = 0; i < 36; i++) {
            rand_nums[i] = random.nextInt(36)+1;
            for(int j = 0; j < i; j++) {
                if(rand_nums[i] == rand_nums[j]) {
                    i--;
                    break;
                }
            }
        }
    }

    public void allView(ViewGroup view){
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
        button[16] = (RelativeLayout)view.findViewById(R.id.button_17);
        button[17] = (RelativeLayout)view.findViewById(R.id.button_18);
        button[18] = (RelativeLayout)view.findViewById(R.id.button_19);
        button[19] = (RelativeLayout)view.findViewById(R.id.button_20);
        button[20] = (RelativeLayout)view.findViewById(R.id.button_21);
        button[21] = (RelativeLayout)view.findViewById(R.id.button_22);
        button[22] = (RelativeLayout)view.findViewById(R.id.button_23);
        button[23] = (RelativeLayout)view.findViewById(R.id.button_24);
        button[24] = (RelativeLayout)view.findViewById(R.id.button_25);
        button[25] = (RelativeLayout)view.findViewById(R.id.button_26);
        button[26] = (RelativeLayout)view.findViewById(R.id.button_27);
        button[27] = (RelativeLayout)view.findViewById(R.id.button_28);
        button[28] = (RelativeLayout)view.findViewById(R.id.button_29);
        button[29] = (RelativeLayout)view.findViewById(R.id.button_30);
        button[30] = (RelativeLayout)view.findViewById(R.id.button_31);
        button[31] = (RelativeLayout)view.findViewById(R.id.button_32);
        button[32] = (RelativeLayout)view.findViewById(R.id.button_33);
        button[33] = (RelativeLayout)view.findViewById(R.id.button_34);
        button[34] = (RelativeLayout)view.findViewById(R.id.button_35);
        button[35] = (RelativeLayout)view.findViewById(R.id.button_36);

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
        button_text[16] = (TextView)view.findViewById(R.id.button_text_17);
        button_text[17] = (TextView)view.findViewById(R.id.button_text_18);
        button_text[18] = (TextView)view.findViewById(R.id.button_text_19);
        button_text[19] = (TextView)view.findViewById(R.id.button_text_20);
        button_text[20] = (TextView)view.findViewById(R.id.button_text_21);
        button_text[21] = (TextView)view.findViewById(R.id.button_text_22);
        button_text[22] = (TextView)view.findViewById(R.id.button_text_23);
        button_text[23] = (TextView)view.findViewById(R.id.button_text_24);
        button_text[24] = (TextView)view.findViewById(R.id.button_text_25);
        button_text[25] = (TextView)view.findViewById(R.id.button_text_26);
        button_text[26] = (TextView)view.findViewById(R.id.button_text_27);
        button_text[27] = (TextView)view.findViewById(R.id.button_text_28);
        button_text[28] = (TextView)view.findViewById(R.id.button_text_29);
        button_text[29] = (TextView)view.findViewById(R.id.button_text_30);
        button_text[30] = (TextView)view.findViewById(R.id.button_text_31);
        button_text[31] = (TextView)view.findViewById(R.id.button_text_32);
        button_text[32] = (TextView)view.findViewById(R.id.button_text_33);
        button_text[33] = (TextView)view.findViewById(R.id.button_text_34);
        button_text[34] = (TextView)view.findViewById(R.id.button_text_35);
        button_text[35] = (TextView)view.findViewById(R.id.button_text_36);
    }

    private void button_clicked(ViewGroup container){
        final Context context;
        context = container.getContext();
        for (int i = 0; i < 36; i++) {
            final int j = i;
            button[j].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int number = j;
                    if(now_num == rand_nums[number]) {
                        if(now_num == 1){
//                          시작
                            callback.start();
                        }
                        now_num += 1;
                        button[number].setVisibility(View.INVISIBLE);
                    }
                    if(now_num == 37){
//                      성공 메시지
                        callback.success();
                    }
                }
            });
        }
    }
}
