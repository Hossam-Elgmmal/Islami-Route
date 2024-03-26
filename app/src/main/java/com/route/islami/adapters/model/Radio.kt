package com.route.islami.adapters.model

import com.google.gson.annotations.SerializedName


data class Radio(

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("url")
    var url: String? = null,

    @SerializedName("recent_date")
    var recentDate: String? = null

)
