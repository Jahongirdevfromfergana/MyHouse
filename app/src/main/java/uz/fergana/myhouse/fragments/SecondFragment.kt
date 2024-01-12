package uz.fergana.myhouse.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import uz.fergana.myhouse.R
import uz.fergana.myhouse.databinding.FragmentSecondBinding
import uz.fergana.myhouse.model.DoorModel
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

        binding.recDoor.layoutManager = LinearLayoutManager(requireActivity())
        binding.recDoor.adapter = DoorAdapter(listOf(
            DoorModel("Подьезд 1", R.drawable.baseline_lock_24),
            DoorModel("Подьезд 1", R.drawable.baseline_lock_24),
            DoorModel("Подьезд 1", R.drawable.baseline_lock_24),
            DoorModel("Подьезд 1", R.drawable.baseline_lock_24),
            DoorModel("Подьезд 1", R.drawable.baseline_lock_24),
            DoorModel("Подьезд 1", R.drawable.baseline_lock_24),
            DoorModel("Подьезд 1", R.drawable.baseline_lock_24),
            DoorModel("Подьезд 1", R.drawable.baseline_lock_24),
            DoorModel("Подьезд 1", R.drawable.baseline_lock_24),
            DoorModel("Подьезд 1", R.drawable.baseline_lock_24),
        ))
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            SecondFragment()
    }
}