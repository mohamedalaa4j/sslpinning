package com.example.sslpinning


import com.google.gson.annotations.SerializedName

data class ContactUsResponse(
    @SerializedName("data")
    val `data`: List<Data?>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?
) {
    data class Data(
        @SerializedName("address")
        val address: String?,
        @SerializedName("facebook")
        val facebook: String?,
        @SerializedName("instagram")
        val instagram: String?,
        @SerializedName("linkedIn")
        val linkedIn: String?,
        @SerializedName("location")
        val location: String?,
        @SerializedName("mail")
        val mail: String?,
        @SerializedName("phone")
        val phone: String?,
        @SerializedName("twitter")
        val twitter: String?,
        @SerializedName("youtube")
        val youtube: String?
    )
}