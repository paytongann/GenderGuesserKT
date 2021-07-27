package payton.gann.genderguesser.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import payton.gann.genderguesser.R
import payton.gann.genderguesser.viewmodel.GenderViewModel

class GenderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editTextName = findViewById<EditText>(R.id.et_name)
        val textViewGender = findViewById<TextView>(R.id.tv_gender)
        val textViewGenderProbability = findViewById<TextView>(R.id.tv_gender_probability)
        val textViewGenderCount = findViewById<TextView>(R.id.tv_gender_count)
        val loginButton = findViewById<Button>(R.id.btn_gender)

        loginButton.setOnClickListener {
            GenderViewModel().loadGenderData(editTextName.text.toString()).observe(
                this,
                {
                    when (it.gender) {
                        "female" -> editTextName.setBackgroundColor(resources.getColor(R.color.pink))
                        "male" -> editTextName.setBackgroundColor(resources.getColor(R.color.blue))
                    }
                    textViewGender.text = it.gender
                    val probablity = (it.probability * 100).toString() + "%"
                    textViewGenderProbability.text = probablity
                    val count = "count: " + it.count
                    textViewGenderCount.text = count
                }
            )
        }
    }
}