package payton.gann.genderguesser.servicecall

import io.reactivex.Observable
import payton.gann.genderguesser.model.GenderData
import retrofit2.http.GET
import retrofit2.http.Query

interface GenderInterface {

    //https://api.genderize.io/?name=payton

    @GET("?")
    fun getGenderData(
            @Query("name") name : String
    ) : Observable<GenderData>
}