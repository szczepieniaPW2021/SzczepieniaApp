package pl.students.szczepieniaapp.presentation.adapter

import android.view.View
import pl.students.szczepieniaapp.domain.model.Order

interface OrderAdapterListener {

    fun removeItem(view: View, order: Order)
}