package com.example.adopet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adopet.repository.petsForAdoption
import com.example.adopet.ui.pets.PetsAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerViewPets = findViewById<RecyclerView>(R.id.petsRecyclerView)
        recyclerViewPets.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }
        val petsAdapter = PetsAdapter()
        recyclerViewPets.adapter = petsAdapter
        petsAdapter.submitList(petsForAdoption.map { it.pet })
    }


}