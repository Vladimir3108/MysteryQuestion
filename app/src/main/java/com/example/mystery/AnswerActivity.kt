package com.example.mystery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mystery.databinding.ActivityAnswerBinding
import com.example.mystery.databinding.ActivityMainBinding

class AnswerActivity : AppCompatActivity() {
    lateinit var binding: ActivityAnswerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var txtQuestion = intent.getStringExtra("txtQuestion")
        binding.TxtQuestion.text = txtQuestion
    }

    fun onClickCheck(view: View) {
        var user = binding.EditTxtAnswer.text
        val main = Intent(this,MainActivity::class.java)
        main.putExtra("user",user)
        setResult(RESULT_OK,main)
        finish()
    }

    fun onClickEditAnswer(view: View) {
        if (binding.EditTxtAnswer.text.toString()!="")
        {
            binding.CheckBtn.setEnabled(true)
        }
        else
        {
            binding.CheckBtn.setEnabled(false)
        }
    }
}