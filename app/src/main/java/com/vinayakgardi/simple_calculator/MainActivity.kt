package com.vinayakgardi.simple_calculator

import android.graphics.Color.red
import android.media.VolumeShaper.Operation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var op1 : Double?=null
    private var op2 : Double=0.0
    private var pendingoperation = "="
    private lateinit var result : EditText
    private lateinit var newNumber : EditText
    private lateinit var  displayOperation : TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        result = findViewById(R.id.result)
        newNumber = findViewById(R.id.newNumber)
        displayOperation = findViewById(R.id.operation)


        // number buttons for input
          val button0 : Button = findViewById(R.id.button0)
          val button1 : Button = findViewById(R.id.button1)
          val button2 : Button = findViewById(R.id.button2)
          val button3 : Button = findViewById(R.id.button3)
          val button4 : Button = findViewById(R.id.button4)
          val button5 : Button = findViewById(R.id.button5)
          val button6 : Button = findViewById(R.id.button6)
          val button7 : Button = findViewById(R.id.button7)
          val button8 : Button = findViewById(R.id.button8)
          val button9 : Button = findViewById(R.id.button9)
          val buttonDot : Button = findViewById(R.id.buttonDot)

       // operation buttons
        val buttonPlus : Button = findViewById(R.id.buttonPlus)
        val buttonEquals : Button = findViewById(R.id.buttonEquals)
        val buttonMinus : Button = findViewById(R.id.buttonMinus)
        val buttonMultiply : Button = findViewById(R.id.buttonMultiply)
        val buttonDivide : Button = findViewById(R.id.buttonDivide)
        val buttonClear : Button = findViewById(R.id.buttonClear)

        val listener = View.OnClickListener { v->
            val b = v as Button
            newNumber.append(b.text)
        }



        button0.setOnClickListener (listener)
        button1.setOnClickListener (listener)
        button2.setOnClickListener (listener)
        button3.setOnClickListener (listener)
        button4.setOnClickListener (listener)
        button5.setOnClickListener (listener)
        button6.setOnClickListener (listener)
        button7.setOnClickListener (listener)
        button8.setOnClickListener (listener)
        button9.setOnClickListener (listener)
        buttonDot.setOnClickListener (listener)



        buttonClear.setOnClickListener {
            result.setText("")
            newNumber.setText("")
            displayOperation.text = ""
            op1 = null
            op2 = 0.0
        }

        val opListener = View.OnClickListener { v->
            val op = (v as Button).text.toString()
            val value = newNumber.text.toString()
            if(value.isNotEmpty()){
               performOperation(value,op)
            }
            pendingoperation = op
            displayOperation.text = pendingoperation
        }

        buttonEquals.setOnClickListener(opListener)
        buttonMinus.setOnClickListener(opListener)
        buttonPlus.setOnClickListener(opListener)
        buttonDivide.setOnClickListener(opListener)
        buttonMultiply.setOnClickListener(opListener)



    }

    private fun performOperation(value: String, op: String) {
        if(op1 == null){
          op1 = value.toDouble()
        }else {
            op2 = value.toDouble()

            if (pendingoperation == "=") {
                pendingoperation = op
            }
            when (pendingoperation) {
                "=" -> op1 = op2
                "/" -> if (op2 == 0.0) {
                    op1 = Double.NaN
                }else{
                    op1 = op1!!/ op2
                }

                "*" -> op1 = op1!!*op2
                "-" ->op1 = op1!!-op2
                "+" -> op1 = op1!!+op2
            }
        }
         result.setText(op1.toString())
        newNumber.setText("")
    }


}