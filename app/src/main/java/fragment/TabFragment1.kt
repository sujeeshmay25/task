package fragment

import Data.PojoData
import adapter.HomeAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sujeesh_inter.R
import com.google.gson.JsonObject
import network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class TabFragment1 : Fragment() {
    val listPojo=ArrayList<PojoData.claimObj.patientObj>()
    val listPojoPract=ArrayList<PojoData.claimObj.providerObj>()
    lateinit var homeRV: RecyclerView
    lateinit var homeAdapter:HomeAdapter
    var totamnt:String=""
    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root= inflater.inflate(R.layout.tab1, container, false)
        apiCall()

        homeRV=root.findViewById(R.id.home_rv)

        homeRV.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        homeAdapter = HomeAdapter(listPojo,listPojoPract, context)
        homeRV.adapter = homeAdapter

        return  root
    }

    fun apiCall() {

        ApiClient.getValue().homeData().enqueue(object : Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>?, response: Response<JsonObject?>) {

                if (response.isSuccessful) {

                    try {
                        // val jobj: JsonObject = Gson().fromJson(response.body(), JsonObject::class.java)

                        val jsonObject = response.body() as JsonObject
                        Log.w("fgfgfg", "$jsonObject")

                        val jsonArray = jsonObject.getAsJsonArray("claimObj")
                        for (i in 0 until jsonArray.size()) {
                            val id = jsonArray[i] as JsonObject
                            totamnt = id.get("totalAmount").asString
                            val subval=id.get("claimStatusStrForEOB").asString
                            val jsonobj = id.getAsJsonObject("patientObj")
                            listPojo.addAll(listOf(PojoData.claimObj.patientObj(jsonobj.get("firstName").asString, jsonobj.get("lastName").asString, totamnt,subval)))

                            val dateArray=id.getAsJsonArray("dateOfServices")
                            val dateAr=dateArray.get(0)
                            println("date::: "+dateAr.toString())

                            val provArray = id.getAsJsonArray("providerObj")
                            for (j in 0 until provArray.size()) {
                                val id1 = provArray[j] as JsonObject
                                //listPojo.addAll(listOf(PojoData.claimObj.patientObj(id1.get("firstName").asString,id1.get("lastName").asString)))
                                listPojoPract.addAll(listOf(PojoData.claimObj.providerObj(id1.get("firstName")?.asString, id1.get("lastName")?.asString,dateAr.toString())))
                            }


                        }
                        homeAdapter.notifyDataSetChanged()

                    }catch (e:Exception){
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<JsonObject?>?, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                Log.w("err ", t)
            }
        });
    }
}