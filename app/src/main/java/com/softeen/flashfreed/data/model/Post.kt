package com.softeen.flashfreed.data.model

data class Post(
    val id: String = "",
    val authorUid: String = "",
    val authorName: String = "",
    val authorPhotoUrl: String = "",
    val content: String = "",
    val imageUrl: String = "",
    val likesCount: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)