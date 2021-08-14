package pl.students.szczepieniaapp.presentation.ui.viewmodel

import android.view.View
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

    var name: String = ""
    var lastName: String = ""

    fun addDriver(view: View){

        if (name.isNullOrEmpty() || name.isNullOrBlank() || lastName.isNullOrBlank() || lastName.isNullOrEmpty()){
            callback.toastMessage(view, view.context.getString(R.string.driver_manager_fragment_empty_fields_text))
            return
        }

        callback.setDialog(view, view.context.getString(R.string.vaccine_order_fragment_order_is_being_registered_text))

        disposable.add(
            useCaseFactory.getAddDriverUseCase
                .execute(name, lastName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate {
                    callback.dismissDialog()
                }
                .subscribeWith(object : DisposableCompletableObserver(){
                    override fun onComplete() {
                        name = ""
                        lastName = ""
                        //TODO remove names from the view
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