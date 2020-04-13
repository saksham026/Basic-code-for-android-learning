package com.example.saksham.flash.feature;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {

    private Button buttonEnable;
    private ImageView imageFlashLight;
    private static final int CAMERA_REQUEST=50;
    private boolean flashLightStatus=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageFlashLight=(ImageView)findViewById(R.id.imageFlashLight);
        buttonEnable=(Button)findViewById(R.id.buttonEnable);

        final boolean hasCameraFlash=getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        boolean isEnabled= ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED;
        buttonEnable.setEnabled(!isEnabled);
        imageFlashLight.setEnabled(isEnabled);
        buttonEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{android.Manifest.permission.CAMERA},CAMERA_REQUEST);
            }
        });

        imageFlashLight.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if(hasCameraFlash)
                {
                    if(flashLightStatus)
                        flasLightOff();
                    else
                        flashLightOn();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"no flash ight on your device",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void flashLightOn()
    {
        CameraManager cameraManager=(CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId,true);
            flashLightStatus=true;
            imageFlashLight.setImageResource(R.drawable.images);
        }catch(CameraAccessException e)
        {

        }

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void flasLightOff()
    {
        CameraManager cameraManager=(CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId,false);
            flashLightStatus=false;
            imageFlashLight.setImageResource(R.drawable.image);
        }catch(CameraAccessException e)
        {

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode)
        {
            case CAMERA_REQUEST:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    buttonEnable.setEnabled(false);
                    buttonEnable.setText("camera enabled");
                    imageFlashLight.setEnabled(true);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"permission denied for the camera",Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }
}

