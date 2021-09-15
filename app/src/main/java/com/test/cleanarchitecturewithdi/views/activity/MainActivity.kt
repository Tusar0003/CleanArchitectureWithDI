package com.test.cleanarchitecturewithdi.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.cleanarchitecturewithdi.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
