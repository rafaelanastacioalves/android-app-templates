package com.example.rafaelanastacioalves.moby.util;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class HelperMethods {

    public static Matcher<RecyclerView.ViewHolder> withHolderContainingId(final int id) {
        return new BoundedMatcher<RecyclerView.ViewHolder, RecyclerView.ViewHolder>(RecyclerView.ViewHolder.class) {

            @Override
            protected boolean matchesSafely(RecyclerView.ViewHolder item) {
                View visualizeView = item.itemView.findViewById(id);
                if (visualizeView == null) {
                    return false;
                }
                return true;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("No ViewHolder found with id: " + id);
            }

        };
    }
}
