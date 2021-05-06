package pl.students.szczepieniaapp.presentation.ui.driver

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.launchFragmentInHiltContainer
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.not


@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class DriverFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun displayDataInPatientFragment() {

        launchFragmentInHiltContainer<DriverFragment> {}

        //checks if title is displayed
        Espresso.onView(withText(R.string.driver_fragment)).check(
            matches(isDisplayed())
        )

        //checks if map is displayed
        Espresso.onView(withId(R.id.mapView)).check(
            matches(isDisplayed())
        )

        //checks if route data is not displayed
        Espresso.onView(withId(R.id.navContainer)).check(
            matches(not(isDisplayed()))
        )

        //checks if floating button to google maps is not displayed
        Espresso.onView(withId(R.id.navBtn)).check(
            matches(not(isDisplayed()))
        )

    }
}