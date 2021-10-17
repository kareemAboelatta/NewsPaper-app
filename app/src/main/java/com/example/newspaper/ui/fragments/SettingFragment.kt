package com.example.newspaper.ui.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.example.newspaper.R
import com.example.newspaper.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_setting.view.*


class SettingFragment : Fragment(R.layout.fragment_setting) {





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v=inflater.inflate(R.layout.fragment_setting, container, false)
        val sharedPref =context?.getSharedPreferences("myPref",Context.MODE_PRIVATE)
        val editor=sharedPref!!.edit()

        var chosenCountry :String="egypt"
        var chosenCode :String="eg"
        v.spin_country.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                chosenCountry=adapterView?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

        v.currentCountry.text=sharedPref.getString("CurrentCountry","Egypt")


        v.btn_submit.setOnClickListener {
            when(chosenCountry){
                "Egypt" ->{
                    chosenCode="eg"
                }
                "United States" ->{
                    chosenCode="us"
                }
                "Germany" ->{
                    chosenCode="de"
                }
                "United Kingdom" ->{
                    chosenCode="gb"
                }
                "Serbia" ->{
                    chosenCode="rs"
                }
                "Saudi Arabia" ->{
                    chosenCode="sa"
                }
            }
            editor.putString("CurrentCountry",chosenCountry)
            editor.putString("CurrentCode",chosenCode)
            editor.apply()
            Toast.makeText(activity,
                "you chose ${chosenCountry}+(${chosenCode})", Toast.LENGTH_LONG).show()

            activity?.startActivity(Intent(activity,MainActivity::class.java))
        }





        return v
    }


}