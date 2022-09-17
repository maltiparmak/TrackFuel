package com.technowalker.trackfuel.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.technowalker.trackfuel.R
import com.technowalker.trackfuel.adapter.RideAdapter
import com.technowalker.trackfuel.databinding.FragmentRideBinding
import com.technowalker.trackfuel.viewmodel.RideViewModel
import com.unity3d.ads.IUnityAdsInitializationListener
import com.unity3d.ads.UnityAds
import com.unity3d.ads.UnityAds.UnityAdsInitializationError
import com.unity3d.services.banners.BannerView
import com.unity3d.services.banners.UnityBannerSize


class RideFragment : Fragment() {
    private lateinit var viewModel: RideViewModel
    private val rideAdapter = RideAdapter(arrayListOf())
    private var _binding: FragmentRideBinding? = null
    private val binding get() = _binding!!
    private val GAME_ID = "#"
    private val BANNER_ID = "Banner_Android"
    private val testMode = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[RideViewModel::class.java]
        viewModel.refreshData()
        UnityAds.initialize(context, GAME_ID, testMode, object : IUnityAdsInitializationListener {
            override fun onInitializationComplete() {}
            override fun onInitializationFailed(
                unityAdsInitializationError: UnityAdsInitializationError,
                s: String
            ) {
                Toast.makeText(context, "UnityAds Failed", Toast.LENGTH_SHORT).show()
            }
        })
        val bannerView = BannerView(activity, BANNER_ID, UnityBannerSize(320, 50))
        bannerView.load()
        binding.bannerAd.addView(bannerView)





        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = rideAdapter
        observeLiveData()

        binding.fab.setOnClickListener {
            goAddRideFragment()

        }
        if (rideAdapter.rideList.size==0 ){

            Snackbar.make(view,"Sürüş eklemek için + işaretine basınız",Snackbar.LENGTH_LONG).show()
        }


    }

    //session start

    private var backPressedTime: Long = 0
    private var backToast: Toast? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {


                // And when you want to go back based on your condition
                if (backPressedTime + 2000 > System.currentTimeMillis()) {
                    this.isEnabled = false
                    System.exit(0)
                    requireActivity().onBackPressed()
                    return
                } else {
                    val pressExit = requireContext().resources.getString(R.string.pressExit)
                    backToast = Toast.makeText(getContext(), pressExit, Toast.LENGTH_SHORT)
                    backToast?.show()
                }
                backPressedTime = System.currentTimeMillis()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    fun observeLiveData() {
        viewModel.rideList.observe(viewLifecycleOwner, Observer { rides ->

            rides?.let {
                binding.recyclerView.visibility = View.VISIBLE
                rideAdapter.updateRideList(rides)
            }

        })
        viewModel.rideError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    binding.textViewError.visibility = View.VISIBLE
                } else {
                    binding.textViewError.visibility = View.GONE
                }
            }
        })
        viewModel.rideLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    binding.progressBarRideFragment.visibility = View.VISIBLE
                    binding.textViewError.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                } else {
                    binding.progressBarRideFragment.visibility = View.GONE
                }
            }
        })

    }

    private fun goAddRideFragment() {
        val action = RideFragmentDirections.actionRideFragmentToAddRideFragment()
        view?.findNavController()?.navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRideBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RideFragment().apply {

            }
    }


}