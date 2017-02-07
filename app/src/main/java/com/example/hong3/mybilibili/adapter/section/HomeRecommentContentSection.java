package com.example.hong3.mybilibili.adapter.section;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hong3.mybilibili.R;
import com.example.hong3.mybilibili.entity.recomment.RecommentInfo;
import com.example.hong3.mybilibili.ui.customView.widget.StatusLessSection;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hong3 on 2016/12/12.
 */

public class HomeRecommentContentSection extends StatusLessSection {

    private static final String TAG = "HomeRecommentContentSec";

    private final String TYPE_RECOMMENDED = "recommend";
    private final String TYPE_LIVE = "live";
    private final String TYPE_BANGUMI = "bangumi_2";
    private final String GOTO_BANGUMI = "bangumi_list";
    private final String TYPE_ACTIVITY = "activity";


    Context context;
    List<RecommentInfo.BodyBean> beans;
    private String type;
    private RecommentInfo.HeadBean head;

    private int[] icons = {
            R.mipmap.ic_header_hot, R.mipmap.ic_head_live,
            R.mipmap.ic_category_t13, R.mipmap.ic_category_t1,
            R.mipmap.ic_category_t3, R.mipmap.ic_category_t129,
            R.mipmap.ic_category_t4, R.mipmap.ic_category_t119,
            R.mipmap.ic_category_t36, R.mipmap.ic_category_t160,
            R.mipmap.ic_category_t155, R.mipmap.ic_category_t5,
            R.mipmap.ic_category_t11, R.mipmap.ic_category_t23
    };


    public HomeRecommentContentSection(Context context,List<RecommentInfo.BodyBean> beans,String type,RecommentInfo.HeadBean head){
        super(R.layout.layout_recomment_body,R.layout.layout_recomment_header, R.layout.layout_recomment_footer);
        this.beans = beans;
        this.context = context;
        this.type = type;
        this.head = head;
    }


