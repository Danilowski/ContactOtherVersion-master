package com.example.contactotherversion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Contact extends AppCompatActivity {

    String[] contacts;
    ArrayAdapter<String> aa;
    Spinner sp;
    int pos = 0;
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        sp = findViewById(R.id.spinner);

        contacts = getResources().getStringArray(R.array.contacts);
        aa = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item, contacts);
        sp.setAdapter(aa);

        /*sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
    }

    public void cancel(View view) {
        finish();
    }

    public void ok(View view) {
        pos = sp.getSelectedItemPosition();
        result = contacts[pos];
        Intent intent = new Intent();
        intent.putExtra("contact", result);
        setResult(RESULT_OK, intent);
        finish();
    }
}
