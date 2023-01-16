package com.example.adopet.ui.pets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.adopet.R
import com.example.adopet.model.Gender
import com.example.adopet.model.Pet

class PetsAdapter : ListAdapter<Pet, PetsAdapter.PetViewHolder>(PetsDiffUtil()) {

    inner class PetViewHolder(view: View) : ViewHolder(view) {

        private val petImage = view.findViewById<ImageView>(R.id.petImage)
        private val petName = view.findViewById<TextView>(R.id.textViewName)
        private val petAge = view.findViewById<TextView>(R.id.textViewAge)
        private val petGender = view.findViewById<AppCompatImageView>(R.id.petGender)

        fun bind(pet: Pet) {
            petImage.load(pet.images.random()) {
                placeholder(R.drawable.ic_dog)
                transformations(
                    RoundedCornersTransformation(
                        bottomLeft = 48f,
                        bottomRight = 48f,
                        topLeft = 48f
                    )
                )
            }
            petName.text = pet.name
            petAge.text = "${pet.age} years"
            when (pet.gender) {
                Gender.MALE -> petGender.setImageResource(R.drawable.baseline_male_24)
                Gender.FEMALE -> petGender.setImageResource(R.drawable.baseline_female_24)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewT_ype: Int): PetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pet_item, parent, false)
        return PetViewHolder(view)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val pet = getItem(position)
        holder.bind(pet)
    }
}

class PetsDiffUtil : DiffUtil.ItemCallback<Pet>() {
    override fun areItemsTheSame(oldItem: Pet, newItem: Pet): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Pet, newItem: Pet): Boolean {
        return oldItem == newItem
    }

}

