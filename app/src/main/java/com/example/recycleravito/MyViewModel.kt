package com.example.recycleravito

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    var itemList : MutableLiveData<MutableList<Int>> = MutableLiveData()           // Список значений в recyclerView
    var deletedItemList : MutableLiveData<MutableList<Int>> = MutableLiveData()    // Список удаленных значений из recyclerView

    init {                                                                         // При иницизализации значений делаем 15,
                                                                                   // Список удаленных делаем пустым
        var listItem : MutableList<Int> = ArrayList()
        for (i in 1..15) {

            listItem.add(i)
        }
        deletedItemList.value = mutableListOf()
        itemList.value = listItem
    }

    fun getListItems() = itemList

    fun getDeletedItems() = deletedItemList

    fun updateListItems(listItem : MutableList<Int>){                            // Для обновления элементов списка
        itemList.value = listItem
    }

    fun addNewDeleted(deletedItem : Int){                                        // Для добавления элемента в список удаленных
        deletedItemList.value?.add(deletedItem)
    }

    fun addNewItem(): Int {                                                      // Логика добавления нового элемента в список
        val maxItem = itemList.value?.max()                                      // Если в удаленных ничего нет, добавляется элемент max() + 1
        val sizeDel = deletedItemList.value?.size                                // Если есть, то забирается нулевой элемент из удаленных
        val rnds = (0..maxItem!!).random()                                       // Реализована очередь для удаленных
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