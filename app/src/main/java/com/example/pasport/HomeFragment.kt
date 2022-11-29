package com.example.pasport

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.pasport.databinding.FragmentHomeBinding
import com.example.pasport.db.AppDatabase
import com.example.pasport.db.User


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var list: ArrayList<User>
    private lateinit var myRvAdapter: MyRvAdapter
    private lateinit var appDatabase: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        list = ArrayList()

        appDatabase = AppDatabase.getInstance(binding.root.context)

        list.addAll(appDatabase.userDao().getPassport())

        myRvAdapter = MyRvAdapter(list)

        binding.rv.adapter = myRvAdapter

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.addFragment)
        }

        return binding.root
    }


}