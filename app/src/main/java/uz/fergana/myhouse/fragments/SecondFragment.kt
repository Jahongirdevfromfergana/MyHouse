package uz.fergana.myhouse.fragments

import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import uz.fergana.myhouse.databinding.FragmentSecondBinding
import uz.fergana.myhouse.model.DoorApiResponse
import uz.fergana.myhouse.repository.RetrofitInstance
import uz.fergana.myhouse.utils.DatabaseHelper
import uz.fergana.myhouse.view.DoorAdapter


class SecondFragment : Fragment() {
    lateinit var binding: FragmentSecondBinding
    private val doorApiService = RetrofitInstance.createDoorApiService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = doorApiService.getDoorData()
                handleDoorApiResponse(response)
            } catch (e: Exception) {
                // Handle error
            }
        }
        binding.swipe.setOnRefreshListener {
            lifecycleScope.launch {
                try {
                    val response = doorApiService.getDoorData()
                    handleDoorApiResponse(response)
                } catch (e: Exception) {
                    // Handle error
                }
            }
        }
       binding.swipe.postDelayed({
           binding.swipe.isRefreshing = false

       }, 2000)
        binding.swipe.isRefreshing = true

    }
   private  fun handleDoorApiResponse(response: DoorApiResponse) {
        if (response.success) {
            binding.swipe.isRefreshing = false
            val doors = response.data
            binding.recDoor.adapter = DoorAdapter(response.data)
            binding.recDoor.layoutManager = LinearLayoutManager(requireActivity())
            val databaseHelper = DatabaseHelper(requireContext())
            val db = databaseHelper.writableDatabase
            for (door in doors) {
                val values = ContentValues().apply {
                    put(DatabaseHelper.COLUMN_NAME, door.name)
                    put(DatabaseHelper.COLUMN_ROOM, door.room)
                    put(DatabaseHelper.COLUMN_FAVORITES, door.favorites.toString().toInt())
                    put(DatabaseHelper.COLUMN_SNAPSHOT, door.snapshot)
                }
                db.insert(DatabaseHelper.TABLE_NAME, null, values)
            }
            db.close()

        } else {
                binding.swipe.isRefreshing = false
        }
    }
    companion object {

        @JvmStatic
        fun newInstance() =
            SecondFragment()
    }
}