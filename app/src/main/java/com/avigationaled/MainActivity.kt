package com.avigationaled

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        val btnLogin = findViewById<Button>(R.id.btn_sign_in)
        val edUsername = findViewById<EditText>(R.id.ed_username)
        val edPassword = findViewById<EditText>(R.id.ed_password)
        val btnCreate = findViewById<TextView>(R.id.tv_signup)

        val sharePreference = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val getUsername = sharePreference.getString("USERNAME","")
        val getPassword = sharePreference.getString("PASSWORD","")

        val sharedPreferenceUser = getSharedPreferences("USERS", Context.MODE_PRIVATE)

        if(sharedPreferenceUser.contains(getUsername))
        {
            Log.d("MOT DE PASSE",sharedPreferenceUser.all[getUsername].toString())
            if(getPassword == sharedPreferenceUser.all[getUsername].toString()){
                val i = Intent(this,LoginPage::class.java)
                startActivity(i)
            }
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
            if(sharedPreferenceUser.contains(edUsername.text.toString()))
            {
                Log.d("MOT DE PASSE",sharedPreferenceUser.all[username].toString())
                if(edPassword.text.toString() == sharedPreferenceUser.all[username].toString()){
                    val i = Intent(this,LoginPage::class.java)
                    startActivity(i)
                }
            }
        }
        btnCreate.setOnClickListener {
            val i = Intent(this,CreateAccount::class.java)
            startActivity(i)
        }
    }


}
