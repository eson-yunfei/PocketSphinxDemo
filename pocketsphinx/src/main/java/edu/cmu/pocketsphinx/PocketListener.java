package edu.cmu.pocketsphinx;

import java.util.List;

/**
 * Created by xiaoyunfei on 2017/6/14.
 */

public interface PocketListener {
    String SEARCH = "zh_test";

//    void onPrepare();

//    void onPrepareError();

//    void onReady();

    void onSpeechStart();

    void onSpeechResult(List<String> strings);

    void onSpeechError(String error);
}
