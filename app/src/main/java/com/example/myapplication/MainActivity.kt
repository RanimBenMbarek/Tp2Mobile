package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var spinner: Spinner
    private lateinit var searchEditText: EditText
    private lateinit var spinnerFilterOptions: Spinner
    private val students: Array<Student> = arrayOf(
        Student("Mbarek", "Ranim", "female", "Cours"),
        Student("Doss", "Chiraz", "female", "TP"),
        Student("Seddik", "Farah", "female", "TP")
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.spinner)
        searchEditText=findViewById(R.id.editText)
        spinnerFilterOptions = findViewById(R.id.spinnerFilterOptions)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val matieres = listOf("Cours", "TP")
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, matieres)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                filterAndUpdateAdapter()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                filterAndUpdateAdapter()
            }
        }

        val filterOptions = listOf("All", "Absent", "Présent")
        spinnerFilterOptions.adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, filterOptions)
        spinnerFilterOptions.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                filterAndUpdateAdapter()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                filterAndUpdateAdapter()
            }
        }

        filterAndUpdateAdapter()
    }

    private fun filterAndUpdateAdapter() {
        val selectedCourse = spinner.selectedItem as? String ?: ""
        val selectedFilterOption = spinnerFilterOptions.selectedItem as? String ?: "All"

        val filteredStudentsArray = when (selectedFilterOption) {
            "All" -> students.filter { it.matiere == selectedCourse }
            "Absent" -> students.filter { it.matiere == selectedCourse && !it.etat }
            "Présent" -> students.filter { it.matiere == selectedCourse && it.etat }
            else -> emptyList()
        }.toTypedArray()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = StudentAdapter(filteredStudentsArray)
    }

}
