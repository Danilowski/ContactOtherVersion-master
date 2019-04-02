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

        //nadaję zmiennej spinner id z XML
        sp = findViewById(R.id.spinner);

        //zmiennej contacts przypisuję tablice z string.xml
        contacts = getResources().getStringArray(R.array.contacts);
        //określam wygląd spinnera
        aa = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item, contacts);
        //przypisuję wygląd spinnera
        sp.setAdapter(aa);
    }

    //jeśli wcisnę cancel wróć do poprzedniej aktywności
    public void cancel(View view) {
        finish();
    }

    //jeśli wcisnę przycisk ok
    public void ok(View view) {
        //przypisuje do zmiennej pos posycję itemu ze spinnera 
        pos = sp.getSelectedItemPosition();
        //do zmiennej result przypisuję z tablicy contact o indeksie pos
        result = contacts[pos];
        Intent intent = new Intent();
        intent.putExtra("contact", result);
        setResult(RESULT_OK, intent);
        finish();
    }
}
