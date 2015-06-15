package com.plusbits.social.utils.baasbox;

import android.content.Context;
import android.util.Log;

import com.baasbox.android.BaasDocument;
import com.baasbox.android.BaasFile;
import com.baasbox.android.BaasHandler;
import com.baasbox.android.BaasLink;
import com.baasbox.android.BaasQuery;
import com.baasbox.android.BaasResult;
import com.baasbox.android.BaasUser;
import com.baasbox.android.Grant;
import com.baasbox.android.RequestOptions;
import com.baasbox.android.Role;
import com.plusbits.social.interfaces.ApiListener;
import com.plusbits.social.models.Event;

import java.util.ArrayList;
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

    public static void getAllEvents(final ApiListener listener){
        // using pagination and selection
        BaasQuery.Criteria filter = BaasQuery.builder().pagination(0, 20)
                .orderBy("field name")
                .criteria();
        //TODO: Afegir filter com a parametre del fetchall

        BaasDocument.fetchAll(BaasboxConstants.EVENTS_COLLECTION,
                new BaasHandler<List<BaasDocument>>() {
                    @Override
                    public void handle(BaasResult<List<BaasDocument>> res) {
                        if (res.isSuccess()) {
                            ArrayList<Event> events = new ArrayList<Event>();
                            for (BaasDocument doc : res.value()) {
                                //setPublicDocument(doc);
                                events.add(BaasboxParser.parseEvent(doc));
                            }
                            listener.onGetEventsSuccess(events);
                        } else {
                            Log.e("LOG", "Error", res.error());
                            listener.onRequestFail(res.error().getMessage());
                        }
                    }
                });
    }

    /**
     * Permet que un document sigui llegible per a tothom, encara que no estigui registrat
     * @param doc BaasDocument que es vol fer public
     */
    public static void setPublicDocument(BaasDocument doc){
        // assumes doc is an instance of the document
        doc.grantAll(Grant.READ, Role.ANONYMOUS,
                new BaasHandler<Void>() {
                    @Override
                    public void handle(BaasResult<Void> res) {
                        if (res.isSuccess()) {
                            Log.d("LOG","Permission granted");
                        } else {
                            Log.e("LOG","Error",res.error());
                        }
                    }
                });

        // ara fem servir assets que són públics
        /*BaasFile.fetch(doc.getString("imageID"), new BaasHandler<BaasFile>() {
            @Override
            public void handle(BaasResult<BaasFile> res) {
                if (res.isSuccess()) {
                    Log.d("LOG","Your file details"+res);
                    BaasFile file = res.value();
                } else {
                    Log.e("LOG","Deal with eror",res.error());
                }
            }
        });*/
    }

    private void setPublicFile(BaasFile file){
        file.grant(Grant.READ, Role.ANONYMOUS,new BaasHandler<Void>(){
            @Override
            public void handle(BaasResult<Void> res){
                if (res.isSuccess()) {
                    Log.d("LOG","Everybody can read the file");
                } else {
                    Log.e("LOG","deal with error",res.error());
                }
            }
        });
    }

    private void createLinkFromDocumentToImage(String strDoc, String strImage){
        BaasLink.create(strDoc, "image-id", strImage, RequestOptions.DEFAULT, new BaasHandler<BaasLink>() {
            @Override
            public void handle(BaasResult<BaasLink> res) {
                if (res.isSuccess()) {
                    BaasLink value = res.value();
                    Log.d("TAG", "value is your new link");
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
