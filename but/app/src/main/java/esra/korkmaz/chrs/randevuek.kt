package esra.korkmaz.chrs

import android.content.Intent
import android.graphics.Color.LTGRAY
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.collection.LLRBNode.Color
import esra.korkmaz.chrs.databinding.ActivityRandevuekBinding
import esra.korkmaz.chrs.databinding.ActivityRandevuekleeBinding
import java.util.*

class randevuek : AppCompatActivity() {
    private lateinit var tvrandevu: TextView
    private lateinit var tvrandevu2: TextView
    private lateinit var btnrandevu: Button
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseref: DatabaseReference
    private lateinit var binding:ActivityRandevuekBinding
    private lateinit var takvim: CalendarView

    var gun = 0
    var yıl = 0
    var ay = 0
    var HOUR_OF_DAY = 0
    var MINUTE = 0
    var saat = 0
    var dakika = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandevuekBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        val uid = firebaseAuth.currentUser?.uid
        databaseref = FirebaseDatabase.getInstance().getReference("Randevu")
        var makineno = findViewById<EditText>(R.id.makineno)
        var btnrandevu = findViewById<Button>(R.id.btnrandevu)
        var takvim = findViewById<CalendarView>(R.id.takvim)
        var tvrandevu = findViewById<TextView>(R.id.tvrandevu)
        var tvrandevu2 = findViewById<TextView>(R.id.tvrandevu2)
        var button11=findViewById<Button>(R.id.button11)
        var button12=findViewById<Button>(R.id.button12)
        var button13=findViewById<Button>(R.id.button13)
        var button14=findViewById<Button>(R.id.button14)
        var button15=findViewById<Button>(R.id.button15)
        var button16=findViewById<Button>(R.id.button16)
        var a= 0.toString()
        val intent=intent
        var yann=intent.getStringExtra("a")

        takvim.setOnClickListener {
            val simdikizaman = Calendar.getInstance()
            val gun = simdikizaman.get(Calendar.DAY_OF_MONTH)
            val ay = simdikizaman.get(Calendar.MONTH)
            val yıl = simdikizaman.get(Calendar.YEAR)
        }

