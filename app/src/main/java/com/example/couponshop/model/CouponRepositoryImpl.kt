package com.example.couponshop.model

import android.util.Log
import com.example.couponshop.R
import com.example.couponshop.presenter.CouponPresenter
import com.example.couponshop.retrofit.Retrofit
import com.example.couponshop.view.RecyclerCouponsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CouponRepositoryImpl(var couponPresenter: CouponPresenter): CouponRepository {
    //TODA LA LOGICA DE CONEXION
    override fun getCouponsAPI() {
        val coupons: ArrayList<Coupon>? = ArrayList<Coupon>()
        val retrofit = Retrofit()
        val apiService = retrofit.getClientService()
        val call = apiService.getCoupons()
        call.enqueue(object : Callback<CouponResponse> {
            override fun onFailure(call: Call<CouponResponse>, t: Throwable) {
                t.message?.let { Log.e("ERROR: ", it) }
                t.stackTrace
            }

            override fun onResponse(
                call: Call<CouponResponse>,
                response: Response<CouponResponse>
            ) {
                /*val offersJsonArray = response.body()?.getAsJsonArray("offers")
                offersJsonArray?.forEach { jsonElement: JsonElement ->
                    var jsonObject = jsonElement.asJsonObject
                    var coupon = Coupon(jsonObject)
                    coupons.add(coupon)
                }*/
                for (c in response.body()?.offers!!) {
                    coupons?.add(c)
                }
                //VIEW
                couponPresenter.showCoupons(coupons)
            }
        })


    }

    }
}