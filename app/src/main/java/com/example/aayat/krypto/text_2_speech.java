 package com.example.aayat.krypto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Locale;

public class text_2_speech extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private Button btnSpeak;
    private EditText txtText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_2_speech);

        tts = new TextToSpeech(this, this);

        btnSpeak = (Button)findViewById(R.id.button);

        txtText = (EditText) findViewById(R.id.editText3);

        Bundle bundle=getIntent().getExtras();
        String myword=bundle.getString("pronounce_it");
        String nothing=bundle.getString("nothing");
        txtText.setText(myword);
        txtText.clearFocus();

        txtText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayKeyboard();
            }
        });


        txtText.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        txtText.setTextIsSelectable(true);

        // button on click event
        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                speakOut();
            }

        });
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(final int status) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub

                if (status == TextToSpeech.SUCCESS) {

                    int result = tts.setLanguage(Locale.US);

                    tts.setPitch(1); // set pitch level

                    tts.setSpeechRate(1); // set speech speed rate

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language is not supported");
                    } else {
                        btnSpeak.setEnabled(true);
                        speakOut();
                    }

                } else {
                    Log.e("TTS", "Initilization Failed");
                }
            }
        }).start();

    }

    private void speakOut() {

        try {


            String text = txtText.getText().toString();
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }catch(Exception e)
        {
            Toast.makeText(this, "Enter some word", Toast.LENGTH_SHORT).show();
        }
    }


    //keyboard show//
    private void displayKeyboard()
    {
        if(txtText!=null)
        {
            InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInputFromWindow(txtText.getApplicationWindowToken(),InputMethodManager.SHOW_FORCED,0);
        }
    }
}





