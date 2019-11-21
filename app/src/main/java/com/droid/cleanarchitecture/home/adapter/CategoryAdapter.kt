package com.droid.cleanarchitecture.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.droid.cleanarchitecture.BR
import com.droid.cleanarchitecture.R
import com.droid.cleanarchitecture.databinding.ItemViewBinding
import com.droid.cleanarchitecture.db.entity.ProductsEntity
import com.droid.cleanarchitecture.home.viewmodel.HomeViewModel

class CategoryAdapter(
    val items: ArrayList<ProductsEntity>,
    val context: Context?,
    val model: HomeViewModel
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemViewBinding>(
            LayoutInflater.from(context),
            R.layout.item_view,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    class ViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Any) {
            binding.setVariable(BR.product, data)
            binding.executePendingBindings()
        }
    }
}