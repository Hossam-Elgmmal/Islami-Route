package com.route.islami

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.islami.R
import com.example.islami.databinding.ActivityHomeBinding
import com.route.islami.fragments.HadeethFragment
import com.route.islami.fragments.QuranFragment
import com.route.islami.fragments.RadioFragment
import com.route.islami.fragments.SebhaFragment

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
        binding.navBar.selectedItemId = R.id.nav_quran
    }

    private fun pushFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(binding.frameContainer.id , fragment)
            .commit()
    }

}