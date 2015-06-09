package com.plusbits.social.utils.baasbox;

import android.util.Log;

import com.baasbox.android.BaasDocument;
import com.baasbox.android.BaasHandler;
import com.baasbox.android.BaasQuery;
import com.baasbox.android.BaasResult;
import com.baasbox.android.BaasUser;

import java.util.List;

/**
 * Created by Marc on 03/06/2015.
 */
public class BaasboxApi {


    public void getAllUsers(){
        BaasQuery.Criteria filter = BaasQuery.builder()
                .pagination(0,2)
                .orderBy("user.name")
                .criteria();
        getUsers(filter);
    }

    public void getUserProfile(String username){
        BaasUser.fetch(username,new BaasHandler<BaasUser>() {
            @Override
            public void handle(BaasResult<BaasUser> res) {
                if(res.isSuccess()){
                    BaasUser user = res.value();
                    Log.d("LOG","The user: "+user);
                } else {
                    Log.e("LOG","Error",res.error());
                }
            }
        });
    }

    public void getAllEvents(){
        // using pagination and selection
        BaasQuery.Criteria filter = BaasQuery.builder().pagination(0, 20)
                .orderBy("field name")
                .criteria();

        BaasDocument.fetchAll(BaasboxConstants.EVENTS_COLLECTION, filter,
                new BaasHandler<List<BaasDocument>>() {
                    @Override
                    public void handle(BaasResult<List<BaasDocument>> res) {
                        if (res.isSuccess()) {
                            for (BaasDocument doc : res.value()) {
                                Log.d("LOG", "Doc: " + doc);
                            }
                        } else {
                            Log.e("LOG", "Error", res.error());
                        }
                    }
                });
    }

    /** PRIVATE METHODS **/

    private void getUsers(BaasQuery.Criteria filter){
        BaasUser.fetchAll(filter,new BaasHandler<List<BaasUser>>() {
            @Override
            public void handle(BaasResult < List <BaasUser>> res) {
                if(res.isSuccess()) {
                    for(BaasUser u: res.value()) {
                        Log.d("LOG", "The user is: " + u.getName());
                    }
                } else {
                    Log.e("LOG","error");
                }
            }
        });
    }
}
