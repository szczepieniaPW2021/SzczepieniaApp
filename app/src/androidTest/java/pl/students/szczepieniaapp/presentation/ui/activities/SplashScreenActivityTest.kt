package pl.students.szczepieniaapp.presentation.ui.activities

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.*
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.students.szczepieniaapp.R

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class SplashScreenActivityTest {

    private val SPLASH_SCREEN = 5000L

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun launchSplashScreen_navMainActivity() {

        val activityScenario = ActivityScenario.launch(SplashScreenActivity::class.java)

        //checks if app name is displayed
        onView(
            allOf(
                withId(R.id.appNameTextView),
                withParent(withId(R.id.splashScreenActivity))
            )
        ).check(matches(isDisplayed()))

        //checks if icon name is displayed
        onView(
            allOf(
                withId(R.id.imageView),
                withParent(withId(R.id.splashScreenActivity))
            )
        ).check(matches(isDisplayed()))

        //waits 5 sec
        Thread.sleep(SPLASH_SCREEN)

        //checks if main activity is displayed
        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()))
        
    }

}