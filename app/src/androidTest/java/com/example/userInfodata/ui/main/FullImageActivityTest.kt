package com.example.userInfodata.ui.main

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import com.example.userInfodata.R
import com.example.userInfodata.TestComponentRule
import com.example.userInfodata.ui.fullimage.FullImageActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

class FullImageActivityTest {
    private val component = TestComponentRule(InstrumentationRegistry.getInstrumentation().targetContext)

    private val main = IntentsTestRule(MainActivity::class.java, false, false)

    @get:Rule
    val chain = RuleChain.outerRule(component).around(main)

    @Before
    fun setup() {

    }

    @Test
    fun testCheckViewsDisplay() {
        main.launchActivity(Intent(component.getContext(), FullImageActivity::class.java))
        Espresso.onView(ViewMatchers.withId(R.id.toolbarAlbumId))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.toolbarPhotoId))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.itemFullIV))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.itemFullTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }
}