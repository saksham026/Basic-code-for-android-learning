package com.example.saksham.flappy_bird.feature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class startGame extends Activity {

    GameView gameView;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        gameView=new GameView(this);
        setContentView(gameView);

    }

    public void StartGame(View view)
    {
        Intent intent=new Intent(this,startGame.class);
        startActivity(intent);
        finish();
    }
}
