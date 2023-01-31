package com.example.couponshop.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.couponshop.R
import com.example.couponshop.model.Coupon
import com.example.couponshop.model.CouponResponse
import com.example.couponshop.presenter.CouponPresenter
import com.example.couponshop.presenter.CouponPresenterImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), CouponView {

    private var couponPresenter: CouponPresenter? = null

    lateinit var couponSelected: CouponResponse.Offers

    private var rvCoupons: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        couponPresenter = CouponPresenterImpl(this)

        //VIEW
        rvCoupons = findViewById(R.id.rvCoupons)
        rvCoupons?.layoutManager = LinearLayoutManager(this)

        getCoupons()

    }

    override fun showCoupons(coupons: ArrayList<Coupon>) {
        try {
            rvCoupons!!.adapter = RecyclerCouponsAdapter(coupons, R.layout.card_coupon)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getCoupons() {
        couponPresenter?.getCoupons()
    }

}