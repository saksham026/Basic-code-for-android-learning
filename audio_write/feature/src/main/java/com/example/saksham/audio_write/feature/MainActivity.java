package com.example.saksham.audio_write.feature;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView speechinput;
    private ImageButton imagebt;
    private final int REQ_CODE_SPEECH_INPUT=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speechinput=(TextView) findViewById(R.id.speechinput);
        imagebt=(ImageButton) findViewById(R.id.imagebt);

        imagebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"SAY SOMETHING");
                try
                {
                    startActivityForResult(intent,REQ_CODE_SPEECH_INPUT);
                  //  Toast.makeText(getApplicationContext(),"working",Toast.LENGTH_SHORT).show();

                }
                catch(ActivityNotFoundException a)
                {
                    Toast.makeText(getApplicationContext(),"sorry  your device doesn,t support speech input",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

@Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        Toast.makeText(getApplicationContext(),"working3 ",Toast.LENGTH_SHORT).show();

        switch (requestCode)
        {

            case REQ_CODE_SPEECH_INPUT:
            {
                //Toast.makeText(getApplicationContext(),"working2 ",Toast.LENGTH_SHORT).show();

                if(resultCode==RESULT_OK && null!=data)
                {
                    //Toast.makeText(getApplicationContext(),"1123vh vbmn ",Toast.LENGTH_SHORT).show();

                    ArrayList<String> result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String speechText=speechinput.getText().toString()+"\n"+result.get(0);
                    //Toast.makeText(getApplicationContext(),speechText,Toast.LENGTH_SHORT).show();

                    speechinput.setText(speechText);
                }
                    break;
            }
        }
    }
}
