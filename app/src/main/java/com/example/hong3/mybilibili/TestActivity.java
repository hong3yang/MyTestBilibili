package com.example.hong3.mybilibili;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import com.example.hong3.mybilibili.ui.activity.BaseActivity;
import com.example.hong3.mybilibili.ui.activity.MainActivity;

/**
 * Created by hong3 on 2016/11/29.
 */

public class TestActivity extends BaseActivity implements View.OnClickListener {

    Button btn1,btn2,btn3,btn4;

    @Override
    public int getlayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void initView() {
        super.initView();

        btn1 = fastFindViewById(R.id.button1 );
        btn2 = fastFindViewById(R.id.button2 );
        btn3 = fastFindViewById(R.id.button3 );
        btn4 = fastFindViewById(R.id.button4 );
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

    }

    @Override
    public void initData() {
        super.initData();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                sendNotify();
                break;
            case R.id.button2:
                sendNotify2();
                break;
            case R.id.button3:
                break;
            case R.id.button4:
                break;
        }
    }

    public void sendNotify2(){
//        Bitmap btm = BitmapFactory.decodeResource(getResources(),
//                R.mipmap.ic_download);
        Intent intent = new Intent(TestActivity.this,
                MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                TestActivity.this, 0, intent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        Notification noti = new NotificationCompat.Builder(
                TestActivity.this)
                .setSmallIcon(R.mipmap.icon_up)
                .setAutoCancel(true)
//                .setLargeIcon(btm)
                .setNumber(13)
//                .setContentIntent(pendingIntent)
//                .setStyle(
//                        new NotificationCompat.InboxStyle()
//                                .addLine(
//                                        "M.Twain (Google+) Haiku is more than a cert...")
//                                .addLine("M.Twain Reminder")
//                                .addLine("M.Twain Lunch?")
//                                .addLine("M.Twain Revised Specs")
//                                .addLine("M.Twain ")
//                                .addLine(
//                                        "Google Play Celebrate 25 billion apps with Goo..")
//                                .addLine(
//                                        "Stack Exchange StackOverflow weekly Newsl...")
//                                .setBigContentTitle("6 new message")
//                                .setSummaryText("mtwain@android.com"))
                .build();
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, noti);
    }

    public void sendNotify(){
        NotificationCompat.Builder ncb = new NotificationCompat.Builder(TestActivity.this);
//        ncb.setSmallIcon(R.mipmap.ic_22);
//        ncb.setContentText("消息");
//        ncb.setContentTitle("title");
//        ncb.setAutoCancel(true);
//        ncb.setSubText("subText");
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1,ncb.build());

    }
}
