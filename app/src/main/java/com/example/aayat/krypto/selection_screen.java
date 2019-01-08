package com.example.aayat.krypto;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;
public class selection_screen extends AppCompatActivity {
    ImageView im1;
    ImageView im2;
    ImageView im3;
    Button b1,b2,b3;
    CardView cCam,cMicro,cSound,chelp;

    private static final int PERMS_REQUEST_CODE= 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_selection);
        //for permissions
        if(!haspermissions())
            requestPerms();



//
//
//        b1 = (Button)findViewById(R.id.button3);
//        b2 = (Button)findViewById(R.id.button4);
//        b3 = (Button)findViewById(R.id.button5);

        cCam= (CardView)findViewById(R.id.card_view_camera);
        cMicro= (CardView)findViewById(R.id.card_view_microphone);
        cSound= (CardView)findViewById(R.id.card_view_sound);
        chelp= (CardView)findViewById(R.id.card_view_help);



        cCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

// mainactivity instead of capture activity
                Intent i1 = new Intent(selection_screen.this,MainActivity.class);
                startActivity(i1);
            }
        });

      cSound.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String s="";
              Intent i2=new Intent(selection_screen.this,text_2_speech.class);
              i2.putExtra("nothing",s);
              startActivity(i2);
          }
      });

        cMicro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(selection_screen.this, speech_2_text.class);
                startActivity(i3);
            }
        });
    }


    public boolean haspermissions()
    {
        int res=0;
        String[] permissions= new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};
        for(String perms: permissions)
        {
            res=checkCallingOrSelfPermission(perms);
            if(!(res==PackageManager.PERMISSION_GRANTED))
            {
                return false;
            }
        }
        return true;
    }
    public void requestPerms()
    {
        String[] permissions=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
         requestPermissions(permissions,PERMS_REQUEST_CODE);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean allowed=true;
        switch(requestCode)
        {
            case PERMS_REQUEST_CODE:
                for(int res:grantResults)
                    allowed=allowed&&(res==PackageManager.PERMISSION_GRANTED);
                    break;
            default:
                allowed=false;
                break;


        }

        if(!allowed) Toast.makeText(this, "You don't have the required permissions. Go to Settings>apps>Krypto->App Permissions", Toast.LENGTH_SHORT).show();
    }


}

