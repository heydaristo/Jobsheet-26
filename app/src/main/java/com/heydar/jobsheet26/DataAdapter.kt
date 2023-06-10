package com.heydar.jobsheet26

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(private val data: ArrayList<Data>?): RecyclerView.Adapter<DataAdapter.DataViewHolder>() {
    class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val judul = itemView.findViewById<TextView>(R.id.txtJudul)
        private val isi = itemView.findViewById<TextView>(R.id.txtIsi)
        private val editBtn = itemView.findViewById<Button>(R.id.btnEdit)
        private val deleteBtn = itemView.findViewById<Button>(R.id.btnHapus)

        fun bind(get: Data) {
            judul.text = get.judul
            isi.text = get.isi

            editBtn.setOnClickListener() {
                val intent = Intent(itemView.context, EditActivity::class.java)
                intent.putExtra("id", get.id)
                intent.putExtra("judul", get.judul)
                intent.putExtra("isi", get.isi)
                itemView.context.startActivity(intent)
            }

            deleteBtn.setOnClickListener() {
                val dialogBuilder = AlertDialog.Builder(itemView.context)
                dialogBuilder.setTitle("Hapus data")
                dialogBuilder.setMessage("Hapus data" + get.judul)
                dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener{ _, _ ->
                    val db = DBHelper(itemView.context, null)
                    val status = db.deleteData(get.id)
                    if(status > -1 ) Toast.makeText(itemView.context, "Data berhasil dihapus", Toast.LENGTH_LONG).show()
                })
                dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ ->
                })

                dialogBuilder.create()
                dialogBuilder.show()
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_list, parent, false))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(data?.get(position) ?: Data("", "", "" ))
    }
    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

}