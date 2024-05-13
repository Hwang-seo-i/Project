package com.example.project.board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.dataclass.BoardDataClass
import com.example.project.R

class BoardAdapterActivity(private val data: List<BoardDataClass>) : RecyclerView.Adapter<BoardAdapterActivity.BoardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return BoardViewHolder(view)
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class BoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
