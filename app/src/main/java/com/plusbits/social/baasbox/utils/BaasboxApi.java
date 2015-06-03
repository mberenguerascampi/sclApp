package com.plusbits.social.baasbox.utils;

import android.util.Log;

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
