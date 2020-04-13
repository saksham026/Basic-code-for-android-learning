package com.example.saksham.text_speech.feature;

import android.annotation.SuppressLint;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech t1;
    EditText write;
    ImageButton speakbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        write=(EditText) findViewById(R.id.editText);
        speakbtn=(ImageButton)findViewById(R.id.board);

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                if(i!=TextToSpeech.ERROR)
                {
                 t1.setLanguage(Locale.KOREAN);
                }
            }
        });

        speakbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toSpeak=write.getText().toString();
                Toast.makeText(getApplicationContext(),toSpeak,Toast.LENGTH_SHORT).show();
                t1.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

    }

    @Override
    public void onDestroy() {
        if (t1 != null) {
         t1.stop();
         t1.shutdown();
        }
        super.onDestroy();
    }
}
