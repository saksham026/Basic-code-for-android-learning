package com.example.saksham.camera.feature;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Config;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Camera extends AppCompatActivity {

    public static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE=100;
    public static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE=200;
    public static final int MEDIA_TYPE_IMAGE=1;
    public static final int MEDIA_TYPE_VIDEO=2;

    private static final String IMAGE_DIRECTORY_NAME="HELLO CAMERA";

    private Uri fileUri;

    private ImageView imgPreview;
    private VideoView videoPreview;
    private Button btnCapturePicture,btnRecordVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imgPreview=(ImageView) findViewById(R.id.imgPreview);
        videoPreview=(VideoView) findViewById(R.id.videoPreview);
        btnCapturePicture=(Button) findViewById(R.id.btnCapturePicture);
        btnRecordVideo=(Button) findViewById(R.id.btnRecordVideo);

        btnCapturePicture.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View view) {

                                                     captureImage();
                                                 }
                                             });



                btnRecordVideo.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View view) {

                                                          recordVideo();

                                                      }
                                                  });


        if(!isDeviceSupportCamera())
        {
            Toast.makeText(getApplicationContext(),"sorry your device do not support camera",Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private boolean isDeviceSupportCamera()
    {
        if(getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    private void captureImage()
    {
        Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri=getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);
        startActivityForResult(intent,CAMERA_CAPTURE_IMAGE_REQUEST_CODE);


    }

    protected void onSaveInstanceState(Bundle outState){

        super.onSaveInstanceState(outState);
        outState.putParcelable("file_uri",fileUri);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {

        super.onRestoreInstanceState(savedInstanceState);
        fileUri=savedInstanceState.getParcelable("file_uri");
    }
    private void recordVideo()
    {
        Intent intent =new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        fileUri=getOutputMediaFileUri(MEDIA_TYPE_VIDEO);

        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
         intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);

        startActivityForResult(intent,CAMERA_CAPTURE_IMAGE_REQUEST_CODE);

    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        if(requestCode==CAMERA_CAPTURE_IMAGE_REQUEST_CODE)
        {
            if(resultCode==RESULT_OK)
            {
                previewCapturedImage();
            }
            else if(resultCode==RESULT_CANCELED)
            {
                Toast.makeText(getApplicationContext(),"user cancelled image capture",Toast.LENGTH_SHORT).show();

            }
            else
            {
                Toast.makeText(getApplicationContext(),"sorry failed to capture image",Toast.LENGTH_SHORT).show();
            }
        }else if(requestCode==CAMERA_CAPTURE_VIDEO_REQUEST_CODE)
        {
            if(resultCode==RESULT_OK)
            {
                previewVideo();
            }
            else if(resultCode==RESULT_CANCELED)
            {
                Toast.makeText(getApplicationContext(),"user cancelled video capture",Toast.LENGTH_SHORT).show();

            }
            else
            {
                Toast.makeText(getApplicationContext(),"sorry failed to capture video",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void previewCapturedImage()
    {
       try{

           videoPreview.setVisibility(View.GONE);
           imgPreview.setVisibility(View.VISIBLE);

           BitmapFactory.Options options=new BitmapFactory.Options();

           options.inSampleSize=8;

           final Bitmap bitmap= BitmapFactory.decodeFile(fileUri.getPath(),options);
            imgPreview.setImageBitmap(bitmap);

       }catch (NullPointerException e)
       {
           e.printStackTrace();
           e.printStackTrace();

       }
    }
    private void previewVideo()
    {
        try{
            imgPreview.setVisibility(View.GONE);
            videoPreview.setVisibility(View.VISIBLE);
            videoPreview.setVideoPath(fileUri.getPath());

            videoPreview.start();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public Uri getOutputMediaFileUri(int type)
    {
      //  requestRuntimePermission();
        return Uri.fromFile(getOutputMediaFile(type));

    }

    private static File getOutputMediaFile(int type)
    {
        File mediaStorageDir=new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),IMAGE_DIRECTORY_NAME);

        if(!mediaStorageDir.exists())
        {
            if(!mediaStorageDir.mkdirs())
            {
                Log.d(IMAGE_DIRECTORY_NAME,"OOPS! FAILED CREATE"+ IMAGE_DIRECTORY_NAME+"DIRECTORY");
                return null;
            }
        }

        String timeStamp=new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());

        File mediaFile;
        if(type==MEDIA_TYPE_IMAGE)
        {
            mediaFile =new File(mediaStorageDir.getPath()+ File.separator +"IMG_"+timeStamp+".jpg");

        }else if(type==MEDIA_TYPE_VIDEO)
        {
            mediaFile =new File(mediaStorageDir.getPath()+ File.separator +"VID_"+timeStamp+".mp4");

        }else
        {
            return null;
        }
            return mediaFile;
    }
   /* public void requestRuntimePermission()
    {
        if(Build.VERSION.SDK_INT<=23)
        {

            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED);

            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,1});
        }
    }*/
}
