package com.example.hong3.mybilibili.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.PowerManager;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hong3.mybilibili.R;
import com.example.hong3.mybilibili.ui.customView.LoveLikeLayout;
import com.example.hong3.mybilibili.util.ConstantUtil;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

import static android.os.PowerManager.SCREEN_BRIGHT_WAKE_LOCK;

/**
 * Created by hong3 on 2016-12-24.
 */

public class LiveDitailActivity extends BaseActivity implements View.OnClickListener {
    private SurfaceView surfaceView;
    private ImageView biliAnim;
    private TextView startInfo;
    private ImageView rightPlay;
    private View bottomLayout;
    private ImageView bottomPlay;
    private ImageView bottomLove;
    private ImageView bottomFullScreen;
    private LoveLikeLayout loveLikeLayout;
    private View userInfo;
    private CircleImageView circleImageView;
    private TextView userName;
    private TextView liveNum;
    private TextView focus;

    private int cid;
    private String title;
    private int online;
    private String face;
    private String name;
    private int mid;
    private String playurl;


    private IjkMediaPlayer ijkMediaPlayer;
    private SurfaceHolder surfaceHolder;
    PowerManager.WakeLock wakeLock;


    @Override
    public int getlayoutId() {

        return R.layout.activity_live_detail;

    }

    @Override
    public void getIntentData() {
        super.getIntentData();

        Intent intent = getIntent();
        if (intent != null)
        {
            cid = intent.getIntExtra(ConstantUtil.EXTRA_CID, 0);
            title = intent.getStringExtra(ConstantUtil.EXTRA_TITLE);
            online = intent.getIntExtra(ConstantUtil.EXTRA_ONLINE, 0);
            face = intent.getStringExtra(ConstantUtil.EXTRA_FACE);
            name = intent.getStringExtra(ConstantUtil.EXTRA_NAME);
            mid = intent.getIntExtra(ConstantUtil.EXTRA_MID, 0);
            playurl = intent.getStringExtra(ConstantUtil.PLAY_URL);
        }


        PowerManager manager = (PowerManager) getSystemService(POWER_SERVICE);
        /*
        PARTIAL_WAKE_LOCK :保持CPU 运转，屏幕和键盘灯有可能是关闭的。
SCREEN_DIM_WAKE_LOCK ：保持CPU 运转，允许保持屏幕显示但有可能是灰的，允许关闭键盘灯
SCREEN_BRIGHT_WAKE_LOCK ：保持CPU 运转，允许保持屏幕高亮显示，允许关闭键盘灯
FULL_WAKE_LOCK ：保持CPU 运转，保持屏幕高亮显示，键盘灯也保持亮度
         */

        wakeLock = manager.newWakeLock(SCREEN_BRIGHT_WAKE_LOCK,"mytag");


    }

    @Override
    public void initView() {
        super.initView();

        surfaceView = fastFindViewById(R.id.video_view);
        biliAnim = fastFindViewById(R.id.bili_anim);
        startInfo = fastFindViewById(R.id.video_start_info);
        rightPlay = fastFindViewById(R.id.right_play);
        rightPlay.setVisibility(View.VISIBLE);
        rightPlay.setOnClickListener(this);

        bottomLayout = fastFindViewById(R.id.bottom_layout);
        bottomPlay  = fastFindViewById(R.id.bottom_play);
        bottomLove = fastFindViewById(R.id.bottom_love);
        bottomFullScreen = fastFindViewById(R.id.bottom_fullscreen);

        loveLikeLayout = fastFindViewById(R.id.love_layout);


        userInfo = fastFindViewById(R.id.user_info_layout);
        circleImageView = fastFindViewById(R.id.user_pic);
        Picasso.with(this).load(face).into(circleImageView);

        userName = fastFindViewById(R.id.user_name);
        userName.setText(name);

        liveNum = fastFindViewById(R.id.live_num);
        liveNum.setText(online+"");

        focus = fastFindViewById(R.id.focus);
        focus.setOnClickListener(this);

        initVideo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        wakeLock.acquire();
    }

    private void initVideo() {
        surfaceHolder = surfaceView.getHolder();
        ijkMediaPlayer = new IjkMediaPlayer();
    }



    @Override
    public void initData() {
        super.initData();

        getData();
    }

    //请求数据，准备视频资源
    public void getData() {
        biliAnim.setVisibility(View.VISIBLE);
        startInfo.setVisibility(View.VISIBLE);
    }

    //显示播放底部布局
    public void showBottomLayout(){
        bottomLayout.setVisibility(View.VISIBLE);
        bottomPlay.setOnClickListener(this);
        bottomLove.setOnClickListener(this);
        bottomFullScreen.setOnClickListener(this);
    }

    public void changePlayerStatus(){
        if (ijkMediaPlayer.isPlaying()){
            ijkMediaPlayer.pause();
            bottomPlay.setImageResource(R.mipmap.ic_portrait_play);
        }else{
            ijkMediaPlayer.start();
            bottomPlay.setImageResource(R.mipmap.ic_portrait_stop);
        }
    }

    /**
     * 播放视频
     * @param uri
     */
    private void playVideo(String uri) {
        surfaceView.setVisibility(View.VISIBLE);
        biliAnim.setVisibility(View.GONE);
        startInfo.setVisibility(View.GONE);
        try
        {
            ijkMediaPlayer.setDataSource(this, Uri.parse(uri));
            ijkMediaPlayer.setDisplay(surfaceHolder);
            surfaceHolder.addCallback(new SurfaceHolder.Callback()
            {

                @Override
                public void surfaceCreated(SurfaceHolder holder)
                {

                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
                {

                    ijkMediaPlayer.setDisplay(holder);
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder)
                {

                }
            });
            ijkMediaPlayer.prepareAsync();
            ijkMediaPlayer.start();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        ijkMediaPlayer.setKeepInBackground(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        wakeLock.release();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ijkMediaPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    public static void startActivity(Activity activity, int cid, String title, int online, String face, String name, int mid,String playurl)
    {

        Intent mIntent = new Intent(activity, LiveDitailActivity.class);
        mIntent.putExtra(ConstantUtil.EXTRA_CID, cid);
        mIntent.putExtra(ConstantUtil.EXTRA_TITLE, title);
        mIntent.putExtra(ConstantUtil.EXTRA_ONLINE, online);
        mIntent.putExtra(ConstantUtil.EXTRA_FACE, face);
        mIntent.putExtra(ConstantUtil.EXTRA_NAME, name);
        mIntent.putExtra(ConstantUtil.EXTRA_MID, mid);
        mIntent.putExtra(ConstantUtil.PLAY_URL,playurl);
        activity.startActivity(mIntent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.right_play:
                //初始界面的播放
                playVideo(playurl);
                rightPlay.setVisibility(View.GONE);
                showBottomLayout();
                break;
            case R.id.bottom_play:
                //底部布局的播放/暂停
                changePlayerStatus();
                break;
            case R.id.bottom_love:
                //送礼物
                loveLikeLayout.addLove();
                break;
            case R.id.bottom_fullscreen:
                //全屏
                setFullScreen();
                break;
            case R.id.focus:
                //关注
                fastToast("关注");
                break;

        }
    }

    boolean isFullScreen;
    private void setFullScreen() {


    }


}
