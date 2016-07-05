package com.busylee.devpanelsample;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.busylee.devpanel.DevPanel;
import com.busylee.devpanel.info.ButtonInfo;
import com.busylee.devpanel.info.preferences.IntPreferenceInfo;
import com.busylee.devpanel.mutable.BooleanMutable;
import com.busylee.devpanel.mutable.SetStringMutableEntry;

import java.util.HashSet;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/**
 * Created by busylee on 23.10.15.
 */
public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DevPanel.init(this);
        // simple object info
        DevPanel.info().simple(getString(R.string.app_name)).title("App name").add();
        // preference info
        DevPanel.info().pref().title("Int pref example").key("int_key").integer(100).add();
        // preference info
        DevPanel.info().pref().title("Int pref example").key("long_key").llong(100L).add();

        //Boolean mutable
        DevPanel.mutable().bool(false).key("bool_key").add();
        //String set
        DevPanel.mutable().set().key("host").values("test", "prod").add();

        //simple button
        DevPanel.button().title("Test button").onClick(new Function1<Context, Unit>() {
            @Override
            public Unit invoke(Context context) {
                Toast.makeText(context, "Test alert", Toast.LENGTH_LONG).show();
                return null;
            }
        }).add();

    }
}
