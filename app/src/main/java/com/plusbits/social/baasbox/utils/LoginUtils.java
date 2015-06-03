package com.plusbits.social.baasbox.utils;

import android.util.Log;

import com.baasbox.android.BaasHandler;
import com.baasbox.android.BaasResult;
import com.baasbox.android.BaasUser;
import com.baasbox.android.RequestToken;
import com.plusbits.social.interfaces.LoginListener;

/**
 * Created by Marc on 02/06/2015.
 */
public class LoginUtils {
    private RequestToken mSignupOrLogin;
    private LoginListener listener;

    private final BaasHandler<BaasUser> onComplete =
            new BaasHandler<BaasUser>() {
                @Override
                public void handle(BaasResult<BaasUser> result) {

                    mSignupOrLogin = null;
                    if (result.isFailed()){
                        Log.d("ERROR", "ERROR", result.error());
                    }
                    if(listener != null)listener.onCompleteLogin(result.isSuccess());
                }
            };

    /**
     * Sign Up or login using baasbox's database
     * @param mUsername String with the username
     * @param mPassword String with the password
     * @param newUser True if it is a new user
     */
    public void signupWithBaasBox(String mUsername, String mPassword,  boolean newUser){
        BaasUser user = BaasUser.withUserName(mUsername);
        user.setPassword(mPassword);
        if (newUser) {
            mSignupOrLogin=user.signup(onComplete);
        } else {
            mSignupOrLogin=user.login(onComplete);
        }
    }

    public void logoutCurrentUser(){
        BaasUser.current().logout(new BaasHandler<Void>() {
            @Override
            public void handle(BaasResult<Void> result) {
                if(result.isSuccess()) {
                    Log.d("LOG", "Logged out: "+(BaasUser.current() == null));
                } else{
                    Log.e("LOG","Show error",result.error());
                }
            };
        });
    }

    //Getters & setters
    public RequestToken getmSignupOrLogin() {
        return mSignupOrLogin;
    }

    public void setmSignupOrLogin(RequestToken mSignupOrLogin) {
        this.mSignupOrLogin = mSignupOrLogin;
    }

    public LoginListener getListener() {
        return listener;
    }

    public void setListener(LoginListener listener) {
        this.listener = listener;
    }
}
