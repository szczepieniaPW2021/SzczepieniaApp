package pl.students.szczepieniaapp.presentation.ui.fragment

import android.view.KeyEvent
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.Is
import org.hamcrest.core.IsNot.not
import org.junit.*
import org.junit.runners.MethodSorters
import org.mockito.Mockito
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.launchFragmentInHiltContainer
import pl.students.szczepieniaapp.presentation.util.ToastMatcher
import pl.students.szczepieniaapp.presentation.MyActivity.Companion.buildToastMessage


@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class SearchPatientFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun displayDataInSearchPatientFragment() {

        launchFragmentInHiltContainer<SearchPatientFragment> {}

        //checks if title is displayed
        onView(withText(R.string.search_patient_fragment_search_title_text)).check(
            matches(isDisplayed())
        )

        //checks if searchView is displayed
        onView(withId(R.id.searchView)).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun searchForPatientUsingPesel_PersonNotFound_IncorrectPesel() {

        launchFragmentInHiltContainer<SearchPatientFragment> {}

        //checks if everything is properly displayed using displayDataInSearchPatientFragment() test
        //displayDataInSearchPatientFragment()

        //click on the search icon
        onView(withId(R.id.searchView)).perform(click())

        //type pesel id "12" and press search icon on keyboard, pesel is too short
        onView(withId(R.id.searchView)).perform(typeText("12")).perform(pressKey(KeyEvent.KEYCODE_ENTER))

        //checks if toast is displayed and the message is correct
        onView(withText(buildToastMessage("PESEL must contains 11 digits"))).inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }

    
    @Test
    fun searchForPatientUsingPesel_PersonNotFound() {

        launchFragmentInHiltContainer<SearchPatientFragment> {}

        //checks if everything is properly displayed using displayDataInSearchPatientFragment() test
        displayDataInSearchPatientFragment()

        //click on the search icon
        onView(withId(R.id.searchView)).perform(click())

        //type pesel id "12345678933" and press search icon on keyboard,
        onView(withId(R.id.searchView)).perform(typeText("12345678933")).perform(pressKey(KeyEvent.KEYCODE_ENTER))

        //no patient warning is displayed
        onView(withId(R.id.noPatientLinearLayout)).check(
            matches(isDisplayed())
        )

        //element containing patient data is not displayed
        onView(withId(R.id.patientDataLinearLayout)).check(
            matches(not(isDisplayed()))
        )

        //element containing input for registering vaccination is displayed
        onView(withId(R.id.registerVaccinationLinearLayout)).check(
            matches(not(isDisplayed()))
        )

        //register vaccination button is displayed
        onView(withId(R.id.registerVaccinationBtn)).check(
            matches(not(isDisplayed()))
        )

    }

    @Test
    fun searchForPatientUsingPesel_PersonIsFound() {

        launchFragmentInHiltContainer<SearchPatientFragment> {}

        //checks if everything is properly displayed using displayDataInSearchPatientFragment() test
        displayDataInSearchPatientFragment()

        //click on the search icon
        onView(withId(R.id.searchView)).perform(click())

        //type pesel id "12345678900" and press search icon on keyboard
        onView(withId(R.id.searchView)).perform(typeText("12345678900")).perform(pressKey(KeyEvent.KEYCODE_ENTER))

        //element containing patient data is displayed
        onView(withId(R.id.patientDataLinearLayout)).check(
            matches(isDisplayed())
        )

        //no patient warning is not displayed
        onView(withId(R.id.noPatientLinearLayout)).check(
            matches(not(isDisplayed()))
        )

        //element containing input for registering vaccination is displayed
        onView(withId(R.id.registerVaccinationLinearLayout)).check(
            matches(isDisplayed())
        )

        //register vaccination button is displayed
        onView(withId(R.id.registerVaccinationBtn)).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun searchForPatientUsingName_PersonIsFound() {

        launchFragmentInHiltContainer<SearchPatientFragment> {}

        //checks if everything is properly displayed using displayDataInSearchPatientFragment() test
        displayDataInSearchPatientFragment()

        //click on the search icon
        onView(withId(R.id.searchView)).perform(click())

        //type name "Jan Nowak" and press search icon on keyboard
        onView(withId(R.id.searchView)).perform(typeText("Jan Nowak")).perform(pressKey(KeyEvent.KEYCODE_ENTER))

        //element containing patient data is displayed
        onView(withId(R.id.patientDataLinearLayout)).check(
            matches(isDisplayed())
        )

        //no patient warning is not displayed
        onView(withId(R.id.noPatientLinearLayout)).check(
            matches(not(isDisplayed()))
        )

        //element containing input for registering vaccination is displayed
        onView(withId(R.id.registerVaccinationLinearLayout)).check(
            matches(isDisplayed())
        )

        //register vaccination button is displayed
        onView(withId(R.id.registerVaccinationBtn)).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun searchForPatientUsingName_PersonIsNotFound() {

        launchFragmentInHiltContainer<SearchPatientFragment> {}

        //checks if everything is properly displayed using displayDataInSearchPatientFragment() test
        displayDataInSearchPatientFragment()

        //click on the search icon
        onView(withId(R.id.searchView)).perform(click())

        //type name "Jan Nowak" and press search icon on keyboard
        onView(withId(R.id.searchView)).perform(typeText("Jan")).perform(pressKey(KeyEvent.KEYCODE_ENTER))

        //no patient warning is displayed
        onView(withId(R.id.noPatientLinearLayout)).check(
            matches(isDisplayed())
        )

        //element containing patient data is not displayed
        onView(withId(R.id.patientDataLinearLayout)).check(
            matches(not(isDisplayed()))
        )

        //element containing input for registering vaccination is displayed
        onView(withId(R.id.registerVaccinationLinearLayout)).check(
            matches(not(isDisplayed()))
        )

        //register vaccination button is displayed
        onView(withId(R.id.registerVaccinationBtn)).check(
            matches(not(isDisplayed()))
        )
    }

    @Test
    fun vaccinationRegistration() {

        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<SearchPatientFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        //person is found and data are displayed
        searchForPatientUsingPesel_PersonIsFound()

        //clicks selectVaccineTypeSpinner and selects vaccineType "Moderna"
        val selectVaccineTypeSpinner = onView(
            allOf(
                withId(R.id.selectVaccineTypeSpinner),
                withParent(withId(R.id.selectVaccineRelativeLayout))
            )
        )

        selectVaccineTypeSpinner.perform(click())

        onData(Is.`is`("Moderna")).perform(click())

        //clicks selectVaccineDoseSpinner and selects vaccineType "Dose 1"
        val selectVaccineDoseSpinner = onView(
            allOf(
                withId(R.id.selectVaccineDoseSpinner),
                withParent(withId(R.id.selectVaccineDoseRelativeLayout))
            )
        )

        selectVaccineDoseSpinner.perform(click())

        onData(Is.`is`("Dose 1")).perform(click())

        //click on vaccineIdNumberEditText, type "1234ABC" and press ENTER button
        onView(withId(R.id.vaccineIdNumberEditText)).perform(click())

        onView(withId(R.id.vaccineIdNumberEditText)).perform(typeText("1234ABC")).perform(pressKey(KeyEvent.KEYCODE_ENTER))

        //click registerVaccinationBtn
        onView(withId(R.id.registerVaccinationBtn)).perform(click())

        //checks if dialog is displayed
        onView(withText(R.string.search_patient_fragment_vaccination_is_being_registered_text)).check(
            matches(isDisplayed())
        )

        //TODO check if dialog is dismissed, toast shown and navigation happened
    }

}