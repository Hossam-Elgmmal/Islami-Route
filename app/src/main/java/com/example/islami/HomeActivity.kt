package com.example.islami

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.islami.databinding.ActivityHomeBinding
import com.example.islami.fragments.HadeethFragment
import com.example.islami.fragments.QuranFragment
import com.example.islami.fragments.RadioFragment
import com.example.islami.fragments.SebhaFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navBar.setOnItemSelectedListener {
            when (it.itemId) {
            R.id.nav_quran ->{
                pushFragment(QuranFragment())
            }
            R.id.nav_hadeeth ->{
                pushFragment(HadeethFragment())
            }
            R.id.nav_sebha ->{
                pushFragment(SebhaFragment())
            }
            R.id.nav_radio ->{
                pushFragment(RadioFragment())
            }

            }

            return@setOnItemSelectedListener true
        }
    }

    private fun pushFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(binding.frameContainer.id , fragment)
            .commit()
    }

}