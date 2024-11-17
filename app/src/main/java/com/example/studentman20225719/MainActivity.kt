package com.example.studentman20225719


import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnStudentItemClickListener {
    lateinit var students: MutableList<StudentModel>
    lateinit var studentAdapter: StudentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AddText("20")
        students = mutableListOf(
            StudentModel("Nguyễn Văn An", "SV001"),
            StudentModel("Trần Thị Bảo", "SV002"),
            StudentModel("Lê Hoàng Cường", "SV003"),
            StudentModel("Phạm Thị Dung", "SV004"),
            StudentModel("Đỗ Minh Đức", "SV005"),
            StudentModel("Vũ Thị Hoa", "SV006"),
            StudentModel("Hoàng Văn Hải", "SV007"),
            StudentModel("Bùi Thị Hạnh", "SV008"),
            StudentModel("Đinh Văn Hùng", "SV009"),
            StudentModel("Nguyễn Thị Linh", "SV010"),
            StudentModel("Phạm Văn Long", "SV011"),
            StudentModel("Trần Thị Mai", "SV012"),
            StudentModel("Lê Thị Ngọc", "SV013"),
            StudentModel("Vũ Văn Nam", "SV014"),
            StudentModel("Hoàng Thị Phương", "SV015"),
            StudentModel("Đỗ Văn Quân", "SV016"),
            StudentModel("Nguyễn Thị Thu", "SV017"),
            StudentModel("Trần Văn Tài", "SV018"),
            StudentModel("Phạm Thị Tuyết", "SV019"),
            StudentModel("Lê Văn Vũ", "SV020")
        )
        AddText("22")

        studentAdapter = StudentAdapter(students, this)

        findViewById<RecyclerView>(R.id.recycler_view_students).run {
            adapter = studentAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        AddText("57")
        findViewById<Button>(R.id.btn_add_new).setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.add_student)
            val editFullName = dialog.findViewById<EditText>(R.id.edit_fullname)
            val editStudentId = dialog.findViewById<EditText>(R.id.edit_student_id)
            val buttonCancel = dialog.findViewById<Button>(R.id.button_cancel)
            val buttonOk = dialog.findViewById<Button>(R.id.button_ok)
            buttonCancel.setOnClickListener {
                dialog.dismiss()
            }
            buttonOk.setOnClickListener {
                val canAddStudent = !editFullName.text.isNullOrEmpty() || !editStudentId.text.isNullOrEmpty()
                if (canAddStudent){
                    students.add(0, StudentModel(editFullName.text.toString(), editStudentId.text.toString()))
                    studentAdapter.notifyItemInserted(0)
                    findViewById<RecyclerView>(R.id.recycler_view_students).scrollToPosition(0)
                }
                dialog.dismiss()
            }

            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.show()
        }
        AddText("19")
    }
    override fun onEditClick(student: StudentModel) {
        val index = students.indexOf(student)
        if (index == -1) {
            return
        }
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.add_student)
        val editFullName = dialog.findViewById<EditText>(R.id.edit_fullname)
        val editStudentId = dialog.findViewById<EditText>(R.id.edit_student_id)
        val buttonCancel = dialog.findViewById<Button>(R.id.button_cancel)
        val buttonOk = dialog.findViewById<Button>(R.id.button_ok)
        editFullName.setText(student.studentName)
        editStudentId.setText(student.studentId)
        buttonCancel.setOnClickListener {
            dialog.dismiss()
        }
        buttonOk.setOnClickListener {
            val canModifyStudent = !editFullName.text.isNullOrEmpty() || !editStudentId.text.isNullOrEmpty()
            if (canModifyStudent){
                students[index] = StudentModel(editFullName.text.toString(), editStudentId.text.toString())
                studentAdapter.notifyItemChanged(index)
            }
            dialog.dismiss()
        }
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.show()
    }

    override fun onRemoveClick(student: StudentModel) {
        val index = students.indexOf(student)
        if (index == -1) {
            return
        }
        AlertDialog.Builder(this).setMessage("Are you sure to delete this student?")
            .setPositiveButton("Yes") { _, _ ->
                students.removeAt(index)
                studentAdapter.notifyItemRemoved(index)
                Snackbar.make(findViewById(R.id.main), "Student deleted", Snackbar.LENGTH_SHORT)
                    .setAction("Undo") {
                        students.add(index, student)
                        studentAdapter.notifyItemInserted(index)
                        findViewById<RecyclerView>(R.id.recycler_view_students).scrollToPosition(index)
                    }
                    .show()
            }
            .setNegativeButton("No") { _, _ -> }
            .create().show()
    }
    fun AddText(s: String){
        val name = findViewById<TextView>(R.id.name)
        name.text = buildString {
            append(name.text.toString())
            append(s)
        };
    }
}


