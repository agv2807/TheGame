package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class GameScreen : AppCompatActivity() {

    private var points = 0
    private lateinit var selectedPlayer: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)

        val upContainer = findViewById<RelativeLayout>(R.id.up_container)
        val playersListIcon = findViewById<ImageView>(R.id.players_icon)
        val editIcon = findViewById<ImageView>(R.id.edit_icon)

        val centerCont = findViewById<LinearLayout>(R.id.center_cont)
        val playerTask = findViewById<TextView>(R.id.player_task)
        val hodTv = findViewById<TextView>(R.id.hod_tv)
        val taskTv = findViewById<TextView>(R.id.task_tv)
        val greenBtn = findViewById<Button>(R.id.greenBtn)
        val yellowBtn = findViewById<Button>(R.id.yellowBtn)
        val redBtn = findViewById<Button>(R.id.redBtn)
        val punishmentBtn = findViewById<Button>(R.id.punishment_btn)
        val completeBtn = findViewById<Button>(R.id.complete_btn)

        val playersTable = findViewById<LinearLayout>(R.id.players_table)
        val cancelIcon = findViewById<ImageView>(R.id.cancel_icon)
        val playersList = findViewById<RecyclerView>(R.id.players_list)


        val adapter = PlayersGameListAdapter()

        val arrFromIntent = intent.getSerializableExtra("playersArray") as ArrayList<Player>
        val playersArr = arrFromIntent.toCollection(ArrayList())
        selectedPlayer = playersArr[0]

        playerTask.text = "Очередь игрока ${selectedPlayer.name}"

        playersList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        playersList.adapter = adapter
        adapter.setData(playersArr)


        playersListIcon.setOnClickListener{
            playersTable.visibility = View.VISIBLE
            upContainer.visibility = View.GONE
            centerCont.visibility = View.GONE
        }
        cancelIcon.setOnClickListener{
            playersTable.visibility = View.GONE
            upContainer.visibility = View.VISIBLE
            centerCont.visibility = View.VISIBLE
        }

        //Кнопки заданий
        greenBtn.setOnClickListener{
            if (points == 0){
                points = 2
                taskTv.text = getRandomTask(points)
                hodTv.text = resources.getString(R.string.turn)

            } else {
                Toast.makeText(this, "Поздняк метаться", Toast.LENGTH_SHORT).show()
            }

        }
        yellowBtn.setOnClickListener{
            if (points == 0){
                points = 4
                taskTv.text = getRandomTask(points)
                hodTv.text = resources.getString(R.string.turn)
            } else {
                Toast.makeText(this, "Поздняк метаться", Toast.LENGTH_SHORT).show()
            }

        }
        redBtn.setOnClickListener{
            if (points == 0){
                points = 6
                taskTv.text = getRandomTask(points)
                hodTv.text = resources.getString(R.string.turn)
            } else {
                Toast.makeText(this, "Поздняк метаться", Toast.LENGTH_SHORT).show()
            }
        }

        //Кнопки наказания и выполнено
        punishmentBtn.setOnClickListener{
            if (points == 0){
                Toast.makeText(this, "Сначала выбери ход", Toast.LENGTH_SHORT).show()
            } else if (points/2 == 1){
                Toast.makeText(this, "Хорош уже", Toast.LENGTH_SHORT).show()
            } else {
                hodTv.text = resources.getString(R.string.punish)
                taskTv.text = getRandomPunishment()
                points /= 2
            }
        }
        completeBtn.setOnClickListener{
            if (points == 0){
                Toast.makeText(this, "Сначала выбери ход", Toast.LENGTH_SHORT).show()
            } else {
                taskTv.text = "Выбирай быстрее"
                hodTv.text = ""
                selectedPlayer.score += points
                adapter.setData(playersArr)
                points = 0
                playersArr.indexOf(selectedPlayer)
                if ( playersArr.indexOf(selectedPlayer) + 1 == playersArr.size){
                    selectedPlayer = playersArr[0]
                } else {
                    selectedPlayer = playersArr[playersArr.indexOf(selectedPlayer) + 1]
                }
                playerTask.text = "Очередь игрока ${selectedPlayer.name}"
            }
        }

    }

    private fun getRandomPunishment(): String{
        val punishArr = resources.getStringArray(R.array.punishments)
        val randomPunish = punishArr.random()
        return randomPunish
    }

    private fun getRandomTask(points: Int): String {
        var randomTask = ""
        when (points){
            2 -> {
                val tasksArr = resources.getStringArray(R.array.easy_tasks)
                randomTask = tasksArr.random()
            }
            4 -> {
                val tasksArr = resources.getStringArray(R.array.middle_tasks)
                randomTask = tasksArr.random()
            }
            6 -> {
                val tasksArr = resources.getStringArray(R.array.hard_tasks)
                randomTask = tasksArr.random()
            }
        }
        return randomTask
    }
}