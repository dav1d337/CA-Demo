package com.koch.sampleproject;


import android.content.Intent;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import com.koch.sampleproject.ui.main.MainActivity;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class SimpleEspressoTests {

    public static final int EXPECTED_RECYCLERVIEW_ITEMS = 100;
    private IdlingResource idlingResource;

    @Rule
    public ActivityTestRule<MainActivity> testRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void init() {
        testRule.launchActivity(new Intent());
        IdlingRegistry.getInstance().register(testRule.getActivity().getIdlingResource());
    }

    @Test
    public void testIfTextViewChangesAfterButtonClick() {
        // given
        ViewInteraction viewInteractionButton = onView(withId(R.id.button));

        // when
        viewInteractionButton.perform(ViewActions.click());

        // then
        onView(withId(R.id.textView)).check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.withText("Binded Text from VW"))));
    }

    @Test
    public void testIfRecyclerViewInflateItemsChangesAfterButtonClick() {
        // given
        ViewInteraction viewInteractionButton = onView(withId(R.id.button));
        RecyclerView recyclerView = testRule.getActivity().findViewById(R.id.recyclerView);

        // when
        viewInteractionButton.perform(ViewActions.click());

        // then
        Assert.assertTrue(recyclerView.getAdapter().getItemCount() == EXPECTED_RECYCLERVIEW_ITEMS);
    }


    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(idlingResource);
    }
}
