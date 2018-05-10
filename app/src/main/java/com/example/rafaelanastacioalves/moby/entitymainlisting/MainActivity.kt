package com.example.rafaelanastacioalves.moby.entitymainlisting

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

import com.example.rafaelanastacioalves.moby.entitydetailing.EntityDetailsFragment
import com.example.rafaelanastacioalves.moby.entitydetailing.EntityDetailActivity
import com.example.rafaelanastacioalves.moby.R
import com.example.rafaelanastacioalves.moby.entities.MainEntity
import com.example.rafaelanastacioalves.moby.listeners.RecyclerViewClickListener

import timber.log.Timber

class MainActivity : AppCompatActivity(), RecyclerViewClickListener {
    private val mClickListener = this
    private var mTripPackageListAdapter: MainEntityAdapter? = null
    private val tripPackageListLoaderId = 10
    private var mRecyclerView: RecyclerView? = null
    private var mLiveDataMainEntityListViewModel: LiveDataMainEntityListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
        setupRecyclerView()
        subscribe()
        loadData()

    }

    private fun loadData() {
        mLiveDataMainEntityListViewModel!!.loadData()
    }

    private fun subscribe() {
        mLiveDataMainEntityListViewModel = ViewModelProviders.of(this).get(LiveDataMainEntityListViewModel::class.java!!)
        mLiveDataMainEntityListViewModel!!.mainEntityList.observe(this, Observer { mainEntities ->
            Timber.d("On Changed")
            populateRecyclerView(mainEntities)
        })
    }

    private fun setupViews() {
        setContentView(R.layout.activity_main)
        Timber.tag("LifeCycles")
        Timber.i("onCreate Activity")
    }

    private fun setupRecyclerView() {
        mRecyclerView = findViewById<View>(R.id.trip_package_list) as RecyclerView
        val layoutManager = LinearLayoutManager(applicationContext)
        mRecyclerView!!.layoutManager = layoutManager
        if (mTripPackageListAdapter == null) {
            mTripPackageListAdapter = MainEntityAdapter(this)
        }
        mTripPackageListAdapter!!.setRecyclerViewClickListener(mClickListener)
        mRecyclerView!!.adapter = mTripPackageListAdapter
    }


    private fun populateRecyclerView(data: List<MainEntity>?) {
        if (data == null) {
            mTripPackageListAdapter!!.setItems(null)
            //TODO add any error managing
            Timber.w("Nothing returned from Trip Package List API")

        } else {
            mTripPackageListAdapter!!.setItems(data)
        }

    }


    override fun onClick(view: View, position: Int) {
        val MainEntity = mTripPackageListAdapter!!.getItems()!!.get(position)

        val transitionImageView = view.findViewById<View>(R.id.main_entity_imageview)
        startActivityByVersion(MainEntity, transitionImageView as AppCompatImageView)


    }

    private fun startActivityByVersion(mainEntity: MainEntity, transitionImageView: AppCompatImageView) {
        val i = Intent(this, EntityDetailActivity::class.java)
        i.putExtra(EntityDetailsFragment.ARG_PACKAGE_ID, mainEntity.getId())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            var bundle: Bundle? = null
            bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity,
                    transitionImageView, transitionImageView.transitionName).toBundle()
            startActivity(i, bundle)

        } else {
            startActivity(i)
        }
    }
}
