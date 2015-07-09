package com.plusbits.social.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.koushikdutta.ion.Ion;
import com.plusbits.social.R;
import com.plusbits.social.models.Event;
import com.plusbits.social.utils.Constants;
import com.plusbits.social.utils.FontsUtils;
import com.plusbits.social.utils.baasbox.BaasboxApi;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_event_detail)
public class EventDetailActivity extends AppCompatActivity implements ObservableScrollViewCallbacks {
    @ViewById
    ImageView ivEventDetail;

    @ViewById
    TextView tvNameEvent, tvDateEvent, tvLocationEvent, tvDescriptionEvent;

    @ViewById
    Button btnSetPublicEvent;

    @ViewById
    ObservableScrollView eventObservableScrollView;

    @ViewById
    ProgressBar validate_event_loading;

    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    //EVENTS
    @Click
    void btnSetPublicEvent(){
        BaasboxApi.setPublicEvent(event.getId(), validate_event_loading);
    }


    //PRIVATE METHODS

    @AfterViews
    void fillEventDetails(){
        if(event == null) return;
        eventObservableScrollView.setScrollViewCallbacks(this);
        if(Constants.VERSION != Constants.ADMIN_VERSION || event.isValidated()) btnSetPublicEvent.setVisibility(View.GONE);
        tvNameEvent.setText(event.getName());
        FontsUtils.setFont("roboto/Roboto-Medium", tvNameEvent, getApplicationContext());
        tvDateEvent.setText(event.getDate());
        tvLocationEvent.setText(event.getLocation());
        tvDescriptionEvent.setText(event.getDescription());
        Ion.with(ivEventDetail)
                .placeholder(R.drawable.placeholder_image)
                //.error(R.drawable.error_image)
                //.animateLoad(spinAnimation)
                //.animateIn(fadeInAnimation)
                .load(event.getImageURL());
    }

    //ObservableScrollViewCallback Implements
    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll,
                                boolean dragging) {
    }

    @Override
    public void onDownMotionEvent() {
        /*ActionBar ab = getSupportActionBar();
        if (ab.isShowing()) {
            ab.hide();
        }*/
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        ActionBar ab = getSupportActionBar();
        if (scrollState == ScrollState.UP) {
            if (ab.isShowing()) {
                ab.hide();
            }
        } else if (scrollState == ScrollState.DOWN) {
            if (!ab.isShowing()) {
                ab.show();
            }
        }
    }
}
