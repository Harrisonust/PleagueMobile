package com.example.gamechangermobile.user

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import com.example.gamechangermobile.MainActivity.Companion.currentUser
import com.example.gamechangermobile.R
import kotlinx.android.synthetic.main.activity_user_data.*
import kotlinx.android.synthetic.main.fragment_user_info.*

class UserInformationActivity : AppCompatActivity() {
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

        user_firstname_edittext.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                currentUser.firstName = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        user_lastname_edittext.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                currentUser.lastName = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

}