package com.example.hong3.mybilibili.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hong3.mybilibili.entity.live.Banner;
import com.example.hong3.mybilibili.ui.customView.widget.Section;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by hong3 on 2016/12/7.
 */

public class HomeRecommendRecycleAdapter extends RecyclerView.Adapter {
    private static final String TAG = "HomeRecommendRecycleAda";

    public final static int VIEW_TYPE_HEADER = 0;

    public final static int VIEW_TYPE_FOOTER = 1;

    public final static int VIEW_TYPE_ITEM_LOADED = 2;

    public final static int VIEW_TYPE_LOADING = 3;

    public final static int VIEW_TYPE_FAILED = 4;

    List<Banner> bannerList = new ArrayList<>();
    private HashMap<String, Integer> sectionViewTypeNumber = new HashMap<>();
    LinkedHashMap<String, Section> sections = new LinkedHashMap<>();

    private final static int VIEW_TYPE_QTY = 5;
    private int viewTypeCount;
    
    public HomeRecommendRecycleAdapter(){
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        View view = null;

        for (Map.Entry<String, Integer> entry : sectionViewTypeNumber.entrySet()) {
            if (entry.getValue() <= viewType && viewType < entry.getValue() + VIEW_TYPE_QTY ) {

                Section section = sections.get(entry.getKey());
                int sectionViewType = viewType - entry.getValue();
                switch (sectionViewType) {
                    case VIEW_TYPE_HEADER: {
                        Integer resId = section.getHeaderResourceId();
                        if (resId == null) {
                            throw new NullPointerException("missing header resource");
                        }
                        view = LayoutInflater.from(parent.getContext()).inflate(resId, null);
                        viewHolder = section.getHeaderViewHolder(view);
                        break;
                    }
                    case VIEW_TYPE_FOOTER: {
                        Integer resId = section.getFooterResourceId();
                        if (resId == null) {
                            throw new NullPointerException("missing footer resource");

                        }
                        view = LayoutInflater.from(parent.getContext()).inflate(resId, null);
                        viewHolder = section.getFooterViewHolder(view);

                        break;
                    }
                    case VIEW_TYPE_ITEM_LOADED: {
                        Integer resId = section.getItemResourceId();
                        view = LayoutInflater.from(parent.getContext()).inflate(resId, null);
                        viewHolder = section.getItemViewHolder(view);
                        break;
                    }
                    case VIEW_TYPE_LOADING: {
                        Integer resId = section.getLoadingResourceId();
                        if (resId == null) {
                            throw new NullPointerException("missing loading resource");
                        }
                        view = LayoutInflater.from(parent.getContext()).inflate(resId, null);
                        viewHolder = section.getLoadingViewHolder(view);
                        break;
                    }
                    case VIEW_TYPE_FAILED: {
                        Integer resId = section.getFailsResourceId();
                        if (resId == null) {
                            throw new NullPointerException("missing fails resource");
                        }
                        view = LayoutInflater.from(parent.getContext()).inflate(resId, null);
                        viewHolder = section.getFailsViewHolder(view);
                        break;
                    }
                    default:
                        throw new NullPointerException("Invalid viewType");

                }
            }

        }


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int currentPos = 0;
        for (Map.Entry<String, Section> entry : sections.entrySet()) {
            Section section = entry.getValue();

            int sectionTotal = section.getSectionItemTotal();
            if (position >= currentPos && position < currentPos + sectionTotal) {
                if (section.isHasHeader()) {

                    if (position == currentPos) {
                        getSectionForPosition(position).onBindHeaderViewHolder(holder);
                        return;
                    }
                }
                if (section.isHasFooter()) {

                    if (position == currentPos + sectionTotal - 1) {
                        getSectionForPosition(position).onBindFooterViewHolder(holder);
                        return;
                    }
                }
                getSectionForPosition(position).onBindItemViewHolder(holder, getSectionPosition(position));
                return;
            }
            Log.d(TAG, "onBindViewHolder: + finish");
            currentPos += sectionTotal;
        }

    }

    /**
     * Return the item position relative to the section.
     *
     * @param position position of the item in the adapter
     * @return position of the item in the section
     */
    public int getSectionPosition(int position) {

        int currentPos = 0;

        for (Map.Entry<String, Section> entry : sections.entrySet()) {
            Section section = entry.getValue();

            // ignore invisible sections
//            if (!section.isVisible()) continue;

            int sectionTotal = section.getSectionItemTotal();

            // check if position is in this section
            if (position >= currentPos && position <= (currentPos + sectionTotal - 1)) {
                return position - currentPos - (section.isHasHeader() ? 1 : 0);
            }

            currentPos += sectionTotal;
        }

        throw new IndexOutOfBoundsException("Invalid position");
    }

    public Section getSectionForPosition(int position) {
        int currentPos = 0;
        for (Map.Entry<String, Section> entry : sections.entrySet()) {
            Section section = entry.getValue();
//            if (!section.isVisible()) {
//                continue;
//            }
            int sectionTotal = section.getSectionItemTotal();

            if (position >= currentPos && position < currentPos + sectionTotal) {

                return section;
            }
            currentPos += sectionTotal;
        }
        throw new IndexOutOfBoundsException("Invalid position");
    }

    @Override
    public int getItemCount() {

        int count = 0;
        for (Map.Entry<String, Section> entry : sections.entrySet()) {
            Section section = entry.getValue();
            // ignore invisible sections
//            if (!section.isVisible()) continue;
            count += section.getSectionItemTotal();
        }
        return count;
    }


    public int getSpanSize(int position) {
        int type = getSectionItemViewType(position);
        switch (type){
            case VIEW_TYPE_HEADER:
                return 2;
            case VIEW_TYPE_FOOTER:
                return 2;
            default:
                return 1;
        }

    }


    @Override
    public int getItemViewType(int position) {
        int currentPos = 0;
        for (Map.Entry<String, Section> entry : sections.entrySet()) {
                Section section = entry.getValue();

//            if (!section.isVisible()) {
//                continue;
//            }

            int sectionTotal = section.getSectionItemTotal();
            if (position >= currentPos && position < currentPos + sectionTotal) {
                int viewType = sectionViewTypeNumber.get(entry.getKey());


                if (section.isHasHeader()) {
                    if (position == currentPos) {
                        return viewType;
                    }
                }

                if (section.isHasFooter()) {
                    if (position == currentPos + sectionTotal - 1) {
                        return viewType+1;
                    }
                }

                switch (section.getState()) {
                    case LOADED:
                        return viewType + 2;
                    case LOADING:
                        return viewType + 3;
                    case FAILD:
                        return viewType + 4;
                    default:
                        throw new IllegalStateException("Invalid state");
                }
            }

            currentPos += sectionTotal;
        }


        throw new IndexOutOfBoundsException("Invalid position");
    }

    public int getSectionItemViewType(int position) {
        int viewType = getItemViewType(position);
        return viewType % VIEW_TYPE_QTY;
    }



    public LinkedHashMap<String, Section> getSections() {
        return sections;
    }

    public void addSection(String tag, Section section) {

        this.sections.put(tag, section);
        this.sectionViewTypeNumber.put(tag, viewTypeCount);
        viewTypeCount += VIEW_TYPE_QTY;
    }

    public String addSection(Section section) {
        String tag = UUID.randomUUID().toString();
        addSection(tag, section);
        return tag;
    }

    public Section getSection(String tag){
        return sections.get(tag);
    }

    public void removeSection(String tag){
        sections.remove(tag);
    }

    public void clearSection(){
        sections.clear();
    }

    public static class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
