package com.kedra.trendappsample.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.bumptech.glide.Glide
import com.kedra.trendappsample.R
import com.kedra.trendappsample.remote.TrendingResponse

class CustomExpandableListAdapter(
    private val context: Context
) :
    BaseExpandableListAdapter() {

    private var list: List<TrendingResponse> = ArrayList()


    fun addList(list: List<TrendingResponse>) {
        this.list = list
    }

    override fun getGroupCount() = list.size

    override fun getChildrenCount(groupPosition: Int) = list.size

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
        Glide.with(context).load(item.avatar).into(view?.findViewById(R.id.ivPoster))
        return view!!
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
        return view!!
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = false
}