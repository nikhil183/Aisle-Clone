package com.example.aisleassignment.view.user

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aisleassignment.R
import com.example.aisleassignment.adapter.LikesAdapter
import com.example.aisleassignment.databinding.FragmentNotesBinding
import com.example.aisleassignment.model.notes.Profiles
import com.example.aisleassignment.viewmodel.UserDataViewModel

class NotesFragment : Fragment() {

    private lateinit var dataBinding: FragmentNotesBinding
    private lateinit var viewModel: UserDataViewModel
    private lateinit var likesAdapter: LikesAdapter
    private lateinit var likes: MutableList<Profiles>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
        viewModel = ViewModelProvider(requireActivity())[UserDataViewModel::class.java]
        getNotes()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_notes, container, false)

        initRecyclerView()
        initObserver()
        return dataBinding.root
    }

    private fun initAdapter() {
        likes = mutableListOf()
        likesAdapter = LikesAdapter(requireContext(), likes)
    }

    private fun initObserver() {
        viewModel.notes.observe(viewLifecycleOwner) {
            dataBinding.profile = it.invites?.profiles?.get(0)
            likes.addAll(it?.likes?.profiles!!)
            likesAdapter.notifyDataSetChanged()
        }
    }

    private fun getNotes() {
        val sharedPref = requireActivity().getSharedPreferences("shared_preference", Context.MODE_PRIVATE)
        val accessToken = sharedPref.getString("accessToken","")
        viewModel.getNotes(accessToken.toString())
    }

    private fun initRecyclerView() {
        dataBinding.rvLikes.layoutManager = GridLayoutManager(requireContext(), 2)
        dataBinding.rvLikes.adapter = likesAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        likes.clear()
    }
}