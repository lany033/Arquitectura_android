package com.example.couponshop.presenter

import com.example.couponshop.model.Coupon
import com.example.couponshop.model.CouponInteractor
import com.example.couponshop.model.CouponInteractorImpl
import com.example.couponshop.view.CouponView

class CouponPresenterImpl(var couponView: CouponView ): CouponPresenter {

    private var couponInteractor: CouponInteractor() = CouponsInteractorImpl()

    override fun showCoupons(coupons: ArrayList<Coupon>) {
       couponView.showCoupons(coupons)
    }

    override fun getCoupons() {
        couponInteractor.getCouponsAPI()
    }
}