        takvim.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // Bu fonksiyon tarih değiştiğinde çalışacak
            tvrandevu.text = "$dayOfMonth/${month + 1}/$year"
            val selectedDate = Calendar.getInstance()
            selectedDate.set(Calendar.YEAR, year)
            selectedDate.set(Calendar.MONTH, month)
            selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val currentDate = Calendar.getInstance()
            if (selectedDate.before(currentDate)) {
                // Seçilen tarih geçmişte kalmışsa bir hata mesajı gösterin ve zaman seçici düğmesini devre dışı bırakın
                Toast.makeText(this@randevuek, "Geçmiş zamandan randevu seçemezsiniz", Toast.LENGTH_SHORT)
                btnrandevu.isEnabled = false
                tvrandevu2.text = ""
            } else {
                // Seçilen tarih gelecektedir, zaman seçici düğmesini etkinleştirin
                btnrandevu.isEnabled = true}

        }

        button11.setBackgroundColor(android.graphics.Color.DKGRAY)
        button12.setBackgroundColor(android.graphics.Color.DKGRAY)
        button13.setBackgroundColor(android.graphics.Color.DKGRAY)
        button14.setBackgroundColor(android.graphics.Color.DKGRAY)
        button15.setBackgroundColor(android.graphics.Color.DKGRAY)
        button16.setBackgroundColor(android.graphics.Color.DKGRAY)

        button11.setOnClickListener {
            tvrandevu2.text="09:00"
            button11.setBackgroundColor(android.graphics.Color.RED)
            button12.setBackgroundColor(android.graphics.Color.DKGRAY)
            button13.setBackgroundColor(android.graphics.Color.DKGRAY)
            button14.setBackgroundColor(android.graphics.Color.DKGRAY)
            button15.setBackgroundColor(android.graphics.Color.DKGRAY)
            button16.setBackgroundColor(android.graphics.Color.DKGRAY)
            a="1"
        }
        button12.setOnClickListener {
            tvrandevu2.text="11:00"
            button11.setBackgroundColor(android.graphics.Color.DKGRAY)
            button12.setBackgroundColor(android.graphics.Color.RED)
            button13.setBackgroundColor(android.graphics.Color.DKGRAY)
            button14.setBackgroundColor(android.graphics.Color.DKGRAY)
            button15.setBackgroundColor(android.graphics.Color.DKGRAY)
            button16.setBackgroundColor(android.graphics.Color.DKGRAY)
            a="2"
        }
        button13.setOnClickListener {
            tvrandevu2.text="13:00"
            button11.setBackgroundColor(android.graphics.Color.DKGRAY)
            button12.setBackgroundColor(android.graphics.Color.DKGRAY)
            button13.setBackgroundColor(android.graphics.Color.RED)
            button14.setBackgroundColor(android.graphics.Color.DKGRAY)
            button15.setBackgroundColor(android.graphics.Color.DKGRAY)
            button16.setBackgroundColor(android.graphics.Color.DKGRAY)
            a="3"
        }
        button14.setOnClickListener {
            tvrandevu2.text="15:00"
            button11.setBackgroundColor(android.graphics.Color.DKGRAY)
            button12.setBackgroundColor(android.graphics.Color.DKGRAY)
            button13.setBackgroundColor(android.graphics.Color.DKGRAY)
            button14.setBackgroundColor(android.graphics.Color.RED)
            button15.setBackgroundColor(android.graphics.Color.DKGRAY)
            button16.setBackgroundColor(android.graphics.Color.DKGRAY)
            a="4"
        }
        button15.setOnClickListener {
            tvrandevu2.text="17:00"
            button11.setBackgroundColor(android.graphics.Color.DKGRAY)
            button12.setBackgroundColor(android.graphics.Color.DKGRAY)
            button13.setBackgroundColor(android.graphics.Color.DKGRAY)
            button14.setBackgroundColor(android.graphics.Color.DKGRAY)
            button15.setBackgroundColor(android.graphics.Color.RED)
            button16.setBackgroundColor(android.graphics.Color.DKGRAY)
            a="5"

        }
        button16.setOnClickListener {
            tvrandevu2.text="19:00"
            button11.setBackgroundColor(android.graphics.Color.DKGRAY)
            button12.setBackgroundColor(android.graphics.Color.DKGRAY)
            button13.setBackgroundColor(android.graphics.Color.DKGRAY)
            button14.setBackgroundColor(android.graphics.Color.DKGRAY)
            button15.setBackgroundColor(android.graphics.Color.DKGRAY)
            button16.setBackgroundColor(android.graphics.Color.RED)
            a="6"
        }



