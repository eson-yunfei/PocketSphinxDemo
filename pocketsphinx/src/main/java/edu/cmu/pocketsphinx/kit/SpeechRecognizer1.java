package edu.cmu.pocketsphinx.kit;


import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

import java.io.IOException;

import edu.cmu.pocketsphinx.Config;
import edu.cmu.pocketsphinx.SpeechRecognizer;

/**
 * Created by xiaoyunfei on 2017/8/1.
 */

public class SpeechRecognizer1 extends SpeechRecognizer {

    protected SpeechRecognizer1(Config config) throws IOException {
        super(config);
    }
}
