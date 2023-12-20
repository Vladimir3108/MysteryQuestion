package com.example.mystery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mystery.databinding.ActivityMainBinding
import com.example.mystery.databinding.ActivityStaticBinding

class StaticActivity : AppCompatActivity() {
    lateinit var binding: ActivityStaticBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStaticBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.TxtRight.text = "Правильных ответов: " + intent.getStringExtra("rightNumber")
        binding.TxtWrong.text = "Неправильных ответов: " + intent.getStringExtra("wrongNumber")
    }

    fun onClickBackBtn(view:View)
    {
        finish()
    }
}