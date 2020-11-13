package com.koch.sampleproject;


import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.koch.sampleproject.ui.movies.MoviesActivity;
import com.koch.sampleproject.ui.movies.MoviesViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.not;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static org.mockito.ArgumentMatchers.any;

@RunWith(AndroidJUnit4.class)
public class MoviesActivityTests {

    @Rule
    public ActivityTestRule<MoviesActivity> testRule = new ActivityTestRule<>(MoviesActivity.class);

    @Mock
    MoviesViewModel viewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testRule.launchActivity(new Intent());
    }

    @Test
    public void testIfErrorToastIsShown() {
        viewModel.onError(new Error("TestError"));

        // TODO

        onView(withText("TestError")).inRoot(withDecorView(not(testRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void testIfRecyclerViewInflateItemsAfterButtonClick() throws InterruptedException {
        // given
        ViewInteraction viewInteractionButton = onView(withId(R.id.trendingButton));
        RecyclerView recyclerView = testRule.getActivity().findViewById(R.id.moviesList);

        // when
        viewInteractionButton.perform(ViewActions.click());
        Thread.sleep(2500);  // TODO: replace sleep with idlingResource or another way to handle waiting for api response

        // then
        Assert.assertTrue(recyclerView.getAdapter().getItemCount() > 0);
    }
}
