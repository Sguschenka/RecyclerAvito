package com.example.recycleravito

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerAdapter() : RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {

    private var values: MutableList<Int> = ArrayList()
    var myViewModel : MyViewModel = MyViewModel()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recyclerview_item,
            parent,
            false
        )

        return MyViewHolder(itemView)
    }



    override fun getItemCount() = values.size


    fun addItem(){
        val item = myViewModel.addNewItem()
        val index = myViewModel.itemList.value?.indexOf(item)
        if (index == null) {return}
        val listItemSize : Int = myViewModel.itemList.value!!.size
        if (values.size != listItemSize) values.add(index, item)
        notifyItemInserted(index)

    }



    fun refreshItems(items: MutableList<Int>) {
        this.values = items
        notifyDataSetChanged()
    }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.textViewItem?.text = values[position].toString()
            holder.itemButton.setOnClickListener {

                myViewModel.addNewDeleted(values[holder.adapterPosition])
                values.removeAt(holder.adapterPosition)
                notifyItemRemoved(holder.adapterPosition)
                myViewModel.updateListItems(values)
            }

        }




    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewItem: TextView? = null
        val itemButton: Button
        init {
            textViewItem = itemView.findViewById(R.id.textViewItem)
            itemButton = itemView.findViewById(R.id.buttonItem)
        }
    }


}
