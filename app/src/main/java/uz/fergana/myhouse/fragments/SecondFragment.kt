package uz.fergana.myhouse.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.fergana.myhouse.databinding.FragmentSecondBinding
import uz.fergana.myhouse.model.BaseResponseModel
import uz.fergana.myhouse.model.CameraModel
import uz.fergana.myhouse.repository.NetworkManager
import uz.fergana.myhouse.view.CameraAdapter
import uz.fergana.myhouse.view.DoorAdapter


class SecondFragment : Fragment() {

    lateinit var binding: FragmentSecondBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.recDoor.adapter = DoorAdapter(listOf(
//            DoorModel("Подьезд 1", R.drawable.baseline_lock_24),
//            DoorModel("Подьезд 1", R.drawable.baseline_lock_24),
//            DoorModel("Подьезд 1", R.drawable.baseline_lock_24),
//            DoorModel("Подьезд 1", R.drawable.baseline_lock_24),
//            DoorModel("Подьезд 1", R.drawable.baseline_lock_24),
//            DoorModel("Подьезд 1", R.drawable.baseline_lock_24),
//            DoorModel("Подьезд 1", R.drawable.baseline_lock_24),
//            DoorModel("Подьезд 1", R.drawable.baseline_lock_24),
//            DoorModel("Подьезд 1", R.drawable.baseline_lock_24),
//            DoorModel("Подьезд 1", R.drawable.baseline_lock_24),
//        ))
        NetworkManager.getApiService().getCamera2().enqueue(object : Callback<BaseResponseModel<List<CameraModel>>>{
            override fun onResponse(
                call: Call<BaseResponseModel<List<CameraModel>>>,
                response: Response<BaseResponseModel<List<CameraModel>>>
            ) {
                binding.recDoor.adapter = DoorAdapter(response.body()?.data ?: emptyList())
                binding.recDoor.layoutManager = LinearLayoutManager(requireActivity())

            }

            override fun onFailure(call: Call<BaseResponseModel<List<CameraModel>>>, t: Throwable) {
            }
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            SecondFragment()
    }
}