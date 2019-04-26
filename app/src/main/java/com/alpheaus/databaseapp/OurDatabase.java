package com.alpheaus.databaseapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Account.class}, version = 1)
    public abstract class OurDatabase extends RoomDatabase{
        public abstract AccountDao accountDao();
        //singleton
        private static OurDatabase instance;

        public static OurDatabase getInstance(Context context){
            if(instance == null)
            {
                instance = Room.databaseBuilder(context, OurDatabase.class, "bank_db").allowMainThreadQueries().build();
            }
            return instance;

        }

}
