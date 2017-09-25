package edu.cmu.pocketsphinx.kit;

/**
 * Created by xiaoyunfei on 2017/7/21.
 */

public interface RecognizerSetupListener {

    void onRecognizerAlreadySetup();

    Exception doInBackGround();

    void onRecognizerPrepareError();

    void onRecognizerPrepareSuccess();
}
