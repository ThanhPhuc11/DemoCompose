package com.example.democompose.model

data class BaseResponse<T>(
    var unitModels: T? = null,
    var gameInfoModels: List<GameInfoModel?>? = null,
    var updated: Long? = null
)