package com.technowalker.trackfuel.adapter


import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.green
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.technowalker.trackfuel.R
import com.technowalker.trackfuel.databinding.RideRowBinding
import com.technowalker.trackfuel.model.Ride
import com.technowalker.trackfuel.view.RideFragmentDirections
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class RideAdapter(val rideList: ArrayList<Ride>) :RecyclerView.Adapter<RideAdapter.RideViewHolder>() {



    class RideViewHolder(var binding: RideRowBinding) :RecyclerView.ViewHolder(binding.root){
    }

    val colors = arrayListOf<String>("#C0392B","#CA6F1E","#E74C3C","#73C6B6","#16A085","#3498DB","#1ABC9C","#2980B9","#27AE60","#F1C40F","#F39C12","#95A5A6")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideViewHolder {

        val binding = RideRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RideViewHolder(binding)


    }

    override fun onBindViewHolder(holder: RideViewHolder, position: Int) {
        holder.binding.textViewRoad.text = rideList[position].rideDistance.toString()+" km"
        holder.binding.textViewGas.text = rideList[position].rideLiter.toString()+" Lt"
        holder.binding.textViewLira.text = rideList[position].rideMoney.toString()+""
        val myDateString: String = rideList[position].rideDaySayi.toString() +" "+ rideList[position].rideMonth + " " +rideList[position].rideYearSayi +" " + rideList[position].rideDay
        holder.binding.textViewDate.text= myDateString


       holder.binding.cardViewRow.setCardBackgroundColor(Color.parseColor(colors[position%11]))

        holder.itemView.setOnClickListener( View.OnClickListener {
        goUpdateFragment(holder.itemView, rideList[position])


                }
        )



    }


    private fun goUpdateFragment(view: View, ride: Ride) {
        val action = RideFragmentDirections.actionRideFragmentToUpdateFragment(ride)


        view?.findNavController()?.navigate(action)
    }

    override fun getItemCount(): Int {
    return rideList.size
        }
    fun updateRideList (newRideList: List<Ride>) {
        rideList.clear()
        rideList.addAll(newRideList)
        notifyDataSetChanged()


    }
}