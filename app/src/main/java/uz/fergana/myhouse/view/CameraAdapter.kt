package uz.fergana.myhouse.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.fergana.myhouse.databinding.CamItemLayoutBinding
import uz.fergana.myhouse.model.CameraModel

class CameraAdapter(val items: List<CameraModel>): RecyclerView.Adapter<CameraAdapter.ItemHolder>() {
    inner class ItemHolder(val binding: CamItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(CamItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        Glide.with(holder.itemView.context).load(item.snapshot).into(holder.binding.postImg)
        holder.binding.postTitle.text = item.name
        holder.binding.postComment.text = item.room
    }
}