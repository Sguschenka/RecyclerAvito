package com.example.recycleravito

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {



    private val myViewModel by lazy {ViewModelProviders.of(this).get(MyViewModel::class.java)}



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = MyRecyclerAdapter()


        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recyclerView.layoutManager = GridLayoutManager(this, 4)
        } else
            recyclerView.layoutManager = GridLayoutManager(this, 2)

        recyclerView.adapter = adapter
       myViewModel.getListItems().observe(this, Observer {
           it?.let {
               adapter.refreshItems(it as MutableList<Int>) } })
       val job: Job = GlobalScope.launch(Dispatchers.Main) {
                while(true) {
                    delay(5000)
                    adapter.addItem()
                }
       }

    }
}