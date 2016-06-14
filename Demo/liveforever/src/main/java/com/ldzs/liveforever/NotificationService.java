package com.ldzs.liveforever;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;


public class NotificationService extends Service {
    private MessageManager msgManager;
    //private StartServiceReceiver mServiceReceiver;

    private void avoidKilled() {
        startForeground(0, new Notification());
    }

    public void onCreate() {
        try {
            //avoidKilled();
            //监听对应的系统广播
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_BOOT_COMPLETED);
            filter.addAction(Intent.ACTION_SCREEN_ON);
            filter.addAction(Intent.ACTION_SCREEN_OFF);
           // registerReceiver(mServiceReceiver = new StartServiceReceiver(), filter);
            this.msgManager = new MessageManager(this);
            this.msgManager.restartThread();
            return;
        } catch (Exception localException){
            localException.printStackTrace();
        }
    }

    public void onDestroy() {
//        if(mServiceReceiver!=null) {
//            unregisterReceiver(mServiceReceiver);
//        }
        if(msgManager!=null) {
            msgManager.pauseThread();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public boolean onUnbind(Intent paramIntent) {
        return super.onUnbind(paramIntent);
    }

}