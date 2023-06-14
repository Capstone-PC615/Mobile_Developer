package com.capstone.trashsortapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.trashsortapp.R
import com.capstone.trashsortapp.data.ListPlaceAdapter
import com.capstone.trashsortapp.data.Place
import com.capstone.trashsortapp.databinding.FragmentHomeBinding
import com.capstone.trashsortapp.ui.activity.TrashDetail

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private lateinit var rvPlace: RecyclerView
    private val list = ArrayList<Place>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        rvPlace = view.findViewById(R.id.rv_place)
        rvPlace.setHasFixedSize(true)

        list.addAll(getListPlace())
        showRecyclerView()

        return view
    }

    private fun getListPlace(): ArrayList<Place> {
        val dataName = resources.getStringArray(R.array.placeName)
        val dataImages = resources.obtainTypedArray(R.array.placeImages)
        val dataDesctiption = resources.getStringArray(R.array.placeDescription)
        val dataClasses = resources.getStringArray(R.array.placeClasses)
        val listPlace = ArrayList<Place>()

        for (i in dataName.indices) {
            val place = Place(
                dataName[i],
                dataImages.getResourceId(i, -1),
                dataDesctiption[i],
                dataClasses[i]

            )
            listPlace.add(place)
        }

        return listPlace
    }

    private fun showRecyclerView() {
        rvPlace.layoutManager = GridLayoutManager(requireContext(), 2)
        val listPlaceAdapter = ListPlaceAdapter(list)
        rvPlace.adapter = listPlaceAdapter
        listPlaceAdapter.onItemClick = { place ->
            val intent = Intent(requireContext(), TrashDetail::class.java)
            intent.putExtra("key_place", place)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}