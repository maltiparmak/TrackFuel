package com.technowalker.trackfuel.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.technowalker.trackfuel.databinding.FragmentAddRideBinding
import com.technowalker.trackfuel.model.Ride
import com.technowalker.trackfuel.viewmodel.AddRideViewModel
import com.unity3d.ads.IUnityAdsInitializationListener
import com.unity3d.ads.UnityAds
import com.unity3d.services.banners.BannerView
import com.unity3d.services.banners.UnityBannerSize
import java.time.MonthDay

import java.util.*


class AddRideFragment : Fragment() {
    private lateinit var viewModel: AddRideViewModel
    private var _binding: FragmentAddRideBinding? = null
    private val binding get() = _binding!!
    var mDay: String = ""
    var mMonth: String = ""
    var kmEmpty: Boolean? = null
    var ltEmpty: Boolean? = null
    var moneyEmpty: Boolean? = null
    private val GAME_ID = "#"
    private val BANNER_ID = "Banner_Android"
    private val testMode = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddRideViewModel::class.java)

        val dateString: String = Date().toString()
        val delim = " "
        val dateToList = dateString.split(delim)
        println(dateToList)

        dayConverter(dateToList[0])
        println(mDay)
        monthConverter(dateToList[1])
        println(mMonth)
        println(dateToList[2])
        println(dateToList[5])
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







        binding.buttonAddRide.setOnClickListener(View.OnClickListener {
            var rideNote: String = ""
            if (binding.editTextNot.text.isEmpty() ||
                    binding.editTextNot.equals("")||
                    binding.editTextNot.text== null
                    ) {
                rideNote = "Not Yok"
            } else {
                rideNote = binding.editTextNot.text.toString()
            }


            if (binding.editTextKM.text.isEmpty() ||
                binding.editTextKM.text.equals("") ||
                binding.editTextKM.text == null
            ) {
                //km is empty
                kmEmpty = true
                // Toast.makeText(context,"Kilometre  bilgisi giriniz.",Toast.LENGTH_SHORT).show()

            }
            if (binding.editTextLiter.text.isEmpty() ||
                binding.editTextLiter.text.equals("") ||
                binding.editTextLiter.text == null
            ) {
                // liter is empty
                ltEmpty = true
                //  Toast.makeText(context,"Litre bilgisi giriniz.",Toast.LENGTH_SHORT).show()
            }
            if (binding.editTextMoney.text.isEmpty() ||
                binding.editTextMoney.text.equals("") ||
                binding.editTextMoney.text == null
            ) {
                //money is empty
                moneyEmpty = true

                //    Toast.makeText(context,"Masraf bilgisi giriniz.",Toast.LENGTH_SHORT).show()

            }
            if (kmEmpty == true) {
                Toast.makeText(context, "Mesafe bilgisi giriniz.", Toast.LENGTH_SHORT).show()

            } else if (ltEmpty == true) {
                Toast.makeText(context, "Litre bilgisi giriniz.", Toast.LENGTH_SHORT).show()

            } else if (moneyEmpty == true) {
                Toast.makeText(context, "Masraf bilgisi giriniz.", Toast.LENGTH_SHORT).show()

            } else if (ltEmpty == true && moneyEmpty == true) {
                Toast.makeText(context, "Litre ve Masraf bilgisi giriniz.", Toast.LENGTH_SHORT)
                    .show()

            } else if (kmEmpty == true && ltEmpty == true) {
                Toast.makeText(context, "Kilometre ve Litre bilgisi giriniz.", Toast.LENGTH_SHORT)
                    .show()

            } else if (kmEmpty == true && moneyEmpty == true) {
                Toast.makeText(context, "Kilometre ve Masraf bilgisi giriniz.", Toast.LENGTH_SHORT)
                    .show()

            } else if (kmEmpty == true && ltEmpty == true && moneyEmpty == true) {
                Toast.makeText(context, "Hiç bilgi girmediniz.", Toast.LENGTH_SHORT).show()

            } else {
                val textKM = binding.editTextKM.text.toString().toDouble()
                val textLiter = binding.editTextLiter.text.toString().toDouble()
                val textMoney = binding.editTextMoney.text.toString().toDouble()
                val newRide = Ride(
                    textKM,
                    textLiter,
                    textMoney,
                    0,
                    Date(),
                    dateToList[2].toInt(),
                    dateToList[5].toInt(),
                    mDay,
                    mMonth,
                    rideNote
                )
                viewModel.addRideToRoomDB(newRide)
                Snackbar.make(view, "Sürüş Eklendi", Snackbar.LENGTH_SHORT).show()
                goRideFragment()

            }


            kmEmpty = null
            ltEmpty = null
            moneyEmpty = null
        }
        )
    }

    private fun monthConverter(month: String) {
        val monthList = listOf<String>(
            "Ocak",
            "Şubat",
            "Mart",
            "Nisan",
            "Mayıs",
            "Haziran",
            "Temmuz",
            "Ağustos",
            "Eylül",
            "Ekim",
            "Kasım",
            "Aralık"
        )

        if (month == "Jan") {
            mMonth = monthList[0]
        }
        if (month == "Feb") {
            mMonth = monthList[1]
        }
        if (month == "Mar") {
            mMonth = monthList[2]
        }
        if (month == "Apr") {
            mMonth = monthList[3]
        }
        if (month == "May") {
            mMonth = monthList[4]
        }
        if (month == "Jun") {
            mMonth = monthList[5]
        }
        if (month == "Jul") {
            mMonth = monthList[6]
        }
        if (month == "Aug") {
            mMonth = monthList[7]
        }
        if (month == "Sep") {
            mMonth = monthList[8]
        }
        if (month == "Oct") {
            mMonth = monthList[9]
        }
        if (month == "Nov") {
            mMonth = monthList[10]
        }
        if (month == "Dec") {
            mMonth = monthList[11]
        }

    }


    private fun dayConverter(day: String) {
        val daylist = listOf<String>(
            "Pazartesi",
            "Salı",
            "Çarşamba",
            "Perşembe",
            "Cuma",
            "Cumartesi",
            "Pazar"
        )

        if (day == "Mon") {
            mDay = daylist[0]
        }
        if (day == "Tue") {
            mDay = daylist[1]
        }
        if (day == "Wed") {
            mDay = daylist[2]
        }
        if (day == "Thu") {
            mDay = daylist[3]
        }
        if (day == "Fri") {
            mDay = daylist[4]
        }
        if (day == "Sat") {
            mDay = daylist[5]
        }
        if (day == "Sun") {
            mDay = daylist[6]
        }


    }


    private fun goRideFragment() {
        val action = AddRideFragmentDirections.actionAddRideFragmentToRideFragment()
        view?.findNavController()?.navigate(action)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddRideBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            AddRideFragment().apply {

            }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
