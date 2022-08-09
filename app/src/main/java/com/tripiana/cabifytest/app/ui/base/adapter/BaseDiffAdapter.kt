package com.tripiana.cabifytest.app.ui.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import androidx.recyclerview.widget.RecyclerView

abstract class BaseDiffAdapter<K : Idable>(
    var data: MutableList<K> = mutableListOf()
) : RecyclerView.Adapter<BaseDiffAdapter.BaseViewHolder>() {

    @LayoutRes
    abstract fun getLayout(viewType: Int): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder(LayoutInflater.from(parent.context).inflate(getLayout(viewType), parent, false))

    override fun getItemCount(): Int = data.size

    override fun onViewAttachedToWindow(holder: BaseViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.onAttach()
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.onDetach()
    }

    open fun update(newList: List<K>) {
        val diffResult = DiffUtil.calculateDiff(BaseDiff(newList, data))
        data = ArrayList(newList)
        diffResult.dispatchUpdatesTo(object : ListUpdateCallback {
            override fun onInserted(position: Int, count: Int) {
                notifyItemRangeInserted(position, count)
            }

            override fun onRemoved(position: Int, count: Int) {
                notifyItemRangeRemoved(position, count)
            }

            override fun onMoved(fromPosition: Int, toPosition: Int) {
                notifyItemMoved(fromPosition, toPosition)
            }

            override fun onChanged(position: Int, count: Int, payload: Any?) {
                notifyItemRangeChanged(position, count, payload)
            }
        })
    }

    class BaseViewHolder(v: View) : RecyclerView.ViewHolder(v), LifecycleOwner {
        val binding: ViewDataBinding? = DataBindingUtil.bind(v)

        private var lifecycleRegistry = LifecycleRegistry(this)

        override fun getLifecycle(): Lifecycle = lifecycleRegistry

        init {
            lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
            binding?.lifecycleOwner = this
        }

        fun onAttach() {
            lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }

        fun onDetach() {
            lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        }
    }

    inner class BaseDiff(
        private val newList: List<Idable>,
        private val oldList: List<Idable>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition] == newList[newItemPosition]

    }
}