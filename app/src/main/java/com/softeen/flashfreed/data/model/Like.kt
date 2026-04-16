package com.softeen.flashfreed.data.model

data class Like(
    val uid: String = "",
    val likedAt: Long = System.currentTimeMillis()
)