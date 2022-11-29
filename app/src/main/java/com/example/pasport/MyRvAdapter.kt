package com.example.pasport

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pasport.databinding.ItemRvBinding
import com.example.pasport.db.User

class MyRvAdapter(var list: List<User>) : RecyclerView.Adapter<MyRvAdapter.Vh>() {

    inner class Vh(var rvItemBinding: ItemRvBinding):RecyclerView.ViewHolder(rvItemBinding.root){
        fun onBind(user: User, position: Int ){
            rvItemBinding.name.text = user.name
            rvItemBinding.surname.text = user.surname
            rvItemBinding.passportNum.text = user.number
            rvItemBinding.image.setImageURI(Uri.parse(Data.path))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context) , parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

}