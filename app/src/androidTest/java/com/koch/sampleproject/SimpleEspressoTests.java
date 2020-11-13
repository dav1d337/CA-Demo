package com.koch.sampleproject;


import android.content.Intent;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.koch.sampleproject.network.SimpleIdlingResource;
import com.koch.sampleproject.ui.main.MainActivity;
import com.koch.sampleproject.ui.main.MainViewModel;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class SimpleEspressoTests {

    public static final int EXPECTED_RECYCLERVIEW_ITEMS = 100;  // only for test_api
    private SimpleIdlingResource idlingResource;

    @Rule
    public ActivityTestRule<MainActivity> testRule = new ActivityTestRule<>(MainActivity.class);

     // TODO: do we need instantTaskExectutorRule while having IdlingResource?
     @Rule
     public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    MainViewModel viewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
     //   IdlingRegistry.getInstance().register(idlingResource);
        testRule.launchActivity(new Intent());
    }

    @Test
    public void testIfTextViewChangesAfterButtonClick() throws InterruptedException {
        // given
        ViewInteraction viewInteractionButton = onView(withId(R.id.button));

        // when
        viewInteractionButton.perform(ViewActions.click());
        Thread.sleep(2500); // TODO: replace sleep with idlingResource or another way to handle waiting for api response

        // then
        onView(withId(R.id.textView)).check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.withText("Binded Text from VW"))));
    }

    @Test
    public void testIfRecyclerViewInflateItemsAfterButtonClick() throws InterruptedException {
        // given
        ViewInteraction viewInteractionButton = onView(withId(R.id.button));
        RecyclerView recyclerView = testRule.getActivity().findViewById(R.id.recyclerView);

        // when
        viewInteractionButton.perform(ViewActions.click());
        Thread.sleep(2500); // TODO: replace sleep with idlingResource or another way to handle waiting for api response
        
        // then
        Assert.assertTrue(recyclerView.getAdapter().getItemCount() == EXPECTED_RECYCLERVIEW_ITEMS);
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(idlingResource);
    }
}
