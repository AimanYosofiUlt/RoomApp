package com.moonx.roomapp.ui.mainActvity.fragments.login

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moonx.roomapp.R
import com.moonx.roomapp.models.User

class UserAdapter : RecyclerView.Adapter<UserViewHolder>(), UserViewHolder.UserListener {
    var userList = emptyList<User>()

    lateinit var listner: UserReListener

    fun listener(listener: UserReListener) {
        this.listner = listener
    }

    interface UserReListener {
        fun onUserChange(user: User)
        fun onDeleteUser(user: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val userViewHolder = UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_user, parent, false)
        )

        userViewHolder.listner = this
        return userViewHolder
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.set(userList.get(position))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onUserChange(user: User) {
        listner.onUserChange(user)
    }

    override fun onDeleteUser(user: User) {
        val position = userList.indexOf(user)
        listner.onDeleteUser(user)
        notifyItemRemoved(position)
    }
}