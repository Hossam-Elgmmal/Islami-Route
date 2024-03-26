package com.route.islami.adapters.model

import com.google.gson.annotations.SerializedName


data class RadiosResponse(

    @SerializedName("radios")
    var radios: List<Radio> = listOf()

)
