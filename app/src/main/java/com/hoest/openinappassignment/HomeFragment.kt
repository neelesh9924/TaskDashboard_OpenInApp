package com.hoest.openinappassignment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.gson.JsonElement
import com.hoest.adapters.LinksRecyclerViewAdapter
import com.hoest.openinappassignment.databinding.FragmentHomeBinding
import com.hoest.pojos.DashboardDataPojo
import com.hoest.utils.UserAuth
import com.hoest.viewModels.HomeViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    lateinit var navController: NavController

    lateinit var homeViewModel: HomeViewModel

    lateinit var userAuth: UserAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        userAuth = UserAuth(requireContext())

        //mimicking the login process
        val token =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI"

        val name = "Ajay Manva"

        if (userAuth.saveUserInfo(token, name)) {

            //Login Successful and now proceed to make the API call

            homeViewModel.getHomePageData().observe(viewLifecycleOwner) {
                setData(it)

            }

        }

//        binding.fab.setImageResource(R.drawable.add_fill0_wght400_grad0_opsz24)
        binding.fab.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.add_fill0_wght400_grad0_opsz24
            )
        )


    }

    //setting the data to the views
    private fun setData(current: DashboardDataPojo?) {


        //Task 1: Display Greeting from Local Time

        val hourOfDay =
            Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        binding.greetingTextView.text = when (hourOfDay) {
            in 0..11 -> "Good Morning"
            in 12..15 -> "Good Afternoon"
            in 16..20 -> "Good Evening"
            else -> "Good Night"
        }


        //Task 2: Display User Name from API, as API currently not giving the name, I am using the name from the SharedPref
        binding.nameTextView.text = userAuth.getName() + " 👋"

        //Task 3: Draw the chart from the API response
        drawChart(current?.data?.overallUrlChart)

        //top tags
        binding.todayClickValue.text = current?.todayClicks.toString()

        binding.topLocationValue.text = when (current?.topLocation) {
            "" -> "N/A"
            else -> current?.topLocation
        }

        binding.topSourceValue.text = when (current?.topSource) {
            "" -> "N/A"
            else -> current?.topSource
        }

        current?.startTime?.let { it1 ->

            if (it1.isNotEmpty()) {
                val startDate = SimpleDateFormat("hh:mm", Locale.ENGLISH).parse(it1)
                val plus1HrFromStartDate = Calendar.getInstance().apply {
                    time = startDate!!
                    add(Calendar.HOUR, 1)
                }.time

                //fromToTime
                binding.bestTimeValue.text =
                    "${SimpleDateFormat("hh:mm", Locale.ENGLISH).format(startDate)} - ${
                        SimpleDateFormat(
                            "hh:mm",
                            Locale.ENGLISH
                        ).format(plus1HrFromStartDate)
                    }"
            } else {
                binding.bestTimeValue.text = "N/A"
            }

        } ?: run {
            binding.bestTimeValue.text = "N/A"
        }


        //top links and recent links recycler view, switch using chip group and recycler view adapter update
        val llManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.linksRecyclerView.layoutManager = llManager

        val adapter = LinksRecyclerViewAdapter(
            current?.data?.topLinks!!,
            requireContext(),
            LinksRecyclerViewAdapter.ListType.TOP_LINKS
        )
        binding.linksRecyclerView.adapter = adapter

        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedId ->

            when (checkedId[0]) {

                R.id.topLinksChip -> {
                    binding.linksRecyclerView.adapter =
                        LinksRecyclerViewAdapter(
                            current?.data?.topLinks!!,
                            requireContext(),
                            LinksRecyclerViewAdapter.ListType.TOP_LINKS
                        )
                }

                R.id.recentLinksChip -> {
                    binding.linksRecyclerView.adapter =
                        LinksRecyclerViewAdapter(
                            current?.data?.recentLinks!!,
                            requireContext(),
                            LinksRecyclerViewAdapter.ListType.RECENT_LINKS
                        )
                }
            }
        }

        binding.talkWithUsButton.setOnClickListener {
            //open whatsapp
            val url =
                "https://api.whatsapp.com/send?phone=+91${current?.supportWhatsappNumber}&text=Hi"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }


    }

    private fun drawChart(jsonElement: JsonElement?) {

        val entries = ArrayList<com.github.mikephil.charting.data.Entry>()
        val dates = ArrayList<String>()

        val jsonObject = jsonElement?.asJsonObject

        jsonObject?.let {

            val keys = it.entrySet().iterator()

            while (keys.hasNext()) {
                val entry = keys.next()
                val value = it.get(entry.key)

                entries.add(
                    com.github.mikephil.charting.data.Entry(
                        entries.size.toFloat(),
                        value.asFloat
                    )
                )
                val currentDate =
                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(entry.key)
                val month = SimpleDateFormat("dd MMM", Locale.getDefault()).format(currentDate!!)
                dates.add(month)
            }

        }

        val dataSet = LineDataSet(entries, "Daily Clicks")
        dataSet.color = ContextCompat.getColor(requireContext(), R.color.colorPrimary)
        dataSet.setDrawFilled(true)
        dataSet.fillColor = ContextCompat.getColor(requireContext(), R.color.colorPrimary)
        dataSet.fillAlpha = 60
        dataSet.setDrawCircles(false)
        dataSet.setDrawValues(false)
        dataSet.lineWidth = 1f
        val lineData = LineData(dataSet)

        binding.lineChartHome.data = lineData

        val xAxis = binding.lineChartHome.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(dates)

        val yAxisLeft = binding.lineChartHome.axisLeft
        val yAxisRight = binding.lineChartHome.axisRight
        yAxisLeft.axisMinimum = 0f
        yAxisRight.axisMinimum = 0f

        setChartPropertiesAndInvalidate()

    }

    private fun setChartPropertiesAndInvalidate() {
        binding.lineChartHome.description.isEnabled = false
        binding.lineChartHome.legend.isEnabled = false
        binding.lineChartHome.setDrawGridBackground(false)
        binding.lineChartHome.setDrawBorders(false)
        binding.lineChartHome.setDrawMarkers(false)
        binding.lineChartHome.setTouchEnabled(false)
        binding.lineChartHome.isDragEnabled = false
        binding.lineChartHome.setScaleEnabled(false)
        binding.lineChartHome.setPinchZoom(false)
        binding.lineChartHome.isDoubleTapToZoomEnabled = false
        binding.lineChartHome.isHighlightPerDragEnabled = false
        binding.lineChartHome.isHighlightPerTapEnabled = false
        binding.lineChartHome.isAutoScaleMinMaxEnabled = false
        binding.lineChartHome.isKeepPositionOnRotation = false
        binding.lineChartHome.isDragDecelerationEnabled = false
        binding.lineChartHome.invalidate()
    }
}