    @Override
    public int getContentItemTotal() {
        return beans.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ContentViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ContentViewHolder viewHolder = (ContentViewHolder) holder;

        RecommentInfo.BodyBean bean = beans.get(position);
        Picasso.with(context).load(bean.getCover()).into(viewHolder.body_image);
        viewHolder.title.setText(bean.getTitle());

        viewHolder.body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: "+beans.get(position).getGotoX());
                switch (beans.get(position).getGotoX()){
                    case TYPE_LIVE:
                        Toast.makeText(context,"直播",Toast.LENGTH_SHORT).show();
                        break;
                     default:
                         Toast.makeText(context,"点击事件",Toast.LENGTH_SHORT).show();
                         break;
                }
            }
        });


        switch (type){
            case TYPE_RECOMMENDED:
                viewHolder.layout_video.setVisibility(View.VISIBLE);
                viewHolder.layout_live.setVisibility(View.GONE);
                viewHolder.layout_bangumi.setVisibility(View.GONE);
                viewHolder.video_play_num.setText(bean.getPlay());
                viewHolder.video_review_count.setText(bean.getDanmaku());
                break;
            case TYPE_LIVE:
                viewHolder.layout_video.setVisibility(View.GONE);
                viewHolder.layout_live.setVisibility(View.VISIBLE);
                viewHolder.layout_bangumi.setVisibility(View.GONE);
                viewHolder.item_live_up.setText(bean.getUp());
                viewHolder.item_live_online.setText(bean.getOnline());
                break;
            case TYPE_BANGUMI:
                viewHolder.layout_video.setVisibility(View.GONE);
                viewHolder.layout_live.setVisibility(View.GONE);
                viewHolder.layout_bangumi.setVisibility(View.VISIBLE);
                viewHolder.item_bangumi_update.setText(bean.getDesc1());
                break;
            case GOTO_BANGUMI:
                viewHolder.layout_video.setVisibility(View.GONE);
                viewHolder.layout_live.setVisibility(View.GONE);
                viewHolder.layout_bangumi.setVisibility(View.GONE);
                break;
            default:
                viewHolder.layout_video.setVisibility(View.VISIBLE);
                viewHolder.layout_live.setVisibility(View.GONE);
                viewHolder.layout_bangumi.setVisibility(View.GONE);
                viewHolder.video_play_num.setText(bean.getPlay());
                viewHolder.video_review_count.setText(bean.getDanmaku());
                break;
        }

    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;

        headerViewHolder.text.setText(head.getTitle());
        switch (head.getTitle())
        {
            case "热门焦点":
                headerViewHolder.image.setImageResource(icons[0]);
                break;
            case "正在直播":
                headerViewHolder.image.setImageResource(icons[1]);
                break;
            case "番剧推荐":
                headerViewHolder.image.setImageResource(icons[2]);
                break;
            case "动画区":
                headerViewHolder.image.setImageResource(icons[3]);
                break;
            case "音乐区":
                headerViewHolder.image.setImageResource(icons[4]);
                break;
            case "舞蹈区":
                headerViewHolder.image.setImageResource(icons[5]);
                break;
            case "游戏区":
                headerViewHolder.image.setImageResource(icons[6]);
                break;
            case "鬼畜区":
                headerViewHolder.image.setImageResource(icons[7]);
                break;
            case "科技区":
                headerViewHolder.image.setImageResource(icons[8]);
                break;
            case "生活区":
                headerViewHolder.image.setImageResource(icons[9]);
                break;
            case "时尚区":
                headerViewHolder.image.setImageResource(icons[10]);
                break;
            case "娱乐区":
                headerViewHolder.image.setImageResource(icons[11]);
                break;
            case "电视剧区":
                headerViewHolder.image.setImageResource(icons[12]);
                break;
            case "电影区":
                headerViewHolder.image.setImageResource(icons[13]);
                break;
        }

        super.onBindHeaderViewHolder(holder);
    }

    @Override
    public RecyclerView.ViewHolder getFooterViewHolder(View view) {
        return new FooterViewHolder(view);
    }

    @Override
    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
        FooterViewHolder viewHolder = (FooterViewHolder) holder;

        viewHolder.center_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.animate().rotation(360).setDuration(2000).start();
            }
        });
        viewHolder.more_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.animate().rotation(360).setDuration(2000).start();
            }
        });
        viewHolder.bangumi_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"新番放送",Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.bangumi_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"番剧索引",Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"查看更多",Toast.LENGTH_SHORT).show();
            }
        });


        switch (type){
            case TYPE_RECOMMENDED:
                //推荐  显示刷新布局在中间
                viewHolder.footer_center.setVisibility(View.VISIBLE);
                viewHolder.footer_more.setVisibility(View.GONE);
                viewHolder.footer_bangumi.setVisibility(View.GONE);
                break;
            case TYPE_LIVE:
            case TYPE_ACTIVITY:
                viewHolder.footer_center.setVisibility(View.GONE);
                viewHolder.footer_more.setVisibility(View.VISIBLE);
                viewHolder.footer_bangumi.setVisibility(View.GONE);
                break;
            case TYPE_BANGUMI:
            case GOTO_BANGUMI:
                viewHolder.footer_center.setVisibility(View.GONE);
                viewHolder.footer_more.setVisibility(View.GONE);
                viewHolder.footer_bangumi.setVisibility(View.VISIBLE);
                break;
            default:
                viewHolder.footer_center.setVisibility(View.VISIBLE);
                viewHolder.footer_more.setVisibility(View.GONE);
                viewHolder.footer_bangumi.setVisibility(View.GONE);
                break;
        }



        super.onBindFooterViewHolder(holder);
    }



    class FooterViewHolder extends RecyclerView.ViewHolder{
        View footer_center;//刷新布局在中心   --
        View footer_more;  //带有查看更多的button的布局
        View footer_bangumi;//番剧的布局
        ImageView center_refresh;
        ImageView  more_refresh;
        ImageView  bangumi_left;
        ImageView bangumi_right;
        Button btn_more;


        public FooterViewHolder(View itemView) {
            super(itemView);
            footer_center = itemView.findViewById(R.id.footer_center);
            footer_more = itemView.findViewById(R.id.footer_more);
            footer_bangumi = itemView.findViewById(R.id.footer_bangumi);
            center_refresh = (ImageView) itemView.findViewById(R.id.center_refresh);
            more_refresh = (ImageView) itemView.findViewById(R.id.more_refresh);
            bangumi_left = (ImageView) itemView.findViewById(R.id.bangumi_left);
            bangumi_right = (ImageView) itemView.findViewById(R.id.bangumi_right);
            btn_more = (Button) itemView.findViewById(R.id.footer_btn_more);
        }
    }


    class HeaderViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView text;

        public HeaderViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.title_icon);
            text = (TextView) itemView.findViewById(R.id.title_content);
        }
    }


    class ContentViewHolder extends RecyclerView.ViewHolder{
        CardView body;
        ImageView body_image;
        TextView title;

        View layout_video,layout_live,layout_bangumi;
        TextView video_play_num,video_review_count;
        TextView item_live_up,item_live_online;
        TextView item_bangumi_update;


        public ContentViewHolder(View itemView) {
            super(itemView);

            body = $(itemView,R.id.body_cardview);
            body_image = $(itemView,R.id.body_image);
            title = $(itemView,R.id.body_text);
            layout_video = $(itemView,R.id.layout_video);
            layout_live = $(itemView,R.id.layout_live);
            layout_bangumi = $(itemView,R.id.layout_bangumi);
            video_play_num = $(itemView,R.id.video_play_num);
            video_review_count = $(itemView,R.id.video_review_count);
            item_live_up = $(itemView,R.id.item_live_up);
            item_live_online = $(itemView,R.id.item_live_online);
            item_bangumi_update = $(itemView,R.id.item_bangumi_update);


        }

        public <T extends View>T $(View v,int id){
            return (T) v.findViewById(id);
        }
    }
}
