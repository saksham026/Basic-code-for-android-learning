package com.example.saksham.flappy_bird.feature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void StartGame(View view)            //METHOD TO BE CALLED
    {
        Intent intent=new Intent(this,startGame.class);
        startActivity(intent);
        finish();
    }
}
