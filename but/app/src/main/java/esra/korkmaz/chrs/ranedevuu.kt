package esra.korkmaz.chrs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import esra.korkmaz.chrs.databinding.ActivityRandevuekleeBinding
import esra.korkmaz.chrs.databinding.ActivityRanedevuuBinding

class ranedevuu : AppCompatActivity(), randevuadapter.randevuadapterclicklistener {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseref: DatabaseReference
    private lateinit var binding: ActivityRanedevuuBinding
    private lateinit var adapter:randevuadapter
    private lateinit var mlist:MutableList<randevudata>
    private lateinit var randevuRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRanedevuuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        randevuRecyclerView=findViewById(R.id.recyclerview)
        var btnrandevu=findViewById<Button>(R.id.btnrandevu)
        var tvrandevu=findViewById<TextView>(R.id.tvrandevu)
        var tvrandevu2=findViewById<TextView>(R.id.tvrandevu2)


        firebaseAuth= FirebaseAuth.getInstance()
        databaseref= FirebaseDatabase.getInstance().reference.child("Randevu")
        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.layoutManager= LinearLayoutManager(this)
        mlist= mutableListOf<randevudata>()
        adapter= randevuadapter(mlist)
        adapter.setlistener(this)
        binding.recyclerview.adapter= adapter
        getdatafromfirebase()





    }
    private fun getdatafromfirebase(){
        databaseref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                mlist.clear()
                if (snapshot.exists()){
                    for (randevuSnapshot in snapshot.children){
                        val randevu=randevuSnapshot.getValue(randevudata::class.java)
                        mlist.add(randevu!!)
                    }

                }
                adapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {

            }


        })
    }

    override fun ondeleterandevubtnclciked(randevudata: randevudata) {
        val uid=firebaseAuth.currentUser?.uid
        val randevu=randevudata(randevudata.tarih,randevudata.saat,randevudata.makineno)
        if (uid!= null){
        databaseref.child(uid).removeValue().addOnCompleteListener {
            if (it.isSuccessful){
               // Toast.makeText(this," randevu başarı ile silindi", Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()

            }
        }}


    }
    override fun oneditrandevubtnclicked(randevudata: randevudata) {

    }
}