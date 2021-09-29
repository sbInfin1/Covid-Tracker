package android.example.covid_tracker_remastered.overview

import android.example.covid_tracker_remastered.Adapter
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.example.covid_tracker_remastered.R
import android.example.covid_tracker_remastered.databinding.OverviewFragmentBinding
import androidx.lifecycle.Observer

class OverviewFragment : Fragment() {

    /**
     * Lazily initialize our [OverviewViewModel]
     */
    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // We inflate the layout using data binding
        val binding = OverviewFragmentBinding.inflate(inflater)

        val adapter = Adapter()
        binding.locationList.adapter = adapter

        viewModel.property.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        // Allows Data Binding to observe LiveData with the lifecycle of this fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

       return binding.root
    }
}