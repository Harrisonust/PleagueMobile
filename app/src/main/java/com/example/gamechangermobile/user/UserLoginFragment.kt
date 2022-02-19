package com.example.gamechangermobile.user

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gamechangermobile.MainActivity.Companion.currentUser
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.User
import kotlinx.android.synthetic.main.fragment_user_login.*

const val SIGN_IN_REQUEST_CODE = 1

class UserLoginFragment() : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sign_in_button.setOnClickListener {
            val intent = Intent(activity, UserSignInActivity::class.java)
            startActivityForResult(intent, SIGN_IN_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        when (requestCode) {
//            SIGN_IN_REQUEST_CODE -> if (resultCode == Activity.RESULT_OK) {
//                val ret = data?.getParcelableExtra<User>("USER")
//                ret?.let {
//                    currentUser = it
//                    currentUser.isLogIn = true
//                    // switch to UserInfoFragment
//                }
//                Log.d("Debug", ret?.name.toString())
//            }
//        }
    }
}