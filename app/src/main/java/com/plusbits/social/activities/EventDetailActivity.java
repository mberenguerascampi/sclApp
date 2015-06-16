package com.plusbits.social.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.plusbits.social.R;
import com.plusbits.social.models.Event;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_event_detail)
public class EventDetailActivity extends ActionBarActivity {
    @ViewById
    ImageView ivEventDetail;

    @ViewById
    TextView tvNameEvent, tvDateLocationEvent, tvDescriptionEvent;

    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        event = (Event)bundle.getSerializable("EVENT");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_detail, menu);
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


    //PRIVATE METHODS

    @AfterViews
    void fillEventDetails(){
        if(event == null) return;
        tvNameEvent.setText(event.getName());
        tvDateLocationEvent.setText(event.getDate() + " " + event.getLocation());
        tvDescriptionEvent.setText(event.getDescription());
        Ion.with(ivEventDetail)
                //.placeholder(R.drawable.placeholder_image)
                //.error(R.drawable.error_image)
                //.animateLoad(spinAnimation)
                //.animateIn(fadeInAnimation)
                .load(event.getImageURL());
    }
}
