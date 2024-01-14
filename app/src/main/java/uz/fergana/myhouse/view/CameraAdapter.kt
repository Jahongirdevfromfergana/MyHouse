package uz.fergana.myhouse.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.fergana.myhouse.R
import uz.fergana.myhouse.databinding.CamItemLayoutBinding
import uz.fergana.myhouse.model.Camera

class CameraAdapter(val items: List<Camera>): RecyclerView.Adapter<CameraAdapter.ItemHolder>() {
    inner class ItemHolder(val binding: CamItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(CamItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        if (item.snapshot.isNullOrEmpty()){
            holder.binding.cardView.visibility = View.GONE
            holder.binding.postStar.visibility = View.GONE
        }else{
            Glide.with(holder.itemView.context).load(item.snapshot).into(holder.binding.postImg)

        }
        if (item.room.isNullOrEmpty()){
            holder.binding.postIcon.setImageResource(R.drawable.group_54)
        }else{
            holder.binding.postIcon.setImageResource(R.drawable.group_55)
        }
        if (item.favorites) holder.binding.postStar.setImageResource(R.drawable.star_2) else holder.binding.postStar.visibility = View.GONE

        holder.binding.postTitle.text = item.name
        holder.binding.postComment.text = item.room
    }
}