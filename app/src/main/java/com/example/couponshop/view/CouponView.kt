package com.example.couponshop.view

import com.example.couponshop.model.Coupon

interface CouponView {
    //VISTA
    fun showCoupons( coupons: ArrayList<Coupon>)

    //Presentador
    fun getCoupons()
}