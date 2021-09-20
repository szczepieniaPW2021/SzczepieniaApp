package pl.students.szczepieniaapp.presentation.ui.fragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.launchFragmentInHiltContainer

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class FacilityManagerFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun displayDataInDoctorFragment() {

        launchFragmentInHiltContainer<FacilityManagerFragment> {}

        //checks if title is displayed
        onView(withText(R.string.facility_manager_fragment_title_text)).check(
            matches(isDisplayed())
        )

        //checks if title is displayed
        onView(withText(R.string.facility_manager_fragment_list_header_text)).check(
            matches(isDisplayed())
        )

        //checks if vaccinesImageView is displayed
        onView(withId(R.id.vaccinesImageView)).check(
            matches(isDisplayed())
        )

        //checks if vaccinesTextView is displayed
        onView(withText(R.string.facility_manager_fragment_vaccines_text)).check(
            matches(isDisplayed())
        )

        //checks if vaccinesOrderTextView is displayed
        onView(withId(R.id.vaccinesOrderTextView)).check(
            matches(isDisplayed())
        )

        //checks if vaccinesOrderImageView is displayed
        onView(withId(R.id.vaccinesOrderImageView)).check(
            matches(isDisplayed())
        )

        //checks if amountVaccinesTextView is displayed
        onView(withId(R.id.amountVaccinesTextView)).check(
            matches(isDisplayed())
        )

        //checks if amountVaccinesImageView is displayed
        onView(withId(R.id.amountVaccinesImageView)).check(
            matches(isDisplayed())
        )
    }
}