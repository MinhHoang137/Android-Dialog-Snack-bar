package com.example.studentman20225719

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(val students: MutableList<StudentModel>,
                     val listener: OnStudentItemClickListener): RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textStudentName: TextView = itemView.findViewById(R.id.text_student_name)
        val textStudentId: TextView = itemView.findViewById(R.id.text_student_id)
        public val imageEdit: ImageView = itemView.findViewById(R.id.image_edit)
        public val imageRemove: ImageView = itemView.findViewById(R.id.image_remove)
        init {
            imageEdit.setOnClickListener {
                listener.onEditClick(students[adapterPosition])
            }
            imageRemove.setOnClickListener {
                listener.onRemoveClick(students[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_student_item,
            parent, false)
        return StudentViewHolder(itemView)
    }

    override fun getItemCount(): Int = students.size

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]

        holder.textStudentName.text = student.studentName
        holder.textStudentId.text = student.studentId
    }
}

interface OnStudentItemClickListener {
    fun onEditClick(student: StudentModel)
    fun onRemoveClick(student: StudentModel)
}

