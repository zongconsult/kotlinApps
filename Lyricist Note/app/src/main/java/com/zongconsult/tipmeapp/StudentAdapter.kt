package com.zongconsult.tipmeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private var stdList: ArrayList<StudentModel> = ArrayList()
    private var onClickItem: ((StudentModel) -> Unit)? = null
    private var onClickDeleteItem: ((StudentModel) -> Unit)? = null
    private var onClickEditLyricItem: ((StudentModel) -> Unit)? = null

    fun addItem(items: ArrayList<StudentModel>) {
        this.stdList = items
        notifyDataSetChanged()
    }

    fun setOnClickItem(callBack: (StudentModel) -> Unit) {

        this.onClickItem = callBack
    }

    fun setOnClickDeleteItem(callBack: (StudentModel) -> Unit) {

        this.onClickDeleteItem = callBack
    }

    fun setOnClickEditLyricItem(callBack: (StudentModel) -> Unit) {

        this.onClickEditLyricItem = callBack
    }

    class StudentViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        private var id = view.findViewById<TextView>(R.id.tvId)
        private var name = view.findViewById<TextView>(R.id.tvName)
        private var email = view.findViewById<TextView>(R.id.tvEmail)
        var btnDelete = view.findViewById<Button>(R.id.btnDelete)
        var btnEditLyric = view.findViewById<Button>(R.id.btnEditLyric)

        fun bindView(std: StudentModel) {
            id.text = std.id.toString()
            name.text = std.title
            email.text = std.desc

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StudentViewHolder(

        LayoutInflater.from(parent.context).inflate(R.layout.card_items_std, parent, false)
    )

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val std = stdList[position]
        holder.bindView(std)
        holder.itemView.setOnClickListener { onClickItem?.invoke(std) }
        holder.btnDelete.setOnClickListener { onClickDeleteItem?.invoke(std) }
        holder.btnEditLyric.setOnClickListener { onClickEditLyricItem?.invoke(std) }
    }

    override fun getItemCount(): Int {
        return stdList.size
    }

}