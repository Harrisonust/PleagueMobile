package com.example.gamechangermobile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gamechangermobile.MainActivity.Companion.currentUser
import com.example.gamechangermobile.user.*
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment() {
    val userInfoFragment = UserInfoFragment()
    val userLoginFragment = UserLoginFragment()
    val isLogin: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_setting.setOnClickListener {
            Toast.makeText(view.context, "setting", Toast.LENGTH_SHORT).show()
        }
        my_info_button.setOnClickListener {
            if (currentUser.isLogIn) {
                val intent = Intent(activity, UserInformationActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(context, "Please Login", Toast.LENGTH_SHORT).show()
            }
        }
        my_teams_button.setOnClickListener {
            val intent = Intent(activity, UserFavTeamsActivity::class.java)
            startActivity(intent)
        }
        my_players_button.setOnClickListener {
            val intent = Intent(activity, UserFavPlayersActivity::class.java)
            startActivity(intent)
        }

//        if (currentUser.isLogIn) {
        replaceFragment(userInfoFragment)
//        } else {
//            replaceFragment(userLoginFragment)
//        }
    }

    fun replaceFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction().apply {
            replace(R.id.framelayout, fragment)
            commit()
        }
    }
}

