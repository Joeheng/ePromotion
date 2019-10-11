package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.adapter.HomeAdapter
import com.example.myapplication.data.ServiceGenerator
import com.example.myapplication.data.service.ApiService
import com.example.myapplication.model.Cardview
import com.example.myapplication.model.response.ApiResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    lateinit var adapter: HomeAdapter
    var cardItems: ArrayList<Cardview> = ArrayList()
    var compositeDisposable: CompositeDisposable? = null
    //    var apiService: ApiService? = null
//    lateinit var apiService : ApiService

    private val apiService = ServiceGenerator.createSerive(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = HomeAdapter(cardItems, this)
        rv.adapter = adapter

        getApiData()


        /*val getCard = intent.getSerializableExtra("obj") as Cardview

        val card = Cardview(0, "we", "we")
        val intent = Intent(applicationContext, MainActivity::class.java)
            .putExtra("obj", card)
        startActivity(intent)*/
    }

    fun getApiData() {
//        apiService = ServiceGenerator.createSerive(ApiService::class.java)
//        compositeDisposable?.add(apiService.getApiwithRx()
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribeWith(object : DisposableSubscriber<ApiResponse>() {
//                override fun onComplete() {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onNext(t: ApiResponse?) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onError(t: Throwable?) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//            }))


        apiService.getApiwithRx()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : DisposableSubscriber<ApiResponse>() {
                override fun onComplete() {
                    Log.e("app", "onComplete")
                }

                override fun onNext(t: ApiResponse?) {
//                    if (t?.cards != null) {
//                        adapter.setdata(t.cards)
//                    } else adapter.setdata(emptyList())

                    val data = t?.cards ?: emptyList()
                    adapter.setdata(data)
                }

                override fun onError(t: Throwable?) {
                    Log.e("app", t?.message.toString())
                }
            })
    }
}
