package com.liangmayong.apkbox.hook.service;

import android.content.ComponentName;
import android.content.Intent;

import com.liangmayong.apkbox.core.constant.ApkConstant;
import com.liangmayong.apkbox.hook.component.HookComponent_Activity;
import com.liangmayong.apkbox.proxy.service.Proxy0Service;
import com.liangmayong.apkbox.proxy.service.Proxy10Service;
import com.liangmayong.apkbox.proxy.service.Proxy11Service;
import com.liangmayong.apkbox.proxy.service.Proxy12Service;
import com.liangmayong.apkbox.proxy.service.Proxy13Service;
import com.liangmayong.apkbox.proxy.service.Proxy14Service;
import com.liangmayong.apkbox.proxy.service.Proxy15Service;
import com.liangmayong.apkbox.proxy.service.Proxy16Service;
import com.liangmayong.apkbox.proxy.service.Proxy17Service;
import com.liangmayong.apkbox.proxy.service.Proxy18Service;
import com.liangmayong.apkbox.proxy.service.Proxy19Service;
import com.liangmayong.apkbox.proxy.service.Proxy1Service;
import com.liangmayong.apkbox.proxy.service.Proxy20Service;
import com.liangmayong.apkbox.proxy.service.Proxy2Service;
import com.liangmayong.apkbox.proxy.service.Proxy3Service;
import com.liangmayong.apkbox.proxy.service.Proxy404Service;
import com.liangmayong.apkbox.proxy.service.Proxy4Service;
import com.liangmayong.apkbox.proxy.service.Proxy5Service;
import com.liangmayong.apkbox.proxy.service.Proxy6Service;
import com.liangmayong.apkbox.proxy.service.Proxy7Service;
import com.liangmayong.apkbox.proxy.service.Proxy8Service;
import com.liangmayong.apkbox.proxy.service.Proxy9Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiangMaYong on 2017/4/5.
 */
public class HookService_Component {

    private HookService_Component() {
    }

    // COMPONENTMAP
    private static Map<String, Class> COMPONENTMAP = new HashMap<>();
    // CLASSES
    public static Class<?>[] CLASSES = new Class[]{
            Proxy0Service.class,
            Proxy1Service.class,
            Proxy2Service.class,
            Proxy3Service.class,
            Proxy4Service.class,
            Proxy5Service.class,
            Proxy6Service.class,
            Proxy7Service.class,
            Proxy8Service.class,
            Proxy9Service.class,
            Proxy10Service.class,
            Proxy11Service.class,
            Proxy12Service.class,
            Proxy13Service.class,
            Proxy14Service.class,
            Proxy15Service.class,
            Proxy16Service.class,
            Proxy17Service.class,
            Proxy18Service.class,
            Proxy19Service.class,
            Proxy20Service.class,
    };
    // index
    private static int index = 0;

    public static Intent modify(Intent raw) {
        if (!raw.hasExtra(ApkConstant.EXTRA_APK_MODIFIED)
                && raw != null
                && raw.getComponent() != null
                && raw.hasExtra(ApkConstant.EXTRA_APK_PATH)) {
            String key = getKey(raw);
            Class<?> clazz = null;
            if (raw.hasExtra(ApkConstant.EXTRA_APK_PROXY)) {
                String proxyClass = raw.getStringExtra(ApkConstant.EXTRA_APK_PROXY);
                try {
                    clazz = HookComponent_Activity.class.getClassLoader().loadClass(proxyClass);
                } catch (ClassNotFoundException e) {
                }
            }
            if (clazz == null) {
                if (COMPONENTMAP.containsKey(key)) {
                    clazz = COMPONENTMAP.get(key);
                } else {
                    if (index >= CLASSES.length) {
                        clazz = Proxy404Service.class;
                    } else {
                        clazz = CLASSES[index];
                        COMPONENTMAP.put(key, clazz);
                    }
                    index++;
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

    public static String getKey(Intent intent) {
        return getPath(intent) + "@" + getClassName(intent);
    }

    public static String getPath(Intent intent) {
        if (intent != null && intent.hasExtra(ApkConstant.EXTRA_APK_PATH)) {
            return intent.getStringExtra(ApkConstant.EXTRA_APK_PATH);
        }
        return null;
    }

    public static String getClassName(Intent intent) {
        if (intent != null && intent.getComponent() != null) {
            return intent.getComponent().getClassName();
        }
        return null;
    }
}
