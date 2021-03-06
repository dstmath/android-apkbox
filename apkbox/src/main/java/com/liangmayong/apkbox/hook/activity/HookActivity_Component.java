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
package com.liangmayong.apkbox.hook.activity;

import android.content.ComponentName;
import android.content.Intent;

import com.liangmayong.apkbox.core.constant.ApkConstant;
import com.liangmayong.apkbox.hook.modify.ApkComponentModifier;
import com.liangmayong.apkbox.proxy.activity.ProxyInstanceActivity;
import com.liangmayong.apkbox.proxy.activity.ProxyStandardActivity;
import com.liangmayong.apkbox.proxy.activity.ProxyTaskActivity;
import com.liangmayong.apkbox.proxy.activity.ProxyTopActivity;
import com.liangmayong.apkbox.proxy.activity.Proxy404Activity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiangMaYong on 2017/4/5.
 */
public class HookActivity_Component {


    private HookActivity_Component() {
    }

    // launch mode
    public static final String SINGLE_TASK = "SINGLE_TASK";
    public static final String SINGLE_INSTANCE = "SINGLE_INSTANCE";
    public static final String SINGLE_TOP = "SINGLE_TOP";
    public static final String STANDARD = "STANDARD";

    // COMPONENTMAP
    private static final Map<String, Class> COMPONENTMAP = new HashMap<>();
    // CLASSES
    private static final Class<?>[] CLASSES = new Class[]{
            ProxyTaskActivity.class,
            ProxyInstanceActivity.class,
            ProxyTopActivity.class,
            ProxyStandardActivity.class,
    };
    // index
    private static int index = 0;

    public static Intent modify(Intent raw) {
        if (!raw.hasExtra(ApkConstant.EXTRA_APK_MODIFIED)
                && raw != null
                && raw.getComponent() != null
                && raw.hasExtra(ApkConstant.EXTRA_APK_PATH)) {
            String key = ApkComponentModifier.getKey(raw);
            Class<?> clazz = null;
            if (raw.hasExtra(ApkConstant.EXTRA_APK_PROXY)) {
                String proxyClass = raw.getStringExtra(ApkConstant.EXTRA_APK_PROXY);
                try {
                    clazz = HookActivity_Component.class.getClassLoader().loadClass(proxyClass);
                } catch (ClassNotFoundException e) {
                }
            }
            if (clazz == null) {
                if (COMPONENTMAP.containsKey(key)) {
                    clazz = COMPONENTMAP.get(key);
                } else {
                    String launchMode = "";
                    if (raw.hasExtra(ApkConstant.EXTRA_APK_LAUNCH_MODE)) {
                        launchMode = raw.getStringExtra(ApkConstant.EXTRA_APK_LAUNCH_MODE);
                    }
                    if (launchMode.equals(SINGLE_TASK)) {
                        index = 0;
                    } else if (launchMode.equals(SINGLE_INSTANCE)) {
                        index = 1;
                    } else if (launchMode.equals(SINGLE_TOP)) {
                        index = 2;
                    } else if (launchMode.equals(STANDARD)) {
                        index = 3;
                    }
                    if (index >= CLASSES.length) {
                        clazz = Proxy404Activity.class;
                    } else {
                        clazz = CLASSES[index];
                        COMPONENTMAP.put(key, clazz);
                    }
                }
            }
            Intent newIntent = new Intent();
            ComponentName componentName = new ComponentName(raw.getComponent().getPackageName(), clazz.getName());
            newIntent.setComponent(componentName);
            newIntent.putExtra(ApkConstant.EXTRA_APK_TARGET_INTENT, raw);
            newIntent.putExtra(ApkConstant.EXTRA_APK_MODIFIED, clazz.getName());
            return newIntent;
        }
        return raw;
    }

}
