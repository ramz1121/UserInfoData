package om.example.userInfodata.ui.main

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.corona.java.userinfo.model.UserInfo
import com.example.userInfodata.R
import com.example.userInfodata.TestComponentRule
import com.example.userInfodata.ui.main.MainActivity
import com.example.userInfodata.ui.main.adapter.UserInfoItemViewHolder
import com.example.userInfodata.utils.espresso.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.mockito.AdditionalMatchers.not


class MainActivityTest {

    private val component =
        TestComponentRule(InstrumentationRegistry.getInstrumentation().targetContext)

    private val main = IntentsTestRule(MainActivity::class.java, false, false)

    @get:Rule
    val chain = RuleChain.outerRule(component).around(main)
    val user = arrayOf(
        UserInfo(1, "LeanneGraham", "Sincere@april.biz", "1-770-736-8031 x564"),
        UserInfo(2, "Ervin Howell", "Shanna@melissa.tv", "45345645787")
    )
    val LIST_ITEM_IN_TEST = 4
    val ITEM_IN_TEST: UserInfo = user[0]


    @Before
    fun setupIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun testCheckViewsDisplay() {
        main.launchActivity(Intent(component.getContext(), MainActivity::class.java))
        onView(withId(R.id.toolbar_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.mainRecycleview)).check(matches(isDisplayed()))


    }

    @Test
    fun test_SelectListItem_isDetailFragmentVisible() {
        main.launchActivity(Intent(component.getContext(), MainActivity::class.java))
        onView(withId(R.id.mainRecycleview)).perform(
            actionOnItemAtPosition<UserInfoItemViewHolder>(
                LIST_ITEM_IN_TEST,
                click()
            )
        )
    }
}