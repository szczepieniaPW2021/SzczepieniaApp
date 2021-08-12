package pl.students.szczepieniaapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.databinding.OrdersItemBinding
import pl.students.szczepieniaapp.domain.model.ReceivedOrder

class ReceivedOrderAdapter(
    private val orders: List<ReceivedOrder>
) : RecyclerView.Adapter<ReceivedOrderAdapter.ReceivedOrderViewHolder>() {

    inner class ReceivedOrderViewHolder(
        val recyclerItem: OrdersItemBinding) : RecyclerView.ViewHolder(recyclerItem.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ReceivedOrderViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.orders_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ReceivedOrderViewHolder, position: Int) {
        holder.recyclerItem.apply {
            order = orders[position]
        }
    }

    override fun getItemCount() = orders.size
}