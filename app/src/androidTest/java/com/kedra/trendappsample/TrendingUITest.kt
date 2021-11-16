package com.kedra.trendappsample

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TrendingUITest {

    @Rule
    @JvmField
    val activityTestRule : ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun expandItem(){

        Espresso.onView(ViewMatchers.withId(R.id.lvItems)).perform(click())
    }


    @Test
    fun expandItemThenCollapse(){

        Espresso.onView(ViewMatchers.withId(R.id.lvItems)).perform(click()).perform(click())
    }

    @Test
    fun sortListByNames(){

        Espresso.onView(ViewMatchers.withId(R.id.sortByName)).perform(click())
    }
    @Test
    fun retryButton(){

        Espresso.onView(ViewMatchers.withId(R.id.btnRetry)).perform(click())
    }
}