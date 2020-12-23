package adapter

import Data.PojoData
import android.annotation.SuppressLint
import android.content.Context
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sujeesh_inter.R
import java.util.*


class HomeAdapter(val fileList: ArrayList<PojoData.claimObj.patientObj>, val provList: ArrayList<PojoData.claimObj.providerObj>, val mContext: Context?) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val VIEW_TYPE_LOADING = 1
    private val VIEW_TYPE_ITEM = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {

            val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_home, parent, false)
            return ViewHolder(v)

    }

    //this method is binding the data on the list
    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        val data = fileList[position]
        val  data1=provList[position]
        holder.patienttxt.text=data.firstName+" "+data.lastName
        holder.totaltxt.text=data.totamnt
        holder.practtxt.text=data1.firstName+" "+data1.lastName
        holder.datetxt.text= data1.datea
        holder.subtxt.text=data.subval

    }


    //this method is giving the size of the list
    override fun getItemCount(): Int {
        println("tag size:::: " + fileList.size)
        return fileList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (fileList[position] == null) {
            return VIEW_TYPE_LOADING
        } else {
            return VIEW_TYPE_ITEM
        }
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        override fun onClick(v: View?) {
        }

        internal var patienttxt: TextView
        internal var practtxt:TextView
        internal var totaltxt: TextView
        internal var datetxt:TextView
        internal var subtxt:TextView


        init {

            patienttxt = view.findViewById(R.id.patient_txt)
            practtxt=view.findViewById(R.id.pract_txt)
            totaltxt = view.findViewById(R.id.total_txt)
            datetxt=view.findViewById(R.id.date_txt)
            subtxt=view.findViewById(R.id.subtxt)

        }

    }


}