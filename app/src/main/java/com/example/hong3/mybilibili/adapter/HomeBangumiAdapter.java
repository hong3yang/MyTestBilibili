package com.example.hong3.mybilibili.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hong3.mybilibili.R;
import com.example.hong3.mybilibili.entity.bangumi.BangumiBean;
import com.example.hong3.mybilibili.entity.live.Banner;
import com.example.hong3.mybilibili.ui.customView.banner.BannerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hong3 on 2016/12/17.
 */

public class HomeBangumiAdapter extends RecyclerView.Adapter {
    private static final String TAG = "HomeBangumiAdapter";
    private final int BANNER = 1;
    private final int HEADER = 2;
    private final int PREVIOUS = 3;
    private final int ADVER = 4;
    private final int NEWBANGUMI = 5;

    BangumiBean bangumiBean;
    Context context;

    private int[] icons = {
            R.mipmap.ic_category_t13, R.mipmap.ic_header_hot,
            R.mipmap.ic_head_live, R.mipmap.ic_category_t1,
    };

    public HomeBangumiAdapter(Context context) {
        this.context = context;
    }

    public void setBangumiBean(BangumiBean bangumiBean) {
        this.bangumiBean = bangumiBean;
        Log.d(TAG, "setBangumiBean: " + bangumiBean.getAd().getBody().size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        if (viewType == BANNER) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_home_bangumi_banner, null);
            return new BannerViewHolder(view);
        }
        if (viewType == HEADER) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_bangumi_header, null);
            return new HeaderViewHolder(view);
        }
        if (viewType == PREVIOUS) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_home_bangumi_content, null);
            return new ContentViewHolder(view);
        }


        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: position  >" + position);
        if (holder instanceof BannerViewHolder) {
            BannerViewHolder viewHolder = (BannerViewHolder) holder;
            viewHolder.banner.delayTime(3).build(formatBanner(bangumiBean.getAd()));
            return;
        }
        position -= 1;
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder viewHolder = (HeaderViewHolder) holder;
            switch (position) {
                case 0:
                    ((HeaderViewHolder) holder).icon.setImageResource(icons[0]);
                    ((HeaderViewHolder) holder).text.setText("7月新番");
                    ((HeaderViewHolder) holder).more.setText("分季列表");
                    return;
                case 4:
                    ((HeaderViewHolder) holder).icon.setImageResource(icons[1]);
                    ((HeaderViewHolder) holder).text.setText("新番连载");
                    ((HeaderViewHolder) holder).more.setText("所有连载");
                    return;
            }
        }
        position -= 1;
        if (holder instanceof ContentViewHolder) {
            ContentViewHolder viewHolder = (ContentViewHolder) holder;
            if (position >= 0 && position < bangumiBean.getPrevious().getList().size()) {
                BangumiBean.PreviousBean.ListBean listBean = bangumiBean.getPrevious().getList().get(position);
                Picasso.with(context).load(listBean.getCover()).into(viewHolder.image);
                viewHolder.des.setText(listBean.getWatching_count() + "人在看");
                viewHolder.title.setText(listBean.getTitle());
                viewHolder.message.setText("更新至" + listBean.getNewest_ep_index() + "话");
            } else if (position >= bangumiBean.getPrevious().getList().size() && position <= bangumiBean.getSerializing().size() + bangumiBean.getPrevious().getList().size()){
                BangumiBean.SerializingBean listBean = bangumiBean.getSerializing().get(position-bangumiBean.getPrevious().getList().size()-1);
                Picasso.with(context).load(listBean.getCover()).into(viewHolder.image);
                viewHolder.des.setText(listBean.getWatching_count() + "人在看");
                viewHolder.title.setText(listBean.getTitle());
                viewHolder.message.setText("更新至" + listBean.getNewest_ep_index() + "话");
            }

            return;
        }

    }


    public List<Banner> formatBanner(BangumiBean.AdBean bean) {
        List<Banner> banners = new ArrayList<>();
        for (int i = 0, len = bean.getHead().size(); i < len; i++) {
            Banner ban = new Banner();
            BangumiBean.AdBean.HeadBean bodyBean = bean.getHead().get(i);
            ban.setTitle(bodyBean.getTitle());
            ban.setLink(bodyBean.getLink());
            ban.setImg(bodyBean.getImg());
            ban.setRemark(bodyBean.getPub_time());
            banners.add(ban);
        }
        return banners;
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (bangumiBean == null) {
            return 0;
        }
        count = 1 + (1 + bangumiBean.getPrevious().getList().size());
        count = count + 1 + bangumiBean.getSerializing().size();

        Log.d(TAG, "getItemCount: cou"+count);
        return count;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return BANNER;
        }
        position -= 1;

        int size = bangumiBean.getPrevious().getList().size();
        if (position == 0) {
            return HEADER;
        } else if (position <= size) {
            return PREVIOUS;
        }
        position -= 1;
        position -= size;
        int size1 = bangumiBean.getSerializing().size();
        if (position == 0) {
            return HEADER;
        } else if (position <= size1) {
            return PREVIOUS;
        }

        return super.getItemViewType(position);
    }

    public int getSpanSize(int position) {

        switch (position) {
            case 0:  //banner
            case 1:  //inter
            case 5:
                return 3;
            default:
                return 1;

        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        BannerView banner;

        public BannerViewHolder(View itemView) {
            super(itemView);

            banner = (BannerView) itemView.findViewById(R.id.bangumi_banner);
            itemView.findViewById(R.id.bangumi_text1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();
                }
            });

            itemView.findViewById(R.id.bangumi_text2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "2", Toast.LENGTH_SHORT).show();
                }
            });

            itemView.findViewById(R.id.bangumi_text3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "3", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView text;
        TextView more;

        public HeaderViewHolder(View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.bangumi_header_icon);
            text = (TextView) itemView.findViewById(R.id.bangumi_header_content);
            more = (TextView) itemView.findViewById(R.id.bangumi_header_more);
        }
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView des;
        TextView title;
        TextView message;

        public ContentViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.content_image);
            des = (TextView) itemView.findViewById(R.id.content_des);
            title = (TextView) itemView.findViewById(R.id.content_title);
            message = (TextView) itemView.findViewById(R.id.content_message);

        }
    }
}
