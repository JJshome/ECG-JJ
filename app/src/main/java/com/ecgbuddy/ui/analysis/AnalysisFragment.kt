package com.ecgbuddy.ui.analysis

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnalysisFragment : Fragment() {
    private val viewModel: AnalysisViewModel by viewModels()
    private lateinit var lineChart: LineChart
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupChart()
        observeData()
    }
    
    private fun setupChart() {
        lineChart = viewBinding.ecgChart
        lineChart.apply {
            description.isEnabled = false
            setTouchEnabled(true)
            isDragEnabled = true
            setScaleEnabled(true)
            setPinchZoom(true)
            
            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                setDrawGridLines(false)
            }
            
            axisLeft.apply {
                setDrawGridLines(true)
                setDrawAxisLine(true)
            }
            
            axisRight.isEnabled = false
        }
    }
    
    private fun updateChartData(ecgData: List<Float>) {
        val entries = ecgData.mapIndexed { index, value ->
            Entry(index.toFloat(), value)
        }
        
        val dataSet = LineDataSet(entries, "ECG").apply {
            color = ContextCompat.getColor(requireContext(), R.color.ecg_line)
            setDrawCircles(false)
            lineWidth = 1.5f
        }
        
        lineChart.data = LineData(dataSet)
        lineChart.invalidate()
    }
}
