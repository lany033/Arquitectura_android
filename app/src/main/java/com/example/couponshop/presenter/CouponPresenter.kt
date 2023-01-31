package com.example.couponshop.presenter

import com.example.couponshop.model.Coupon

interface CouponPresenter {
    //vista
    fun showCoupons( coupons: ArrayList<Coupon> )

    //interactor
    fun getCoupons()
}