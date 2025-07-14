import com.example.recyclerview.data.DeleteReq
import com.example.recyclerview.data.DeleteRes
import com.example.recyclerview.data.IssueResponse
import com.example.recyclerview.data.SignInRequest
import com.example.recyclerview.data.SignInResponse
import com.example.recyclerview.data.SignUpRequest
import com.example.recyclerview.data.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("api/signin")
    fun signIn(@Body request: SignInRequest): Call<SignInResponse>
    @POST("api/signup")
    fun signUp(@Body request: SignUpRequest): Call<SignUpResponse>
    @GET("api/issues")
    fun getAllIssues() : Call<IssueResponse>
    @DELETE("api/issues")
    fun deleteIssue(@Body request : String) : Call<DeleteRes>
}
