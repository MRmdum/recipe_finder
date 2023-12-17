package com.avigationaled

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible

class CreateAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        val btnLogin = findViewById<Button>(R.id.btn_createAccount)
        val edUsername = findViewById<EditText>(R.id.ed_username)
        val edPassword = findViewById<EditText>(R.id.ed_password)
        val edPasswordAgain = findViewById<EditText>(R.id.ed_passwordAgain)
        findViewById<TextView>(R.id.txt_alreadyExist).isVisible = false;
        val sharedPreferenceUser = getSharedPreferences("USERS", Context.MODE_PRIVATE)
        val sharedPreference = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)


        val editorU = sharedPreferenceUser.edit()
        val editor = sharedPreference.edit()


        btnLogin.setOnClickListener {
            val username = edUsername.text.toString()
            val password = edPassword.text.toString()
            val passwordAgain = edPasswordAgain.text.toString()
            val allentries = sharedPreferenceUser.all.keys
            Log.d("PASSWORD", password)
            Log.d("PASSWORD AGAIN",passwordAgain)
            Log.d("USERNAME",username)
            if(password == passwordAgain)
            {
                if(username !in allentries){
                    editorU.putString(username,password)
                    editorU.apply()
                    val i = Intent(this,MainActivity::class.java)
                    startActivity(i)
                }
                else{
                    findViewById<TextView>(R.id.txt_alreadyExist).isVisible = true;
                    findViewById<TextView>(R.id.txt_alreadyExist).setText("Nom d'utilisateur déjà utilisé")
                }
            }
            else
            {
                findViewById<TextView>(R.id.txt_alreadyExist).isVisible = true;
                findViewById<TextView>(R.id.txt_alreadyExist).setText("Mots de passe différents")
            }
        }
    }
}
