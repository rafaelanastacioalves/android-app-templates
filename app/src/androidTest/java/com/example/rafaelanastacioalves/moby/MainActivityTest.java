package com.example.rafaelanastacioalves.moby;

import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.rafaelanastacioalves.moby.trippackagelisting.MainActivity;
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
import static com.example.rafaelanastacioalves.moby.util.HelperMethods.withHolderContainingId;
import static org.hamcrest.core.AllOf.allOf;



@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class, true, false);
    private String fileNameTripPackagesOKResponse = "trip_packages_ok_response.json";
    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start(1234);
        InstrumentationRegistry.registerInstance(InstrumentationRegistry.getInstrumentation(),new Bundle());
        server.url("/").toString();


    }

    @Test
    public void shouldShowTripPackageSuccess() throws IOException {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(
                        InstrumentationRegistry.getInstrumentation().getContext()
                        , fileNameTripPackagesOKResponse)
                )
        );

        Intent intent = new Intent();

        mainActivityActivityTestRule.launchActivity(intent);

        onView(
                withId(R.id.trip_package_list)
        ).perform(
                RecyclerViewActions.scrollToHolder(
                        withHolderContainingId(R.id.detail_entity_title_textview)
                )
        );
        onView(allOf(withId(R.id.detail_entity_title_textview), withText("Disney Premium"))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.trip_package_value_textview), withText("5000,00"))).check(matches(isDisplayed()));

    }


    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }
}
