package pl.students.szczepieniaapp.presentation.ui.fragment

import androidx.test.espresso.Espresso.onView
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
import androidx.test.espresso.assertion.ViewAssertions.matches

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class PatientFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun displayDataInPatientFragment() {

        launchFragmentInHiltContainer<PatientFragment> {}

        //checks if title is displayed
        onView(withText(R.string.patient_fragment_title_text)).check(
            matches(isDisplayed())
        )

        //checks if detailsTextView is displayed
        onView(withId(R.id.detailsTextView)).check(
            matches(isDisplayed())
        )

        //checks if referralsTextView is displayed
        onView(withId(R.id.referralsTextView)).check(
            matches(isDisplayed())
        )

        //checks if visitsTextView is displayed
        onView(withId(R.id.visitsTextView)).check(
            matches(isDisplayed())
        )

        //checks if selectedTimeTextView is displayed
        onView(withId(R.id.qrCodeTextView)).check(
            matches(isDisplayed())
        )

        //checks if referralsImageView is displayed
        onView(withId(R.id.referralsImageView)).check(
            matches(isDisplayed())
        )

        //checks if visitsImageView is displayed
        onView(withId(R.id.visitsImageView)).check(
            matches(isDisplayed())
        )

        //checks if selectedTimeTextView is displayed
        onView(withId(R.id.qrCodeImageView)).check(
            matches(isDisplayed())
        )

    }

}