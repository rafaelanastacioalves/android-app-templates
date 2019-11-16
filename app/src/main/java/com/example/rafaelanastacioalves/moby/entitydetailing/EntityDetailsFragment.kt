package com.example.rafaelanastacioalves.moby.entitydetailing


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rafaelanastacioalves.moby.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import domain.domain.entities.EntityDetails
import kotlinx.android.synthetic.main.fragment_detail_entity_detail_view.*


/**
 * A simple [Fragment] subclass.
 */
class EntityDetailsFragment : Fragment(), View.OnClickListener {
    private val PACKAGE_ID_LOADER_KEY = "package_id_loader_key"

    lateinit private var mLiveDataEntityDetailsViewModel: LiveDataEntityDetailsViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadData()
    }

    private fun loadData() {
        val mPackageId = arguments!!.getString(ARG_PACKAGE_ID)
        mLiveDataEntityDetailsViewModel = ViewModelProvider.NewInstanceFactory().create(LiveDataEntityDetailsViewModel::class.java)
        mLiveDataEntityDetailsViewModel.loadData(mPackageId).observe(this, Observer { entityDetails -> setViewsWith(entityDetails?.data) })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflateViews(inflater, container)
    }


    private fun inflateViews(inflater: LayoutInflater, container: ViewGroup?): View {
        val rootView = inflater.inflate(R.layout.fragment_detail_entity_detail_view, container, false)
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

    private fun setViewsWith(entityDetails: EntityDetails?) {

        detail_entity_detail_name!!.text = entityDetails?.price
        setupActionBarWithTitle(entityDetails?.title?: "" )
        Picasso.get()
                .load(entityDetails?.image_url)
                .into(trip_package_detail_imageview, object : Callback {
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
