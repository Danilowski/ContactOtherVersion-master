package com.example.contactotherversion;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Sound extends AppCompatActivity {

    RadioGroup rg;
    RadioButton rb1, rb2, rb3, rb4, rb5, rb6;
    boolean checked = false;
    int soundID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        rg = findViewById(R.id.rg);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);
        rb6 = findViewById(R.id.rb6);

        rb1.setText("Sound 1");
        rb2.setText("Sound 2");
        rb3.setText("Sound 3");
        rb4.setText("Sound 4");
        rb5.setText("Sound 5");
        rb6.setText("Sound 6");
    }

    public void cancel(View view) {
        finish();
    }

    public void ok(View view) {
        if(checked) {
            Intent i = new Intent();
            i.putExtra("sound", soundID);
            setResult(RESULT_OK, i);
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Select sound", Toast.LENGTH_LONG).show();
        }
    }

    public void choose(View view) {
        checked = ((RadioButton) view).isChecked();

        if(checked) {
            switch (view.getId()) {
                case R.id.rb1:
                    soundID = R.raw.mario;
                    break;
                case R.id.rb2:
                    soundID = R.raw.ring01;
                    break;
                case R.id.rb3:
                    soundID = R.raw.ring02;
                    break;
                case R.id.rb4:
                    soundID = R.raw.ring03;
                    break;
                case R.id.rb5:
                    soundID = R.raw.ring04;
                    break;
                case R.id.rb6:
                    soundID = R.raw.ringd;
                    break;
            }
        }
    }
}
