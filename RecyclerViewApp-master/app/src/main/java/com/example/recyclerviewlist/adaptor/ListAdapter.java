package com.example.recyclerviewlist.adaptor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.recyclerviewlist.MainActivityTwo;
import com.example.recyclerviewlist.MealActivity;
import com.example.recyclerviewlist.R;
import com.example.recyclerviewlist.ShoppingActivity;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.customViewHolder> {
    private String[] chineseDataset;
    private String[] englishDataSet;
    private Context mContext;

    Intent intent;

    public static class customViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public TextView chineseTextView;
        public TextView englishTextView;
        public RelativeLayout relativeLayout;
        public customViewHolder(View v) {
            super(v);
            view = v;
            chineseTextView = (TextView) v.findViewById(R.id.chineseTextView);
            englishTextView = (TextView) v.findViewById(R.id.englishTextView);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.list_content);
        }
    }

    public ListAdapter(Context context, String[] chineseChar, String[] englishChar) {
        chineseDataset = chineseChar;
        englishDataSet = englishChar;
        mContext = context;
    }

    @Override
    public ListAdapter.customViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_content, parent, false);
        customViewHolder vh = new customViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(customViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.chineseTextView.setText(chineseDataset[position]);
        holder.englishTextView.setText(englishDataSet[position]);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if add option is clicked
                if(position == 0) {
                    intent = new Intent(mContext, MealActivity.class);
                    mContext.startActivity(intent);
                } else if(position == 1){
                    intent = new Intent(mContext, ShoppingActivity.class);
                    mContext.startActivity(intent);
                }
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return chineseDataset.length;
    }
}
