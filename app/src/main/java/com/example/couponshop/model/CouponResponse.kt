package com.example.couponshop.model

data class CouponResponse(
    val offers : ArrayList<Offers>
){
    data class Offers(
        val id: String? = "",
        val image_url: String? = "",
        val title: String? = "",
        val descriptionShort: String? = "",
        val categories: String? = "",
        val description:String? = "",
        val offer: String? = "",
        val website: String? = "",
        val end_date: String? = "",
        val url: String? = "",
    ){

    }
}
