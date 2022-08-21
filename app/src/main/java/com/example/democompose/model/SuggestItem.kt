package com.example.democompose.model

data class SuggestItem(
    var item_image_id: String? = null,
    var sub_items: List<SubItem?>? = null
)