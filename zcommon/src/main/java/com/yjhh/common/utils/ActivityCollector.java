package com.yjhh.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {
    private static List<Activity> activityList = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    // 获取栈顶 Activity
    public static Activity getTopActivity() {
        if (activityList.isEmpty())
            return null;
        return activityList.get(activityList.size() - 1);
    }

    public static void JumpActivity(Context context, Class clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }


    public static void JumpActivity(Context context, Class clazz,String key,String value) {
        Intent intent = new Intent(context, clazz);
        intent.putExtra(key,value);
        context.startActivity(intent);
    }

}
