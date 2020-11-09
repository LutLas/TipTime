package io.erehsawsaltul.tiptime


import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import io.erehsawsaltul.tiptime.databinding.ActivityMainBinding
import java.security.AccessController.getContext
import java.text.NumberFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{
            calculateTip()
        }

    }

    private fun calculateTip() {
        //com.google.android.material.textfield.TextInputEditText
       if (binding.costOfService.isHelperTextEnabled)binding.costOfService.isHelperTextEnabled = false
        val cost = binding.costOfServiceEditText.text.toString().toDoubleOrNull()
         ?: run {
             //
             binding.tipResult.text = ""

             binding.costOfService.isHelperTextEnabled = true
             binding.costOfService.helperText = "Please Enter Cost of Service."
             return
            //return Toast.makeText(this, "Please Enter Cost of Service.", Toast.LENGTH_SHORT).show()
        }
        //
        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
            binding.optionTwentyPercent.id -> 0.20
            binding.optionEighteenPercent.id -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost
        if(binding.roundUpSwitch.isChecked)tip=kotlin.math.ceil(tip)

        val formattedTip = NumberFormat.getCurrencyInstance(Locale("en", "ZM")).format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

        binding.costOfService.isHelperTextEnabled = true
        binding.costOfService.helperText = binding.tipResult.text

        //Toast.makeText(this, "${binding.tipResult.text}", Toast.LENGTH_SHORT).show()
        //binding.costOfService.setText("")
    }
}