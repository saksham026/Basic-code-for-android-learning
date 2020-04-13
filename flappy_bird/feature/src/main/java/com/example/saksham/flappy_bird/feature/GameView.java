package com.example.saksham.flappy_bird.feature;

//import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import android.os.Handler;

import java.util.Random;

public class GameView extends View
        {

            Handler handler;         //it is use to handle threads
            Runnable runnable;
            final int UPDATE_MILLIS=30;
            Bitmap background;
            Bitmap toptube;
            Bitmap bottomtube;
            Display display;
            Point point;
            int dwidth,dheight;
            Rect rect;

            Bitmap[] birds;     //array for birds
            int birdFrame=0;
            int velocity=0,gravity=3;


            int birdx,birdy;
            boolean gamestate=false;
            int gap=400;
            int mintubeoffset,maxtubeoffset;
            int notubes=4;
            int distbwtubes;
            int[] tubex=new int[notubes];
            int[] toptubey=new int[notubes];
            Random random;
            int tubevelo=8;


            public GameView(Context context) {
                super(context);
                handler = new Handler();
                runnable = new Runnable() {
                    @Override

                    public void run() {
                        invalidate();
                    }
                };

                background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
                toptube=BitmapFactory.decodeResource(getResources(),R.drawable.toptube);
                bottomtube=BitmapFactory.decodeResource(getResources(),R.drawable.bottomtube);
                display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();

                point = new Point();
                display.getSize(point);
                dwidth = point.x;
                dheight = point.y;
                rect = new Rect(0, 0, dwidth, dheight);
                birds = new Bitmap[2];
                birds[0] = BitmapFactory.decodeResource(getResources(), R.drawable.bird);
                birds[1] = BitmapFactory.decodeResource(getResources(), R.drawable.bird1);

                birdx = dwidth / 2 - birds[0].getWidth() / 2;
                birdy = dheight / 2 - birds[0].getHeight() / 2;
                distbwtubes=dwidth*3/4;
                 mintubeoffset=gap/2;
                maxtubeoffset=dheight-mintubeoffset-gap;
                random=new Random();
                for (int i = 0; i < notubes; i++) {
                    tubex[i] = dwidth + i * distbwtubes;
                    toptubey[i] = mintubeoffset + random.nextInt(maxtubeoffset - mintubeoffset + 1);
                }

            }
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        //canvas.drawBitmap(background,0,0,null);
        canvas.drawBitmap(background,null,rect,null);
        if(birdFrame==0)
        {
            birdFrame=1;
        }else
        {
            birdFrame=0;
        }
        if(gamestate) {
            if (birdy < dheight - birds[0].getHeight() || velocity < 0)      //prevent from falling off screen
            {
                velocity += gravity; //as bird falls its value goes on increasing as increment with g every time
                birdy += velocity;
            }

            for(int i=0;i<notubes;i++)
            {
                tubex[i]-=tubevelo;
                if(tubex[i]<toptube.getWidth())
                {
                    tubex[i]+=notubes*distbwtubes;
                    toptubey[i] = mintubeoffset + random.nextInt(maxtubeoffset - mintubeoffset + 1);
                }
                canvas.drawBitmap(toptube, tubex[i], toptubey[i] - toptube.getHeight(), null);
                canvas.drawBitmap(bottomtube, tubex[i], toptubey[i] + gap, null);
            }
        }
        //for displaying birds at center of the screen
        canvas.drawBitmap(birds[birdFrame],birdx,birdy,null);
        handler.postDelayed(runnable,UPDATE_MILLIS);
    }

    //@SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent event)
    {
        int action=event.getAction();

        if(action==MotionEvent.ACTION_DOWN)       //TAP IN DESIRED DIRECTION OF STRING
        {
            velocity=-30;
            gamestate=true;
        }

        return true;             //returning true indiactes that we are done with touch event and no further action is required by nadroid
    }

}
