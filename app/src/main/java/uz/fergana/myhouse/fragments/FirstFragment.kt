package uz.fergana.myhouse.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.launch
import uz.fergana.myhouse.databinding.FragmentFirstBinding
import uz.fergana.myhouse.model.BaseModel
import uz.fergana.myhouse.repository.RetrofitInstance
import uz.fergana.myhouse.view.CameraAdapter


class FirstFragment : Fragment() {
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    lateinit var binding: FragmentFirstBinding
    private val camerasApiService = RetrofitInstance.createDoorApiService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipe.isRefreshing = true
        lifecycleScope.launch {
            try {
                val response = camerasApiService.getCamera()
                handleApiResponse(response)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
    private fun handleApiResponse(response: BaseModel) {
        if (response.success) {
            binding.swipe.isRefreshing = false
            // Handle successful response
            binding.recCam.adapter = CameraAdapter(response.data.cameras ?: emptyList())
            binding.recCam.layoutManager = LinearLayoutManager(requireActivity())
            // Do something with the camera data
        } else {
            // Handle unsuccessful response
        }
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            FirstFragment()
    }
}