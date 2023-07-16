package com.hoest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hoest.openinappassignment.R
import com.hoest.pojos.DashboardDataPojo
import java.text.SimpleDateFormat
import java.util.Locale

class LinksRecyclerViewAdapter(
    private val links: ArrayList<*>,
    private val context: Context,
    private val type: ListType
) :
    RecyclerView.Adapter<LinksRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_links_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (type == ListType.TOP_LINKS) {

            val cur = links[position] as DashboardDataPojo.TopLinks

            Glide.with(context)
                .load(cur.originalImage)
                .placeholder(R.drawable.ic_launcher_background)
                .transition(com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade())
                .into(holder.image)

            holder.name.text = cur.title

            val date = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                Locale.getDefault()
            ).parse(cur.createdAt.toString())

            val formattedDate = SimpleDateFormat(
                "dd MMM yyyy",
                Locale.getDefault()
            ).format(date!!)

            holder.date.text = formattedDate

            holder.clicksCount.text = cur.totalClicks.toString()

            holder.link.text = cur.webLink


        } else if (type == ListType.RECENT_LINKS) {

            val cur = links[position] as DashboardDataPojo.RecentLinks

            Glide.with(context)
                .load(cur.originalImage)
                .placeholder(R.drawable.ic_launcher_background)
                .transition(com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade())
                .into(holder.image)

            holder.name.text = cur.title

            val date = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                Locale.getDefault()
            ).parse(cur.createdAt.toString())

            val formattedDate = SimpleDateFormat(
                "dd MMM yyyy",
                Locale.getDefault()
            ).format(date!!)

            holder.date.text = formattedDate

            holder.clicksCount.text = cur.totalClicks.toString()

            holder.link.text = cur.webLink

        }


    }

    override fun getItemCount(): Int {
        return links.size
    }


    class ViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.linkImageView)

        val name: TextView = itemView.findViewById(R.id.titleNameTextView)

        val date: TextView = itemView.findViewById(R.id.dateTextView)

        val clicksCount: TextView = itemView.findViewById(R.id.clicksTextView)

        val link: TextView = itemView.findViewById(R.id.linkTextView)


    }

    enum class ListType {
        TOP_LINKS,
        RECENT_LINKS
    }

}
