package pl.students.szczepieniaapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import pl.students.szczepieniaapp.presentation.util.PatientsNameMapper

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @ViewModelScoped
    @Provides
    fun providePatientsNameMapper(): PatientsNameMapper {
        return PatientsNameMapper()
    }
}