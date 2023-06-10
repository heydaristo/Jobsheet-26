package com.heydar.jobsheet26

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        var id = intent?.getStringExtra("id")
        val simpanBtn = findViewById<Button>(R.id.btnSimpan)
        val judulEdit = findViewById<EditText>(R.id.judulEdit)
        val isiEdit = findViewById<EditText>(R.id.isiEdit)

        judulEdit.setText(intent?.getStringExtra("judul"))
        isiEdit.setText(intent?.getStringExtra("isi"))

        simpanBtn.setOnClickListener {
            val db = DBHelper(this, null)
            if (id == null) {
                db.addData(judulEdit.text.toString(), isiEdit.text.toString())
            } else {
                db.updateData(Data(id, judulEdit.text.toString(), isiEdit.text.toString()))
            }

            db.close()
            finish()
        }
    }
}