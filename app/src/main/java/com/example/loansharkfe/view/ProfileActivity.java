package com.example.loansharkfe.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loansharkfe.R;
import com.example.loansharkfe.controller.implementations.LoanSharkEventsController;
import com.example.loansharkfe.controller.implementations.LoanSharkProfileController;
import com.example.loansharkfe.controller.interfaces.EventsController;
import com.example.loansharkfe.controller.interfaces.ProfileController;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProfileActivity extends AppCompatActivity {

    public ImageView profilePicture;
    public TextView username, email, firstName, lastName;
    private ProfileController profileController;
    private Button viewFriendsList;
    private final int GALLERY_REQ_CODE = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilePicture = findViewById(R.id.pictureProfile);
        username = findViewById(R.id.usernameProfile);
        email = findViewById(R.id.emailProfile);
        firstName = findViewById(R.id.firstNameProfile);
        lastName = findViewById(R.id.lastNameProfile);
        viewFriendsList = findViewById(R.id.viewFriendsListProfile);

        profileController = new LoanSharkProfileController(this);
        try {
            profileController.fillInAllTheFields();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        viewFriendsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileController.startFriendsListActivity();
            }
        });

        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (profilePicture.getDrawable() == null){
                Intent gallery = new Intent(Intent.ACTION_PICK);
                gallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery, GALLERY_REQ_CODE);}
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK){
            if (requestCode==GALLERY_REQ_CODE){

                try {
                    profilePicture.setImageURI(data.getData());

                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    selectedImage.compress(Bitmap.CompressFormat.JPEG,100,baos);
                    byte[] b = baos.toByteArray();
//                    String encImage = Base64.encodeToString(b, Base64.DEFAULT);

                    profileController.updateProfilePicture(b);
                }
                catch (FileNotFoundException exception){
                    exception.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        profileController.startMenuActivity();
    }

}