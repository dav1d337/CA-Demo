package com.koch.sampleproject;


import android.content.Intent;
import android.view.View;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.koch.sampleproject.ui.main.MainActivity;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;


@RunWith(AndroidJUnit4.class)
public class SimpleEspressoTests {

    @Rule
    public ActivityTestRule<MainActivity> testRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void testIfTextViewChangesAfterButtonClick() throws InterruptedException {
        // given
        testRule.launchActivity(new Intent());
        ViewInteraction viewInteractionButton = onView(ViewMatchers.withId(R.id.button));
        // when
        viewInteractionButton.perform(ViewActions.click());
        // Thread.sleep(5000);
        // then
        onView(ViewMatchers.withId(R.id.textView)).check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.withText("Hallo"))));
      //  Thread.sleep(5000);
    }
}
