package com.example.contactotherversion;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    ImageView iv;
    String[] pic;
    String name;
    int rand = 0, soundID = R.raw.mario;
    MediaPlayer mp;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Przypisuje do zmiennej tv, TextView z XML po id
        tv = findViewById(R.id.tv);
        iv = findViewById(R.id.iv);

        //ustawiam tekst w TextView
        tv.setText("Choose contact");

        //do zmiennej pic przypisuje tablice zdefiniowana w string.xml
        pic = getResources().getStringArray(R.array.pictures);
        uri = Uri.parse(pic[0]); //zamieniam sciezke string zdefiniowaną w tablicy do zmiennej uri
        //ustawiam obraz za pomoca uri
        iv.setImageResource(getApplicationContext().getResources().getIdentifier
                ("" + uri, null, getApplicationContext().getPackageName()));

        //Toast.makeText(getApplicationContext(), ""+uri, Toast.LENGTH_LONG).show();
        //iv.setImageResource(R.drawable.avatar_1);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //jesli mój multiplayer jest włączony po wciśnięciu przycisku wyłacz mediaplayer i ustaw mp na null
                //jeśli mój mediaplayer jest wyłączony po wciśnięciu przycisku włącz mediaplayer
                if(mp != null){
                    mp.stop();
                    mp = null;
                }else{
                    //podaj soundtrack za pomocą id zwróconego z klasy Sounds
                    mp = MediaPlayer.create(getApplicationContext(), soundID);
                    mp.start();
                    Snackbar.make(view, "playing...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //przejście do aktywności kontakt
    public void contact(View view) {
        Intent intent = new Intent(getApplicationContext(), Contact.class);
        startActivityForResult(intent, 1);
    }

    //przejście do aktywności sound 
    public void sound(View view) {
        Intent intent = new Intent(getApplicationContext(), Sound.class);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //sprawdzam co zwróciło mi startActivityforresult() i względem resultCode i requestCode wybieram odpowiedznią opcję
        if(resultCode == RESULT_OK){
            if(requestCode == 1){
                name = data.getStringExtra("contact");
                //ustawiam w TextView wybraną nazwę
                tv.setText(name); 
                //losuje obrazek
                choosePic();
            }
            else if(requestCode == 2){
                //nadaje soundID zmienna zwróconą z klasy sound
                soundID = data.getIntExtra("sound", 0);
            }
        }else{
            Toast.makeText(getApplicationContext(), "I'm BACK", Toast.LENGTH_LONG).show();
        }
    }

    //losowanie obrazu
    public void choosePic(){
        Random r = new Random();
        rand = r.nextInt(5);

        uri = Uri.parse(pic[rand]);
        iv.setImageResource(getApplicationContext().getResources().getIdentifier
                ("" + uri, null, getApplicationContext().getPackageName()));

        //iv.setImageDrawable(Drawable.createFromPath(pic[rand]));
    }
}
