package com.example.rafaelanastacioalves.moby;

import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.rafaelanastacioalves.moby.entitydetailing.EntityDetailsFragment;
import com.example.rafaelanastacioalves.moby.entitydetailing.EntityDetailActivity;
import com.example.rafaelanastacioalves.moby.util.RestServiceTestHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;


@RunWith(AndroidJUnit4.class)
public class MainEntityDetailActivityTest {
    @Rule
    public ActivityTestRule<EntityDetailActivity> tripPackageDetailActivityTestRule = new ActivityTestRule<EntityDetailActivity>(EntityDetailActivity.class, true, false);
    private String fileNameTripPackageDetailOKResponse = "package_detail_ok_response.json";
    private MockWebServer server;
    private String MOCK_PACKAGE_ID = "01";


    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start(1234);
        InstrumentationRegistry.registerInstance(InstrumentationRegistry.getInstrumentation(),new Bundle());
        server.url("/").toString();
    }

    @Test
    public void shouldShowTripPackageDetailSuccess() throws IOException {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(
                        InstrumentationRegistry.getInstrumentation().getContext()
                        , fileNameTripPackageDetailOKResponse)
                )
        );

        Intent intent = new Intent();
        intent.putExtra(EntityDetailsFragment.Companion.getARG_PACKAGE_ID(), MOCK_PACKAGE_ID);
        tripPackageDetailActivityTestRule.launchActivity(intent);



        onView(allOf(withId(R.id.detail_entity_detail_name), withText("5000,00"))).check(matches(isDisplayed()));

    }


    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }
}
