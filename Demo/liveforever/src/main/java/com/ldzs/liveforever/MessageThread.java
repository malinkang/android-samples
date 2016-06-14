package com.ldzs.liveforever;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;


/**
 * Created by ldfs on 16/2/19.
 */
public class MessageThread extends Thread {
    private Context mContext;
    private String processName = "com.ldfs.wxkd:daemon";
    private boolean isRun = false;//判断是否已经运行了日活
    public MessageThread(Context context) {
        this.mContext=context;
    }
    /**
     * 表示屏幕是否亮着
     * @return
     */
    public boolean isScreenOn(){
        boolean isScreenOn=true;
        if (this.mContext != null) {
            PowerManager pm = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
                isScreenOn = pm.isInteractive();
            } else {
                isScreenOn = pm.isScreenOn();//如果为true，则表示屏幕“亮”了，否则屏幕“暗”了
            }
        }
        return isScreenOn;
    }

    public void run(){
        try {
            while (true){
                if (isInterrupted()){return;}
                Thread.sleep(10*1000);
//                if (PackageUtils.isProessRunning(mContext, processName)) {
//                    if (!isScreenOn()){
//                        DbHelper.queryItems(new ActiveItem(), "id=?", new String[]{"0"}, null, items -> {
//                            if (null != items && !items.isEmpty()) {
//                                ActiveItem activeItem = items.get(0);
//                                if (activeItem != null && !"0".equals(activeItem.status) && !DateUtils.isToday(activeItem.ut)) {
//                                    runActivity(mContext,activeItem.run_time);
//                                }
//                            }
//                        });
//                    }
//                }
            }
        }catch (InterruptedException e){
        }catch (Exception localException){}
    }

    private void runActivity(Context context, int runTime){
        if(!isRun) {
            isRun=true;
            Intent localIntent = new Intent();
            localIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //localIntent.setClass(context.getApplicationContext(), NewDetailActivity.class);
            localIntent.putExtra("run_time", runTime);
            context.startActivity(localIntent);
        }
    }
}
