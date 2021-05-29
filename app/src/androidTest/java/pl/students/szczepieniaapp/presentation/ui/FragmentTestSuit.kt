package pl.students.szczepieniaapp.presentation.ui

import org.junit.runner.RunWith
import org.junit.runners.Suite
import pl.students.szczepieniaapp.presentation.ui.fragment.DriverFragmentTest
import pl.students.szczepieniaapp.presentation.ui.fragment.LoginFragmentTest
import pl.students.szczepieniaapp.presentation.ui.fragment.PatientCalendarFragmentTest
import pl.students.szczepieniaapp.presentation.ui.fragment.PatientFragmentTest

@RunWith(Suite::class)
@Suite.SuiteClasses(
    LoginFragmentTest::class,
    PatientFragmentTest::class,
    DriverFragmentTest::class,
    PatientCalendarFragmentTest::class
)
class FragmentTestSuit
