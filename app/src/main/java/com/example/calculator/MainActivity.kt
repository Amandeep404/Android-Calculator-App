package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException


class MainActivity : AppCompatActivity() {

    var lastName = false
    var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun onClickDigit(view: View){
        texttView.append((view as Button).text)
        lastName=true
    }
    fun onClr(view: View){
        texttView.text=""
        lastName=false
        lastDot=false
    }
    fun onClickDot(view: View){
        if (lastName && !lastDot){
            texttView.append(".")
            lastName=false
            lastDot=true
        }
    }

    fun onClickBsp(view: View){
        var bsp = texttView.text.toString()
        if (bsp.length>=1){
            bsp= bsp.substring(0, bsp.length-1)
            texttView.text= bsp

        }
    }

    fun onClickEqual(view: View){
        var prefix= ""
        if(lastName){
            var result= texttView.text.toString()
            try{
                if(result.startsWith('-')){
                    prefix="-"
                    result = result.substring(1)

                }
                if(result.contains("-")) {
                    var splitResult = result.split("-")

                    var one = splitResult[0]
                    var two = splitResult[1]

                    if (!prefix.isEmpty()) {
                        one = one + prefix
                    }
                    texttView.text = remove0AfterDot((one.toDouble()  - two.toDouble()).toString())

                }
                if(result.contains("+")) {
                    var splitResult = result.split("+")

                    var one = splitResult[0]
                    var two = splitResult[1]

                    if (!prefix.isEmpty()) {
                        one = one + prefix
                    }
                    texttView.text = remove0AfterDot((one.toDouble()  + two.toDouble()).toString())

                }
                if(result.contains("*")) {
                    var splitResult = result.split("*")

                    var one = splitResult[0]
                    var two = splitResult[1]

                    if (!prefix.isEmpty()) {
                        one = one + prefix
                    }
                    texttView.text = remove0AfterDot((one.toDouble()  * two.toDouble()).toString())

                }
                if(result.contains("/")) {
                    var splitResult = result.split("/")

                    var one = splitResult[0]
                    var two = splitResult[1]

                    if (!prefix.isEmpty()) {
                        one = one + prefix
                    }
                    texttView.text = remove0AfterDot((one.toDouble()  / two.toDouble()).toString())

                }

            }catch (e:ArithmeticException){
                e.printStackTrace()

            }

        }
    }
    fun onClickOperator(view: View){
        if (lastName && !isOperatorAdded(texttView.text.toString())){
            texttView.append((view as Button).text)
            lastName=false
            lastDot=false

        }

    }

    private fun remove0AfterDot(removeZero: String): String {
        var value= removeZero
        if (removeZero.contains(".0")){
            value= removeZero.substring(0, removeZero.length-2)
        }//if 23.00  --> it says start at index 0 and stop at index length-2
        //outpurt: 23
        return value
    }

    private  fun isOperatorAdded(value: String): Boolean{
        return if(value.startsWith("-" )||value.startsWith("*")||value.startsWith("/")||value.startsWith("+")){
            false
        }else{
            value.contains("/")||value.contains("*")||value.contains("-")||value.contains("+")
        }
    }

}

