package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Media extends AppCompatActivity implements SurfaceHolder.Callback {
    TextView welcome;
    String currentuser;
    Button stopaudio,stopvideo;
    ToggleButton playaudio,loopaudio,playvideo,loopvideo;
    SeekBar audiotime,videotime;
    SurfaceView videoscreen;
    SurfaceHolder videosurface;
    MediaPlayer mp1=new MediaPlayer(), mp2=new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        Intent i = getIntent();
        currentuser=i.getStringExtra("currentuser");
        findViews();
        mp1=MediaPlayer.create(Media.this,R.raw.test);
        mp2=MediaPlayer.create(Media.this,R.raw.demo);
        audiotime.setMax(mp1.getDuration());
        audiotime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mp1.seekTo(progress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        class CurrentAudioLength implements Runnable {
            private Handler h = new Handler(){
                public void handleMessage(Message msg){
                    super.handleMessage(msg);
                    audiotime.setProgress(mp1.getCurrentPosition());
                }
            };
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(33);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    h.sendEmptyMessage(mp1.getCurrentPosition());
                }
            }
        }
        CurrentAudioLength currentAudioLength=new CurrentAudioLength();
        new Thread(currentAudioLength).start();
        videotime.setMax(mp2.getDuration());
        videotime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mp2.seekTo(progress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        class CurrentVideoLength implements Runnable{
            private Handler h = new Handler(){
                public void handleMessage(Message msg){
                    super.handleMessage(msg);
                    videotime.setProgress(mp2.getCurrentPosition());
                }
            };
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(33);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    h.sendEmptyMessage(mp2.getCurrentPosition());
                }
            }
        }
        CurrentVideoLength currentVideoLength=new CurrentVideoLength();
        new Thread(currentVideoLength).start();
        playaudio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    try{
                        mp1.start();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        playaudio.setChecked(false);
                    }
                }
                else{
                    mp1.pause();
                }
            }
        });
        stopaudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp1.pause();
                mp1.seekTo(0);
                playaudio.setChecked(false);
            }
        });
        loopaudio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mp1.setLooping(true);
                }
                else{
                    mp1.setLooping(false);
                }
            }
        });
        videosurface=videoscreen.getHolder();
        videosurface.addCallback((SurfaceHolder.Callback)this);
        playvideo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    try{
                        mp2.start();
                    }
                    catch(Exception e){
                        e.printStackTrace();
                        playvideo.setChecked(false);
                    }
                }
                else{
                    mp2.pause();
                }
            }
        });
        stopvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp2.pause();
                mp2.seekTo(0);
                playvideo.setChecked(false);
            }
        });
        loopvideo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mp2.setLooping(true);
                }
                else{
                    mp2.setLooping(false);
                }
            }
        });
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mp2.setDisplay(holder);
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) { }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp1.pause();
        mp1.seekTo(0);
        playaudio.setChecked(false);
    }
    private void findViews() {
        welcome=findViewById(R.id.textView14);
        welcome.setText("欢迎回来，"+currentuser);
        stopaudio=findViewById(R.id.button6);
        playaudio=findViewById(R.id.toggleButton);
        loopaudio=findViewById(R.id.toggleButton2);
        audiotime=findViewById(R.id.seekBar);
        videoscreen=findViewById(R.id.surfaceView);
        stopvideo=findViewById(R.id.button7);
        playvideo=findViewById(R.id.toggleButton3);
        loopvideo=findViewById(R.id.toggleButton4);
        videotime=findViewById(R.id.seekBar2);
    }
}
