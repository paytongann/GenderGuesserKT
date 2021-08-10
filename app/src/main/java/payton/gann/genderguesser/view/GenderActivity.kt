package payton.gann.genderguesser.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import payton.gann.genderguesser.R

class GenderActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_gender.setOnClickListener {
            if (et_name.text.isEmpty()) {
                Toast.makeText(this, getString(R.string.enter_valid_name), Toast.LENGTH_LONG).show()
            } else {
                showGenderResults()
            }
        }
    }

    private fun showGenderResults() {
        GenderResultsFragment.newInstance(
            et_name.text.toString()
        ).show(supportFragmentManager, GenderResultsFragment.TAG)
    }
}