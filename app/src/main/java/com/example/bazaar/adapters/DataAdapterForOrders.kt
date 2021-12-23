package com.example.bazaar.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bazaar.R
import com.example.bazaar.model.Order
import com.example.bazaar.ui.orders.OrdersListFragment

class DataAdapterForOrders(private var list: ArrayList<Order>,
                           private val context: Context,
                           private val listener: OnItemClickListener,
                           private val listener2: OnItemLongClickListener
) : RecyclerView.Adapter<DataAdapterForOrders.DataViewHolder>() {

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    interface OnItemLongClickListener{
        fun onItemLongClick(position: Int)
    }

    // 1. user defined ViewHolder type - Embedded class!
    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener, View.OnLongClickListener {
        val textView_user: TextView = itemView.findViewById(R.id.user)
        val textView_status: TextView = itemView.findViewById(R.id.status)
        val textView_prod_title: TextView = itemView.findViewById(R.id.productTitle)
        val textView_amount: TextView = itemView.findViewById(R.id.amount)
        val textView_amountInfo: TextView = itemView.findViewById(R.id.amountInfo)
        val textView_price: TextView = itemView.findViewById(R.id.price)
        val textView_priceInfo: TextView = itemView.findViewById(R.id.priceInfo)
        val imageView: ImageView = itemView.findViewById(R.id.imageView_item_layout)

        init{
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
            Log.d("xxx","Init list: ${list.size}, ${list}")
        }
        override fun onClick(p0: View?) {
            val currentPosition = this.adapterPosition
            listener.onItemClick(currentPosition)

        }

        override fun onLongClick(p0: View?): Boolean {
            val currentPosition = this.adapterPosition
            listener2.onItemLongClick(currentPosition)
            return true
        }
    }

    // 2. Called only a few times = number of items on screen + a few more ones
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.order_item_layout, parent, false)
        return DataViewHolder(itemView)
    }


    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list[position]
        //Log.d("xxx","Current item: ${list[position]}")
        Log.d("xxx","Current item: ${currentItem}")
//        holder.textView_prod_title.text = currentItem.title
//        holder.textView_price.text = currentItem.price_per_unit
//        //holder.textView_priceInfo.text = currentItem.
//        holder.textView_user.text = currentItem.username
//        holder.textView_status.text = currentItem.status
//        holder.textView_amount.text = currentItem.units
//        //holder.textView_amountInfo.text = currentItem.username
//        val images = currentItem.images
//        if( images != null && images.size > 0) {
//            Log.d("xxx", "#num_images: ${images.size}")
//        }
        Glide.with(this.context)
            .load(R.drawable.palinka)
            .override(200, 200)
            .into(holder.imageView);
    }

    override fun getItemCount() = list.size

    // Update the list
    fun setData(newlist: ArrayList<Order>){
        list = newlist
    }
}