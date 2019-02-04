package com.pratilipi.pvr_admin.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EmailRequest {

    @SerializedName("movieName")
    @Expose
    var movieName: String? = null
    @SerializedName("to")
    @Expose
    var to: String? = null
    @SerializedName("from")
    @Expose
    var from: String? = null
    @SerializedName("subject")
    @Expose
    var subject: String? = null
    @SerializedName("text")
    @Expose
    var text: String? = null

}