package com.example.bazaar.ui.products

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bazaar.R
import com.example.bazaar.adapters.DataAdapter
import com.example.bazaar.model.Product
import com.example.bazaar.repository.Repository
import com.example.bazaar.viewmodels.ListViewModel
import com.example.bazaar.viewmodels.ListViewModelFactory

class ProductDetailFragment : Fragment() {
    val factoryList = ListViewModelFactory(Repository())
    private val listViewModel: ListViewModel by lazy{
        ViewModelProvider(requireActivity(),factoryList).get((ListViewModel::class.java))
    }

    private lateinit var recycler_view: RecyclerView
    private lateinit var adapter: DataAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_detail, container, false)


        return view
    }



    companion object {

    }
}