package com.grishberg.mvvmstatelessexample.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.grishberg.mvvmstatelessexample.R;

/**
 * Created by grishberg on 26.02.17.
 */

public class SprintInfoActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, SprintInfoActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sprint_info_activity);
    }
}
