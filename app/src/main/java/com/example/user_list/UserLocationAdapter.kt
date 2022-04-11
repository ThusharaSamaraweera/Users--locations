package com.example.user_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserLocationAdapter: RecyclerView.Adapter<UserLocationAdapter.UserLocationViewHolder>() {
    private var userLocationList : ArrayList<UserLocationModel> = ArrayList()
    private var onClickUserLoc: ((UserLocationModel) -> Unit)? = null
    private var onClickDeleteUser: ((UserLocationModel) -> Unit)? = null

    fun addUserLoc(locs: ArrayList<UserLocationModel>){
        this.userLocationList = locs
        notifyDataSetChanged()
    }

    fun setOnClickItem(callback: (UserLocationModel) -> Unit){
        this.onClickUserLoc = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserLocationViewHolder =  {
        LayoutInflater.from(parent.context).inflate(R.layout.user,parent,false)
    }

    fun setOnClickDeleteUserLoc(callback: (UserLocationModel) -> Unit){
        this.onClickDeleteUser = callback
    }

    class UserLocationViewHolder(var view: View): RecyclerView.ViewHolder(view){
        private var username = view.findViewById<TextView>(R.id.tv_username)
        private var city = view.findViewById<TextView>(R.id.tv_city)
        private var long = view.findViewById<TextView>(R.id.tv_long)
        private var lati = view.findViewById<TextView>(R.id.tv_lati)
        var btnDelete = view.findViewById<Button>(R.id.btn_delete)

        fun bindView(userLocationModel: UserLocationModel){
            username.text = userLocationModel.username
            city.text = userLocationModel.city
            long.text = userLocationModel.longitude
            lati.text = userLocationModel.latitude
        }
    }
    override fun onBindViewHolder(holder: UserLocationViewHolder, position: Int) {
        val userLoc = userLocationList[position]
        holder.bindView(userLoc)
        holder.itemView.setOnClickListener{ onClickUserLoc?.invoke(userLoc)}
        holder.btnDelete.setOnClickListener{ onClickDeleteUser?.invoke(userLoc)}
    }

    override fun getItemCount(): Int {
       return userLocationList.size
    }


}