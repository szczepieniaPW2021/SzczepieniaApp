package pl.students.szczepieniaapp.presentation.ui.fragment

import android.widget.DatePicker
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Matchers.equalTo
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.Is
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.data.FakePatientCalendarFragmentData
import pl.students.szczepieniaapp.launchFragmentInHiltContainer


@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class PatientCalendarFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun displayDataInPatientCalendarFragment() {

        launchFragmentInHiltContainer<PatientCalendarFragment> {}

        //checks if sign up for a vaccination textView is displayed
        onView(withText(R.string.patient_calendar_fragment_sign_up_vaccination_text)).check(
            matches(isDisplayed())
        )

        //checks if selectCityTextView is displayed
        onView(withText(R.string.patient_calendar_fragment_select_city_text)).check(
            matches(isDisplayed())
        )

        //checks if spinner to select city is displayed
        onView(withId(R.id.selectCitySpinner)).check(
            matches(isDisplayed())
        )

        //checks if selectFacilityTextView is not displayed
        onView(withId(R.id.selectFacilityTextView)).check(
            matches(not(isDisplayed()))
        )

        //checks if selectFacilitySpinner is not displayed
        onView(withId(R.id.selectFacilitySpinner)).check(
            matches(not(isDisplayed()))
        )

        //checks if visit details data is not displayed
        onView(withId(R.id.visitDataLinearLayout)).check(
            matches(not(isDisplayed()))
        )

        //checks if signUpBtn button is not displayed
        onView(withId(R.id.signUpBtn)).check(
            matches(not(isDisplayed()))
        )
    }

    @Test
    fun registerVisit() {

        launchFragmentInHiltContainer<PatientCalendarFragment> {}

        //clicks selectCitySpinner and selects city "Warszawa"
        val selectCitySpinner = onView(
            allOf(
                withId(R.id.selectCitySpinner),
                withParent(withId(R.id.selectCitySpinnerLayout))
            )
        )
        selectCitySpinner.perform(click())
        onData(Is.`is`(FakePatientCalendarFragmentData.cities[1])).perform(click())

        //checks if selectFacilityTextView is displayed
        onView(withId(R.id.selectFacilityTextView)).check(
            matches(isDisplayed())
        )

        //checks if selectFacilitySpinner is displayed
        onView(withId(R.id.selectFacilitySpinner)).check(
            matches(isDisplayed())
        )

        //clicks selectFacilitySpinner and selects facility "Punkt 1"
        val selectFacilitySpinner = onView(
            allOf(
                withId(R.id.selectFacilitySpinner),
                withParent(withId(R.id.selectFacilityRelativeLayout))
            )
        )
        selectFacilitySpinner.perform(click())
        onData(Is.`is`(FakePatientCalendarFragmentData.facilities[1])).perform(click())

        //checks if calendarView is displayed
        val calendarView = onView(
            allOf(
                withId(R.id.calendarView),
                withParent(withId(R.id.calendarViewLinearLayout))
            )
        )
        calendarView.check(
            matches(isDisplayed())
        )

        calendarView.perform(click())

    }

}