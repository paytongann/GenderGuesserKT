package payton.gann.genderguesser.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import payton.gann.genderguesser.model.GenderData
import payton.gann.genderguesser.servicecall.GenderInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GenderViewModel : ViewModel() {

    private val genderData = MutableLiveData<GenderData>()

    @SuppressLint("CheckResult")
    fun loadGenderData(name: String): MutableLiveData<GenderData> {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://api.genderize.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        val genderInterface: GenderInterface = retrofit.create(GenderInterface::class.java)
        genderInterface.getGenderData(name)
                .subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(
                {
                    genderData.value = it
                    Log.d("TAG", "gender success: $it")
                },
                { e ->
                    Log.d("TAG", "gender error: " + e.localizedMessage)
                }
        )
        return genderData
    }

}