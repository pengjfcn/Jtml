package com.github.jtml.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jtml.R;

/**
 * Created by pengjf on 2016/5/12.
 */
public class MyAdapter extends BaseRecyclerAdapter<String> {

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_buy_frist, parent, false);
        return new MyHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, String data) {
        if(viewHolder instanceof MyHolder) {
         //   ((MyHolder) viewHolder).text.setText(data);
        }
    }

    class MyHolder extends BaseRecyclerAdapter.Holder {
      //  TextView text;
        public MyHolder(View itemView) {
            super(itemView);
         //   text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
