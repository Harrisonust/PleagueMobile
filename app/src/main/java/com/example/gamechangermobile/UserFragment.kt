package com.example.gamechangermobile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_setting.setOnClickListener{
            Toast.makeText(view.context, "setting", Toast.LENGTH_SHORT).show()
        }
        my_info_button.setOnClickListener {
            Toast.makeText(view.context, "info", Toast.LENGTH_SHORT).show()
        }
        my_teams_button.setOnClickListener {
            Toast.makeText(view.context, "teams", Toast.LENGTH_SHORT).show()
        }
        my_players_button.setOnClickListener {
            Toast.makeText(view.context, "players", Toast.LENGTH_SHORT).show()
        }
    }
}

