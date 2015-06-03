package com.plusbits.social;

import android.app.Application;

import com.baasbox.android.BaasBox;

/**
 * Created by Marc on 02/06/2015.
 */
public class CustomApplication extends Application {

        @Override
        public void onCreate() {
            super.onCreate();

            BaasBox.builder(this).setAuthentication(BaasBox.Config.AuthType.SESSION_TOKEN)
                    .setApiDomain("baasbox-plusbits.rhcloud.com")
                    .setPort(80)
                    .setAppCode("1234567890")
                    .init();
//        BaasBox.Config config = new BaasBox.Config();
//        config.authenticationType= BaasBox.Config.AuthType.SESSION_TOKEN;
//        config.apiDomain = "192.168.56.1"; // the host address
//        config.httpPort=9000;
//        BaasBox.initDefault(this,config);
        }

    }
