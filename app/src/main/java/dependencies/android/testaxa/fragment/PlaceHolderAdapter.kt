package dependencies.android.testaxa.fragment

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.entity.PlaceHolderResponseItem
import com.android.testaxa.databinding.PlaceHolderItemLayoutBinding
import com.bumptech.glide.Glide

class PlaceHolderAdapter : RecyclerView.Adapter<PlaceHolderViewHolder>() {

    private val differ = AsyncListDiffer<PlaceHolderResponseItem>(this, itemCallback)
    var query:String?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolderViewHolder {
        return PlaceHolderViewHolder(
            PlaceHolderItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PlaceHolderViewHolder, position: Int) {
        val data = differ.currentList[position]

        holder.binding.data = data
        holder.binding.cardView.animation = AnimationUtils.loadAnimation(
            holder.itemView.context,
            com.android.testaxa.R.anim.animation
        )
        val string = data.title.replace(query.toString(), "<font color='red'>$query</font>")
        holder.binding.title.text = Html.fromHtml(string)
        data.thumbnailUrl.let {
            Glide.with(holder.binding.imagePoster)
                .load("$it.png").into(holder.binding.imagePoster)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitData(data: List<PlaceHolderResponseItem>) {
        differ.submitList(data)
    }

    companion object {

        val itemCallback = object : DiffUtil.ItemCallback<PlaceHolderResponseItem>() {
            override fun areItemsTheSame(
                oldItem: PlaceHolderResponseItem,
                newItem: PlaceHolderResponseItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: PlaceHolderResponseItem,
                newItem: PlaceHolderResponseItem
            ): Boolean {
                return oldItem==newItem
            }
        }
    }
}

class PlaceHolderViewHolder(val binding: PlaceHolderItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root)