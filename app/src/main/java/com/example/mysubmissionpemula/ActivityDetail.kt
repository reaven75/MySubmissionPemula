package com.example.mysubmissionpemula

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.example.mysubmissionpemula.databinding.ActivityDetailBinding

class ActivityDetail : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val tvDataName :TextView = findViewById(R.id.data_nama)
//
//        val tvDataLeader: TextView = findViewById(R.id.data_pimpinan)
//
//        val tvDataBorn: TextView = findViewById(R.id.data_dibentuk)
//
//        val tvLDataDesc: TextView = findViewById(R.id.tv_data_deskripsi)
//        val tvDataPhoto: ImageView = findViewById(R.id.img_detail_photo)

        val dataParpol = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Parpol>("DATA", Parpol::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Parpol>("DATA")
        }

        if(dataParpol != null) {

            binding.dataNama.text = dataParpol.name
            binding.dataPimpinan.text = dataParpol.leader
            binding.dataDibentuk.text = dataParpol.born
            binding.dataDeskripsi.text = dataParpol.description
            binding.imgDetailPhoto.setImageResource(dataParpol.photo)
//             tvDataName.text = dataParpol.name
//            tvDataLeader.text = dataParpol.leader
//            tvDataBorn.text = dataParpol.born
//            tvLDataDesc.text = dataParpol.description
//            tvDataPhoto.setImageResource(dataParpol.photo)
            Log.d("DETAIL DATA", dataParpol.leader)
        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val dataParpol = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Parpol>("DATA", Parpol::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Parpol>("DATA")
        }

        if(dataParpol != null) {

            // Test Log data
            Log.d("DETAIL DATA", dataParpol.leader)
        }

        val dataSend = "Nama : " + dataParpol?.name.toString()  +"\n" +"Ketua Umum : " + dataParpol?.leader +"\n" + "\n" +
                "GUNAKAN HAK SUARA ANDA, JANGAN GOLPUTT !!!"
        when(item.itemId){

            R.id.action_share -> {

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT,dataSend)
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)

                Log.d("SEND BUTTON", "BERHASIL ")
            }

        }
        return super.onOptionsItemSelected(item)
    }



}