package com.example.democompose.model

import com.google.gson.annotations.SerializedName

data class ChampionModel(
    @SerializedName("champion_id")
    var championId: String? = null,
    var __v: Int? = null,
    var cost: Int? = null,
    var createdAt: String? = null,
    var image: String? = null,
    var name: String? = null,
    var path: String? = null,
    var property: PropertyModel? = null,
    var set: String? = null,
    var skill: SkillModel? = null,
    @SerializedName("suggest_item")
    var suggestItem: List<SuggestItem>? = null,
    var traits: List<TraitModel>? = null,
    var updatedAt: String? = null,
    var slug: String? = null
)