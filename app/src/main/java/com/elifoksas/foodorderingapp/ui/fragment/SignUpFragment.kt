package com.elifoksas.foodorderingapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.elifoksas.foodorderingapp.databinding.FragmentSignUpBinding
import com.elifoksas.foodorderingapp.ui.viewmodel.SignUpViewModel
import com.elifoksas.foodorderingapp.utils.gecis
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        viewModel.viewModelScope.launch {
            if (viewModel.isValid()){
                val transition = SignUpFragmentDirections.actionSignUpFragmentToHomePageFragment()
                view?.let { it1 -> Navigation.gecis(it1,transition) }
            }
        }


        binding.signUpButton.setOnClickListener {
            viewModel.signUp(binding.emailText.text.toString(),binding.emailPassword.text.toString())
        }


        viewModel.signUpCheck.observe(viewLifecycleOwner){
            if(it){
                val transition = SignUpFragmentDirections.actionSignUpFragmentToHomePageFragment()
                view?.let { it1 -> Navigation.gecis(it1,transition) }
            }
            else{
                Toast.makeText(context,"Bilgileriniz Yanlış",Toast.LENGTH_SHORT).show()
            }
        }

        binding.loginTextView.setOnClickListener {
            val transition = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
            view?.let { it1 -> Navigation.gecis(it1,transition) }
        }
        return binding.root
    }

}