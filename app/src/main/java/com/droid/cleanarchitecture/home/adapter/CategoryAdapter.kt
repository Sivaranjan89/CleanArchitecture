package com.droid.cleanarchitecture.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.droid.cleanarchitecture.R
import com.droid.cleanarchitecture.db.ProductsEntity
import com.droid.cleanarchitecture.home.model.Product
import com.droid.cleanarchitecture.home.viewmodel.HomeViewModel

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view.view.*

class CategoryAdapter(
    val items: ArrayList<ProductsEntity>,
    val context: Context?,
    val model: HomeViewModel
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(items.get(position).image).placeholder(R.mipmap.placeholder)
            .into(holder.image)
        holder.name.text = items.get(position).productName
        holder.price.text = items.get(position).price
        holder.item_view.setOnClickListener {
            model.clickedProduct.value = items.get(position)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.item_img
        val name = view.item_name
        val price = view.item_price
        val item_view = view.item_view
    }
}