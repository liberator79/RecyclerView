package com.example.recyclerview.fragments.AuthFragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.recyclerview.R
import com.example.recyclerview.activities.HomeActivity
import com.example.recyclerview.fragments.AuthFragments.userSignup

class userSigninFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_signin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val signinEmailView = view.findViewById<EditText>(R.id.loginEmail)
        val signinPasswordView = view.findViewById<EditText>(R.id.loginPassword)
        val loginButtonView = view.findViewById<Button>(R.id.loginButton);
        loginButtonView.setOnClickListener {
            val signinEmail = signinEmailView.text;
            if(signinEmail.isEmpty()){
                Toast.makeText(context, "Email field should not be empty", Toast.LENGTH_SHORT).show();
                return@setOnClickListener
            }
            val signinPassword = signinPasswordView.text;
            if(signinPassword.isEmpty() || signinPassword.length < 8){
                Toast.makeText(context, "Password should be atleast 8 characters", Toast.LENGTH_SHORT).show();
                return@setOnClickListener
            }

            val intent = Intent(requireContext(), HomeActivity::class.java)
            startActivity(intent);

            requireActivity().finish();
        }
        val signupText = view.findViewById<TextView>(R.id.moveTosignUp)
        signupText.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, userSignup())
                //?.addToBackStack(null)
                ?.commit()
        }
    }
}