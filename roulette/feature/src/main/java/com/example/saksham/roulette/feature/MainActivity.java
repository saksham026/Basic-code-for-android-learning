package com.example.saksham.roulette.feature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;
    ImageView ic_wheeel;

    Random r;

    int degree=0,degrre_old=0;

    private static final float FACTOR=4.86f;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button =(Button)findViewById(R.id.button);
        textView=(TextView)findViewById(R.id.textView);
        ic_wheeel=(ImageView)findViewById(R.id.ic_wheel);

        r=new Random();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                degrre_old=degree%360;
                degree=r.nextInt(3600)+720;

                RotateAnimation rotate =new RotateAnimation(degrre_old,degree,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);

                rotate.setDuration(2000);
                rotate.setFillAfter(true);
                rotate.setInterpolator(new DecelerateInterpolator());

                rotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        textView.setText("");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        textView.setText(currentNumber(360-(degree%360)));
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                ic_wheeel.startAnimation(rotate);
            }
        });
    }

    private String currentNumber(int degree){
        String text="";

        if(degree>=(FACTOR *1) && degree< (FACTOR*3))
        {
            text="32 red";

        }
        if(degree>=(FACTOR *3) && degree< (FACTOR*5))
        {
            text="32 red";

        }if(degree>=(FACTOR *5) && degree< (FACTOR*7))
        {
            text="32 red";

        }if(degree>=(FACTOR *7) && degree< (FACTOR*9))
        {
            text="32 red";

        }if(degree>=(FACTOR *9) && degree< (FACTOR*11))
        {
            text="32 red";

        }if(degree>=(FACTOR * 11) && degree< (FACTOR*13))
        {
            text="32 red";

        }if(degree>=(FACTOR * 13) && degree< (FACTOR*15))
        {
            text="32 red";

        }if(degree>=(FACTOR * 15) && degree< (FACTOR*17))
        {
            text="32 red";

        }if(degree>=(FACTOR * 17) && degree< (FACTOR*19))
        {
            text="32 red";

        }if(degree>=(FACTOR * 19) && degree< (FACTOR*21))
        {
            text="32 red";

        }if(degree>=(FACTOR * 21) && degree< (FACTOR*23))
        {
            text="32 red";

        }if(degree>=(FACTOR * 23) && degree< (FACTOR*25))
        {
            text="32 red";

        }if(degree>=(FACTOR * 25) && degree< (FACTOR*27))
        {
            text="32 red";

        }if(degree>=(FACTOR * 27) && degree< (FACTOR*29))
        {
            text="32 red";

        }
                return text;
    }

}
