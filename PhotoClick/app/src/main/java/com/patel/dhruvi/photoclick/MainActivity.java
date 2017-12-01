package com.patel.dhruvi.photoclick;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity
{
    static final int REQUEST_IMAGE_CAPTURE = 1;

    Uri temp_uri;
    static final int REQUEST_TAKE_PHOTO = 1;

    ImageView showImage ;
    Button btn_take_photo ;

    String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        showImage = (ImageView) findViewById(R.id.imageViewPhoto);
        btn_take_photo = (Button)findViewById(R.id.btn_takePhoto);

        askForCamea();

        btn_take_photo.setOnClickListener(new View.OnClickListener()
        {
             @Override
            public void onClick(View view)
            {
                dispatchTakePictureIntent();
            }
        });
    } //onCreate

    public void askForCamea()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            btn_take_photo.setEnabled(true);
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))
            {
                ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
            }
        }
    }

    private File createImageFile() throws IOException
    {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return image;
    }


    private void dispatchTakePictureIntent()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null)
        {
            // Create the File where the photo should go
            File photoFile = null;
            try
            {
                photoFile = createImageFile();

            } catch (IOException ex)
            {}
            // Continue only if the File was successfully created
            if (photoFile != null)
            {
                Context context = getApplicationContext();
                Uri photoURI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", photoFile);
//                Log.e("     PHoTO  " , "         photofile  : " + photoFile);
                photoPath = photoFile.getPath().toString();
//                Log.e("Paat   " , "Path from photofile"   + "       "+photoPath) ;

                temp_uri = photoURI;
                takePictureIntent.putExtra(android.content.Intent.EXTRA_STREAM, Uri.fromFile(photoFile));
                takePictureIntent.putExtra("path", photoFile.toString());
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }


    public void savePhotoToSD(Bitmap bitmap)
    {
        File image = new File( photoPath);
        boolean success = false;
        // Encode the file as a PNG image.
        FileOutputStream outStream;
        try
        {
            outStream = new FileOutputStream(image);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            /* 100 to keep full quality of the image */
            outStream.flush();
            outStream.close();
            success = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (success) {
            Toast.makeText(getApplicationContext(), "Image saved with success",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(),
                    "Error during image saving", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            Log.e("     PHoTO  " , "         got data extras "   + extras.get("data")) ;
            showImage.setImageBitmap(imageBitmap);
            savePhotoToSD(imageBitmap);
        }
        else if (resultCode == RESULT_CANCELED)
        {
            //Cancel code
            Log.e("     PHoTO  " , "      No photo clicked .. ") ;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        if (requestCode == 0)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED)
            {
                btn_take_photo.setEnabled(true);
            }
        }

    }
} // class
