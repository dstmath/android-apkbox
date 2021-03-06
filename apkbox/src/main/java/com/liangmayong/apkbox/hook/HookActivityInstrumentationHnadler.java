/**
 The MIT License (MIT)

 Copyright (c) 2017 LiangMaYong ( ibeam@qq.com )

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/ or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 **/
package com.liangmayong.apkbox.hook;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.Instrumentation;
import android.app.UiAutomation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.liangmayong.apkbox.hook.activity.HookActivity_Intent;
import com.liangmayong.apkbox.hook.activity.HookActivity_LifeCycle;
import com.liangmayong.apkbox.reflect.ApkMethod;
import com.liangmayong.apkbox.utils.ApkLogger;

/**
 * Created by LiangMaYong on 2017/4/5.
 */
public class HookActivityInstrumentationHnadler extends Instrumentation {

    private Instrumentation mInstrumentation = null;

    public HookActivityInstrumentationHnadler(Instrumentation mInstrumentation) {
        this.mInstrumentation = mInstrumentation;
    }

    @Override
    public void onCreate(Bundle arguments) {
        mInstrumentation.onCreate(arguments);
    }

    @Override
    public void start() {
        mInstrumentation.start();
    }

    @Override
    public void onStart() {
        mInstrumentation.onStart();
    }

    @Override
    public boolean onException(Object obj, Throwable e) {
        return mInstrumentation.onException(obj, e);
    }

    @Override
    public void sendStatus(int resultCode, Bundle results) {
        mInstrumentation.sendStatus(resultCode, results);
    }

    @Override
    public void finish(int resultCode, Bundle results) {
        mInstrumentation.finish(resultCode, results);
    }

    @Override
    public void setAutomaticPerformanceSnapshots() {
        mInstrumentation.setAutomaticPerformanceSnapshots();
    }

    @Override
    public void startPerformanceSnapshot() {
        mInstrumentation.startPerformanceSnapshot();
    }

    @Override
    public void endPerformanceSnapshot() {
        mInstrumentation.endPerformanceSnapshot();
    }

    @Override
    public void onDestroy() {
        mInstrumentation.onDestroy();
    }

    @Override
    public Context getContext() {
        return mInstrumentation.getContext();
    }

    @Override
    public ComponentName getComponentName() {
        return mInstrumentation.getComponentName();
    }

    @Override
    public Context getTargetContext() {
        return mInstrumentation.getTargetContext();
    }

    @Override
    public boolean isProfiling() {
        return mInstrumentation.isProfiling();
    }

    @Override
    public void startProfiling() {
        mInstrumentation.startProfiling();
    }

    @Override
    public void stopProfiling() {
        mInstrumentation.stopProfiling();
    }

    @Override
    public void setInTouchMode(boolean inTouch) {
        mInstrumentation.setInTouchMode(inTouch);
    }

    @Override
    public void waitForIdle(Runnable recipient) {
        mInstrumentation.waitForIdle(recipient);
    }

    @Override
    public void waitForIdleSync() {
        mInstrumentation.waitForIdleSync();
    }

    @Override
    public void runOnMainSync(Runnable runner) {
        mInstrumentation.runOnMainSync(runner);
    }

    @Override
    public Activity startActivitySync(Intent intent) {
        return mInstrumentation.startActivitySync(intent);
    }

    @Override
    public void addMonitor(ActivityMonitor monitor) {
        mInstrumentation.addMonitor(monitor);
    }

    @Override
    public ActivityMonitor addMonitor(IntentFilter filter, ActivityResult result, boolean block) {
        return mInstrumentation.addMonitor(filter, result, block);
    }

    @Override
    public ActivityMonitor addMonitor(String cls, ActivityResult result, boolean block) {
        return mInstrumentation.addMonitor(cls, result, block);
    }

    @Override
    public boolean checkMonitorHit(ActivityMonitor monitor, int minHits) {
        return mInstrumentation.checkMonitorHit(monitor, minHits);
    }

    @Override
    public Activity waitForMonitor(ActivityMonitor monitor) {
        return mInstrumentation.waitForMonitor(monitor);
    }

    @Override
    public Activity waitForMonitorWithTimeout(ActivityMonitor monitor, long timeOut) {
        return mInstrumentation.waitForMonitorWithTimeout(monitor, timeOut);
    }

    @Override
    public void removeMonitor(ActivityMonitor monitor) {
        mInstrumentation.removeMonitor(monitor);
    }

    @Override
    public boolean invokeMenuActionSync(Activity targetActivity, int id, int flag) {
        return mInstrumentation.invokeMenuActionSync(targetActivity, id, flag);
    }

