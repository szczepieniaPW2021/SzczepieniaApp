package pl.students.szczepieniaapp.presentation.ui.fragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.launchFragmentInHiltContainer


import androidx.test.espresso.matcher.ViewMatchers.*


@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class DoctorFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun displayDataInDoctorFragment() {

        launchFragmentInHiltContainer<DoctorFragment> {}

        //checks if title is displayed
        onView(withText(R.string.doctor_fragment_title_text)).check(
            matches(isDisplayed())
        )

        //checks if detailsTextView is displayed
        onView(withId(R.id.detailsTextView)).check(
            matches(isDisplayed())
        )

        //checks if patientsTextView is displayed
        onView(withId(R.id.patientsTextView)).check(
            matches(isDisplayed())
        )

        //checks if vaccinesTextView is displayed
        onView(withId(R.id.vaccinesTextView)).check(
            matches(isDisplayed())
        )

        //checks if registrationTextView is displayed
        onView(withId(R.id.registrationTextView)).check(
            matches(isDisplayed())
        )

        //checks if patientsImageView is displayed
        onView(withId(R.id.patientsImageView)).check(
            matches(isDisplayed())
        )

        //checks if vaccinesImageView is displayed
        onView(withId(R.id.vaccinesImageView)).check(
            matches(isDisplayed())
        )

        //checks if registrationImageView is displayed
        onView(withId(R.id.registrationImageView)).check(
            matches(isDisplayed())
        )
    }
}