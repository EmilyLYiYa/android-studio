package com.example.recyclerviewlist.data;

import android.provider.BaseColumns;

public final class TaskContractV2 {
    private TaskContractV2(){}

    public class TaskEntry implements BaseColumns{
        public static final String TABLE_NAME = "tasks";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_PRIORITY = "priority";
    }
}
