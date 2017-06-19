package com.environer.myandroidlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeShowingActivity extends AppCompatActivity {
    public static final String JOKE_INTENT = "JOKE_INTENT";

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_showing);
        String retrivedJoke = getIntent().getStringExtra(JOKE_INTENT);
        textView = (TextView)findViewById(R.id.jokesTextView);
        textView.setText(retrivedJoke);
    }
}
