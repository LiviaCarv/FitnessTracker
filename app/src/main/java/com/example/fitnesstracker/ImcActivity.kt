package com.example.fitnesstracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import kotlin.math.pow

class ImcActivity : AppCompatActivity() {
    private lateinit var editWeight: EditText
    private lateinit var editHeight: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        val calculateImcButton = findViewById<Button>(R.id.btn_calculate_imc)
        editWeight = findViewById(R.id.edtxt_imc_weight)
        editHeight = findViewById(R.id.edtxt_imc_height)

        calculateImcButton.setOnClickListener{
            setListener()
        }

    }

    private fun setListener() {
        val weight = editWeight.text.toString()
        val height = editHeight.text.toString()
        val result: Double
        if (!validate(weight, height)) {
            makeToast(R.string.warning)
            return
        } else {
            result = calcularImc(weight.toDouble(), height.toDouble())

            AlertDialog.Builder(this).apply {
                setTitle(getString(R.string.your_imc_is, result))
                setMessage(imcResponse(result))
                setPositiveButton(android.R.string.ok) { _, _ -> }
                create()
                show()
            }
        }
    }

    private fun validate(kg: String, cm: String): Boolean {
        return !(kg.isEmpty() || cm.isEmpty() || kg.startsWith("0")  || cm.startsWith("0"))
    }

    private fun makeToast(id: Int) {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
    }
    private fun calcularImc(weight: Double, height: Double): Double {
        return weight/((height/100).pow(2))
    }

    @StringRes
    // indica que a função retorna o Id de um recurso
    private fun imcResponse(imc: Double): Int {
        return when {
            imc < 18.5 -> R.string.imc_low_weight
            imc < 24.9 -> R.string.imc_normal
            imc < 29.9 -> R.string.imc_overweight
            imc  < 35 -> R.string.imc_obesity_one
            else -> R.string.imc_several_obesity
        }

    }

}




