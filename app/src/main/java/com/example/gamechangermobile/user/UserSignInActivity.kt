package com.example.gamechangermobile.user

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gamechangermobile.R
import com.example.gamechangermobile.models.User
import kotlinx.android.synthetic.main.activity_user_signin.*

class UserSignInActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_signin)
        sign_in_button.setOnClickListener {
            val email = account_edittext_view.text.toString()
            val password = password_edittext_view.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Invalid account or password", Toast.LENGTH_SHORT).show()
            } else {
                val ret: Pair<Boolean, User> = verifyAccount(email.toString(), password.toString())
                if (ret.first) {
                    val intent = Intent()
                    intent.putExtra("USER", ret.second)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                } else {
                    Toast.makeText(this, "Incorrect account or password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun verifyAccount(email: String, password: String): Pair<Boolean, User> {
        // TODO
        return Pair<Boolean, User>(true, User("Harrison", "LO", email = email, password = password))
    }
}