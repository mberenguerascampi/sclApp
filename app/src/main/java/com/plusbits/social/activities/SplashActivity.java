package com.plusbits.social.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;
import com.plusbits.social.R;
import com.plusbits.social.utils.Constants;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_splah)
public class SplashActivity extends ActionBarActivity {
    @ViewById
    ImageView ivSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start main activity
                Intent i = null;
                if(Constants.VERSION == Constants.USER_VERSION){
                    i = new Intent(SplashActivity.this, MainActivity.class);
                }
                else{
                    i = new Intent(SplashActivity.this, LoginActivity.class);
                }
                startActivity(i);

                // close this activity
                finish();
            }
        }, getResources().getInteger(R.integer.SPLASH_TIME));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash_menu, menu);
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

    @AfterViews
    void loadSplashImage(){
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.loading_img);
        Ion.with(ivSplash).load(uri.toString());
    }
}
