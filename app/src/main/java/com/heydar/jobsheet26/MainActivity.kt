package com.heydar.jobsheet26

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var dataView: RecyclerView
    lateinit var dataAdapter: DataAdapter
    var db = DBHelper(this, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTambah = findViewById<Button>(R.id.btnTambah)
        val btnRefresh = findViewById<Button>(R.id.btnRefresh)

        dataView = findViewById(R.id.recycleView)
        dataView.layoutManager = LinearLayoutManager(this)

        dataAdapter = DataAdapter(db.getData())
        dataView.adapter = dataAdapter

        btnTambah.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }

        btnRefresh.setOnClickListener {
            dataAdapter = DataAdapter(db.getData())
            dataView.adapter = dataAdapter
        }
    }
}