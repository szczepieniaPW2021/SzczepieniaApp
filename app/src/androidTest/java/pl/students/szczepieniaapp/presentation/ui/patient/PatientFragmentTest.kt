package pl.students.szczepieniaapp.presentation.ui.patient

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
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
import pl.students.szczepieniaapp.presentation.ui.fragment.PatientFragment
import pl.students.szczepieniaapp.presentation.util.ImageViewHasDrawableMatcher.hasDrawable

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
        onView(withText(R.string.patient_fragment)).check(
            matches(isDisplayed())
        )

        //checks if button with qrcode is displayed
        onView(withId(R.id.qrCodeButton)).check(
            matches(isDisplayed())
        )

    }

    @Test
    fun test_showDialog_QRCodeDisplay(){

        launchFragmentInHiltContainer<PatientFragment> {}

        onView(withId(R.id.qrCodeButton)).perform(click())

        //checks if dialog is displayed
        onView(withText(R.string.this_code_confirms_vaccination)).check(matches(
            isDisplayed()))

        //checks if dialog have qrCode
        onView(withId(R.id.qrPlaceHolder)).check(matches(
            hasDrawable()))

    }
}