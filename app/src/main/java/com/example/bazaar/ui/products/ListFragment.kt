package com.example.bazaar.ui.products

import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.bazaar.MainActivity
import com.example.bazaar.MyApplication
import com.example.bazaar.R
import com.example.bazaar.adapters.DataAdapter
import com.example.bazaar.manager.SharedPreferencesManager
import com.example.bazaar.model.Product
import com.example.bazaar.repository.Repository
import com.example.bazaar.viewmodels.ListViewModel
import com.example.bazaar.viewmodels.ListViewModelFactory
import com.google.android.material.appbar.AppBarLayout


class ListFragment : Fragment() , DataAdapter.OnItemClickListener, DataAdapter.OnItemLongClickListener {
    lateinit var listViewModel: ListViewModel
    private lateinit var recycler_view: RecyclerView
    private lateinit var adapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ListViewModelFactory(Repository())
        listViewModel = ViewModelProvider(this, factory).get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list, container, false)
        recycler_view = view.findViewById(R.id.recycler_view)
        setupRecyclerView()
        listViewModel.products.observe(viewLifecycleOwner){
            adapter.setData(listViewModel.products.value as ArrayList<Product>)
            adapter.notifyDataSetChanged()
        }
        return view
    }

    private fun setupRecyclerView(){
        adapter = DataAdapter(ArrayList<Product>(), this.requireContext(), this, this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this.context)
        recycler_view.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        recycler_view.setHasFixedSize(true)
    }

    override fun onItemClick(position: Int) {
        listViewModel.products.value?.get(position)
            ?.let { MainActivity.sharedPreferences.putStringValue("actual_item_user", it.username) }
        listViewModel.products.value?.get(position)
            ?.let { MainActivity.sharedPreferences.putStringValue("actual_item_date", it.creation_time.toString()) }
        listViewModel.products.value?.get(position)
            ?.let { MainActivity.sharedPreferences.putStringValue("actual_item_name", it.title) }
        listViewModel.products.value?.get(position)
            ?.let { MainActivity.sharedPreferences.putStringValue("actual_item_price", it.price_per_unit) }
        listViewModel.products.value?.get(position)
            ?.let { MainActivity.sharedPreferences.putStringValue("actual_item_is_active", it.is_active.toString()) }
        listViewModel.products.value?.get(position)
            ?.let { MainActivity.sharedPreferences.putStringValue("actual_item_amount", it.units) }
        listViewModel.products.value?.get(position)
            ?.let { MainActivity.sharedPreferences.putStringValue("actual_item_description", it.description) }
        findNavController().navigate(R.id.action_listFragment_to_productDetailViewFragment2)
    }

    override fun onItemLongClick(position: Int) {
//        TODO("Not yet implemented")
    }

}