package com.avigationaled

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.avigationaled.databinding.ActivityMainBinding
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView

class LoginPage : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDisconnect = findViewById<ImageButton>(R.id.btn_disconnect)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        //val success = findViewById<TextView>(R.id.success)
        val sharePreference = getSharedPreferences("MY_PRE",Context.MODE_PRIVATE)

        /*val username = sharePreference.getString("USERNAME","").toString()
        val password = sharePreference.getString("PASSWORD","").toString()
        success.text = "username is : $username Password is : $password"*/
        Log.d("USERNAME_HOMEPAGE",sharePreference.getString("USERNAME","").toString())
        Log.d("PASSWORD_HOMEPAGE",sharePreference.getString("PASSWORD","").toString())
        btnDisconnect.setOnClickListener {
            val editor = sharePreference.edit()
            editor.putString("USERNAME","")
            editor.putString("PASSWORD","")
            editor.apply()
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
        }
    }
}
