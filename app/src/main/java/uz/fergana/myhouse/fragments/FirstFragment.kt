package uz.fergana.myhouse.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import uz.fergana.myhouse.R
import uz.fergana.myhouse.databinding.FragmentFirstBinding
import uz.fergana.myhouse.model.CameraModel
import uz.fergana.myhouse.view.CameraAdapter


class FirstFragment : Fragment() {

    lateinit var binding: FragmentFirstBinding
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
        binding.recPost.layoutManager = LinearLayoutManager(requireActivity())
//        binding.recPost.adapter = CameraAdapter(listOf(
//            CameraModel("Lorem ipsum", R.drawable.img, "Door 1", 1),
//            CameraModel("Lorem ipsum", R.drawable.img, "Door 1", 1),
//            CameraModel("Lorem ipsum", R.drawable.img, "Door 1", 1),
//            CameraModel("Lorem ipsum", R.drawable.img, "Door 1", 1),
//            CameraModel("Lorem ipsum", R.drawable.img, "Door 1", 1),
//            CameraModel("Lorem ipsum", R.drawable.img, "Door 1", 1),
//            CameraModel("Lorem ipsum", R.drawable.img, "Door 1", 1),
//            CameraModel("Lorem ipsum", R.drawable.img, "Door 1", 1),
//            CameraModel("Lorem ipsum", R.drawable.img, "Door 1", 1),
//            CameraModel("Lorem ipsum", R.drawable.img, "Door 1", 1),
//            CameraModel("Lorem ipsum", R.drawable.img, "Door 1", 1),
//            CameraModel("Lorem ipsum", R.drawable.img, "Door 1", 1),
//            CameraModel("Lorem ipsum", R.drawable.img, "Door 1", 1),
//            CameraModel("Lorem ipsum", R.drawable.img, "Door 1", 1),
//            CameraModel("Lorem ipsum", R.drawable.img, "Door 1", 1),
//            CameraModel("Lorem ipsum", R.drawable.img, "Door 1", 1),
//            CameraModel("Lorem ipsum", R.drawable.img, "Door 1", 1),
//            CameraModel("Lorem ipsum", R.drawable.img, "Door 1", 1),
//            CameraModel("Lorem ipsum", R.drawable.img, "Door 1", 1),
//            CameraModel("Lorem ipsum", R.drawable.img, "Door 1", 1),
//            CameraModel("Lorem ipsum", R.drawable.img, "Door 1", 1),
//            CameraModel("Lorem ipsum", R.drawable.img, "Door 1", 1),
//        ))

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            FirstFragment()
    }
}