package com.example.aisleassignment.view.user

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aisleassignment.R
import com.example.aisleassignment.adapter.LikesAdapter
import com.example.aisleassignment.databinding.FragmentNotesBinding
import com.example.aisleassignment.model.notes.Profiles
import com.example.aisleassignment.network.NetworkResult
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

    @SuppressLint("NotifyDataSetChanged")
    private fun initObserver() {
        viewModel.notes.observe(viewLifecycleOwner) { notes ->
            when(notes) {
                is NetworkResult.Success -> {
                    dataBinding.pbLoading.visibility = View.INVISIBLE
                    dataBinding.profile = notes.data?.invites?.profiles?.get(0)
                    likes.addAll(notes.data?.likes?.profiles!!)
                    likesAdapter.notifyDataSetChanged()
                }
                is NetworkResult.Loading -> {
                    dataBinding.pbLoading.visibility = View.VISIBLE
                }
                is NetworkResult.Failure -> {
                    dataBinding.pbLoading.visibility = View.INVISIBLE
                    Toast.makeText(
                        requireContext(),
                        notes.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
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