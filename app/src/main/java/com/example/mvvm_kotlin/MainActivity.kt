package com.example.mvvm_kotlin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var loginViewModel: LoginViewModel

    lateinit var context: Context

    lateinit var strUsername: String
    lateinit var strPassword: String

    lateinit var btnAddLogin: Button
    lateinit var btnReadLogin: Button

    lateinit var txtUsername: AppCompatEditText
    lateinit var txtPassword: AppCompatEditText

    lateinit var lblInsertResponse: AppCompatTextView
    lateinit var lblReadHeading: AppCompatTextView
    lateinit var lblUseraname: AppCompatTextView
    lateinit var lblPassword: AppCompatTextView
    lateinit var lblReadResponse: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        context = this@MainActivity

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        btnAddLogin.setOnClickListener {

            strUsername = txtUsername.text.toString().trim()
            strPassword = txtPassword.text.toString().trim()

            if (strPassword.isEmpty()) {
                txtUsername.error = "Please enter the username"
            } else if (strPassword.isEmpty()) {
                txtPassword.error = "Please enter the username"
            } else {
                loginViewModel.insertData(context, strUsername, strPassword)
                lblInsertResponse.text = "Inserted Successfully"
            }
        }

        btnReadLogin.setOnClickListener {

            strUsername = txtUsername.text.toString().trim()

            loginViewModel.getLoginDetails(context, strUsername)!!.observe(this, Observer {

                if (it == null) {
                    lblReadResponse.text = "Data Not Found"
                    lblUseraname.text = "- - -"
                    lblPassword.text = "- - -"
                } else {
                    lblUseraname.text = it.Username
                    lblPassword.text = it.Password

                    lblReadResponse.text = "Data Found Successfully"
                }
            })
        }
    }

    fun init() {
        btnAddLogin = findViewById(R.id.btnAddLogin)
        btnReadLogin = findViewById(R.id.btnReadLogin)

        txtUsername = findViewById(R.id.txtUsername)
        txtPassword = findViewById(R.id.txtPassword)

        lblInsertResponse = findViewById(R.id.lblInsertResponse)
        lblReadHeading = findViewById(R.id.lblReadHeading)
        lblUseraname = findViewById(R.id.lblUseraname)
        lblPassword = findViewById(R.id.lblPassword)
        lblReadResponse = findViewById(R.id.lblReadResponse)
    }
}