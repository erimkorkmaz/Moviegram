package com.example.movies.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey val id : Int,
    @ColumnInfo val title : String,
    @ColumnInfo @SerializedName("adult")val isAdult : Boolean,
    @ColumnInfo @SerializedName("poster_path")val posterPath: String,
    @ColumnInfo @SerializedName("release_date") val releaseDate: String,
    @ColumnInfo val overview : String
    ) : Parcelable {
}