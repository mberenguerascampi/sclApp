package com.plusbits.social.utils.baasbox;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.baasbox.android.BaasBox;
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
import com.baasbox.android.SaveMode;
import com.baasbox.android.json.JsonObject;
import com.baasbox.android.net.HttpRequest;
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
        BaasUser.fetch(username, new BaasHandler<BaasUser>() {
            @Override
            public void handle(BaasResult<BaasUser> res) {
                if (res.isSuccess()) {
                    BaasUser user = res.value();
                    Log.d("LOG", "The user: " + user);
                } else {
                    Log.e("LOG", "Error", res.error());
                }
            }
        });
    }

    /**
     * Mètode que retorna tots els esdeveniments visibles per l'usuari
     * @param listener Listener que es crida un cop s'ha acabat de cridar l'api de baasbox
     * @param loadingView Vista que es mostrarà únicament mentre s'estigui realitzan la crida
     */
    public static void getAllEvents(final ApiListener listener, final View loadingView){
        if(loadingView != null) loadingView.setVisibility(View.VISIBLE);
        // using pagination and selection
        BaasQuery.Criteria filter = BaasQuery.builder().pagination(0, 20)
                .orderBy("field name")
                .criteria();
        //TODO: Afegir filter com a parametre del fetchall

        BaasDocument.fetchAll(BaasboxConstants.EVENTS_COLLECTION, new BaasHandler<List<BaasDocument>>() {
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
                if (loadingView != null) loadingView.setVisibility(View.GONE);
            }
        });
    }

    /**
     * Permet que un esdeveniment sigui llegible per a tothom, encara que no estigui registrat
     * @param eventID Id de l'event que es vol fer públic
     */
    public static void setPublicEvent(String eventID, final View loadingView){
        if(loadingView != null) loadingView.setVisibility(View.VISIBLE);

        BaasBox box = BaasBox.getDefault();
        box.rest(HttpRequest.POST,
                "plugin/event.validate",
                new JsonObject().put("eventId", eventID),
                true,
                new BaasHandler<JsonObject>() {
                    @Override
                    public void handle(BaasResult<JsonObject> res) {
                        Log.d("TAG","Ok: " + res.isSuccess());
                        if (res.isSuccess()) {
                            Log.d("LOG", "validate success");
                        } else {
                            Log.e("LOG", "error", res.error());
                        }
                        if (loadingView != null) loadingView.setVisibility(View.GONE);
                    }
                });
    }

/*
    ARA ES FA EN LA PART DEL SERVIDOR, NOMÉS CAL CRIDAR EL PLUGIN

    public static void setPublicDocument(final BaasDocument doc, final View loadingView){
        // assumes doc is an instance of the document
        doc.grantAll(Grant.READ, Role.ANONYMOUS,
                new BaasHandler<Void>() {
                    @Override
                    public void handle(BaasResult<Void> res) {
                        if (res.isSuccess()) {
                            Log.d("LOG", "Permission granted");
                            updateValidatedDocument(doc, loadingView);
                        } else {
                            Log.e("LOG", "Error", res.error());
                        }
                    }
                });
    }

    private static void updateValidatedDocument(BaasDocument doc, final View loadingView){
        doc.put("validated", true);
        doc.save(SaveMode.IGNORE_VERSION, new BaasHandler<BaasDocument>() {
            @Override
            public void handle(BaasResult<BaasDocument> res) {
                if (res.isSuccess()) {
                    Log.d("LOG", "Document saved " + res.value().getId());
                } else {
                    Log.e("LOG", "Error", res.error());
                }
                if (loadingView != null) loadingView.setVisibility(View.GONE);
            }
        });
    }
*/

    private void setPublicFile(BaasFile file){
        file.grant(Grant.READ, Role.ANONYMOUS, new BaasHandler<Void>() {
            @Override
            public void handle(BaasResult<Void> res) {
                if (res.isSuccess()) {
                    Log.d("LOG", "Everybody can read the file");
                } else {
                    Log.e("LOG", "deal with error", res.error());
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
