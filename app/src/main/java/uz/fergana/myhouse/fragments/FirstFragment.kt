package uz.fergana.myhouse.fragments

import android.content.ContentValues
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
import uz.fergana.myhouse.utils.CameraDatabaseHelper
import uz.fergana.myhouse.utils.RoomDatabaseHelper
import uz.fergana.myhouse.view.CameraAdapter


class FirstFragment : Fragment() {
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

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = camerasApiService.getCameraData()
                handleApiResponse(response)
            } catch (e: Exception) {
                // Handle error
            }
        }
//        binding.swipe.setOnRefreshListener()
        binding.swipe.setOnRefreshListener {
            lifecycleScope.launch {
                try {
                    val response = camerasApiService.getCameraData()
                    handleApiResponse(response)
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
        private fun handleApiResponse(response: BaseModel) {
            if (response.success) {
                binding.swipe.isRefreshing = false
                val rooms = response.data.room
                val cameras = response.data.cameras
                binding.recCam.layoutManager = LinearLayoutManager(requireActivity())
                binding.recCam.adapter = CameraAdapter(response.data.cameras)
                val roomDatabaseHelper = RoomDatabaseHelper(requireContext())

                // Insert room data into the database
                val roomDb = roomDatabaseHelper.writableDatabase
                for (room in rooms) {
                    val values = ContentValues().apply {
                        put(RoomDatabaseHelper.COLUMN_NAME, room)
                    }
                    roomDb.insert(RoomDatabaseHelper.TABLE_NAME, null, values)
                }
                roomDb.close()
                // Insert camera data into the main database
                val cameraDbHelper = CameraDatabaseHelper(requireContext())
                val cameraDb = cameraDbHelper.writableDatabase
                for (camera in cameras) {
                    val values = ContentValues().apply {
                        put(CameraDatabaseHelper.COLUMN_NAME, camera.name)
                        put(CameraDatabaseHelper.COLUMN_SNAPSHOT, camera.snapshot)
                        put(CameraDatabaseHelper.COLUMN_ROOM, camera.room)
                        put(CameraDatabaseHelper.COLUMN_FAVORITES, camera.favorites.toString().toInt())
                        put(CameraDatabaseHelper.COLUMN_REC, camera.rec.toString().toInt())
                    }
                    cameraDb.insert(CameraDatabaseHelper.TABLE_NAME, null, values)
                }
                cameraDb.close()
                // Now the data is saved in both databases
            } else {
                // Handle unsuccessful response
                binding.swipe.isRefreshing = false
            }

    }


    companion object {

        @JvmStatic
        fun newInstance() =
            FirstFragment()
    }
}