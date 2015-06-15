package com.plusbits.social.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Marc on 14/06/2015.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView image;

    public DownloadImageTask(ImageView image) {
        this.image = image;
    }

    protected Bitmap doInBackground(String... urls) {
        String urlImage = urls[0];
        Log.d("DownloadImageTask", urlImage);
        Bitmap bmIcon = null;
        try {
            InputStream in = new java.net.URL(urlImage).openStream();
            bmIcon = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bmIcon;
    }

    protected void onPostExecute(Bitmap result) {
        image.setImageBitmap(result);
    }
}
