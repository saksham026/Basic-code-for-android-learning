package com.example.saksham.sound.soundrecord;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button play,stop,record;
    private MediaRecorder myAudioRecorder;
    private String outputFile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play= (Button)findViewById(R.id.play);
        stop= (Button)findViewById(R.id.stop);
        record= (Button)findViewById(R.id.record);
        stop.setEnabled(false);
        play.setEnabled(false);

        outputFile= Environment.getExternalStorageDirectory().getAbsolutePath()+"/recording.3gp";
        myAudioRecorder=new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    myAudioRecorder.prepare();
                    myAudioRecorder.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                record.setEnabled(false);
                stop.setEnabled(true);

                Toast.makeText(getApplicationContext() , "recording started", Toast.LENGTH_LONG).show();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            myAudioRecorder.stop();
            myAudioRecorder.release();
            myAudioRecorder=null;
            record.setEnabled(true);
            stop.setEnabled(false);
            play.setEnabled(true);

                Toast.makeText(getApplicationContext() , "audio recorded succesfully", Toast.LENGTH_LONG).show();
            }
        });
play.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        MediaPlayer mediaPlayer=new MediaPlayer();
        try {
            mediaPlayer.setDataSource(outputFile);
            mediaPlayer.prepare();
            mediaPlayer.start();
            Toast.makeText(getApplicationContext() , "playig audio", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
});


    }
}
