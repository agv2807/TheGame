package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlayersGameListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var array = arrayListOf<Player>()

    fun setData(players: ArrayList<Player>){
        array = players
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vh = MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.player_game, parent, false))
        return vh
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mHolder = holder as PlayersGameListAdapter.MyViewHolder
        mHolder.bind(array[position])
    }

    override fun getItemCount(): Int = array.size

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(str: Player){
            val nameTv = itemView.findViewById<TextView>(R.id.name_tv)
            val scoreTv = itemView.findViewById<TextView>(R.id.score_tv)
            nameTv.text = str.name
            scoreTv.text = str.score.toString()
        }
    }
}