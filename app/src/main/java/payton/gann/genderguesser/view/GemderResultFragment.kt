package payton.gann.genderguesser.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_gemder_result.*
import kotlinx.android.synthetic.main.fragment_gemder_result.view.*
import payton.gann.genderguesser.R
import payton.gann.genderguesser.viewmodel.GenderViewModel

private const val FEMALE = "female"
private const val MALE = "male"
private const val COUNT = "Count: "
private const val PERCENT = "%"

class GenderResultsFragment : DialogFragment() {

    companion object {

        const val TAG = "GenderResultsFragment"
        private const val KEY_NAME = "KEY_NAME"
        private var gender = ""
        private var probability = ""
        private var count = ""

        fun newInstance(
            name: String
        ): GenderResultsFragment {
            val args = Bundle()
            args.putString(KEY_NAME, name)
            val genderResultsFragment = GenderResultsFragment()
            genderResultsFragment.arguments = args
            return genderResultsFragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gemder_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel =  ViewModelProvider(this).get(GenderViewModel::class.java)
        viewModel.loadGenderData(arguments?.getString(KEY_NAME).toString()).observe(
            this,
            {
                gender = it.gender
                probability = (it.probability*100).toString()
                count = it.count.toString()
                setupView(view)
            }
        )
        setupClickListeners(view)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    @SuppressLint("SetTextI18n")
    private fun setupView(view: View) {
        when (gender) {
            FEMALE -> gender_result_layout.setBackgroundColor(resources.getColor(R.color.pink))
            MALE -> gender_result_layout.setBackgroundColor(resources.getColor(R.color.blue))
        }
        view.tv_name.text = arguments?.getString(KEY_NAME)
        view.tv_gender.text = gender
        view.tv_gender_probability.text = probability + PERCENT
        view.tv_gender_count.text = COUNT + count
    }

    private fun setupClickListeners(view: View) {
        view.iv_exit_results.setOnClickListener {
            dismiss()
        }
    }

}