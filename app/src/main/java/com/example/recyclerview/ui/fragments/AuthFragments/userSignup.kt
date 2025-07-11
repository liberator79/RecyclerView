package com.example.recyclerview.ui.fragments.AuthFragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.recyclerview.R
import com.example.recyclerview.activities.HomeActivity
import com.example.recyclerview.data.SignUpRequest
import com.example.recyclerview.data.SignUpResponse
import com.example.recyclerview.data.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class userSignup : Fragment() {
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var userName : EditText
    private lateinit var signUpButton: Button
    private lateinit var loader : ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val signupText = view.findViewById<TextView>(R.id.movetoSignIn)
        emailInput = view.findViewById(R.id.signupEmail);
        userName = view.findViewById(R.id.userName);
        passwordInput = view.findViewById(R.id.signupPassword);
        signUpButton = view.findViewById(R.id.signupButton);
        loader = view.findViewById(R.id.buttonLoader);
        signUpButton.setOnClickListener {
            handleSignUp();
        }
        signupText.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, UserSigninFragment())
                .commit()
        }


    }
    fun handleSignUp(){
        if(userName.text.toString().isEmpty() || emailInput.text.toString().isEmpty()){
            showToast("Every should be filled");
            return;
        }
        if (passwordInput.text.toString().length < 8) {
            showToast("Password must be at least 8 characters")
            return
        }
        setLoadingState(true)
        val user = SignUpRequest(email = emailInput.text.toString(), password = passwordInput.text.toString(), userName = userName.text.toString());
        RetrofitClient.api.signUp(user).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
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

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
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
    fun setLoadingState(isLoading: Boolean) {
        if (isLoading) {
            signUpButton.isEnabled = false
            signUpButton.text = ""
            loader.visibility = View.VISIBLE
        } else {
            signUpButton.isEnabled = true
            signUpButton.text = "Login"
            loader.visibility = View.GONE
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}