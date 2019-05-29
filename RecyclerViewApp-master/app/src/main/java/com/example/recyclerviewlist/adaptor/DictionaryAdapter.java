package com.example.recyclerviewlist.adaptor;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.recyclerviewlist.R;
import com.example.recyclerviewlist.data.TaskContractV2;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.customViewHolder> {
    private String[] entriesDataSet;
    private Context mContext;
    private Cursor mCursor;

    public static class customViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public TextView entryTextView;
        public RelativeLayout relativeLayout;

        public customViewHolder(View v) {
            super(v);
            view = v;
            entryTextView = (TextView) v.findViewById(R.id.tv_dictionary_entry);
        }
    }

    public DictionaryAdapter(Context context, Cursor cursor)
    {
        mContext = context;
        mCursor = cursor;
    }

    @Override
    public DictionaryAdapter.customViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(mContext)
                .inflate(R.layout.dictionary_content, parent, false);
        customViewHolder vh = new customViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(DictionaryAdapter.customViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position)) return;
        String name = mCursor.getString(mCursor.getColumnIndex(TaskContractV2.TaskEntry.COLUMN_DESCRIPTION));
        holder.entryTextView.setText(name);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor)
    {
        if(mCursor != null){
            mCursor.close();
        }

        mCursor = newCursor;

        if(newCursor != null)
        {
            notifyDataSetChanged();
        }
    }
}
