package com.avigationaled

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        val btnLogin = findViewById<Button>(R.id.btn_sign_in)
        val edUsername = findViewById<EditText>(R.id.ed_username)
        val edPassword = findViewById<EditText>(R.id.ed_password)

        val sharePreference = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val getUsername = sharePreference.getString("USERNAME","")
        val getPassword = sharePreference.getString("PASSWORD","")

        if(getUsername == "user" && getPassword == "1234")
        {
            val i = Intent(this,LoginPage::class.java)
            startActivity(i)
        }

        btnLogin.setOnClickListener{
            val username = edUsername.text.toString()
            Log.d("USERNAME",username)
            val password = edPassword.text.toString()
            Log.d("PASSWORD",password)

            val editor = sharePreference.edit()
            editor.putString("USERNAME",username)
            editor.putString("PASSWORD",password)
            editor.apply()
            if(username == "user" && password == "1234")
            {
                val i = Intent(this,LoginPage::class.java)
                startActivity(i)
            }
        }
    }
}