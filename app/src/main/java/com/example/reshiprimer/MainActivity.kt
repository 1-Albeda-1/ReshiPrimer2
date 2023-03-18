package com.example.reshiprimer

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.math.roundToInt
import kotlin.random.Random

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ButtonStart = findViewById<Button>(R.id.btnStart)
        val ButtonCheck = findViewById<Button>(R.id.btnCheck)
        val FirstOperand = findViewById<TextView>(R.id.txtFirstOperand)
        val TwoOperand = findViewById<TextView>(R.id.txtTwoOperand)
        val Operation = findViewById<TextView>(R.id.txtOperation)
        val EditValue = findViewById<TextView>(R.id.editValue)
        val NumberRight = findViewById<TextView>(R.id.txtNumberRight)
        val NumberWrong = findViewById<TextView>(R.id.txtNumberWrong)
        val AllExamples = findViewById<TextView>(R.id.txtAllExamples)
        val PercentageCorrectAnswers = findViewById<TextView>(R.id.txtPercentageCorrectAnswers)
        val LinearLayout = findViewById<LinearLayout>(R.id.linearLayout3)
        var NRight = 0
        var NWrong = 0
        var rnd = Random
        val Oper:List<String> = listOf("+","-","/","*")

        fun Random()
        {
            EditValue.text=""
            Operation.text = Oper.elementAt(rnd.nextInt(0,4))
            var First = rnd.nextInt(10,100)
            var Second = rnd.nextInt(10,100)
            if(Operation.text=="/")
            {
                while (First.toDouble()%Second.toDouble()!=0.0)
                {
                    First = rnd.nextInt(10,100)
                    Second = rnd.nextInt(10,100)
                }
            }
            if(Operation.text=="*")
            {
                First=rnd.nextInt(1,10)
                Second=rnd.nextInt(1,10)
            }
            FirstOperand.text = First.toString()
            TwoOperand.text = Second.toString()
        }

        fun Calculation(): Boolean {
            return when (Operation.text) {
                "+" -> {
                    (FirstOperand.text.toString().toInt() + TwoOperand.text.toString()
                        .toInt() == EditValue.text.toString().toInt())
                }
                "-" -> {
                    (FirstOperand.text.toString().toInt() - TwoOperand.text.toString()
                        .toInt() == EditValue.text.toString().toInt())
                }
                "/" -> {
                    (FirstOperand.text.toString().toInt() / TwoOperand.text.toString()
                        .toInt() == EditValue.text.toString().toInt())
                }
                "*" -> {
                    (FirstOperand.text.toString().toInt() * TwoOperand.text.toString()
                        .toInt() == EditValue.text.toString().toInt())
                }
                else -> true
            }
        }

        ButtonStart.setOnClickListener(){
            ButtonStart.isEnabled = false
            ButtonCheck.isEnabled = true
            EditValue.isEnabled = true
            LinearLayout.setBackgroundColor(Color.WHITE)
            FirstOperand.text=rnd.nextInt(10,100).toString()
            TwoOperand.text=rnd.nextInt(10,100).toString()
            EditValue.text=""
            Random()
        }
        ButtonCheck.setOnClickListener(){
            ButtonCheck.isEnabled = false
            ButtonStart.isEnabled = true
            EditValue.isEnabled = false
            if(Calculation())
            {
                LinearLayout.setBackgroundColor(Color.GREEN)
                ++NRight
                NumberRight.text=NRight.toString()
            }
            else
            {
                LinearLayout.setBackgroundColor(Color.RED)
                ++NWrong
                NumberWrong.text=NWrong.toString()
            }
            AllExamples.text=(NRight+NWrong).toString()
            PercentageCorrectAnswers.text = ((((NRight.toDouble() / (AllExamples.text.toString().toDouble() / 100.0)) * 100).roundToInt().toDouble() / 100).toString() + "%")
        }
    }
}
