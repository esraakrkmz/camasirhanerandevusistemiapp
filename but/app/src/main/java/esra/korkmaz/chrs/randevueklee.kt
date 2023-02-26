package esra.korkmaz.chrs

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import esra.korkmaz.chrs.databinding.ActivityLogin2Binding
import esra.korkmaz.chrs.databinding.ActivityRandevuekleeBinding
import java.util.*

class randevueklee : AppCompatActivity(),randevuadapter.randevuadapterclicklistener {

    private lateinit var btnsaat: Button
    private lateinit var tvrandevu: TextView
    private lateinit var tvrandevu2: TextView
    private lateinit var makineno: EditText
    private lateinit var btnrandevu: Button
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseref: DatabaseReference
    private lateinit var binding: ActivityRandevuekleeBinding

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
        binding = ActivityRandevuekleeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        val uid = firebaseAuth.currentUser?.uid
        databaseref = FirebaseDatabase.getInstance().getReference("Randevu")

        // setContentView(R.layout.activity_randevueklee)
        var btnsaat = findViewById<Button>(R.id.btnsaat)
        var makineno = findViewById<EditText>(R.id.makineno)
        var btnrandevu = findViewById<Button>(R.id.btnrandevu)
        var takvim = findViewById<CalendarView>(R.id.takvim)
        var tvrandevu = findViewById<TextView>(R.id.tvrandevu)
        var tvrandevu2 = findViewById<TextView>(R.id.tvrandevu2)

        takvim.setOnClickListener {
            val simdikizaman = Calendar.getInstance()
            val gun = simdikizaman.get(Calendar.DAY_OF_MONTH)
            val ay = simdikizaman.get(Calendar.MONTH)
            val yıl = simdikizaman.get(Calendar.YEAR)
        }

        takvim.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // Bu fonksiyon tarih değiştiğinde çalışacak
            tvrandevu.text = "$dayOfMonth/${month + 1}/$year"

            // Seçilen tarihin geçmişte olup olmadığını kontrol et
            val selectedDate = Calendar.getInstance()
            selectedDate.set(Calendar.YEAR, year)
            selectedDate.set(Calendar.MONTH, month)
            selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val currentDate = Calendar.getInstance()
            if (selectedDate.before(currentDate)) {
                // Seçilen tarih geçmişte kalmışsa bir hata mesajı gösterin ve zaman seçici düğmesini devre dışı bırakın
                Toast.makeText(this@randevueklee, "Geçmiş zamandan randevu seçemezsiniz", Toast.LENGTH_SHORT)
                btnsaat.isEnabled = false
                tvrandevu2.text = ""
            } else {
                // Seçilen tarih gelecektedir, zaman seçici düğmesini etkinleştirin
                btnsaat.isEnabled = true

                btnsaat.setOnClickListener {
                    val currentime = Calendar.getInstance()
                    val saat = currentime.get(Calendar.HOUR_OF_DAY)
                    val dakika = currentime.get(Calendar.MINUTE)

                    TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, HOUR_OF_DAY, MINUTE ->
                        tvrandevu2.text = "$HOUR_OF_DAY:$MINUTE"

                        // Seçilen tarih ve saatin zaten rezerve edilip edilmediğini kontrol edin
                        val selectedDateTime = Calendar.getInstance()
                        selectedDateTime.set(Calendar.YEAR, year)
                        selectedDateTime.set(Calendar.MONTH, month)
                        selectedDateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        selectedDateTime.set(Calendar.HOUR_OF_DAY, HOUR_OF_DAY)
                        selectedDateTime.set(Calendar.MINUTE, MINUTE)
                        databaseref.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                if (dataSnapshot.hasChild(selectedDateTime.timeInMillis.toString())) {

                                    // Seçilen tarih ve saat zaten rezerve edilmişse, bir hata mesajı gösterin ve "Randevu Planla" düğmesini devre dışı bırakın
                                    Toast.makeText(this@randevueklee, "Bu tarih zamanı seçemezsiniz", Toast.LENGTH_SHORT).show()
                                    btnrandevu.isEnabled = false
                                } else {
                                    // The selected date and time is available, enable the "Schedule Appointment" button
                                    btnrandevu.isEnabled = true
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                // Değer okunamadı
                                Log.w("MainActivity", "Failed to read value.", error.toException())
                            }
                        })
                    }, saat, dakika, false).show()
                }
            }
        }
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


                        }else{
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()

                        }
                    }

                }
            }else{
                Toast.makeText(this,"Boş alanları doldurunuz", Toast.LENGTH_SHORT).show()


            }
    }}
    override fun ondeleterandevubtnclciked(randevudata: randevudata) {

    }
    override fun oneditrandevubtnclicked(randevudata: randevudata) {

    }
}