package com.example.rafaelanastacioalves.moby.entitydetailing


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.example.rafaelanastacioalves.moby.R
import com.example.rafaelanastacioalves.moby.entities.EntityDetails
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

import butterknife.BindView
import butterknife.ButterKnife
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 */
class EntityDetailsFragment : Fragment(), View.OnClickListener {
    private val PACKAGE_ID_LOADER_KEY = "package_id_loader_key"

    lateinit private var mLiveDataEntityDetailsViewModel: LiveDataEntityDetailsViewModel

    @BindView(R.id.detail_entity_detail_name)
    lateinit internal var tripPackageDetailValor: TextView

    @BindView(R.id.trip_package_detail_imageview)
    lateinit internal var tripPackageDetailImageview: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribe()
        loadData()
    }

    private fun loadData() {
        val mPackageId = arguments!!.getString(ARG_PACKAGE_ID)
        mLiveDataEntityDetailsViewModel.loadData(mPackageId)
    }

    private fun subscribe() {
        mLiveDataEntityDetailsViewModel = ViewModelProviders.of(this).get(LiveDataEntityDetailsViewModel::class.java)
        mLiveDataEntityDetailsViewModel.entityDetails.observe(this, Observer { entityDetails -> setViewsWith(entityDetails!!) })

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflateViews(inflater, container)
    }


    private fun inflateViews(inflater: LayoutInflater, container: ViewGroup?): View {
        val rootView = inflater.inflate(R.layout.fragment_detail_entity_detail_view, container, false)
        ButterKnife.bind(this, rootView)
        return rootView
    }


    private fun setupActionBarWithTitle(title: String) {
        val mActivity = activity as AppCompatActivity?
        val actionBar = mActivity!!.supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = title


        }
    }

    private fun setViewsWith(entityDetails: EntityDetails) {

        tripPackageDetailValor!!.text = entityDetails.price
        setupActionBarWithTitle(entityDetails.title)
        Picasso.get()
                .load(entityDetails.image_url)
                .into(tripPackageDetailImageview, object : Callback {
                    override fun onSuccess() {
                        activity!!.supportStartPostponedEnterTransition()
                    }

                    override fun onError(e: Exception) {

                    }
                })


    }


    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onClick(v: View) {
        Toast.makeText(activity, "Comprado!", Toast.LENGTH_SHORT).show()
    }

    companion object {

        var ARG_PACKAGE_ID: String? = null
    }


}// Required empty public constructor
