package com.plusbits.social.utils.baasbox;

import com.baasbox.android.BaasDocument;
import com.plusbits.social.models.Event;

/**
 * Created by Marc on 09/06/2015.
 */
public class BaasboxParser {
    public static Event parseEvent(BaasDocument doc){
        String name = doc.getString("name");
        String date = doc.getString("date");
        String desc = doc.getString("description");
        String location = doc.getString("location");
        String imageURL = "http://" + BaasboxConstants.BAASBOX_URL + "/file/" + doc.getString("imageID") +
                            "?X-BAASBOX-APPCODE=" + BaasboxConstants.BAASBOX_APP_CODE;


        return new Event(doc.getId(), name, date, desc, location, imageURL);
    }
}
