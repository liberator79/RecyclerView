package com.example.recyclerview.ui.fragments.NavFragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.recyclerview.R
import com.example.recyclerview.activities.HomeActivity
import com.example.recyclerview.activities.MainActivity
import com.example.recyclerview.ui.viewmodel.HomeViewModel

class ProfileFragment : Fragment() {
    private lateinit var signOutButton : Button;
    private val viewModel: HomeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signOutButton = view.findViewById(R.id.signout_button)

        signOutButton.setOnClickListener {
            viewModel.handleLogOut(requireContext());
            if(viewModel.isLoggedIn == false){
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }
    }
}