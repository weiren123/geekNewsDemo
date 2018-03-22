package com.example.administrator.geeknewsdemo.ui.zhihu.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.geeknewsdemo.R;
import com.example.administrator.geeknewsdemo.component.ImageLoader;
import com.example.administrator.geeknewsdemo.model.bean.DailyListBean;
import com.example.administrator.geeknewsdemo.widget.SquareImageView;
import com.example.administrator.geeknewsdemo.widget.ZhihuDiffCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/20.
 */

public class DailyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;
    private Context mContext;
    private List<DailyListBean.StoriesBean> mList;
    private boolean isBefore = false;
    private TopPagerAdapter mAdapter;
    private List<DailyListBean.TopStoriesBean> mTopList;
    private TopPagerAdapter topPagerAdapter;
    private ViewPager topViewPager;
    private String currentTitle;

    public DailyAdapter(Context mContext, List<DailyListBean.StoriesBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }

    public enum ITEM_TYPE {
        ITEM_TOP,       //滚动栏
        ITEM_DATE,      //日期
        ITEM_CONTENT    //内容
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TOP.ordinal()) {
            topPagerAdapter = new TopPagerAdapter(mContext, mTopList);
            return new TopViewHolder(inflater.inflate(R.layout.item_top, parent, false));
        } else if (viewType == ITEM_TYPE.ITEM_DATE.ordinal()) {
            return new DateViewHolder(inflater.inflate(R.layout.item_date, parent, false));
        }
        return new ContentViewHolder(inflater.inflate(R.layout.item_daily, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        if (!isBefore) {
            if (position == 0) {
                return ITEM_TYPE.ITEM_TOP.ordinal();
            } else if (position == 1) {
                return ITEM_TYPE.ITEM_DATE.ordinal();
            } else {
                return ITEM_TYPE.ITEM_CONTENT.ordinal();
            }
        } else {
            if (position == 0) {
                return ITEM_TYPE.ITEM_DATE.ordinal();
            } else {
                return ITEM_TYPE.ITEM_CONTENT.ordinal();
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ContentViewHolder){
            final int contentPosition;
            if(isBefore) {
                contentPosition = position - 1;
            } else {
                contentPosition = position - 2;
            }
            ((ContentViewHolder)holder).tvDailyItemTitle.setText(mList.get(contentPosition).getTitle());
            if (mList.get(contentPosition).getReadState()) {
                ((ContentViewHolder)holder).tvDailyItemTitle.setTextColor(ContextCompat.getColor(mContext,R.color.news_read));
            } else {
                ((ContentViewHolder)holder).tvDailyItemTitle.setTextColor(ContextCompat.getColor(mContext,R.color.news_unread));
            }
            ImageLoader.load(mContext,mList.get(contentPosition).getImages().get(0),((ContentViewHolder)holder).ivDailyItemImage);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    if(onItemClickListener != null) {
//                        ImageView iv = (ImageView) view.findViewById(R.id.iv_daily_item_image);
//                        onItemClickListener.onItemClick(contentPosition,iv);
//                    }
                }
            });
        }else if(holder instanceof DateViewHolder){
            ((DateViewHolder) holder).tvDailyDate.setText(currentTitle);
        }else {
            ((TopViewHolder)holder).vpTop.setAdapter(topPagerAdapter);
            topViewPager = ((TopViewHolder)holder).vpTop;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addDailyDate(DailyListBean info) {
        currentTitle = "今日热闻";
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ZhihuDiffCallback(mList, info.getStories()), true);
        mList = info.getStories();
        mTopList = info.getTop_stories();
        isBefore = false;
        diffResult.dispatchUpdatesTo(this);
//        notifyDataSetChanged();
    }
    public static class TopViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.vp_top)
        ViewPager vpTop;
        @BindView(R.id.ll_point_container)
        LinearLayout llPointContainer;

        public TopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class DateViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_daily_date)
        TextView tvDailyDate;

        public DateViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_daily_item_image)
        SquareImageView ivDailyItemImage;
        @BindView(R.id.tv_daily_item_title)
        TextView tvDailyItemTitle;
        public ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class TopPagerAdapter extends PagerAdapter{
        private List<DailyListBean.TopStoriesBean> mList = new ArrayList<>();
        private Context context;

        public TopPagerAdapter(Context context, List<DailyListBean.TopStoriesBean> mList)
        {
            this.mList = mList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.item_top_pager, container, false);
            ImageView ivImage = (ImageView) view.findViewById(R.id.iv_top_image);
            TextView tvTitle = (TextView) view.findViewById(R.id.tv_top_title);
            ImageLoader.load(context,mList.get(position).getImage(),ivImage);
            tvTitle.setText(mList.get(position).getTitle());
            final int id = mList.get(position).getId();
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent();
//                    intent.setClass(mContext, ZhihuDetailActivity.class);
//                    intent.putExtra(Constants.IT_ZHIHU_DETAIL_ID, id);
//                    intent.putExtra(Constants.IT_ZHIHU_DETAIL_TRANSITION, true);
//                    mContext.startActivity(intent);
                }
            });
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
