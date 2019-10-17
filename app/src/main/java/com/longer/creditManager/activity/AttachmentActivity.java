package com.longer.creditManager.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;



import com.longer.creditManager.R;
import com.longer.creditManager.util.AndroidBug54971Workaround;


/**
 * 项目附件
 *
 */
public class AttachmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_attachment);
        AndroidBug54971Workaround.assistActivity(findViewById(android.R.id.content),this);


    }
}
