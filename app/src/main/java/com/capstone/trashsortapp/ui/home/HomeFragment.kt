package com.capstone.trashsortapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.capstone.trashsortapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    companion object {
        const val EXTRA_ID = "resultId"
        const val EXTRA_TITLE = "resultTitle"
        const val EXTRA_IMAGE = "image"
    }

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.text.observe(viewLifecycleOwner) {

        }
        binding.classes.text = EXTRA_TITLE
        binding.previewImageView.setImageURI(EXTRA_IMAGE.toUri())

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}