package com.example.couponshop.model

import com.example.couponshop.presenter.CouponPresenter

class CouponInteractorImpl(var couponPresenter: CouponPresenter): CouponInteractor {

    private var couponRepository: CouponRepository = CouponRepositoryImpl(couponPresenter)

    override fun getCouponsAPI() {
        couponRepository.getCouponsAPI()
    }
}