package com.example.project.View.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.model.data.BoardDataClass

class BoardAdapter(
    var data: List<BoardDataClass>,
    private val itemClickListener: (Int) -> Unit
) : RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return BoardViewHolder(view)
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { itemClickListener(position) }
    }

    override fun getItemCount() = data.size

    inner class BoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewIndex: TextView = itemView.findViewById(R.id.number_data)
        private val textViewTitle: TextView = itemView.findViewById(R.id.title_data)
        private val textViewDate: TextView = itemView.findViewById(R.id.date_data)

        fun bind(item: BoardDataClass) {
            textViewIndex.text = item.number.toString()
            textViewTitle.text = item.title
            textViewDate.text = item.date
        }
    }
}