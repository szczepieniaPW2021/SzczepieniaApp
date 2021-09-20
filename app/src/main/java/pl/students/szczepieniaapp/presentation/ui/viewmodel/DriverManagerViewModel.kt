package pl.students.szczepieniaapp.presentation.ui.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.presentation.MyViewModel
import pl.students.szczepieniaapp.presentation.ui.fragment.DriverManagerFragment
import pl.students.szczepieniaapp.presentation.ui.listener.DriverManagerListener
import pl.students.szczepieniaapp.usecase.UseCaseFactory
import javax.inject.Inject

@HiltViewModel
class DriverManagerViewModel
@Inject
constructor(
    private val useCaseFactory: UseCaseFactory
): MyViewModel() {

    private var callback: DriverManagerListener = DriverManagerFragment()

    private val disposable = CompositeDisposable()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    var name: String = ""
    var lastName: String = ""

    fun addDriver(view: View){

        if (name.isNullOrEmpty() || name.isNullOrBlank() || lastName.isNullOrBlank() || lastName.isNullOrEmpty()){
            callback.toastMessage(view, view.context.getString(R.string.driver_manager_fragment_empty_fields_text))
            return
        }

        _loading.postValue(true)
        callback.setDialog(view, view.context.getString(R.string.driver_manager_fragment_adding_driver_text))

        disposable.add(
            useCaseFactory.getAddDriverUseCase
                .execute(name, lastName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate {
                    callback.dismissDialog()
                    _loading.postValue(false)
                }
                .subscribeWith(object : DisposableCompletableObserver(){
                    override fun onComplete() {
                        callback.toastMessage(
                            view,
                            view.context.getString(R.string.driver_manager_fragment_added_driver_text)
                        )
                    }

                    override fun onError(e: Throwable) {}

                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}