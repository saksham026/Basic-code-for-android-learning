package com.example.saksham.call.feature;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CALL=1;
    private EditText mEditTextNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextNumber=findViewById(R.id.edit_text_number);
        ImageView imagecall=findViewById(R.id.image_call);

        imagecall.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             makephonecall();
                                         }
                                     }
        );

    }
    public void makephonecall()
    {
        String number=mEditTextNumber.getText().toString();
        if(number.trim().length()>0)
        {
            if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
            }else
            {
                String dial="tel:"+number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }else
        {
            Toast.makeText(MainActivity.this,"enter phone no",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUEST_CALL)
        {
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED);
            makephonecall();
        }
        else
        {
            Toast.makeText(MainActivity.this,"permission denied",Toast.LENGTH_SHORT).show();
        }
    }
}
