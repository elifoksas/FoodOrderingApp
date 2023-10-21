package com.elifoksas.foodorderingapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.elifoksas.foodorderingapp.databinding.FragmentFavoritesBinding
import com.elifoksas.foodorderingapp.ui.adapter.FavoritesAdapter
import com.elifoksas.foodorderingapp.ui.viewmodel.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentFavoritesBinding.inflate(inflater, container, false)

        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rv.layoutManager = layoutManager
        viewModel.getFavorites()

        viewModel.favoriteList.observe(viewLifecycleOwner){
            it.forEach {
                Log.d("favori",it.food_name)
            }
            val favoritesAdapter = it?.let { it1 -> FavoritesAdapter(requireContext(), it1,viewModel) }
            binding.rv.adapter = favoritesAdapter
        }

        return binding.root
    }


}