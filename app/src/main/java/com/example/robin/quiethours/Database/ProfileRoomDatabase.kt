package com.example.robin.quiethours.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Profile::class], version = 1)
abstract class ProfileRoomDatabase :RoomDatabase() {

    abstract fun profileDao(): ProfileDAO

    companion object{
        @Volatile
        private var INSTANCE: ProfileRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ProfileRoomDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProfileRoomDatabase::class.java,
                    "Profile_Database"
                ).build()
                INSTANCE = instance
                return  instance
            }
        }
    }

}