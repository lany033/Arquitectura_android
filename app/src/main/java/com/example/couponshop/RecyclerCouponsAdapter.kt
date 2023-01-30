package com.example.couponshop

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.couponshop.model.Coupon
import com.example.couponshop.model.CouponResponse
import com.example.couponshop.utils.Companion
import com.squareup.picasso.Picasso

class RecyclerCouponsAdapter(var coupons : ArrayList<CouponResponse.Offers>, var resource: Int) : RecyclerView.Adapter<RecyclerCouponsAdapter.CardCouponHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CardCouponHolder {
        var view: View = LayoutInflater.from(p0!!.context).inflate(resource, p0, false)
        return CardCouponHolder(view)
    }

    override fun getItemCount(): Int {
        return coupons.size
    }

    override fun onBindViewHolder(p0: CardCouponHolder, p1: Int) {
        var coupon = coupons.get(p1)
        p0.setDataCard(coupon)
    }

    class CardCouponHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var coupon: CouponResponse.Offers? = null
        private var imgCoupon: ImageView = v.findViewById(R.id.imgCoupon)
        private var tvTitle: TextView = v.findViewById(R.id.tvTitle)
        private var tvDescriptionShort: TextView = v.findViewById(R.id.tvDescriptionShort)
        private var tvCategory: TextView = v.findViewById(R.id.tvCategory)
        private var tvDate: TextView = v.findViewById(R.id.tvDate)

        init {
            v.setOnClickListener(this)
        }

        fun setDataCard(coupon: CouponResponse.Offers){
            this.coupon = coupon
            if (!coupon.image_url.equals("")){
                Picasso.get().load(coupon.image_url).resize(520, 520).centerCrop().into(imgCoupon)
            } else {
                Picasso.get().load(R.drawable.header).resize(520, 520).centerCrop().into(imgCoupon)
            }
            tvTitle.setText(coupon.title)
            tvDescriptionShort.setText(coupon.descriptionShort)
            tvCategory.setText(coupon.categories?.let { myCategories(it) })
            tvDate.setText(coupon.end_date)

        }
        fun myCategories(text: String): String{
            val words = text.split(",")
            var newString: String = ""

            for (i in 0..1){
                newString += words.get(i) + " | "
            }

            return newString
        }
        override fun onClick(v: View) {
            coupon?.title?.let { Log.i("CLICK title: ", it) }
            coupon?.categories?.let { Log.i("CLICK category: ", it) }
            coupon?.end_date?.let { Log.i("CLICK end_date: ", it) }
            Companion.couponResponseOffers = coupon!!
            val showPhotoIntent = Intent(v.context, CouponDetailActivity::class.java)
            v.context.startActivity(showPhotoIntent)
            /*val context = v.context
            val showPhotoIntent = Intent(context, CouponDetailActivity::class.java)
            showPhotoIntent.putExtra("COUPON", coupon)
            context.startActivity(showPhotoIntent)
*/
        }

    }

}