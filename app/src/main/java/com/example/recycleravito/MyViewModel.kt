package com.example.recycleravito

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    var itemList : MutableLiveData<MutableList<Int>> = MutableLiveData()
    var deletedItemList : MutableLiveData<MutableList<Int>> = MutableLiveData()

    init {

        var listItem : MutableList<Int> = ArrayList()
        for (i in 1..15) {

            listItem.add(i)
        }
        deletedItemList.value = mutableListOf()
        itemList.value = listItem
    }

    fun getListItems() = itemList

    fun getDeletedItems() = deletedItemList

    fun updateListItems(listItem : MutableList<Int>){
        itemList.value = listItem
    }

    fun addNewDeleted(deletedItem : Int){
        deletedItemList.value?.add(deletedItem)
    }

    fun addNewItem(): Int {
        val maxItem = itemList.value?.max()
        val sizeDel = deletedItemList.value?.size
        val rnds = (0..maxItem!!).random()
        if (sizeDel != 0){
            val item = deletedItemList.value!![0]
            if (rnds == itemList.value!!.size)
                itemList.value?.add(item)
            else
                itemList.value?.add(rnds, item)
            deletedItemList.value!!.removeAt(0)
            return item

        } else {
            if (rnds == itemList.value!!.size)
                itemList.value!!.add(maxItem+1)
             else
                 itemList.value!!.add(rnds, maxItem+1)
            return maxItem + 1
        }


    }
}