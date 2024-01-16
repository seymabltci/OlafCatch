package com.seyma.olafcatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.seyma.olafcatch.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var score = 0
    var imageArray = ArrayList<ImageView>()
    var handler = Handler(Looper.getMainLooper())
    var runnable = Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //tek tek hepsini saklamamak için ArrayList yaptık ve tüm id leri ekledik
        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)
        imageArray.add(binding.imageView10)
        imageArray.add(binding.imageView11)
        imageArray.add(binding.imageView12)
        imageArray.add(binding.imageView13)
        imageArray.add(binding.imageView14)
        imageArray.add(binding.imageView15)
        imageArray.add(binding.imageView16)



        hideImages()

        object : CountDownTimer(15500,1000){ //geriye sayma 15 saniye ve saniyede bir geri say
            override fun onFinish() {

                binding.timeText.text = "Time: 0"

                handler.removeCallbacks(runnable) //runable durdurma

                for (image in imageArray) { //runable durunca hepsini gizle
                    image.visibility = View.INVISIBLE
                }



                //Alert
                val alert = AlertDialog.Builder(this@MainActivity)

                alert.setTitle("Game Over")
                alert.setMessage("Score: ${score} Restart The Game? ")
                alert.setPositiveButton("Yes") {dialog, which ->
                    //Restart
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No") {dialog, which ->
                    Toast.makeText(this@MainActivity,"Game Over", Toast.LENGTH_LONG).show()
                }
                alert.show()


            }

            override fun onTick(millisUntilFinished: Long) {  //: CountDownTimer ile birlikte kullanılan fonk
                binding.timeText.text = "Time: " + millisUntilFinished/1000
            }

        }.start()

    }


    fun hideImages() { //resimleri saklama fonksiyonu

        runnable = object : Runnable {
            override fun run() { //runable a baglı oluşan fonks
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE //gizleme
                }

                val random = Random()
                val randomIndex = random.nextInt(16)
                imageArray[randomIndex].visibility = View.VISIBLE

                handler.postDelayed(runnable,500) //kaç saniyede bir
            }

        }

        handler.post(runnable) //runable çalıştırılması

    }


    fun increaseScore(view: View){ //skoru her tıklandıgında arttıran fonksiyon, ismi xml de onclick yerine yazıldı
        score = score + 1
        binding.scoreText.text = "Score: $score"

    }
}