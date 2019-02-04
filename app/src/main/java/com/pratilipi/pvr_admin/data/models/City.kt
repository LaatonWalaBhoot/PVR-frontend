package com.pratilipi.pvr_admin.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class City {

    @SerializedName("theatres")
    @Expose
    var theatres: List<String>? = null
    @SerializedName("_id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("__v")
    @Expose
    var v: Int? = null

}