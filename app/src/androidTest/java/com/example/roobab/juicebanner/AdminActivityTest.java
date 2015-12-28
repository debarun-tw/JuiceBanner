package com.example.roobab.juicebanner;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AdminActivityTest {

    @Rule
    public ActivityTestRule<ListViewActivity> mActivity = new ActivityTestRule(ListViewActivity.class);

    @Test
    public void employeeNameShouldBeDisplayed() throws InterruptedException {
        Thread.sleep(2000);
        onData(anything()).inAdapterView(withId(R.id.list_of_orders)).atPosition(0).onChildView(withId(R.id.employee_name)).check(matches(isDisplayed()));
    }

    @Test
    public void juiceNameShouldBeDisplayed() throws InterruptedException {
        Thread.sleep(2000);
        onData(anything()).inAdapterView(withId(R.id.list_of_orders)).atPosition(0).onChildView(withId(R.id.english_name)).check(matches(isDisplayed()));
    }

    @Test
    public void juiceImageShouldBeDisplayed() throws InterruptedException {
        Thread.sleep(2000);
        onData(anything()).inAdapterView(withId(R.id.list_of_orders)).atPosition(0).onChildView(withId(R.id.juice_image)).check(matches(isDisplayed()));
    }
}