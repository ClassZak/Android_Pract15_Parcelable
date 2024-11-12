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

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getAnimalData()
    }


    fun getAnimalData(){
        val arguments:Bundle? = intent.extras
        if(arguments!=null){
            val animal:Animal?=arguments.getParcelable("animal")as Animal?
            if(animal!=null){
                findViewById<EditText>(R.id.SecondActivityYearEditText).setText(animal.yearBorn.toString())
                findViewById<EditText>(R.id.SecondActivityMonthEditText).setText(animal.monthBorn.toString())
                findViewById<EditText>(R.id.SecondActivitySpeciesEditText).setText(animal.species)
            }
        }
    }
    fun onClick(view: View){
        val speciesEditTextText=findViewById<EditText>(R.id.SecondActivitySpeciesEditText).text.toString()
        val yearEditTextText=findViewById<EditText>(R.id.SecondActivityYearEditText).text.toString()
        val monthEditTextText=findViewById<EditText>(R.id.SecondActivityMonthEditText).text.toString()

        val animal:Animal=Animal("",0,1U)



        if(speciesEditTextText.length==0 && yearEditTextText.length==0 && monthEditTextText.length==0){
            val toast: Toast = Toast.makeText(this,R.string.input_error_empty_fields, Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        if (speciesEditTextText.length==0){
            val toast: Toast = Toast.makeText(this,R.string.species_input_error_empty_field, Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        if (yearEditTextText.length==0){
            val toast: Toast = Toast.makeText(this,R.string.year_input_error_empty_field, Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        if (monthEditTextText.length==0){
            val toast: Toast = Toast.makeText(this,R.string.month_input_error_empty_field, Toast.LENGTH_SHORT)
            toast.show()
            return
        }



        try {
            animal.yearBorn=yearEditTextText.toShort()
        }
        catch (e:NumberFormatException) {
            val toast: Toast = Toast.makeText(this,R.string.year_input_year_wrong_year, Toast.LENGTH_SHORT)
            toast.show()
            return
        }

        try {
            animal.monthBorn=monthEditTextText.toUByte()
        }
        catch (e:Exception) {
            val toast: Toast = Toast.makeText(this,R.string.month_input_error, Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        animal.species=speciesEditTextText

        val intent: Intent =Intent(this@SecondActivity,MainActivity::class.java)
        intent.putExtra("animal",animal)
        startActivity(intent)
    }
}