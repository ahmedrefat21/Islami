package com.bignerdranch.android.islami.models

data class QuranAPIDataModel(
    val id: Int,
    val chapter_id: Int,
    val file_size: Double,
    val format: String,
    val audio_url: String
)

data class QuranList(
    val audio_files: List<QuranAPIDataModel>
)