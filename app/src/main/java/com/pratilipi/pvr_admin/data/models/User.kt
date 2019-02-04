package com.pratilipi.pvr_admin.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("_id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("password")
    @Expose
    var password: String? = null
    @SerializedName("city")
    @Expose
    var city: String? = null
    @SerializedName("isAdmin")
    @Expose
    var isAdmin: Boolean? = null
    @SerializedName("__v")
    @Expose
    var v: Int? = null

}