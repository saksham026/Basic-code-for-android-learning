package com.example.saksham.gamble.feature;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button b_roll;

    ImageView image1,image2,image3;

    Random r;

    int img1,img2,img3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r=new Random();

        b_roll=(Button) findViewById(R.id.b_roll);

        image1=(ImageView) findViewById(R.id.image1);
        image2=(ImageView) findViewById(R.id.image2);
        image3=(ImageView) findViewById(R.id.image3);

        b_roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image1.setImageResource(R.drawable.anim);
                final AnimationDrawable image1anim=(AnimationDrawable) image1.getDrawable();
                image1anim.start();

                image2.setImageResource(R.drawable.anim);
                final AnimationDrawable image2anim=(AnimationDrawable) image2.getDrawable();
                image2anim.start();

                image3.setImageResource(R.drawable.anim);
                final AnimationDrawable image3anim=(AnimationDrawable) image3.getDrawable();
                image3anim.start();

                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        image1anim.stop();
                        image2anim.stop();
                        image3anim.stop();

                        setImages();

                        getScore();
                    }
                },700); //it gives a delay of 500 milisec
            }
        });


    }

    public void setImages()
    {
            img1=r.nextInt(4)+1;
        img2=r.nextInt(4)+1;
        img3=r.nextInt(4)+1;

        switch(img1)
        {
            case 1:
                image1.setImageResource(R.drawable.apple);
                break;
            case 2:
                image1.setImageResource(R.drawable.cherry);
                break;
            case 3:
                image1.setImageResource(R.drawable.banana);
                break;
            case 4:
                image1.setImageResource(R.drawable.grape);
                break;
        }
        switch(img2)
        {
            case 1:
                image2.setImageResource(R.drawable.apple);
                break;
            case 2:
                image2.setImageResource(R.drawable.cherry);
                break;
            case 3:
                image2.setImageResource(R.drawable.banana);
                break;
            case 4:
                image2.setImageResource(R.drawable.grape);
                break;
        }
        switch(img3)
        {
            case 1:
                image3.setImageResource(R.drawable.apple);
                break;
            case 2:
                image3.setImageResource(R.drawable.cherry);
                break;
            case 3:
                image3.setImageResource(R.drawable.banana);
                break;
            case 4:
                image3.setImageResource(R.drawable.grape);
                break;
        }
    }

    public void getScore()
    {
        if(img1==img2&&img1==img3)
        {
            Toast.makeText(this,"jackpot!!!",Toast.LENGTH_SHORT).show();
        }
        else if(img1==img2||img1==img3||img2==img3)
        {
            Toast.makeText(this,"good but try again!!!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"try again looser!!!",Toast.LENGTH_SHORT).show();
        }
    }
}
