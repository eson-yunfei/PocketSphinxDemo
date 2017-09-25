package com.eson.psx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import edu.cmu.pocketsphinx.PocketListener;
import edu.cmu.pocketsphinx.PocketSphinxService;
import edu.cmu.pocketsphinx.PocketSphinxUtil;
import edu.cmu.pocketsphinx.kit.RecognizerSetupListener;

public class MainActivity extends AppCompatActivity {

    private Intent serviceIntent;
    private PocketSphinxUtil pocketSphinxUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceIntent = new Intent();
        serviceIntent.setClass(this, PocketSphinxService.class);
        startService(serviceIntent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(serviceIntent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        pocketSphinxUtil = PocketSphinxUtil.get();

        pocketSphinxUtil.runRecognizerSetup(new RecognizerSetupListener() {
            @Override
            public void onRecognizerAlreadySetup() {

            }

            @Override
            public Exception doInBackGround() {
                return null;
            }

            @Override
            public void onRecognizerPrepareError() {

            }

            @Override
            public void onRecognizerPrepareSuccess() {

            }
        });

        pocketSphinxUtil.startRecord("", new PocketListener() {
            @Override
            public void onSpeechStart() {

            }

            @Override
            public void onSpeechResult(List<String> strings) {

            }

            @Override
            public void onSpeechError(String error) {

            }
        });
    }
}