    @Override
    public boolean invokeContextMenuAction(Activity targetActivity, int id, int flag) {
        return mInstrumentation.invokeContextMenuAction(targetActivity, id, flag);
    }

    @Override
    public void sendStringSync(String text) {
        mInstrumentation.sendStringSync(text);
    }

    @Override
    public void sendKeySync(KeyEvent event) {
        mInstrumentation.sendKeySync(event);
    }

    @Override
    public void sendKeyDownUpSync(int key) {
        mInstrumentation.sendKeyDownUpSync(key);
    }

    @Override
    public void sendCharacterSync(int keyCode) {
        mInstrumentation.sendCharacterSync(keyCode);
    }

    @Override
    public void sendPointerSync(MotionEvent event) {
        mInstrumentation.sendPointerSync(event);
    }

    @Override
    public void sendTrackballEventSync(MotionEvent event) {
        mInstrumentation.sendTrackballEventSync(event);
    }

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return mInstrumentation.newApplication(cl, className, context);
    }

    @Override
    public void callApplicationOnCreate(Application app) {
        mInstrumentation.callApplicationOnCreate(app);
    }

    @Override
    public Activity newActivity(Class<?> clazz, Context context, IBinder token, Application application, Intent intent, ActivityInfo info, CharSequence title, Activity parent, String id, Object lastNonConfigurationInstance) throws InstantiationException, IllegalAccessException {
        return mInstrumentation.newActivity(clazz, context, token, application, intent, info, title, parent, id, lastNonConfigurationInstance);
    }

    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Activity activity = HookActivity_LifeCycle.onNewActivity(cl, className, intent);
        if (activity != null) {
            return activity;
        }
        return mInstrumentation.newActivity(cl, className, intent);
    }

    @Override
    public void callActivityOnCreate(Activity activity, Bundle icicle) {
        HookActivity_LifeCycle.onCreate(activity, icicle, false);
        mInstrumentation.callActivityOnCreate(activity, icicle);
        HookActivity_LifeCycle.onCreate(activity, icicle, true);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void callActivityOnCreate(Activity activity, Bundle icicle, PersistableBundle persistentState) {
        HookActivity_LifeCycle.onCreate(activity, icicle, persistentState, false);
        mInstrumentation.callActivityOnCreate(activity, icicle, persistentState);
        HookActivity_LifeCycle.onCreate(activity, icicle, persistentState, true);
    }

    @Override
    public void callActivityOnDestroy(Activity activity) {
        HookActivity_LifeCycle.onDestroy(activity, false);
        mInstrumentation.callActivityOnDestroy(activity);
        HookActivity_LifeCycle.onDestroy(activity, true);
    }

    @Override
    public void callActivityOnRestoreInstanceState(Activity activity, Bundle savedInstanceState) {
        HookActivity_LifeCycle.onRestoreInstanceState(activity, savedInstanceState, false);
        mInstrumentation.callActivityOnRestoreInstanceState(activity, savedInstanceState);
        HookActivity_LifeCycle.onRestoreInstanceState(activity, savedInstanceState, true);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void callActivityOnRestoreInstanceState(Activity activity, Bundle savedInstanceState, PersistableBundle persistentState) {
        HookActivity_LifeCycle.onRestoreInstanceState(activity, savedInstanceState, persistentState, false);
        mInstrumentation.callActivityOnRestoreInstanceState(activity, savedInstanceState, persistentState);
        HookActivity_LifeCycle.onRestoreInstanceState(activity, savedInstanceState, persistentState, true);
    }

    @Override
    public void callActivityOnPostCreate(Activity activity, Bundle icicle) {
        HookActivity_LifeCycle.onPostCreate(activity, icicle, false);
        mInstrumentation.callActivityOnPostCreate(activity, icicle);
        HookActivity_LifeCycle.onPostCreate(activity, icicle, true);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void callActivityOnPostCreate(Activity activity, Bundle icicle, PersistableBundle persistentState) {
        HookActivity_LifeCycle.onPostCreate(activity, icicle, persistentState, false);
        mInstrumentation.callActivityOnPostCreate(activity, icicle, persistentState);
        HookActivity_LifeCycle.onPostCreate(activity, icicle, persistentState, true);
    }

    @Override
    public void callActivityOnNewIntent(Activity activity, Intent intent) {
        mInstrumentation.callActivityOnNewIntent(activity, intent);
    }

    @Override
    public void callActivityOnStart(Activity activity) {
        HookActivity_LifeCycle.onStart(activity, false);
        mInstrumentation.callActivityOnStart(activity);
        HookActivity_LifeCycle.onStart(activity, true);
    }

    @Override
    public void callActivityOnRestart(Activity activity) {
        HookActivity_LifeCycle.onRestart(activity, false);
        mInstrumentation.callActivityOnRestart(activity);
        HookActivity_LifeCycle.onRestart(activity, true);
    }

    @Override
    public void callActivityOnResume(Activity activity) {
        HookActivity_LifeCycle.onResume(activity, false);
        mInstrumentation.callActivityOnResume(activity);
        HookActivity_LifeCycle.onResume(activity, true);
    }

    @Override
    public void callActivityOnStop(Activity activity) {
        HookActivity_LifeCycle.onStop(activity, false);
        mInstrumentation.callActivityOnStop(activity);
        HookActivity_LifeCycle.onStop(activity, true);
    }

    @Override
    public void callActivityOnSaveInstanceState(Activity activity, Bundle outState) {
        HookActivity_LifeCycle.onSaveInstanceState(activity, outState, false);
        mInstrumentation.callActivityOnSaveInstanceState(activity, outState);
        HookActivity_LifeCycle.onSaveInstanceState(activity, outState, true);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void callActivityOnSaveInstanceState(Activity activity, Bundle outState, PersistableBundle outPersistentState) {
        HookActivity_LifeCycle.onSaveInstanceState(activity, outState, outPersistentState, false);
        mInstrumentation.callActivityOnSaveInstanceState(activity, outState, outPersistentState);
        HookActivity_LifeCycle.onSaveInstanceState(activity, outState, outPersistentState, true);
    }

    @Override
    public void callActivityOnPause(Activity activity) {
        HookActivity_LifeCycle.onPause(activity, false);
        mInstrumentation.callActivityOnPause(activity);
        HookActivity_LifeCycle.onPause(activity, true);
    }

    @Override
    public void callActivityOnUserLeaving(Activity activity) {
        mInstrumentation.callActivityOnUserLeaving(activity);
    }

    @Override
    public void startAllocCounting() {
        mInstrumentation.startAllocCounting();
    }

    @Override
    public void stopAllocCounting() {
        mInstrumentation.stopAllocCounting();
    }

    @Override
    public Bundle getAllocCounts() {
        return mInstrumentation.getAllocCounts();
    }

    @Override
    public Bundle getBinderCounts() {
        return mInstrumentation.getBinderCounts();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public UiAutomation getUiAutomation() {
        return mInstrumentation.getUiAutomation();
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public UiAutomation getUiAutomation(int flags) {
        return mInstrumentation.getUiAutomation(flags);
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target,
                                            Intent intent, int requestCode) {
        return execStartActivity(who, contextThread, token, target, intent, requestCode, null);
    }


    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target,
                                            Intent intent, int requestCode, Bundle options) {
        try {
            return proxyExecStartActivity(who, contextThread, token, target, intent, requestCode, options);
        } catch (Exception error) {
            ApkLogger.get().error("execStartActivity Exception", error);
            return null;
        }
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Fragment fragment,
                                            Intent intent, int requestCode) {
        return execStartActivity(who, contextThread, token, fragment, intent, requestCode, null);
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Fragment fragment,
                                            Intent intent, int requestCode, Bundle options) {
        try {
            Intent targetIntent = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
                targetIntent = HookActivity_Intent.modify(fragment.getActivity(), intent);
            }
            if (targetIntent == null) {
                targetIntent = intent;
            }
            ApkMethod method = new ApkMethod(Instrumentation.class, mInstrumentation, "execStartActivity", Context.class, IBinder.class, IBinder.class, Fragment.class, Intent.class, int.class, Bundle.class);
            return method.invoke(who, contextThread, token, fragment, targetIntent, requestCode, options);
        } catch (Exception error) {
            ApkLogger.get().error("execStartActivity Exception", error);
            return null;
        }
    }

    protected ActivityResult proxyExecStartActivity(Context who, IBinder contextThread, IBinder token, Activity target,
                                                    Intent intent, int requestCode, Bundle options) throws Exception {
        try {
            Intent targetIntent = HookActivity_Intent.modify(target, intent);
            if (targetIntent == null) {
                targetIntent = intent;
            }
            ApkMethod method = new ApkMethod(Instrumentation.class, mInstrumentation, "execStartActivity", Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, int.class, Bundle.class);
            return method.invoke(who, contextThread, token, target, targetIntent, requestCode, options);
        } catch (Exception e) {
            throw e;
        }
    }

}
