package com.ldzs.liveforever;

import android.util.Log;


public class MessageManager
{
    private boolean hasPaused = false;
    private Thread msgThread;
    private final NotificationService notificationService;

    public MessageManager(NotificationService paramNotificationService)
    {
        this.notificationService = paramNotificationService;
        this.hasPaused = false;
    }

    public void pauseThread(){
        this.hasPaused = true;
        if (this.msgThread == null || !this.msgThread.isAlive()){return;}
        this.msgThread.interrupt();
    }

    public void restartThread(){
        Log.e("clock", "messageMgr.restartThread");
        if (this.msgThread != null)
            this.msgThread.interrupt();
        this.msgThread = new MessageThread(this.notificationService);
        this.msgThread.start();
        this.hasPaused = false;
    }



    public void restartThreadIfNeed()
    {
        if (this.hasPaused)
            restartThread();
    }
}