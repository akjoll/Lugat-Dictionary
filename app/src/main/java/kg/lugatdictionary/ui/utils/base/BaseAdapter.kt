package kg.lugatdictionary.ui.utils.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<Item, VB : ViewBinding, VH : BaseVH<Item, VB>> :
    RecyclerView.Adapter<VH>() {

    protected var items = ArrayList<Item>()

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    open fun addData(items: ArrayList<Item>) {
        if (items.isEmpty()) return
        val positionStart = this.items.size
        this.items.addAll(items)
        notifyItemRangeInserted(positionStart, items.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    open fun setData(items: List<Item>) {
        this.items = ArrayList(items)
        notifyDataSetChanged()
    }

    fun getItem(position: Int) = items[position]

}

