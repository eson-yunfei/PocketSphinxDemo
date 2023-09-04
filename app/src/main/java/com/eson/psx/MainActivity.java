package com.eson.psx;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import edu.cmu.pocketsphinx.PocketListener;
import edu.cmu.pocketsphinx.PocketSphinxService;
import edu.cmu.pocketsphinx.PocketSphinxUtil;
import edu.cmu.pocketsphinx.kit.RecognizerSetupListener;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent serviceIntent;
    private PocketSphinxUtil pocketSphinxUtil;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.request_permission).setOnClickListener(this);
        findViewById(R.id.start_service).setOnClickListener(this);
        findViewById(R.id.start_record).setOnClickListener(this);
        findViewById(R.id.stop_record).setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.request_permission:
                onRecordAudioGranted();
                break;
            case R.id.start_service:
                if (hasRecordAudioPermission()) {
                    startPocketSphinxService();
                } else {
                    Toast.makeText(this, "请先申请权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.start_record:
                if (serviceIntent == null) {
                    Toast.makeText(this, "请先开启服务", Toast.LENGTH_SHORT).show();
                } else {
                    new Handler(Looper.getMainLooper())
                            .postDelayed(this::forTest, 5_000);
                }
                break;
            case R.id.stop_record:
                if (pocketSphinxUtil != null) {
                    pocketSphinxUtil.stopRecord();
                }
                break;
            default:
                break;
        }
    }

    private boolean hasRecordAudioPermission() {
        return EasyPermissions.hasPermissions(this, Manifest.permission.RECORD_AUDIO);
    }

    @AfterPermissionGranted(100)
    private void onRecordAudioGranted() {
        if (hasRecordAudioPermission()) {
            Toast.makeText(this, "已获取录音权限", Toast.LENGTH_SHORT).show();
        }else {
            EasyPermissions.requestPermissions(new PermissionRequest.Builder(this, 100, Manifest.permission.RECORD_AUDIO)
                    .build());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }

    private void startPocketSphinxService() {
        serviceIntent = new Intent();
        serviceIntent.setClass(this, PocketSphinxService.class);
        startService(serviceIntent);
        Toast.makeText(this,"PocketSphinxService 启动成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (pocketSphinxUtil != null) {
            pocketSphinxUtil.stopRecord();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (serviceIntent != null) {
            stopService(serviceIntent);
        }
    }


    void forTest() {
        pocketSphinxUtil = PocketSphinxUtil.get();
        if (pocketSphinxUtil == null) {
            return;
        }
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

        pocketSphinxUtil.startRecord("zh_test", new PocketListener() {
            @Override
            public void onSpeechStart() {

            }

            @Override
            public void onSpeechResult(List<String> strings) {
                Log.d("MainActivity", "find result =" + strings);
                if (strings == null) {
                    return;
                }
                for (String string : strings) {
                    Log.d("MainActivity", "find string =" + string);
                }


            }

            @Override
            public void onSpeechError(String error) {

            }
        });
    }

}
