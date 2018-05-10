package com.example.rafaelanastacioalves.moby

import android.content.Intent
import android.os.Bundle
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import com.example.rafaelanastacioalves.moby.entitydetailing.EntityDetailsFragment
import com.example.rafaelanastacioalves.moby.entitydetailing.EntityDetailActivity
import com.example.rafaelanastacioalves.moby.util.RestServiceTestHelper

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import java.io.IOException

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.view.View
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString


@RunWith(AndroidJUnit4::class)
class MainEntityDetailActivityTest {
    @get:Rule
    var tripPackageDetailActivityTestRule = ActivityTestRule(EntityDetailActivity::class.java, true, false)
    private val fileNameTripPackageDetailOKResponse = "package_detail_ok_response.json"
    private var server: MockWebServer? = null
    private val MOCK_PACKAGE_ID = "01"


    @Before
    @Throws(Exception::class)
    fun setUp() {
        server = MockWebServer()
        server!!.start(1234)
        InstrumentationRegistry.registerInstance(InstrumentationRegistry.getInstrumentation(), Bundle())
        server!!.url("/").toString()
    }

    @Test
    @Throws(IOException::class)
    fun shouldShowTripPackageDetailSuccess() {
        server!!.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(
                        InstrumentationRegistry.getInstrumentation().context, fileNameTripPackageDetailOKResponse)
                )
        )

        val intent = Intent()
        intent.putExtra(EntityDetailsFragment.ARG_PACKAGE_ID, MOCK_PACKAGE_ID)
        tripPackageDetailActivityTestRule.launchActivity(intent)



        onView(allOf<View>(withId(R.id.detail_entity_detail_name), withText("5000,00"))).check(matches(isDisplayed()))

    }


    @After
    @Throws(Exception::class)
    fun tearDown() {
        server!!.shutdown()
    }
}
