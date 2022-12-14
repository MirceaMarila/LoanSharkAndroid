package com.example.loansharkfe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class SignUpActivity extends AppCompatActivity {

    EditText email, username, password, first_name, last_name;
    Button sign_up;
    RequestMaker requestMaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = findViewById(R.id.email_signup);
        username = findViewById(R.id.username_signup);
        password = findViewById(R.id.password_signup);
        first_name = findViewById(R.id.firstname_signup);
        last_name = findViewById(R.id.lastname_signup);
        sign_up = findViewById(R.id.button_signup_sigup);

        String saved_username = getIntent().getStringExtra("username");
        String saved_mail = getIntent().getStringExtra("mail");
        String saved_password = getIntent().getStringExtra("password");

        if (!saved_mail.isEmpty())
            username.setText(saved_mail);

        if (!saved_username.isEmpty())
            email.setText(saved_username);

        if (!saved_password.isEmpty())
            password.setText(saved_password);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                requestMaker.getData(SignUpActivity.this, "https://run.mocky.io/v3/85cf9aaf-aa4f-41bf-b10c-308f032f7ccc");

                requestMaker = new RequestMaker(SignUpActivity.this, "http://192.168.0.137:8080/api/v1/auth/register", "email,username,password,firstName,lastName", email.getText().toString() + "," + username.getText().toString() + "," + password.getText().toString() + "," + first_name.getText().toString() + "," + last_name.getText().toString());
                requestMaker.postData();
            }
        });

    }
}