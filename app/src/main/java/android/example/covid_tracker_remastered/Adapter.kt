package android.example.covid_tracker_remastered

import android.content.Context
import android.example.covid_tracker_remastered.databinding.ListItemBinding
import android.example.covid_tracker_remastered.databinding.OverviewFragmentBinding
import android.example.covid_tracker_remastered.network.CovidProperty
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.floor

class Adapter: ListAdapter<CovidProperty, Adapter.CovidPropertyViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<CovidProperty>(){
        override fun areItemsTheSame(oldItem: CovidProperty, newItem: CovidProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CovidProperty, newItem: CovidProperty): Boolean {
            return oldItem.country == newItem.country
        }
    }

    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CovidPropertyViewHolder {

        context = parent.context

        return CovidPropertyViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CovidPropertyViewHolder, position: Int) {
        val covidProperty = getItem(position)
        holder.bind(covidProperty, context)
    }

    class CovidPropertyViewHolder(private var binding: ListItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(covidProperty: CovidProperty, context: Context) {

            binding.stateNameTextview.text = covidProperty.country
            binding.totalConfirmedTextview.text = covidProperty.cases.toString()
            binding.dailyConfirmedTextview.text = covidProperty.todayCases.toString()
            binding.totalDeathsTextview.text = covidProperty.deaths.toString()
            binding.dailyDeathsTextview.text = covidProperty.todayDeaths.toString()
//            binding.executePendingBindings()

            val TAG = "Adapter"

            val magnitudeFloor = covidProperty.cases / 1000
            Log.i(TAG, "magnitudeFloor = ${magnitudeFloor}")

            val magnitudeColorResourceId = when(magnitudeFloor){
                in 0..10 -> R.color.magnitude1
                in 10..20 -> R.color.magnitude2
                in 20..30 -> R.color.magnitude3
                in 30..40 -> R.color.magnitude4
                in 40..50 -> R.color.magnitude5
                in 50..60 -> R.color.magnitude6
                in 60..70 -> R.color.magnitude7
                in 70..80 -> R.color.magnitude8
                in 80..90 -> R.color.magnitude9
                else -> R.color.magnitude10plus
            }

            Log.i(TAG, "magnitudeColorResourceId = ${magnitudeColorResourceId}")

            binding.pane.setBackgroundColor(ContextCompat.getColor(context, magnitudeColorResourceId))

//            binding.pane.background = ContextCompat.getColor(context, magnitudeColorResourceId)
        }
    }
}