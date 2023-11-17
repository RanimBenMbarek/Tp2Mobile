package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class StudentAdapter(private val students: Array<Student>) :


    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
        val genderImageView:ImageView = itemView.findViewById(R.id.genderImageView)
        val nameTextView: TextView = itemView.findViewById(R.id.textView)


    }
    var dataFilterList = ArrayList<Student>()
    init {
        dataFilterList.addAll(students)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false)
        return StudentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = dataFilterList[position] // Use filtered data

        val fullName = "${student.nom} ${student.prenom}"
        holder.nameTextView.text = fullName

        if (student.genre == "male") {
            holder.genderImageView.setImageResource(R.drawable.male)
        } else if (student.genre == "female") {
            holder.genderImageView.setImageResource(R.drawable.woman)
        } else {
            // Handle other cases
        }
        holder.checkBox.isChecked = student.etat

        // Add an OnCheckedChangeListener to the checkbox
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            student.etat = isChecked
        }


    }

    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                val originalDataArray = students
                val filteredDataArray = if (charSearch.isEmpty()) {
                    originalDataArray
                } else {
                    originalDataArray.filter { student ->
                        student.prenom.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))
                    }.toTypedArray()
                }

                val filterResults = FilterResults()
                filterResults.values = filteredDataArray
                filterResults.count = filteredDataArray.size
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFilterList = results?.values as ArrayList<Student>
                notifyDataSetChanged()
            }
        }
    }


    override fun getItemCount(): Int {
        return students.size
    }




}
