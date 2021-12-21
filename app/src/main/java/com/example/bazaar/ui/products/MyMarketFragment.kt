package com.example.bazaar.ui.products

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bazaar.R
import com.example.bazaar.adapters.DataAdapter
import com.example.bazaar.model.Product
import com.example.bazaar.repository.Repository
import com.example.bazaar.viewmodels.ListViewModel
import com.example.bazaar.viewmodels.ListViewModelFactory
import com.example.bazaar.viewmodels.LoginViewModel
import com.example.bazaar.viewmodels.LoginViewModelFactory

class MyMarketFragment : Fragment(), DataAdapter.OnItemClickListener, DataAdapter.OnItemLongClickListener {

    val factoryLogin = LoginViewModelFactory( Repository())
    private lateinit var addProductButton: ImageButton
    private val loginViewModel: LoginViewModel by lazy{
        ViewModelProvider(requireActivity(),factoryLogin).get((LoginViewModel::class.java))
    }

    val factoryList = ListViewModelFactory(Repository())
    private val listViewModel: ListViewModel by lazy{
        ViewModelProvider(requireActivity(),factoryList).get((ListViewModel::class.java))
    }

    private lateinit var recycler_view: RecyclerView
    private lateinit var adapter: DataAdapter
    lateinit var filteredList: List<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val factory = ListViewModelFactory(Repository())
        //val factoryLogin = LoginViewModelFactory(requireActivity(), Repository())
        //listViewModel = ViewModelProvider(requireActivity(), factory).get(ListViewModel::class.java)
        //loginViewModel = ViewModelProvider(requireActivity(), factoryLogin).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_market, container, false)
        recycler_view = view.findViewById(R.id.recycler_view)
        addProductButton = view.findViewById(R.id.addProductButton)
        Log.d("xxx","Logged in user ${loginViewModel.user.value}")
        setupRecyclerView()
        listViewModel.products.observe(viewLifecycleOwner){

            //Log.d("xxx","IT user {${it.}")
            filteredList = listViewModel.products.value?.filter { it.username == loginViewModel.user.value?.username  }!!
            Log.d("xxx","List {$filteredList}")
            adapter.setData(filteredList as ArrayList<Product>)
            //adapter.setData(listViewModel.products.value as ArrayList<Product>)
            adapter.notifyDataSetChanged()
        }
        addProductButton.setOnClickListener{
            findNavController().navigate(R.id.action_myMarketFragment_to_addProductFragment)
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
        findNavController().navigate(R.id.action_myMarketFragment_to_productDetailViewFragment2)
    }

    override fun onItemLongClick(position: Int) {
//        TODO("Not yet implemented")
    }
}