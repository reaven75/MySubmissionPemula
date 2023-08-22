package com.example.mysubmissionpemula


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mysubmissionpemula.databinding.ItemRowParpolBinding

class ListParpolAdapter(private  val listParpol: ArrayList<Parpol>): RecyclerView.Adapter<ListParpolAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    private lateinit var binding: ItemRowParpolBinding


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    class ListViewHolder(var binding: ItemRowParpolBinding) : RecyclerView.ViewHolder(binding.root) {
//        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
//        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
//        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowParpolBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return  ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listParpol.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
       val (name, description, photo) = listParpol[position]
        holder.binding.imgItemPhoto.setImageResource(photo)
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description
        holder.itemView.setOnClickListener{ onItemClickCallback.onItemClicked(listParpol[holder.adapterPosition])}
    }
    interface  OnItemClickCallback {
        fun onItemClicked(data: Parpol)
    }
}