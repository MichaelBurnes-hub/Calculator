package com.example.calculator3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity

class MainActivity : ComponentActivity() {

    private var tvInput: TextView? = null
    var lastNumeric : Boolean = false
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)

        attachOnClickListener(R.id.btn0)
        attachOnClickListener(R.id.btn1)
        attachOnClickListener(R.id.btn2)
        attachOnClickListener(R.id.btn3)
        attachOnClickListener(R.id.btn4)
        attachOnClickListener(R.id.btn5)
        attachOnClickListener(R.id.btn6)
        attachOnClickListener(R.id.btn7)
        attachOnClickListener(R.id.btn8)
        attachOnClickListener(R.id.btn9)

        attachOnClickListener(R.id.btnClear)
        attachOnClickListener(R.id.btnDecimal)
        attachOnClickListener(R.id.btnPlus)
        attachOnClickListener(R.id.btnMinus)
        attachOnClickListener(R.id.btnMultiply)
        attachOnClickListener(R.id.btnDivide)
        attachOnClickListener(R.id.btnEqual)
    }

    private fun attachOnClickListener(buttonId: Int) {
        findViewById<Button>(buttonId).setOnClickListener { onButtonClick(it) }
    }

    private fun onButtonClick(view: View) {
        when (view.id) {
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9 ->
                onDigit(view)
            R.id.btnClear ->
                onClear(view)
            R.id.btnDecimal ->
                onDecimalPoint(view)
            R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide ->
                onOperator(view)
            R.id.btnEqual ->
                onEqual(view)
        }
    }

    fun onDigit(view: View){
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false

    }
    fun onClear(view: View){
        tvInput?.text = ""
    }
    fun onDecimalPoint(view: View) {
        if(lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View){
        //.let creates a lambda giving me the text from the button and creating a null check
        tvInput?.text?.let{

            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }

    }
    fun onEqual(view : View){
        if(lastNumeric){
            //creating variable to display results needs to be string
            var tvValue = tvInput?.text.toString()

            var prefix = ""
            try{
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("-")) {
                    //splits the values entered separating them into an array
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0] //will store the first value from left to right
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }else if(tvValue.contains("+")) {
                    //splits the values entered separating them into an array
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0] //will store the first value from left to right
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }else if(tvValue.contains("*")) {
                    //splits the values entered separating them into an array
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0] //will store the first value from left to right
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }else if(tvValue.contains("/")) {
                    //splits the values entered separating them into an array
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0] //will store the first value from left to right
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }

            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String) : String{
        var  value = result
        if(result.contains(".0")){
            value = result.substring(0, result.length - 2)
        }
        return value
    }
    private fun isOperatorAdded(value : String) : Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }

    }
}