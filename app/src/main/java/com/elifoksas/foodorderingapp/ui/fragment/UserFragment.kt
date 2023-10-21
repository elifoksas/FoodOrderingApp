package com.elifoksas.foodorderingapp.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.elifoksas.foodorderingapp.databinding.FragmentUserBinding
import com.elifoksas.foodorderingapp.ui.viewmodel.UserViewModel
import com.elifoksas.foodorderingapp.utils.gecis
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private val viewModel: UserViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentUserBinding.inflate(inflater, container, false)
        binding.logOutButton.setOnClickListener {
            viewModel.logOut()
        }
        viewModel.logOutCheck.observe(viewLifecycleOwner){
            if (!it){
                val transition = UserFragmentDirections.actionUserFragmentToSignUpFragment()
                view?.let { it1 -> Navigation.gecis(it1,transition) }
            }
            else{

            }
        }

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val kullaniciAdi = sharedPref?.getString("KullaniciAdi", "")
        binding.emailTextView.text = FirebaseAuth.getInstance().currentUser?.email
        binding.userNameTextView.text = kullaniciAdi


        return binding.root
    }

}