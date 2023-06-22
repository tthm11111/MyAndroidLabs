package ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import algonquin.cst2335.chen0914.R;
import algonquin.cst2335.chen0914.databinding.ActivitySecondBinding;



public class SecondActivity extends AppCompatActivity {

   ActivitySecondBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);
        Intent fromPrevious = getIntent();
        String emailAddress = getIntent().getStringExtra("emailAddress");
        TextView emailTextView = findViewById(R.id.textView3);
        emailTextView.setText("Welcome back, " + emailAddress);

        File file = new File( getFilesDir(), "Picture.png");

        if(file.exists()) {
            Bitmap theImage = BitmapFactory.decodeFile(file.getAbsolutePath());
            ImageView profileView = findViewById(R.id.profileImage);
            profileView.setImageBitmap(theImage);
        }

        Button button2 = findViewById(R.id.callNumber);
        button2.setOnClickListener((v) -> {
            Intent call = new Intent(Intent.ACTION_DIAL);
            EditText phoneNumber = findViewById(R.id.phoneNumber);
            String phoneNumberString = phoneNumber.getText().toString();
            call.setData(Uri.parse("tel:" + phoneNumberString));
            startActivity(call);
        });



        ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
//                        Log.e(TAG, "You clicked the camera");
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            FileOutputStream fOut = null;

                            Intent data = result.getData();
                            Bitmap thumbnail = data.getParcelableExtra("data");

                            ImageView profileImage = findViewById(R.id.profileImage);
                            profileImage.setImageBitmap(thumbnail);
                            try { fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);
                                thumbnail.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                                fOut.flush();
                                fOut.close();
                            }
                            catch (FileNotFoundException e)
                            { e.printStackTrace();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
        //set camera listener
        Button changePicture = findViewById(R.id.changePicture);
        changePicture.setOnClickListener((v)-> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraResult.launch(cameraIntent);
        });
    }
    }
