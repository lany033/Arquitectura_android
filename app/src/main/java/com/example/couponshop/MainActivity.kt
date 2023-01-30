package com.example.couponshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.couponshop.model.Coupon
import com.example.couponshop.model.CouponResponse
import com.example.couponshop.retrofit.Retrofit
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var couponSelected: CouponResponse.Offers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //VIEW
        val rvCoupons: RecyclerView = findViewById(R.id.rvCoupons)
        rvCoupons.layoutManager = LinearLayoutManager(this)
        val coupons = ArrayList<CouponResponse.Offers>()

        //CONTROLLER
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
                for (cupon in response.body()?.offers!!) {
                    coupons.add(cupon)
                }
                //VIEW
                rvCoupons.adapter = RecyclerCouponsAdapter(coupons, R.layout.card_coupon)
            }
        })


    }

}