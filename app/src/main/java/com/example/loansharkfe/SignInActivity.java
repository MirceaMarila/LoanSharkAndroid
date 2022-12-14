package com.example.loansharkfe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    EditText username_or_email, password;
    Button sign_in, sign_up;
    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        username_or_email = findViewById(R.id.username_signin);
        password = findViewById(R.id.password_signin);
        sign_in = findViewById(R.id.button_signin_signin);
        sign_up = findViewById(R.id.button_signup_signin);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignInActivity.this,"El mucho grande nonimplementando !",Toast.LENGTH_SHORT).show();
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_username=username_or_email.getText().toString();
                String txt_password=password.getText().toString();

                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                if(txt_username.contains("@")){
                    intent.putExtra("username", txt_username);
                    intent.putExtra("mail", "");
                }
                else {
                    intent.putExtra("username", txt_username);
                    intent.putExtra("mail", txt_username);
                }

                intent.putExtra("password", txt_password);
                startActivityForResult(intent , REQUEST_CODE);
            }
        });
    }
}