//seçilen tarihi tvrandevu2 ye at     saat butonlarını tanımla

        btnrandevu.setOnClickListener {
            // val randevuid=firebaseAuth.currentUser?.uid
            val tarih=binding.tvrandevu.text.toString()
            val saat=binding.tvrandevu2.text.toString()
            val makineno=binding.makineno.text.toString()
            val randevu=randevudata(tarih,saat,makineno)
            if (uid!= null){
                if (tarih.isNotEmpty() && saat.isNotEmpty() && makineno.isNotEmpty()){
                    databaseref.child(uid).setValue(randevu).addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(this,"randevu başarı ile olduşturuldu", Toast.LENGTH_SHORT).show()
                            Handler().postDelayed({
                                var gecis = Intent(applicationContext, menu::class.java)
                                gecis.putExtra("yan",a )
                                startActivity(gecis)

                            }, 3000)


                        }else{
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()

                        }
                    }

                }else{
                    Toast.makeText(this,"Boş alanları doldurunuz", Toast.LENGTH_SHORT).show()

                }
            }else{
                Toast.makeText(this,"Boş alanları doldurunuz", Toast.LENGTH_SHORT).show()


            }

        }


        makineno.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(makineno.text.toString()=="1"&& yann=="1"){
                    button11.setBackgroundColor(android.graphics.Color.RED)
                    button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button11.setOnClickListener {
                        Toast.makeText(this@randevuek,"Seçtiğiniz Saat Dolu", Toast.LENGTH_SHORT).show()
                        btnrandevu.isEnabled = false
                    }
                    button12.setOnClickListener {
                        button12.setBackgroundColor(android.graphics.Color.RED)
                        button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                        tvrandevu2.text="11:00"
                        btnrandevu.isEnabled = true
                        a="2"
                    }
                    button13.setOnClickListener {
                        button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button13.setBackgroundColor(android.graphics.Color.RED)
                        button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                        tvrandevu2.text="13:00"
                        btnrandevu.isEnabled = true
                        a="3"

                    }
                    button14.setOnClickListener {
                        button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button14.setBackgroundColor(android.graphics.Color.RED)
                        button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                        tvrandevu2.text="15:00"
                        btnrandevu.isEnabled = true
                        a="4"
                    }
                    button15.setOnClickListener {
                        button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button15.setBackgroundColor(android.graphics.Color.RED)
                        button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                        tvrandevu2.text="17:00"
                        btnrandevu.isEnabled = true
                        a="5"
                    }
                    button16.setOnClickListener {
                        button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button16.setBackgroundColor(android.graphics.Color.RED)
                        tvrandevu2.text="19:00"
                        btnrandevu.isEnabled = true
                        a="6"
                    }


                }
                else if(makineno.text.toString()=="1"&& yann=="2"){
                    button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button12.setBackgroundColor(android.graphics.Color.RED)
                    button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button12.setOnClickListener {
                        Toast.makeText(this@randevuek,"Seçtiğiniz Saat Dolu", Toast.LENGTH_SHORT).show()
                        btnrandevu.isEnabled = false
                    }
                    button11.setOnClickListener {
                        button11.setBackgroundColor(android.graphics.Color.RED)
                        button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                        tvrandevu2.text="09:00"
                        btnrandevu.isEnabled = true
                        a="1"
                    }
                    button13.setOnClickListener {
                        button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button13.setBackgroundColor(android.graphics.Color.RED)
                        button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                        tvrandevu2.text="13:00"
                        btnrandevu.isEnabled = true
                        a="3"
                    }
                    button14.setOnClickListener {
                        button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button14.setBackgroundColor(android.graphics.Color.RED)
                        button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                        tvrandevu2.text="15:00"
                        btnrandevu.isEnabled = true
                        a="4"
                    }
                    button15.setOnClickListener {
                        button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button15.setBackgroundColor(android.graphics.Color.RED)
                        button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                        tvrandevu2.text="17:00"
                        btnrandevu.isEnabled = true
                        a="5"
                    }
                    button16.setOnClickListener {
                        button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button16.setBackgroundColor(android.graphics.Color.RED)
                        tvrandevu2.text="19:00"
                        btnrandevu.isEnabled = true
                        a="6"
                    }

                }else if(makineno.text.toString()=="1"&& yann=="3"){
                    button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button13.setBackgroundColor(android.graphics.Color.RED)
                    button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button13.setOnClickListener {
                        Toast.makeText(this@randevuek,"Seçtiğiniz Saat Dolu", Toast.LENGTH_SHORT).show()
                        btnrandevu.isEnabled = false
                    }
                    button11.setOnClickListener {
                        button11.setBackgroundColor(android.graphics.Color.RED)
                        button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                        tvrandevu2.text="09:00"
                        btnrandevu.isEnabled = true
                        a="1"
                    }
                    button12.setOnClickListener {
                        button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button12.setBackgroundColor(android.graphics.Color.RED)
                        button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                        tvrandevu2.text="11:00"
                        btnrandevu.isEnabled = true
                        a="2"
                    }
                    button14.setOnClickListener {
                        button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button14.setBackgroundColor(android.graphics.Color.RED)
                        button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                        tvrandevu2.text="15:00"
                        btnrandevu.isEnabled = true
                        a="4"
                    }
                    button15.setOnClickListener {
                        button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button15.setBackgroundColor(android.graphics.Color.RED)
                        button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                        tvrandevu2.text="17:00"
                        btnrandevu.isEnabled = true
                        a="5"
                    }
                    button16.setOnClickListener {
                        button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button16.setBackgroundColor(android.graphics.Color.RED)
                        tvrandevu2.text="19:00"
                        btnrandevu.isEnabled = true
                        a="6"
                    }

                }else if(makineno.text.toString()=="1"&& yann=="4"){
                    button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button14.setBackgroundColor(android.graphics.Color.RED)
                    button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button14.setOnClickListener {
                        Toast.makeText(this@randevuek,"Seçtiğiniz Saat Dolu", Toast.LENGTH_SHORT).show()
                        btnrandevu.isEnabled = false
                    }
                    button11.setOnClickListener {
                        button11.setBackgroundColor(android.graphics.Color.RED)
                        button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                        tvrandevu2.text="09:00"
                        btnrandevu.isEnabled = true
                        a="1"
                    }
                    button12.setOnClickListener {
                        button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button12.setBackgroundColor(android.graphics.Color.RED)
                        button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                        tvrandevu2.text="11:00"
                        btnrandevu.isEnabled = true
                        a="2"
                    }
                    button13.setOnClickListener {
                        button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button13.setBackgroundColor(android.graphics.Color.RED)
                        button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                        tvrandevu2.text="13:00"
                        btnrandevu.isEnabled = true
                        a="3"
                    }
                    button15.setOnClickListener {
                        button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button15.setBackgroundColor(android.graphics.Color.RED)
                        button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                        tvrandevu2.text="17:00"
                        btnrandevu.isEnabled = true
                        a="5"
                    }
                    button16.setOnClickListener {
                        button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button16.setBackgroundColor(android.graphics.Color.RED)
                        tvrandevu2.text="19:00"
                        btnrandevu.isEnabled = true
                        a="6"
                    }

                }else if(makineno.text.toString()=="1"&& yann=="5"){
                    button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button15.setBackgroundColor(android.graphics.Color.RED)
                    button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button15.setOnClickListener {
                        Toast.makeText(this@randevuek,"Seçtiğiniz Saat Dolu", Toast.LENGTH_SHORT).show()
                        btnrandevu.isEnabled = false
                    }
                    button11.setOnClickListener {
                        button11.setBackgroundColor(android.graphics.Color.RED)
                        button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                        btnrandevu.isEnabled= true
                        tvrandevu2.text="09:00"
                        a="1"

                    }
                    button12.setOnClickListener {
                        button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button12.setBackgroundColor(android.graphics.Color.RED)
                        button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                        btnrandevu.isEnabled= true
                        tvrandevu2.text="11:00"
                        a="2"
                    }
                    button13.setOnClickListener{
                        button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button13.setBackgroundColor(android.graphics.Color.RED)
                        button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                        btnrandevu.isEnabled= true
                        tvrandevu2.text="13:00"
                        a="3"
                    }
                    button14.setOnClickListener {
                        button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button14.setBackgroundColor(android.graphics.Color.RED)
                        button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                        btnrandevu.isEnabled= true
                        tvrandevu2.text="15:00"
                        a="4"

                    }
                    button16.setOnClickListener {
                        button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button16.setBackgroundColor(android.graphics.Color.DKGRAY)
                        btnrandevu.isEnabled= true
                        tvrandevu2.text="19:00"
                        a="6"
                    }


                }else if(makineno.text.toString()=="1"&& yann=="6"){
                    button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button16.setBackgroundColor(android.graphics.Color.RED)
                    button16.setOnClickListener {
                        Toast.makeText(this@randevuek,"Seçtiğiniz Saat Dolu", Toast.LENGTH_SHORT).show()
                        btnrandevu.isEnabled = false
                    }
                    button11.setOnClickListener {
                        button11.setBackgroundColor(android.graphics.Color.RED)
                        button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                        btnrandevu.isEnabled= true
                        tvrandevu2.text="09:00"
                        a="1"
                    }
                    button12.setOnClickListener {
                        button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button12.setBackgroundColor(android.graphics.Color.RED)
                        button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                        btnrandevu.isEnabled= true
                        tvrandevu2.text="11:00"
                        a="2"
                    }
                    button13.setOnClickListener {
                        button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button13.setBackgroundColor(android.graphics.Color.RED)
                        button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                        btnrandevu.isEnabled= true
                        tvrandevu2.text="13:00"
                        a="3"
                    }
                    button14.setOnClickListener {
                        button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button14.setBackgroundColor(android.graphics.Color.RED)
                        button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                        btnrandevu.isEnabled= true
                        tvrandevu2.text="15:00"
                        a="4"
                    }
                    button15.setOnClickListener {
                        button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                        button15.setBackgroundColor(android.graphics.Color.RED)
                        btnrandevu.isEnabled= true
                        tvrandevu2.text="17:00"
                        a="5"

                    }

                }
                else{
                    button11.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button12.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button13.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button14.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button15.setBackgroundColor(android.graphics.Color.DKGRAY)
                    button16.setBackgroundColor(android.graphics.Color.DKGRAY)

                }
                //burya kopyala
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
}}