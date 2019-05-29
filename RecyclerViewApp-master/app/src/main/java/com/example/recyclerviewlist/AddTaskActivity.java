package com.example.recyclerviewlist;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.recyclerviewlist.data.TaskContractV2;
import com.example.recyclerviewlist.data.TaskDbHelperV2;

public class AddTaskActivity extends AppCompatActivity {

    private String mCategory = "";
    private String Meal = "Meal";
    private String Shopping = "Shopping";
    private String Study = "String";
    private String Job = "Job";
    private String Traffic = "Traffic";

    private MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_word);

        ((RadioButton) findViewById(R.id.radButton1)).setChecked(true);
        mCategory = Meal;
    }

    public void onClickAddTask(View view)
    {
        String input = ((EditText) findViewById(R.id.editWordDescription)).getText().toString();
        if(input.length() == 0) return;
        TaskDbHelperV2 dbHelper = new TaskDbHelperV2(this); //Change the context
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TaskContractV2.TaskEntry.COLUMN_DESCRIPTION, input);
        contentValues.put(TaskContractV2.TaskEntry.COLUMN_PRIORITY, mCategory);
        long newRowID = db.insert(TaskContractV2.TaskEntry.TABLE_NAME, null, contentValues);
        Toast.makeText(getBaseContext(), "Added " + input + " to " + mCategory, Toast.LENGTH_LONG).show();
        finish();
    }

    public void onPrioritySelected(View view){
        if(((RadioButton) findViewById(R.id.radButton1)).isChecked()){
            mCategory = Meal;
        } else if(((RadioButton) findViewById(R.id.radButton2)).isChecked()){
            mCategory = Shopping;
        } else if(((RadioButton) findViewById(R.id.radButton3)).isChecked()){
            mCategory = Study;
        } else if(((RadioButton) findViewById(R.id.radButton4)).isChecked()){
            mCategory = Job;
        } else if(((RadioButton) findViewById(R.id.radButton5)).isChecked()){
            mCategory = Traffic;
        }
    }
}
