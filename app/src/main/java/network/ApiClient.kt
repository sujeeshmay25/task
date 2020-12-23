package network

//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import android.content.Context
import com.example.sujeesh_inter.BuildConfig

import com.google.gson.GsonBuilder

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


class ApiClient {

    companion object {


        fun getValue(): ApiCall {

            val clientBuilder = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)

            val loggingInterceptor = HttpLoggingInterceptor()
            // set your desired log level
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            // add logging as last interceptor
            if (BuildConfig.DEBUG)
                clientBuilder.addInterceptor(loggingInterceptor)
            //Create a new Interceptor.
            val headerAuthorizationInterceptor = object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                    var request = chain.request()
                    val headers = request.headers.newBuilder()
                        .add("X-AUTH-TOKEN", "eyJraWQiOiJaazlmdTJ4QlNzMzFuRjhoSjZjK0R2QlN4WHRkOHUrd3VRRlhXVnNQT2ljPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiIzZDZjMWJiNS1jZmI4LTRkMzYtYmEyZC01YjhiZjQyMGE0MGIiLCJldmVudF9pZCI6IjRlZmQyZjBmLTk5ODYtNDI4ZS05MDY2LTRkOTIyOTllMGFjZiIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoiYXdzLmNvZ25pdG8uc2lnbmluLnVzZXIuYWRtaW4iLCJhdXRoX3RpbWUiOjE2MDg1NTI2ODMsImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC51cy1lYXN0LTEuYW1hem9uYXdzLmNvbVwvdXMtZWFzdC0xX0ExY3p6TVJCVyIsImV4cCI6MTYwODU1NjI4MywiaWF0IjoxNjA4NTUyNjgzLCJqdGkiOiJhZjk5NmRlNC1hZDlmLTQyMmYtODg3ZC1hNjk3NGNjNjExNTUiLCJjbGllbnRfaWQiOiIxaTlwNjU5Ym1rc2dydmpwZmE5MXJzMzBncSIsInVzZXJuYW1lIjoicnQtamV5YUBtYWlsaW5hdG9yLmNvbSJ9.d2v45cIvDe4PyAcGuKlm64-WKAACfYFf6m56xYeMIKb4IsG-jqtjHYl8MhQcPMzypzCzKFirBCr7IeDAkr1ck1um-wCwQQmkuuTbZRWAZIOuBaXkbwOLMoHXDPiPbSnxRCIXDgn-Af-Bfe96XZzUpfHd1mgAzkwEzKtWlMxC4jQDlW8s5HpnXQq-ImtL-jo6sIYmLnLvviTmzFSGBc8hI6ITmU6JKcNZMBx51XGQX844wxCwJi8e8KEYJKAYWWe1aS_8uM1KNQYoMefuzoS8zlGe1GtmK5biZlO05riow9w3_ZnFoIXuhGIvCOjDcKQ7yHRIq5fCi3lSc8x0QKI37w")
                        .add("Content-Type","application/json").build()
                    request = request.newBuilder().headers(headers).build()
                    return chain.proceed(request)
                }
            }

            clientBuilder.addInterceptor(headerAuthorizationInterceptor)
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(ValueConstant.BASE_URl)
                .baseUrl("https://qa.reimbursify.com/users/self/")
                .client(clientBuilder.build())
                .build()
            return retrofit.create(ApiCall::class.java)
        }



    }
}