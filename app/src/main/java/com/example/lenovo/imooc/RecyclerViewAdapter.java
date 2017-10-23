package com.example.lenovo.imooc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/10/21.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<ImoocBean.DataBean> mImoocBean = new ArrayList<>();
    private final static int TYPE_NORMAL = 0;
    final static int TYPE_FOOTER = 1;

    public RecyclerViewAdapter(Context context, List<ImoocBean.DataBean> imoocBeen) {
        this.mContext = context;
        this.mImoocBean = imoocBeen;
        Log.d("aaaa","context");

    }
    @Override
    public int getItemCount() {
        return mImoocBean.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 < mImoocBean.size()) {
            return TYPE_NORMAL;
        } else {
            return TYPE_FOOTER;
        }
    }

    class NormalHolder extends RecyclerView.ViewHolder {
        ImageView mPicBig;
        TextView mTitle;
        TextView mDescription;
        TextView mLearners;
        ImageView mLearner;

        //继承。Constructors are not inherited, you must create a new, identically prototyped constructor in the subclass
        // that maps to its matching constructor in the superclass.
        public NormalHolder(View itemView) {
            super(itemView);
            mLearner = (ImageView) itemView.findViewById(R.id.imooc_cv_learn_tip);
            mPicBig = (ImageView) itemView.findViewById(R.id.imooc_cv_pic);
            mTitle = (TextView) itemView.findViewById(R.id.imooc_cv_name);
            mDescription = (TextView) itemView.findViewById(R.id.imooc_cv_description);
            mLearners = (TextView) itemView.findViewById(R.id.imooc_cv_learner);
        }
    }

    class FooterHolder extends RecyclerView.ViewHolder {
        TextView mFooter;

        public FooterHolder(View itemView) {
            super(itemView);
            mFooter = (TextView) itemView.findViewById(R.id.imooc_item_tip);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("aaaa","concreate");
        if (viewType == TYPE_NORMAL) {
            View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.imooc_item_cv, parent, false);
            NormalHolder normalHolder = new NormalHolder(v);
            return normalHolder;
        } else if (viewType == TYPE_FOOTER) {
            View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.imooc_item_foot, parent, false);
            FooterHolder footerHolder = new FooterHolder(v);
            return footerHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Log.d("aaaa","viewholder");
        if (holder instanceof NormalHolder) {
            Log.d("aaaa","normal");
            ((NormalHolder) holder).mTitle.setText(mImoocBean.get(position).name);
            ((NormalHolder) holder).mDescription.setText(mImoocBean.get(position).description);
            ((NormalHolder) holder).mLearners.setText(mImoocBean.get(position).learner+"");
            Glide
                    .with(mContext)
                    .load(mImoocBean.get(position).picBig)
                    .asBitmap()
                    .override(1080, 600)
                    .error(R.drawable.learning)
                    .into(((NormalHolder) holder).mPicBig);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "this is number" + position, Toast.LENGTH_SHORT).show();
                }
            });
        } else if (holder instanceof FooterHolder) {

        }

    }



}
