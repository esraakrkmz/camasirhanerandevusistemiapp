package esra.korkmaz.chrs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import esra.korkmaz.chrs.databinding.ActivityMenuBinding
import esra.korkmaz.chrs.databinding.ActivityRanedevuuBinding

class menu : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseref: DatabaseReference
    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        var card_randevulist=findViewById<CardView>(R.id.card_randevulist)
        var card_cıkısyap=findViewById<CardView>(R.id.card_cıkısyap)
        var card_randevuekle=findViewById<CardView>(R.id.card_randevuekle)
        val intent=intent
        var a=intent.getStringExtra("yan").toString()
        firebaseAuth= FirebaseAuth.getInstance()
        card_cıkısyap.setOnClickListener {
            firebaseAuth.signOut()
            val intent= Intent(this,login2::class.java)
            startActivity(intent)
        }
        card_randevuekle.setOnClickListener {
            Handler().postDelayed({
                var gecis = Intent(applicationContext, randevuek::class.java)
                gecis.putExtra("a",a )
                startActivity(gecis)

            }, 500)
        }
        card_randevulist.setOnClickListener {
            val intent = Intent(this, ranedevuu::class.java)
            startActivity(intent)
        }




    }
}
