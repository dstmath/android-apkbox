package com.liangmayong.apkbox.core.loader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;

import com.liangmayong.apkbox.R;
import com.liangmayong.apkbox.core.ApkLoaded;
import com.liangmayong.apkbox.reflect.ApkReflect;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by LiangMaYong on 2016/9/20.
 */
public final class ApkReceiver {

    // plugin STRING_LINKED_LIST_HASH_MAP
    private static final Map<String, LinkedList<BroadcastReceiver>> STRING_LINKED_LIST_HASH_MAP = new HashMap<String, LinkedList<BroadcastReceiver>>();

    /**
     * unregisterReceiver
     *
     * @param context context
     * @param loaded  loaded
     */
    public static void unregisterReceiver(Context context, ApkLoaded loaded) {
        String key = loaded.getApkPath();
        if (STRING_LINKED_LIST_HASH_MAP.containsKey(key)) {
            LinkedList<BroadcastReceiver> receivers = STRING_LINKED_LIST_HASH_MAP.get(key);
            for (int i = 0; i < receivers.size(); i++) {
                context.getApplicationContext().unregisterReceiver(receivers.get(i));
            }
            STRING_LINKED_LIST_HASH_MAP.remove(key);
        }
    }

    /**
     * registerReceiver
     *
     * @param context context
     * @param loaded  loaded
     */
    public static void registerReceiver(Context context, ApkLoaded loaded) {
        if (!ApkProcess.validateProcessName(context, context.getString(R.string.apkbox_process))) {
            return;
        }
        String key = loaded.getApkPath();
        if (STRING_LINKED_LIST_HASH_MAP.containsKey(key)) {
            return;
        }
        unregisterReceiver(context, loaded);
        if (loaded != null) {
            Map<String, IntentFilter> filters = loaded.getFilters();
            LinkedList<BroadcastReceiver> receivers = new LinkedList<BroadcastReceiver>();
            for (Map.Entry<String, IntentFilter> entry : filters.entrySet()) {
                String clazzName = ApkLoader.parserClassName(loaded.getApkInfo().packageName, entry.getKey());
                Class<?> clazz = null;
                try {
                    clazz = loaded.getClassLoader().loadClass(clazzName);
                    if (clazz != null && ApkReflect.isGeneric(clazz, BroadcastReceiver.class.getName())) {
                        try {
                            BroadcastReceiver broadcastReceiver = (BroadcastReceiver) clazz.newInstance();
                            receivers.add(broadcastReceiver);
                            context.getApplicationContext().registerReceiver(broadcastReceiver, entry.getValue());
                        } catch (Exception e) {
                        }
                    }
                } catch (ClassNotFoundException e) {
                }
            }
            STRING_LINKED_LIST_HASH_MAP.put(key, receivers);
        }
    }
}
