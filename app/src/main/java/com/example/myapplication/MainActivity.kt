package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playersRv = findViewById<RecyclerView>(R.id.players_list)
        val addPlayerBtn = findViewById<Button>(R.id.add_new_player_btn)
        val startBtn = findViewById<Button>(R.id.start_btn)
        val enterNameEt = findViewById<EditText>(R.id.enter_name_et)
        val playersArray = arrayListOf<Player>()
        val adapter = PlayersListAdapter()

        playersRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        addPlayerBtn.setOnClickListener{
            var name = ""
            if (enterNameEt.text.isEmpty()){
                name = getRandomName()
            } else {
                name = enterNameEt.text.toString()
                enterNameEt.setText("")
            }
            playersRv.adapter = adapter
            playersArray.add(Player(name, 0))
            adapter.setData(playersArray)
        }

        startBtn.setOnClickListener{
            if (playersArray.size > 1){
                val intent = Intent(this, GameScreen::class.java)
                intent.putExtra("playersArray", playersArray)
                startActivity(intent)
            } else{
                Toast.makeText(this, "Для игры нужно минимум 2 игрока", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getRandomName(): String{
        val nameArr = resources.getStringArray(R.array.names)
        val randomName = nameArr.random()
        return randomName
    }
}