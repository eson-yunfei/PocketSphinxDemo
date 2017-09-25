package edu.cmu.pocketsphinx;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import edu.cmu.pocketsphinx.kit.RecognizerSetupListener;

/**
 * Created by xiaoyunfei on 2017/6/14.
 */

public class PocketSphinxService extends Service {

    private static final String TAG = "PocketSphinxService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        PocketSphinxUtil.init(this);

        PocketSphinxUtil.get().runRecognizerSetup(new RecognizerSetupListener() {
            @Override
            public void onRecognizerAlreadySetup() {

            }

            @Override
            public Exception doInBackGround() {
                return null;
            }

            @Override
            public void onRecognizerPrepareError() {

                Log.e(TAG, "onRecognizerPrepareError()");
            }

            @Override
            public void onRecognizerPrepareSuccess() {
                Log.e(TAG, "onRecognizerPrepareSuccess()");
            }
        });
    }
}
