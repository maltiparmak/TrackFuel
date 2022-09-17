package com.technowalker.trackfuel.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.technowalker.trackfuel.databinding.FragmentUpdateBinding
import com.technowalker.trackfuel.model.Ride
import com.technowalker.trackfuel.viewmodel.UpdateViewModel
import com.unity3d.ads.IUnityAdsInitializationListener
import com.unity3d.ads.UnityAds
import com.unity3d.services.banners.BannerView
import com.unity3d.services.banners.UnityBannerSize
import java.text.DecimalFormat


class UpdateFragment : Fragment() {
    private lateinit var viewModel: UpdateViewModel
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private var rideUuid = 0
    val args: UpdateFragmentArgs by navArgs()

    var km: Double? = null
    var lt: Double? = null
    var money: Double? = null
    var rideNote: String = "Not Yok"
    private val GAME_ID = "#"
    private val BANNER_ID = "Banner_Android"
    private val testMode = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[UpdateViewModel::class.java]
        var ride = args.currentRide
        viewModel.refreshData(ride)



        observeLiveData()
        UnityAds.initialize(context, GAME_ID, testMode, object : IUnityAdsInitializationListener {
            override fun onInitializationComplete() {}
            override fun onInitializationFailed(
                unityAdsInitializationError: UnityAds.UnityAdsInitializationError,
                s: String
            ) {
                Toast.makeText(context, "UnityAds Failed", Toast.LENGTH_SHORT).show()
            }
        })
        val bannerView = BannerView(activity, BANNER_ID, UnityBannerSize(320, 50))
        bannerView.load()
        binding.bannerAd.addView(bannerView)
        var money100km: Double?= null
        var liter100km: Double?= null
        val seLectedKM = ride.rideDistance?.toDouble()
        val selectedLiter= ride.rideLiter?.toDouble()
        val selectedMoney= ride.rideMoney?.toDouble()
        val m100: Double? =100.0

        val formatter = DecimalFormat("#,###,###.000")
        money100km= (m100!! / seLectedKM!!)* selectedMoney!!
        binding.moneyFor100km.text=formatter.format(money100km).toString()
        liter100km=(m100!!/seLectedKM!!)*selectedLiter!!
        binding.litreFor100km.text=formatter.format(liter100km).toString()
        binding.textViewNote.text= ride.rideNote
        print(ride )


        binding.buttonGuncelle.setOnClickListener(View.OnClickListener {
            // KM
            if (binding.guncelleEditTextKM.text.isEmpty() || binding.guncelleEditTextKM.text.equals(
                    ""
                ) || binding.guncelleEditTextKM.text == null
            ) {
                //EditText is empty
                km = ride.rideDistance
                println("EditText KM is empty worked")
                println(this.km)
            } else {
                //EditText is not empty
                km = binding.guncelleEditTextKM.text.toString().toDouble()
                println("EditText KM is not empty worked")
            }

            // liter
            if (binding.guncelleEditTextLiter.text.isEmpty() || binding.guncelleEditTextLiter.text.equals(
                    ""
                ) || binding.guncelleEditTextLiter.text == null
            ) {
                //EditText is empty
                lt = ride.rideLiter
                println("EditText Liter is empty worked")
                println(this.lt)
            } else {
                //EditText is not empty
                lt = binding.guncelleEditTextLiter.text.toString().toDouble()
                println("EditText Liter is not empty worked")
            }
            // Money
            if (binding.guncelleEditTextMoney.text.isEmpty() || binding.guncelleEditTextMoney.text.equals(
                    ""
                ) || binding.guncelleEditTextMoney.text == null
            ) {
                //EditText is empty
                money = ride.rideMoney
                println("EditText Money is empty worked")
                println(this.money)
            } else {
                //EditText is not empty
                money = binding.guncelleEditTextMoney.text.toString().toDouble()
                println("EditText money is not empty worked")
            }
            // Notes
            if (binding.guncelleEditTextNote.text.isEmpty() ||
                    binding.guncelleEditTextNote.text.equals("")||
                    binding.guncelleEditTextNote.text== null) {
                // note is empty
                rideNote = ride.rideNote
            } else {
                rideNote = binding.guncelleEditTextNote.text.toString()
            }



            ride = Ride(km, lt, money, ride.uuid, ride.dateAdded,ride.rideDaySayi,ride.rideYearSayi,ride.rideDay,ride.rideMonth,rideNote)

            Snackbar.make(view,"Sürüş güncellenecek. Emin misiniz?",Snackbar.LENGTH_SHORT).setAction("Evet", View.OnClickListener {
                viewModel.updateMyRide(ride)
                goRideFraagment()
            }).show()

        })
        binding.buttonDelete.setOnClickListener(View.OnClickListener {
            Snackbar.make(view,"Sürüş silinecek, emin misiniz?", Snackbar.LENGTH_LONG).setAction("Evet",View.OnClickListener {

                viewModel.deleteMyRide(ride)
                goRideFraagment()
            }).show()

        })


    }


    fun observeLiveData() {
        viewModel.rideLiveData.observe(viewLifecycleOwner, Observer { ride ->
            ride.let {
                binding.guncelleEditTextKM.visibility = View.VISIBLE
                binding.guncelleEditTextLiter.visibility = View.VISIBLE
                binding.guncelleEditTextMoney.visibility = View.VISIBLE
                binding.buttonDelete.visibility = View.VISIBLE
                binding.buttonGuncelle.visibility = View.VISIBLE
                binding.guncelleError.visibility = View.INVISIBLE
                binding.guncelleProgressBar.visibility = View.INVISIBLE

                binding.guncelleEditTextMoney.hint = ride.rideMoney.toString() + " Tl"
                binding.guncelleEditTextLiter.hint = ride.rideLiter.toString() + " Lt"
                binding.guncelleEditTextKM.hint = ride.rideDistance.toString() + " Km"


            }
        })


    }

    private fun goRideFraagment() {
        val action = UpdateFragmentDirections.actionUpdateFragmentToRideFragment()
        view?.findNavController()?.navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UpdateFragment().apply {

            }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}



