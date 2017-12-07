package dhruvipatel.littercontrol;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;


import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dhruvipatel.littercontrol.Database.DatabaseHandler;
import dhruvipatel.littercontrol.Model.Post;

public class AddPostActivity extends AppCompatActivity
{
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    Uri temp_uri;
    ImageView showImage ;
    Button btn_take_photo , btn_upload_post ;
    String photoPath;
    TextView tv_imagePath;
    EditText txt_comments;
    String strDate, strTime;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_post_layout);
        showImage = (ImageView) findViewById(R.id.imageViewPhoto);
        btn_take_photo = (Button)findViewById(R.id.btn_takePhoto);
        btn_upload_post = (Button)findViewById(R.id.btn_addMyPost);
        tv_imagePath = (TextView) findViewById(R.id.txtPath);
        txt_comments = (EditText) findViewById(R.id.txt_comments);

        btn_take_photo.setText("Click To Take Photo");
        askForCamea();
        btn_take_photo.setOnClickListener(new View.OnClickListener()
        {
             @Override
            public void onClick(View view)
            {
                dispatchTakePictureIntent();
            }
        });

        btn_upload_post.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.e("------" ,"User Name  : " + constants.USER_NAME
                + " \n Image Path  :  " + photoPath
                + " \n Date  : " + strDate
                + " \n Time : "  + strTime
                + " \n Comments : "  + txt_comments.getText().toString()
                ) ;

                DatabaseHandler database = new DatabaseHandler(getApplicationContext());
                database.addPost(new Post(database.getPostCount()+1
                        ,constants.USER_NAME
                        ,strTime
                        ,strDate
                        ,photoPath
                        ,txt_comments.getText().toString()
                        ,false
                        ));

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
        strDate = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        strTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        //Log.e("crate Image File", image.toString());
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
            { }
            // Continue only if the File was successfully created
            if (photoFile != null)
            {
                Context context = getApplicationContext();
                Log.e("photofile not null" , photoFile.toString());
                Uri photoURI = FileProvider.getUriForFile(getApplicationContext()
                        ,  BuildConfig.APPLICATION_ID+".fileprovider", photoFile);
                Log.e("     PHoTO  " , "         photofile  : " + photoFile);
                photoPath = photoFile.getPath().toString();
                tv_imagePath.setText(photoPath);
//                Log.e("Paat   " , "Path from photofile"   + "       "+photoPath) ;
                btn_take_photo.setText("Reclick Photo");
                temp_uri = photoURI;
                takePictureIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(photoFile));
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
