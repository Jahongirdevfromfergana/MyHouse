package uz.fergana.myhouse.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.fergana.myhouse.databinding.DoorItemLayoutBinding
import uz.fergana.myhouse.databinding.FragmentSecondBinding
import uz.fergana.myhouse.model.DoorModel

class DoorAdapter(val items: List<DoorModel>): RecyclerView.Adapter<DoorAdapter.ItemHolder>() {
    inner class ItemHolder(val binding: DoorItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(DoorItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
       return items.count()
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        holder.binding.title.text = item.title
        holder.binding.icon.setImageResource(item.icon)
    }

}
