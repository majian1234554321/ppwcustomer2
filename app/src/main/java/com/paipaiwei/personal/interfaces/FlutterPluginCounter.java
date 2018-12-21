package com.paipaiwei.personal.interfaces;

import android.app.Activity;
import android.util.Log;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.PluginRegistry;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class FlutterPluginCounter implements EventChannel.StreamHandler {


    public static String CHANNEL = "com.jzhu.counter/plugin";

    static EventChannel channel;

    private Activity activity;

    public FlutterPluginCounter(Activity activity) {
        this.activity = activity;
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        channel = new EventChannel(registrar.messenger(), CHANNEL);
        FlutterPluginCounter instance = new FlutterPluginCounter(registrar.activity());
        channel.setStreamHandler(instance);
    }

    @Override
    public void onListen(Object o, final EventChannel.EventSink eventSink) {
        eventSink.success(o);
    }

    @Override
    public void onCancel(Object o) {
        Log.i("FlutterPluginCounter", "FlutterPluginCounter:onCancel");
    }

}
