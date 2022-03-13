package com.moonx.roomapp.ui.mainActvity.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moonx.roomapp.R
import com.moonx.roomapp.models.User
import com.moonx.roomapp.view_models.LoginViewModel

class LoginFragment : Fragment(), UserAdapter.UserReListener {
    lateinit var mainView: View

    lateinit var userName_et: TextView
    lateinit var add_btn: Button
    lateinit var act_title: TextView
    lateinit var line: View

    lateinit var userAdapter: UserAdapter
    lateinit var recyclerView: RecyclerView

    lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainView = inflater.inflate(R.layout.fragment_login, container, false)
        initViewModel()
        initModelView()
        return mainView
    }

    override fun onResume() {
        super.onResume()
        observeData()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        observeData()

        viewModel.setting.observe(requireActivity(), {
            if (it.isEmpty()) {
                viewModel.initSetting()
            } else {
//                var currentUser = User(0, "UnKnow")
//                for (user in userAdapter.userList) {
//                    if (it[0].currentId == user.id)
//                        currentUser = user
//                }
//                val action = LoginFragmentDirections.actionLoginFragmentToMainFragment(currentUser)
//                findNavController().navigate(action)
            }
        })
    }

    private fun observeData() {
        viewModel.users.observe(requireActivity(), {
            userAdapter.apply {
                userList = it
                notifyDataSetChanged()
            }
            showRecyclerView()
        })
    }

    private fun showRecyclerView() {
        if (userAdapter.userList.isNotEmpty()) {
            act_title.visibility = View.VISIBLE
            recyclerView.visibility = View.VISIBLE
            line.visibility = View.VISIBLE
        }else{
            act_title.visibility = View.GONE
            recyclerView.visibility = View.GONE
            line.visibility = View.GONE
        }
            userName_et.text = ""
    }

    private fun initModelView() {
        userName_et = mainView.findViewById(R.id.user_name_tv)
        add_btn = mainView.findViewById(R.id.add_btn)
        act_title = mainView.findViewById(R.id.act_title)
        line = mainView.findViewById(R.id.line)

        userAdapter = UserAdapter()
        userAdapter.listner = this
        recyclerView = mainView.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = userAdapter


        initEvents()
    }

    private fun initEvents() {
        add_btn.setOnClickListener {
            viewModel.addUser(
                User(0, userName_et.text.toString())
            )
        }
    }

    override fun onUserChange(user: User) {
        viewModel.setCurrentUser(user)
    }

    override fun onDeleteUser(user: User) {
        viewModel.deleteUser(user)

    }
}