package com.homa.catcartoon.base;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * Created by Homa on 2017/9/4.
 * Activity管理类
 */

public class ActivityManager {

    //存储ActivityStack
    private static Stack<Activity> activityStack = new Stack<Activity>();

    //单例模式
    private static ActivityManager instance;

    /**
     * 单列堆栈集合对象
     * @return AppDavikActivityMgr 单利堆栈集合对象
     */
    public static ActivityManager getInstance() {
        if (instance == null) {
            synchronized (ActivityManager.class){
                if (instance == null) {
                    instance = new ActivityManager();
                }
            }
        }
        return instance;
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }
    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public static void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }
    /**
     * 结束到指定页面
     */
    public static void finishToActivity(Context context, Class cla) {
        for (int i = activityStack.size()-1, size = 0; i >=size; i--) {
            if (null != activityStack.get(i)) {
                if (activityStack.get(i).getClass().equals(cla)) {
                    return;
                }else {
                    activityStack.get(i).finish();
                }
            }
        }
    }
    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            System.gc();
            System.exit(0);
        } catch (Exception e) {
        }
    }
}
