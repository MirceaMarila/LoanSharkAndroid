package com.example.loansharkfe.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.loansharkfe.R;

public class SubEventPageActivity extends AppCompatActivity {

    public TextView name;
    public TextView description;
    public TextView admin;
    public TextView members;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_event_page);
        name = findViewById(R.id.nameSubEventPage);
        description = findViewById(R.id.descriptionSubEventPage);
        admin = findViewById(R.id.adminSubEventPage);
        members = findViewById(R.id.membersSubEventPage);
    }
}