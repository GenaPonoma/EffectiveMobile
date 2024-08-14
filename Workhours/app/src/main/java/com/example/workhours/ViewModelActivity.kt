package com.example.workhours

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelActivity: ViewModel() {
    private val fragmentIdLiveData = MutableLiveData<Int>()

    fun setFragmentId(fragmentId: Int) {
        fragmentIdLiveData.value = fragmentId
    }

    fun getFragmentIdLiveData(): LiveData<Int> {
        return fragmentIdLiveData
    }


}