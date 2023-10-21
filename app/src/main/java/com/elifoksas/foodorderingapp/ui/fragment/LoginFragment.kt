package com.elifoksas.foodorderingapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.elifoksas.foodorderingapp.databinding.FragmentLoginBinding
import com.elifoksas.foodorderingapp.ui.viewmodel.LoginViewModel
import com.elifoksas.foodorderingapp.utils.gecis
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentLoginBinding.inflate(inflater, container, false)

        viewModel.loginCheck.observe(viewLifecycleOwner){
            if(it){
                val transition = LoginFragmentDirections.actionLoginFragmentToHomePageFragment()
                view?.let { it1 -> Navigation.gecis(it1,transition) }
            }
            else{
                Toast.makeText(context,"Bilgileriniz Yanlış", Toast.LENGTH_SHORT).show()
            }
        }
        binding.signUpTextView.setOnClickListener {
            val transition = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            Navigation.gecis(it,transition)
        }

        binding.logInButton.setOnClickListener {
            viewModel.login(binding.emailText.text.toString(),binding.emailPassword.text.toString())
        }


        return binding.root
    }


}