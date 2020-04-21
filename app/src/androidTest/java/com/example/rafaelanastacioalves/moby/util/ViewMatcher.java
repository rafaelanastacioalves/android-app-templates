package com.example.rafaelanastacioalves.moby.util;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.rafaelanastacioalves.moby.R;
import com.example.rafaelanastacioalves.moby.entitymainlisting.MainEntityViewHolder;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class ViewMatcher {
    public static Matcher<? super View> showMainItemWithTitle(String title, int position) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("Item with title " + title + " at position " +
                        position + ".");
            }

            @Override
            protected boolean matchesSafely(RecyclerView item) {
                MainEntityViewHolder checkedViewHolder = (MainEntityViewHolder) item.findViewHolderForAdapterPosition(position);
                TextView checkedTitleTextView = checkedViewHolder.itemView.findViewById(R.id.main_entity_title_textview);
                return checkedTitleTextView.getText().toString().equals(title) &&
                        checkedTitleTextView.getVisibility() == View.VISIBLE;
            }
        };
    }
}
