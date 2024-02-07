package com.uz.abu.movies.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.uz.abu.movies.R
import com.uz.abu.movies.core.model.base.BaseModel
import com.uz.abu.movies.databinding.ScreenHomeBinding
import com.uz.abu.movies.core.adapter.MultiAdapter


class PageScreen : Fragment(R.layout.screen_home) {

    private val binding by viewBinding(ScreenHomeBinding::bind)
    private val homeVM: HomeScreenViewModel by viewModels()
    private val multiData = ArrayList<BaseModel>()
    private var one = false
    private var two = false
    private val adapter by lazy { MultiAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeVM.getFilmsData()
        observer()
        setAdapter()

    }

    private fun setAdapter() {
        multiData.clear()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setHasFixedSize(true)
    }

    private fun observer() {

        homeVM.filmsNowLiveData.observe(requireActivity()) {
            multiData.add(it!!)
            one = true
            if (one && two){
                this.adapter.setData(multiData)
            }
        }
        homeVM.filmsPopularLiveData.observe(requireActivity()){
            multiData.add(it!!)
            two = true
            if (one && two){
                this.adapter.setData(multiData)
            }
        }




    }


}