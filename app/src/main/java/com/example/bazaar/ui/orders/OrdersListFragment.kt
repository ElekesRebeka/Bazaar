package com.example.bazaar.ui.orders

import android.os.Bundle
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
import com.example.bazaar.adapters.DataAdapterForOrders
import com.example.bazaar.model.Order
import com.example.bazaar.repository.Repository
import com.example.bazaar.viewmodels.OrdersListViewModel
import com.example.bazaar.viewmodels.OrdersListViewModelFactory

class OrdersListFragment : Fragment(), DataAdapterForOrders.OnItemClickListener, DataAdapterForOrders.OnItemLongClickListener {
    lateinit var ordersViewModel: OrdersListViewModel
    private lateinit var recycler_view: RecyclerView
    private lateinit var adapter: DataAdapterForOrders

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = OrdersListViewModelFactory(Repository())
        ordersViewModel = ViewModelProvider(this, factory).get(OrdersListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_orders_list, container, false)
        recycler_view = view.findViewById(R.id.recycler_view_orders)
        setupRecyclerView()
        ordersViewModel.orders.observe(viewLifecycleOwner){
            adapter.setData(ordersViewModel.orders.value as ArrayList<Order>)
            adapter.notifyDataSetChanged()
        }
        return view
    }

    private fun setupRecyclerView(){
        adapter = DataAdapterForOrders(ArrayList<Order>(), this.requireContext(), this, this)
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

    companion object {

    }

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onItemLongClick(position: Int) {
        TODO("Not yet implemented")
    }
}