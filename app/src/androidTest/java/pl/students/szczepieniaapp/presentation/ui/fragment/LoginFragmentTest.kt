package pl.students.szczepieniaapp.presentation.ui.fragment

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.data.FakeRolesData
import pl.students.szczepieniaapp.data.ToastFakeData
import pl.students.szczepieniaapp.launchFragmentInHiltContainer
import pl.students.szczepieniaapp.presentation.MyActivity.Companion.buildToastMessage
import pl.students.szczepieniaapp.presentation.util.ToastMatcher


@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class LoginFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun displayDataInLoginFragment() {

        launchFragmentInHiltContainer<LoginFragment> {}

        //checks if welcome textView is displayed
        onView(withText(R.string.welcome_text)).check(
            matches(isDisplayed())
        )

        //checks if title is displayed
        onView(withText(R.string.login_fragment)).check(
            matches(isDisplayed())
        )

        //checks if button navigating to patient fragment is displayed
        onView(withId(R.id.spinner)).check(
            matches(isDisplayed())
        )

        //checks if button navigating to other fragments is displayed
        onView(withText(R.string.login_text)).check(
            matches(isDisplayed())
        )

        //checks if textView with app version is displayed
        onView(withId(R.id.versionTextView)).check(
            matches(isDisplayed())
        )

        onView(withSpinnerText(FakeRolesData.data[0])).check(
            matches(isDisplayed())
        )

        //checks if button is disabled
        onView(withText(R.string.login_text)).check(
            matches(not(isEnabled()))
        )
    }

    @Test
    fun clickPatientNavBtn_navigationToPatientFragment() {

        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<LoginFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        //click spinner to select role
        onView(withId(R.id.spinner)).perform(
            click()
        )

        //click on role "Patient"
        onData(`is`(FakeRolesData.data[1])).perform(click())

        //checks if button is disabled
        onView(withText(R.string.login_text)).check(
            matches(isEnabled())
        )

        //click navbutton
        onView(withId(R.id.navBtn)).perform(click())

        pressBackUnconditionally()

        //checks if navigated to patient fragment
        verify(navController).navigate(
            R.id.action_loginFragment_to_patientActivity
        )
    }

    @Test
    fun clickDriverNavBtn_navigationToDriverActivity(){

        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<LoginFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        //click spinner to select role
        onView(withId(R.id.spinner)).perform(
            click()
        )

        //click on role "Driver"
        onData(`is`(FakeRolesData.data[2])).perform(click())

        //checks if button is enabled
        onView(withText(R.string.login_text)).check(
            matches(isEnabled())
        )

        //click navbutton
        onView(withId(R.id.navBtn)).perform(click())

        pressBackUnconditionally()

        //checks if navigated to patient fragment
        verify(navController).navigate(
            R.id.action_loginFragment_to_driverActivity
        )
    }

    @Test
    fun clickLogInInSpinner_DisableButton() {

        launchFragmentInHiltContainer<LoginFragment> {}

        //click spinner to select role
        onView(withId(R.id.spinner)).perform(
            click()
        )

        //click on role "Log in as"
        onData(`is`(FakeRolesData.data[0])).perform(click())

        //checks if button is disabled
        onView(withText(R.string.login_text)).check(
            matches(not(isEnabled()))
        )

    }

    @Test
    fun clickNFZOperatorInSpinner_DisableButtonAndShowToast() {

        launchFragmentInHiltContainer<LoginFragment> {}

        //click spinner to select role
        onView(withId(R.id.spinner)).perform(
            click()
        )

        //click on role "NFZ operator"
        onData(`is`(FakeRolesData.data[6])).perform(click())

        //checks if button is disabled
        onView(withText(R.string.login_text)).check(
            matches(not(isEnabled()))
        )

        //checks if toast is displayed and the message is correct
        onView(withText(buildToastMessage(ToastFakeData.data))).inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }

    @Test
    fun clickDoctorNavBtn_navigationToDoctorFragment() {

        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<LoginFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        //click spinner to select role
        onView(withId(R.id.spinner)).perform(
            click()
        )

        //click on role "Doctor"
        onData(`is`(FakeRolesData.data[3])).perform(click())

        //checks if button is enabled
        onView(withText(R.string.login_text)).check(
            matches(isEnabled())
        )

        //click navbutton
        onView(withId(R.id.navBtn)).perform(click())

        pressBackUnconditionally()

        //checks if navigated to doctor activity
        verify(navController).navigate(
            R.id.action_loginFragment_to_doctorActivity
        )
    }

    @Test
    fun clickLogInAfterClickingPatientInSpinnerInSpinner_DisableButton() {

        launchFragmentInHiltContainer<LoginFragment> {}

        //click spinner to select role
        onView(withId(R.id.spinner)).perform(
            click()
        )

        //click on role "Patient"
        onData(`is`(FakeRolesData.data[1])).perform(click())

        //checks if button is disabled
        onView(withText(R.string.login_text)).check(
            matches(isEnabled())
        )

        //click spinner to select role
        onView(withId(R.id.spinner)).perform(
            click()
        )

        //click on role "Log in as:"
        onData(`is`(FakeRolesData.data[0])).perform(click())

        //checks if button is disabled
        onView(withText(R.string.login_text)).check(
            matches(not(isEnabled()))
        )

    }
}
