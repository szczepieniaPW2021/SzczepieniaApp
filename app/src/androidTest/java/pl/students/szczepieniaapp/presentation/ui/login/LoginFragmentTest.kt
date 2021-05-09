package pl.students.szczepieniaapp.presentation.ui.login

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.launchFragmentInHiltContainer
import androidx.test.espresso.assertion.ViewAssertions.matches

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

        //checks if title is displayed
        onView(withText(R.string.login_fragment)).check(
            matches(isDisplayed())
        )

        //checks if button navigating to patient fragment is displayed
        onView(withText(R.string.go_to_patient)).check(
            matches(isDisplayed())
        )

        //checks if button navigating to driver fragment is displayed
        onView(withText(R.string.go_to_driver)).check(
            matches(isDisplayed())
        )

        //checks if textView with app version is displayed
        onView(withId(R.id.versionTextView)).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun clickPatientNavBtn_navigationToPatientFragment() {

        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<LoginFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.patientNavBtn)).perform(click())

        pressBackUnconditionally()

        verify(navController).navigate(
            R.id.action_loginFragment_to_patientFragment
        )
    }

    @Test
    fun clickDriverNavBtn_navigationToDriverFragment(){

        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<LoginFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.driverNavBtn)).perform(click())

        pressBackUnconditionally()

        verify(navController).navigate(
            R.id.action_loginFragment_to_driverFragment
        )
    }

}