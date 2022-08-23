package com.example.democompose.view.detail

import com.example.democompose.utils.AppConstants

class DetailUseCase() {
    fun getUrlImageSplash(): String {
        return AppConstants.BASE_IMAGE_MOBALYTICS
    }

    fun getUrlImageSkill(): String {
        return AppConstants.BASE_IMAGE_DTCL_LOL_SKILL
    }
}