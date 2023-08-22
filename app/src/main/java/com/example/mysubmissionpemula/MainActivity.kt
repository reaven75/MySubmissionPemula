package com.example.mysubmissionpemula

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysubmissionpemula.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding

    private lateinit var  rvParpol: RecyclerView
    private val list = ArrayList<Parpol>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvParpol = findViewById(R.id.rv_parpol)
        rvParpol.setHasFixedSize(true)

        list.addAll(getListParpol())
        showRecylerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_list -> {
                val intentToAbout = Intent(this@MainActivity,AboutActivity::class.java)
                startActivity(intentToAbout)
                Log.d("BUTTON ABOUT", "BERHASIL ")
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun getListParpol(): ArrayList<Parpol> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDesc = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataLeader = resources.getStringArray(R.array.data_leader)
        val dataBorn = resources.getStringArray(R.array.data_born)
        val listParpol =  ArrayList<Parpol>()
        for(i in dataName.indices) {
            val parpol = Parpol(dataName[i], dataDesc[i], dataPhoto.getResourceId(i, -1),dataLeader[i], dataBorn[i] )
            listParpol.add(parpol)
        }
        return listParpol
    }

    private fun showRecylerList() {
        rvParpol.layoutManager = LinearLayoutManager(this)
        val listParpolAdapter = ListParpolAdapter(list)
        rvParpol.adapter = listParpolAdapter

        listParpolAdapter.setOnItemClickCallback(object : ListParpolAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Parpol) {
               val intentToDetail = Intent(this@MainActivity,ActivityDetail::class.java)
                intentToDetail.putExtra("DATA",data)
                startActivity(intentToDetail)

            }
        })
    }

}