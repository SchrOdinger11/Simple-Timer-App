package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {
    SeekBar newSeekBar;
     TextView timer;
     Boolean counterIsActive=false;
     Button btn;
     CountDownTimer count;
    public void updatetimer(int secondsleft){
        int minutes = (int) secondsleft/60 ;
        int seconds=secondsleft-minutes*60;
        String secondstring=Integer.toString(seconds);

         if(seconds<=9)
        {
            secondstring="0"+Integer.toString(seconds);
        }
        timer.setText(Integer.toString(minutes)+":"+secondstring);

    }
    public void controlTimer(View view) {
        if (counterIsActive == false) {
            counterIsActive = true;
            newSeekBar.setEnabled(false);
            newSeekBar.setVisibility(INVISIBLE);

            btn.setText("Stop");
            count=new CountDownTimer(newSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    updatetimer((int) l / 1000);


                }

                @Override
                public void onFinish() {
                    //Log.i("info","Finished");
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn1);
                    mplayer.start();
                    timer.setText("0:30");
                    newSeekBar.setProgress(30);
                    count.cancel();
                    newSeekBar.setEnabled(true);
                    newSeekBar.setVisibility(VISIBLE);
                    btn.setText("Go");
                    counterIsActive=false;

                }
            }.start();
            Log.i("info", "BUtton pressed");
        }
        else{
            timer.setText("0:30");
            newSeekBar.setProgress(30);
            count.cancel();
            newSeekBar.setEnabled(true);
            newSeekBar.setVisibility(VISIBLE);
            btn.setText("Go");
            counterIsActive=false;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         newSeekBar= (SeekBar)findViewById(R.id.seekBar);
          timer= (TextView) findViewById(R.id.timerTextView);
          btn=(Button)findViewById(R.id.button);

        newSeekBar.setMax(600);
        newSeekBar.setProgress(30);
        newSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            updatetimer(i);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}