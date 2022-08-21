package com.example.democompose.model

data class SkillModel(
    var type: String? = null,
    var img: String? = null,
    var name: String? = null,
    var description: String? = null,
    var sets: List<SetModel>? = null
)