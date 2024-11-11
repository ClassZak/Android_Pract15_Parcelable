package com.example.android_pract15_parcelable

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.NumberFormatException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun onClick(view: View):Unit{


        val speciesEditTextText=findViewById<EditText>(R.id.MainActivitySpeciesEditText).text.toString()
        val yearEditTextText=findViewById<EditText>(R.id.MainActivityYearEditText).text.toString()
        val monthEditTextText=findViewById<EditText>(R.id.MainActivityMonthEditText).text.toString()

        var animal:Animal=Animal("",0,1U)

        if(speciesEditTextText.length==0 && yearEditTextText.length==0 && monthEditTextText.length==0){
            val toast:Toast=Toast.makeText(this,R.string.input_error_empty_fields,Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        if (speciesEditTextText.length==0){
            val toast:Toast=Toast.makeText(this,R.string.species_input_error_empty_field,Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        if (yearEditTextText.length==0){
            val toast:Toast=Toast.makeText(this,R.string.year_input_error_empty_field,Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        if (monthEditTextText.length==0){
            val toast:Toast=Toast.makeText(this,R.string.month_input_error_empty_field,Toast.LENGTH_SHORT)
            toast.show()
            return
        }



        try {
            animal.yearBorn=yearEditTextText.toShort()
        }
        catch (e:NumberFormatException) {
            val toast:Toast=Toast.makeText(this,R.string.year_input_year_wrong_year,Toast.LENGTH_SHORT)
            toast.show()
            return
        }

        try {
            animal.monthBorn=monthEditTextText.toUByte()
        }
        catch (e:Exception) {
            val toast:Toast=Toast.makeText(this,R.string.month_input_error,Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        animal.species=speciesEditTextText

        val intent: Intent=Intent(this@MainActivity,SecondActivity::class.java)
        intent.putExtra("animal",animal)

        startActivity(intent)
    }
}