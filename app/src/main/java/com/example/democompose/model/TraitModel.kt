package com.example.democompose.model

import com.google.gson.annotations.SerializedName

data class TraitModel(
    var description: String? = null,
    var name: String? = null,
    var sets: List<SetModel?>? = null,
    @SerializedName("trait_id")
    var traitId: String? = null,
    var type: String? = null
)