package Data

import com.google.gson.annotations.SerializedName



data class PojoData(val claimobj:claimObj){

        data class claimObj(val patientobj:patientObj,val providerobj:providerObj){
                data class patientObj(val firstName:String,val lastName:String,val totamnt: String,val subval:String)
                data class providerObj(val firstName:String?,val lastName:String?,val datea:String)
        }
}

