package com.michael.products.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [ProductEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(ImageConverter::class)
abstract class ProductDatabase: RoomDatabase (){
    abstract val dao:ProductDao
}