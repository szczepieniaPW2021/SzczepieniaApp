package pl.students.szczepieniaapp.presentation.ui

import org.junit.runner.RunWith
import org.junit.runners.Suite
import pl.students.szczepieniaapp.presentation.ui.driver.DriverFragmentTest
import pl.students.szczepieniaapp.presentation.ui.login.LoginFragmentTest
import pl.students.szczepieniaapp.presentation.ui.patient.PatientFragmentTest

@RunWith(Suite::class)
@Suite.SuiteClasses(
    LoginFragmentTest::class,
    PatientFragmentTest::class,
    DriverFragmentTest::class
)
class FragmentTestSuit
