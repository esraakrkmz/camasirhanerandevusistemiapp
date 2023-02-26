package esra.korkmaz.chrs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout.LiftOnScrollListener
import esra.korkmaz.chrs.databinding.RandevuItemBinding

class  randevuadapter(private val list: MutableList<randevudata>) :RecyclerView.Adapter<randevuadapter.randevuviewholder>() {
    private var listener:randevuadapterclicklistener?=null
    fun setlistener(listener: randevuadapterclicklistener){
        this.listener=listener
    }
    inner class randevuviewholder(val binding: RandevuItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): randevuviewholder {
        val binding=RandevuItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return randevuviewholder(binding)
    }

    override fun onBindViewHolder(holder: randevuviewholder, position: Int) {
        with(holder){
            with(list[position]){
                binding.tvtarih.text=this.tarih
                binding.tvsaat.text=this.saat
                binding.tvmakine.text=this.makineno

                binding.deleterandevu.setOnClickListener{
                    listener?.ondeleterandevubtnclciked(this)

                }

            }
    }

}

    override fun getItemCount(): Int {
        return list.size
    }

    interface randevuadapterclicklistener{
        fun ondeleterandevubtnclciked(randevudata: randevudata)
        fun oneditrandevubtnclicked(randevudata: randevudata)

    }
}