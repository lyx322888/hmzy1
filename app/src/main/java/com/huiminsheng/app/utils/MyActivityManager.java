package com.huiminsheng.app.utils;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Activity管理器
 */
public class MyActivityManager {

    private  ArrayList<Activity> activityList = new ArrayList<Activity>();
    private static MyActivityManager instance;

    public static MyActivityManager getInstance() {
        if (instance == null) {
            instance = new MyActivityManager();
        }
        return instance;
    }

    /**
     * 添加 Activity 到列表
     *
     * @param activity activity
     */
    public  void addActivity(Activity activity) {
        if (instance.activityList == null) {
            instance.activityList = new ArrayList<Activity>();
        }
        instance.activityList.add(activity);
    }

    /**
     * 获取界面数量
     *
     * @return activity size
     */
    public  int getActivitySize() {
        if (instance.activityList != null) {
            return instance.activityList.size();
        }
        return 0;
    }

    /**
     * 获取当前 Activity - 堆栈中最后一个压入的
     *
     * @return current Activity
     */
    public  Activity getCurrentActivity() {
        if (instance.activityList != null && instance.activityList.size() > 0) {
            Activity activity = instance.activityList.get(instance.activityList.size() - 1);
            return activity;
        }
        return null;
    }

    /**
     * 获取指定类名的 Activity
     *
     * @param cls 指定的类
     * @return Activity
     */
    public  Activity getActivity(Class<?> cls) {
        if (instance.activityList == null) {
            return null;
        }
        for (Activity activity : instance.activityList) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    /**
     * 删除指定的 Activity
     *
     * @param activity Activity
     */
    public  void removeActivity(Activity activity) {
        if (activity != null) {
            instance.activityList.remove(activity);
        }
    }

    /**
     * 删除指定类名的 Activity
     *
     * @param cls 指定的类
     */
    public  void removeActivity(Class<?> cls) {
        if (instance.activityList == null) {
            return;
        }
        for (Activity activity : instance.activityList) {
            if (activity.getClass().equals(cls)) {
                instance.activityList.remove(activity);
            }
        }
    }
    /**
     * 结束指定类名的 Activity
     *
     * @param cls 指定的类
     */
    public  void finishActivity(Class<?> cls) {
        if (instance.activityList == null) {
            return;
        }
        for (Activity activity : instance.activityList) {
            if (activity.getClass().equals(cls)) {
               activity.finish();
            }
        }
    }


    /**
     * 结束所有Activity
     */
    public  void finishAllActivity() {
        if (instance.activityList == null) {
            return;
        }
        int size = instance.activityList.size();
        for (int i = 0; i < size; i++) {
            if (null != instance.activityList.get(i)) {
                instance.activityList.get(i).finish();
            }
        }
        instance.activityList.clear();
    }

    /**
     * 结束指定Activity上面的所有Activity
     */
    public void finishAboveActivity(Class<?> cls){
        if (instance.activityList == null) {
            return;
        }
        int index = 0;
        for (int i = 0; i < instance.activityList.size(); i++) {
            if (cls==instance.activityList.get(i).getClass()){
                index = i;
                break;
            }
        }
        for (int i = 0; i <instance.activityList.size(); i++) {
            if (i>index){
                instance. activityList.get(i).finish();
            }
        }
    }
    /**
     * 结束其他所有的Activity
     *
     * @param activity 不需要销毁的Activity
     */
    public  void finishOtherAllActivity(Activity activity) {
        if (instance.activityList == null) {
            return;
        }
        for (int i = 0, size = instance.activityList.size(); i < size; i++) {
            if (activity != instance.activityList.get(i)) {
                instance.activityList.get(i).finish();
                instance.activityList.remove(i);
            }
        }
    }


}
