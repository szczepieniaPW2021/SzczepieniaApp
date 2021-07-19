package pl.students.szczepieniaapp.presentation.ui.fragment

import android.view.KeyEvent
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.Is
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.launchFragmentInHiltContainer

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class VaccineOrderFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun displayDataInVaccineOrderFragment() {

        launchFragmentInHiltContainer<VaccineOrderFragment> {}

        //checks if title is displayed
        onView(withText(R.string.vaccine_order_fragment_title_text)).check(
            matches(isDisplayed())
        )

        //checks if linear layout to add item is displayed
        onView(withId(R.id.addItemLinearLayout)).check(
            matches(isDisplayed())
        )

        //checks if linear layout to show item list is not displayed
        onView(withId(R.id.orderListLinearLayout)).check(
            matches(not(isDisplayed()))
        )

        //checks if linear layout to show additional data layout is displayed
        onView(withId(R.id.additionalDataLinearLayout)).check(
            matches(not(isDisplayed()))
        )

        //checks if makeOrderBtn is not displayed
        onView(withId(R.id.makeOrderBtn)).check(
            matches(not(isDisplayed()))
        )
    }

    @Test
    fun addItemAfterSelectingVaccineType_DisplayOrderList_AdditionalData_OrderBtn() {

        launchFragmentInHiltContainer<VaccineOrderFragment> {}

        displayDataInVaccineOrderFragment()

        val selectVaccineSpinner = onView(
            allOf(
                withId(R.id.selectVaccineSpinner),
                withParent(withId(R.id.selectVaccineTypeRelativeLayout))
            )
        )

        selectVaccineSpinner.perform(click())

        onData(Is.`is`("Moderna")).perform(click())

        val addItemToOrderList = onView(
            allOf(
                withId(R.id.addItemImageButton),
                withParent(withId(R.id.addItemButtonsLinearLayout))
            )
        )

        addItemToOrderList.perform(click())

        //checks if linear layout to show item list is  displayed
        onView(withId(R.id.orderListLinearLayout)).check(
            matches(isDisplayed())
        )

        //checks if linear layout to show additional data layout is displayed
        onView(withId(R.id.additionalDataLinearLayout)).check(
            matches(isDisplayed())
        )

        //checks if makeOrderBtn is displayed
        onView(withId(R.id.makeOrderBtn)).check(
            matches(isDisplayed())
        )

        //checks if makeOrderBtn is disabled
        onView(withText(R.string.vaccine_order_fragment_order_text)).check(
            matches(not(isEnabled()))
        )

    }

    @Test
    fun typeAdditionalData_EnableMakeOrderBtn(){

        launchFragmentInHiltContainer<VaccineOrderFragment> {}

        addItemAfterSelectingVaccineType_DisplayOrderList_AdditionalData_OrderBtn()

        //TODO finish tests
    }
}