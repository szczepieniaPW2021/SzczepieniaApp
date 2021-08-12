package pl.students.szczepieniaapp.presentation.adapter

import android.view.View
import pl.students.szczepieniaapp.domain.model.ReceivedOrder

interface ReceivedOrderAdapterListener {

    fun clickItem(view: View, order: ReceivedOrder)
}