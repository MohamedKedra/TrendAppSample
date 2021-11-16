package com.kedra.trendappsample.ui.main

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.kedra.trendappsample.R
import com.kedra.trendappsample.remote.TrendingResponse
import java.io.InputStream
import java.net.URL


class CustomExpandableListAdapter(
    private val context: Context
) :
    BaseExpandableListAdapter() {

    private var list: List<TrendingResponse> = ArrayList()


    fun addList(list: List<TrendingResponse>) {
        this.list = list
    }

    override fun getGroupCount() = list.size

    override fun getChildrenCount(groupPosition: Int) = 1

    override fun getGroup(groupPosition: Int): Any = list[groupPosition]

    override fun getChild(groupPosition: Int, childPosition: Int) = list[groupPosition]

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var view = convertView
        if (convertView == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            view = inflater.inflate(R.layout.group_item_layout, null)
        }
        val item = list[groupPosition]
        view?.findViewById<TextView>(R.id.tvName)?.text = item.author
        view?.findViewById<TextView>(R.id.tvTitle)?.text = item.name
//        view?.let {
//            val avatar = it.findViewById<ImageView>(R.id.ivPoster)
//            Glide.with(context).load(item.avatar).into(avatar)
//        }
        view?.findViewById<ImageView>(R.id.ivPoster)?.setImageDrawable(getDrawable(item.avatar))
//        Picasso.with(context).load(item.avatar).into(avatar)
        return view!!
    }

    private fun getDrawable(url: String?): Drawable? {
        return try {
            val input: InputStream = URL(url).content as InputStream
            Drawable.createFromStream(input, "src name")
        } catch (e: Exception) {
            null
        }
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var view = convertView
        if (convertView == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.child_item_layout, null)
        }
        val item = list[groupPosition]
        view?.findViewById<TextView>(R.id.tvDescription)?.text = item.description
        view?.findViewById<TextView>(R.id.tvLanguage)?.text = item.language
        view?.findViewById<TextView>(R.id.tvStar)?.text = item.stars.toString()
        view?.findViewById<TextView>(R.id.tvFork)?.text = item.forks.toString()
        view?.findViewById<ImageView>(R.id.ivLanguage)
            ?.setBackgroundColor(Color.parseColor(item.languageColor))
        return view!!
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = false
}