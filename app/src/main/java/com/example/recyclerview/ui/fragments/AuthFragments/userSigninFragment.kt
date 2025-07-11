package com.example.recyclerview.ui.fragments.AuthFragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.recyclerview.R
import com.example.recyclerview.activities.HomeActivity
import com.example.recyclerview.data.AuthResponse
import com.example.recyclerview.data.SignInRequest
import com.example.recyclerview.data.User
import com.example.recyclerview.data.network.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import com.example.recyclerview.data.SignInResponse

class UserSigninFragment : Fragment() {

    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var signupText: TextView
    private lateinit var loader : ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_user_signin, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailInput = view.findViewById(R.id.loginEmail)
        passwordInput = view.findViewById(R.id.loginPassword)
        loginButton = view.findViewById(R.id.loginButton)
        signupText = view.findViewById(R.id.moveTosignUp)
        loader = view.findViewById(R.id.buttonLoader)
        loginButton.setOnClickListener {
            handleLogin()
        }

        signupText.setOnClickListener {
            navigateToSignUp()
        }
    }


    fun setLoadingState(isLoading: Boolean) {
        if (isLoading) {
            loginButton.isEnabled = false
            loginButton.text = ""
            loader.visibility = View.VISIBLE
        } else {
            loginButton.isEnabled = true
            loginButton.text = "Login"
            loader.visibility = View.GONE
        }
    }

    private fun handleLogin() {
        val email = emailInput.text.toString().trim()
        val password = passwordInput.text.toString().trim()

        if (email.isEmpty()) {
            showToast("Email field should not be empty")
            return
        }

        if (password.length < 8) {
            showToast("Password must be at least 8 characters")
            return
        }
        setLoadingState(true)
        val user = SignInRequest(email = email, password = password)


        RetrofitClient.api.signIn(user).enqueue(object : Callback<SignInResponse> {
            override fun onResponse(call: Call<SignInResponse>, response: Response<SignInResponse>) {
                Log.i("is success", response.body()?.token ?: "no")
                if (response.isSuccessful && response.body()?.success == true) {
                    val token = response.body()?.token
                    if (token != null) {
                        saveToken(token)
                        navigateToHome()
                    } else {
                        setLoadingState(false)
                        showToast("Token missing in response")
                    }
                } else {
                    setLoadingState(false)
                    showToast("Login failed. Check credentials.")
                }
            }

            override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                setLoadingState(false)
                showToast("Network error: ${t.message}")
            }
        })
    }

    private fun saveToken(token: String) {
        val prefs = requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
        prefs.edit().putString("token", token).apply()
    }

    private fun navigateToHome() {
        val intent = Intent(context, HomeActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun navigateToSignUp() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragmentContainer, userSignup())
            //?.addToBackStack(null)
            ?.commit()
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
