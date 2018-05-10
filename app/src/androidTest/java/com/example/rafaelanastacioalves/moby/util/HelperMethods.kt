package com.example.rafaelanastacioalves.moby.util

import android.support.test.espresso.matcher.BoundedMatcher
import android.support.v7.widget.RecyclerView
import android.view.View

import org.hamcrest.Description
import org.hamcrest.Matcher

object HelperMethods {

    fun withHolderContainingId(id: Int): Matcher<RecyclerView.ViewHolder> {
        return object : BoundedMatcher<RecyclerView.ViewHolder, RecyclerView.ViewHolder>(RecyclerView.ViewHolder::class.java) {

            override fun matchesSafely(item: RecyclerView.ViewHolder): Boolean {
                val visualizeView = item.itemView.findViewById<View>(id) ?: return false
                return true
            }

            override fun describeTo(description: Description) {
                description.appendText("No ViewHolder found with id: $id")
            }

        }
    }
}
