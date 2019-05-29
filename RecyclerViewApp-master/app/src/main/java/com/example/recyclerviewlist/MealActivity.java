package com.example.recyclerviewlist;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.example.recyclerviewlist.adaptor.DictionaryAdapter;
import com.example.recyclerviewlist.data.TaskContractV2;
import com.example.recyclerviewlist.data.TaskDbHelperV2;

import java.util.ArrayList;
import java.util.List;

public class MealActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private DictionaryAdapter mAdapter;
    private SQLiteDatabase db;
    private Long[] itemEntry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        TaskDbHelperV2 dbHelper = new TaskDbHelperV2(MealActivity.this);
        db = dbHelper.getWritableDatabase();

        itemEntry = eachEntry(getAllEntries());

        recyclerView = (RecyclerView) findViewById(R.id.meal_recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new DictionaryAdapter(this, getAllEntries());
        recyclerView.setAdapter(mAdapter);

        //Swipe to delete function
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                int position = viewHolder.getAdapterPosition();
                String selection = TaskContractV2.TaskEntry._ID + " = ?";
                String[] selectionArgs = {String.valueOf(itemEntry[position])};
                int deletePosition = db.delete(TaskContractV2.TaskEntry.TABLE_NAME, selection, selectionArgs);
                Toast.makeText(getBaseContext(), "Delete Successful!", Toast.LENGTH_SHORT).show();
                mAdapter.swapCursor(getAllEntries());
            }
        }).attachToRecyclerView(recyclerView);
    }

    private Cursor getAllEntries(){
        //String[] projection = {TaskContractV2.TaskEntry.COLUMN_DESCRIPTION};
        String selection = TaskContractV2.TaskEntry.COLUMN_PRIORITY + " = ?";
        String[] selectionArgs = {"Meal"};
        return db.query(TaskContractV2.TaskEntry.TABLE_NAME, null, selection, selectionArgs, null, null, null);
    }

    private Long[] eachEntry(Cursor entryCursor)
    {
        List<Long> items = new ArrayList<Long>();
        while (entryCursor.moveToNext())
        {
            Long itemID = entryCursor.getLong(entryCursor.getColumnIndexOrThrow(TaskContractV2.TaskEntry._ID));
            items.add(itemID);
        }
        entryCursor.close();
        Long[] itemArray = items.toArray(new Long[0]);
        return itemArray;
    }
}
