package com.example.aayat.krypto;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.googlecode.tesseract.android.TessBaseAPI;
public class Image_Process extends AppCompatActivity  {
    private TessBaseAPI mTess;
    String datapath = "";
    EditText OCREdittext;
    public String word;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image__process);
        OCREdittext = (EditText) findViewById(R.id.editText2);


        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("MESSAGE");
        OCREdittext.setText(message);
        OCREdittext.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.mymenu, menu);
                menu.removeItem(android.R.id.selectAll);
                return true;

            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
             //   Toast.makeText(Image_Process.this, "prepare", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
              //  Log.d(TAG, String.format("onActionItemClicked item=%s/%d", item.toString(), item.getItemId()));
                CharacterStyle cs;
                int start = OCREdittext.getSelectionStart();
                int end = OCREdittext.getSelectionEnd();

                //start and end index of substring
                SpannableStringBuilder ssb = new SpannableStringBuilder(OCREdittext.getText());

                switch(item.getItemId()) {

                    case R.id.bold:
                        int start1 = OCREdittext.getSelectionStart();
                        int end1 = OCREdittext.getSelectionEnd();
                        String word = OCREdittext.getText().subSequence(start1,end1).toString();
                        Intent intent = new Intent(getApplicationContext(), text_2_speech.class);
                        intent.putExtra("pronounce_it", word);

                        cs = new StyleSpan(Typeface.BOLD);
                        ssb.setSpan(cs, start, end, 1);
                        OCREdittext.setText(ssb);

                        startActivity(intent);
                        return true;

                    case R.id.italic:
                        cs = new StyleSpan(Typeface.ITALIC);
                        ssb.setSpan(cs, start, end, 1);
                        OCREdittext.setText(ssb);
                        return true;

                    case R.id.underline:
                        cs = new UnderlineSpan();
                        ssb.setSpan(cs, start, end, 1);
                        OCREdittext.setText(ssb);
                        return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                //Toast.makeText(Image_Process.this, "destroy", Toast.LENGTH_SHORT).show();
            }
        });


    }
    }


