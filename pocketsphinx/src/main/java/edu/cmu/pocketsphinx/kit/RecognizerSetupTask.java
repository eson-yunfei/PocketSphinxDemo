package edu.cmu.pocketsphinx.kit;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by xiaoyunfei on 2017/7/21.
 */

public class RecognizerSetupTask extends AsyncTask<Void, Void, Exception> {

    private static final String TAG = "RecognizerSetupTask";
    private RecognizerSetupListener recognizerSetupListener;

    public RecognizerSetupTask(RecognizerSetupListener recognizerSetupListener) {
        this.recognizerSetupListener = recognizerSetupListener;
    }

    @Override
    protected Exception doInBackground(Void... voids) {
        if (recognizerSetupListener != null) {
            return recognizerSetupListener.doInBackGround();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Exception result) {
        setResult(result);
    }

    private void setResult(Exception result) {
        if (result != null) {

            if (recognizerSetupListener != null) {
                recognizerSetupListener.onRecognizerPrepareError();
            }
            Log.e(TAG, "Failed to init recognizer" + result);
        } else {
            if (recognizerSetupListener != null) {
                recognizerSetupListener.onRecognizerPrepareSuccess();
            }
            Log.e(TAG, "Success to init recognizer");
        }
    }
}
