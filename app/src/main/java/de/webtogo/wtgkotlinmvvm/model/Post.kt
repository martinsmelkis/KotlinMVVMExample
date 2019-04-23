package de.webtogo.wtgkotlinmvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

// Data classes have getters, setters, equals, hashcode and copy methods already generated and available
// public is the default modifier
@Entity(
        tableName = "posts"
)
data class Post(
        @Json(name = "title")
        val title: String,

        @Json(name = "body")
        var body: String,

        @Json(name = "userId")
        val userId: Int,

        @Json(name = "id")
        @PrimaryKey
        val id: Int) : Serializable