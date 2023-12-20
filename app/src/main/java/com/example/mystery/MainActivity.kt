package com.example.mystery

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mystery.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    private  var launcher:ActivityResultLauncher<Intent>?=null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == RESULT_OK) {
                    val user = result.data?.getStringExtra("user")
                    var correct = "Пправильный ответ"
                    binding.TxtCorrectness.setTextColor(Color.GREEN)
                    if (user != TextAnswer(random)) {
                        correct = "Неравильный ответ"
                        binding.TxtCorrectness.setTextColor(Color.RED)
                        rightNumber++
                    }
                    count++
                    if (count == 10)
                    {
                        binding.StaticBtn.setEnabled(true)
                        list = (0..29).toMutableList()
                        total += count
                        count = 0
                        binding.QuestionBtn.text = "Начать сначала"
                        binding.TxtQuest.text = "Игра закончена"
                    }
                    binding.QuestionBtn.setEnabled(true)
                    binding.AnswerBtn.setEnabled(false)
                    binding.TxtAnswer.text = "Ваш ответ - " + answer
                    binding.TxtCorrectness.text = correct
                }
            }
    }

    fun TextAnswer(random:Int):String {
        var arr = arrayOf("Яйцо","Иголка","Кукуруза","Я","Сосулька","Луна","Неправильно","Холодильник","Дверь","Перчатка","Бутылка","Тень",
            "Имя","Окно","Все")
        var returntxt = arr[random]
        return returntxt
    }

    fun TextMystery(random:Int):String {
        var arr = arrayOf("Что нужно разбить, прежде чем вы сможете это использовать?",
        "Что имеет одно ушко, но не может слышать?",
        "Выбросить то, что снаружи и сварить то, что внутри, а затем съесть то, что снаружи и выбросить то, что внутри.",
        "какая буква похожа на вас как две капли воды?",
        "Она живет зимой, умирает летом и растет сверху вниз?",
        "Она существует уже миллионы лет, но ей всегда не больше месяца.",
        "Какое слово пишется неправильно в каждом словаре?",
        "У меня много еды, но я ничего не могу съесть.",
        "У меня есть ручка, но я ей не смогу ничего написать.",
        "Что имеет большой палец и еще четыре пальца, но оно не живое?",
        "Что имеет горлышко, но без головы и издает только звук буль-буль-буль",
        "Она всегда рядом с нами, но мы никогда не сможем потерять ее.",
        "Что принадлежит вам, но другие люди пользуются этим гораздо чаще, чем вы?",
        "Какое изобретение позволяет вам смотреть прямо сквозь стену?",
        "Сколько месяцев имеют 28 дней?")
        var returntxt = arr[random]
        return returntxt
    }

    var count=0
    var total = 0
    var list = (0..29).toMutableList()
    var random = 0
    var txtQuestion = ""
    var rightNumber = 0
    val answer = Intent(this, AnswerActivity::class.java)
    val static = Intent(this,StaticActivity::class.java)

    fun onClickQuest(view:View) {
            binding.AnswerBtn.setEnabled(true)
            binding.QuestionBtn.setEnabled(false)
            random = list.random()
            list.removeAt(random)
            binding.NumQuest.text = "Вопрос №" + (1 + count).toString()
        txtQuestion = TextMystery(random)
            binding.TxtQuest.text= txtQuestion
    }

    fun onClickAnswer(view:View) {
        answer.putExtra("txtQuestion",txtQuestion)
        launcher?.launch(answer)
    }

    fun onClickStatic(view:View) {
        static.putExtra("rightNumber",rightNumber)
        static.putExtra("wrongNumber",total - rightNumber)
        startActivity(static)
    }
}