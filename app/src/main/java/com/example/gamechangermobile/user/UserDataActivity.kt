package com.example.gamechangermobile.user

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.example.gamechangermobile.MainActivity.Companion.currentUser
import com.example.gamechangermobile.R
import kotlinx.android.synthetic.main.activity_user_data.*
import kotlinx.android.synthetic.main.fragment_user_info.*

class UserDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_data)
        user_email_textview.text = currentUser.email
        var temp = ""
        repeat(currentUser.password.length) {
            temp += "*"
        }
        user_password_textview.text = temp
        user_firstname_edittext.setText(currentUser.firstName)
        user_lastname_edittext.setText(currentUser.lastName)
    }

}