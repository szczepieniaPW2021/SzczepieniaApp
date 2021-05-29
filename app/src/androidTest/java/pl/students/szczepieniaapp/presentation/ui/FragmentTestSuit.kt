package pl.students.szczepieniaapp.presentation.ui

import org.junit.runner.RunWith
import org.junit.runners.Suite
import pl.students.szczepieniaapp.presentation.ui.fragment.*

@RunWith(Suite::class)
@Suite.SuiteClasses(
    LoginFragmentTest::class,
    PatientFragmentTest::class,
    DriverFragmentTest::class,
    PatientCalendarFragmentTest::class,
    VaccinationQRCodeFragmentTest::class
)
class FragmentTestSuit
