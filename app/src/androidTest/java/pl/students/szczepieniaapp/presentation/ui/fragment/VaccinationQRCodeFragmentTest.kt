package pl.students.szczepieniaapp.presentation.ui.fragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
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
import pl.students.szczepieniaapp.presentation.util.ImageViewHasDrawableMatcher

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class VaccinationQRCodeFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun displayDataInVaccinationQRCodeFragment() {

        launchFragmentInHiltContainer<VaccinationQRCodeFragment> {}

        //checks if title is displayed
        onView(withText(R.string.vaccination_qr_code_fragment_title_text)).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )

        //checks if button with qrcode is displayed
        onView(withId(R.id.qrCodeButton)).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )

    }

    @Test
    fun test_showDialog_QRCodeDisplay(){

        launchFragmentInHiltContainer<VaccinationQRCodeFragment> {}

        onView(withId(R.id.qrCodeButton)).perform(ViewActions.click())

        //checks if dialog is displayed
        onView(withText(R.string.this_code_confirms_vaccination)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )

        //checks if dialog have qrCode
        onView(withId(R.id.qrPlaceHolder)).check(
            matches(
                ImageViewHasDrawableMatcher.hasDrawable()
            )
        )

    }
}