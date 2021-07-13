package pl.students.szczepieniaapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.databinding.OrderItemBinding
import pl.students.szczepieniaapp.domain.model.Order

class OrderAdapter(
    private val orders: List<Order>
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        OrderViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.order_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.recyclerItem.apply {
            order = orders[position]
        }
    }

    override fun getItemCount() = orders.size

    inner class OrderViewHolder(
        val recyclerItem: OrderItemBinding) : RecyclerView.ViewHolder(recyclerItem.root)
}