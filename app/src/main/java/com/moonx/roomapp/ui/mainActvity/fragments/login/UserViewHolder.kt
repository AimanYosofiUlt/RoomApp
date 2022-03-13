package com.moonx.roomapp.ui.mainActvity.fragments.login

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.moonx.roomapp.R
import com.moonx.roomapp.models.User

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var user: User
    val root_view = itemView.findViewById<CardView>(R.id.root)
    val userName_tv = itemView.findViewById<TextView>(R.id.user_name_tv)

    lateinit var listner: UserListener

    fun listener(listener: UserListener) {
        this.listner = listener
    }

    interface UserListener {
        fun onUserChange(user: User)
        fun onDeleteUser(user: User)
    }

    fun set(user: User) {
        this.user = user
        userName_tv.text = user.name

        initEvents()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initEvents() {
        root_view.apply {
            var isSelected = false
            setOnClickListener {
                if (isSelected) {
                    listner.onDeleteUser(user)
                    setCardBackgroundColor(ContextCompat.getColor(context, R.color.white))
                } else {
                    listner.onUserChange(user)
                    val action = LoginFragmentDirections.actionLoginFragmentToMainFragment(user)
                    findNavController().navigate(action)
                }
            }

            setOnTouchListener { _, event ->
                if (!isSelected) {
                    if (event.action == MotionEvent.ACTION_UP || event.action == MotionEvent.ACTION_MOVE)
                        setCardBackgroundColor(ContextCompat.getColor(context, R.color.white))
                    else if (event.action == MotionEvent.ACTION_DOWN)
                        setCardBackgroundColor(ContextCompat.getColor(context, R.color.light))
                }

                return@setOnTouchListener false
            }

            setOnLongClickListener {
                if (isSelected) {
                    isSelected = false
                    setCardBackgroundColor(ContextCompat.getColor(context, R.color.white))
                } else {
                    isSelected = true
                    setCardBackgroundColor(ContextCompat.getColor(context, R.color.red))
                }


                return@setOnLongClickListener true
            }
        }
    }

}
