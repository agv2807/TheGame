package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlayersListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var array = arrayListOf<Player>()

    fun setData(players: ArrayList<Player>){
        array = players
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vh = MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.player, parent, false))
        return vh
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mHolder = holder as MyViewHolder
        mHolder.bind(array[position])

        holder.deleteIcon.setOnClickListener{
            removeItem(pos = position)
        }
    }

    override fun getItemCount(): Int = array.size

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val deleteIcon = itemView.findViewById<ImageView>(R.id.delete_icon)
        val textView = itemView.findViewById<TextView>(R.id.player)

        fun bind(str: Player){
            textView.text = str.name
        }
    }

    private fun removeItem(pos: Int){
        array.removeAt(pos)
        notifyDataSetChanged()
    }

}