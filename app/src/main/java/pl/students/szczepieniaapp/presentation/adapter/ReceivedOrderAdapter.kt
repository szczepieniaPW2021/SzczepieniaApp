package pl.students.szczepieniaapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.database.converter.ReceiveOrderStatus
import pl.students.szczepieniaapp.databinding.OrdersItemBinding
import pl.students.szczepieniaapp.domain.model.ReceivedOrder

class ReceivedOrderAdapter(
    private val orders: List<ReceivedOrder>,
    private val listener: ReceivedOrderAdapterListener,
    private val context: Context
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

            when (order?.deliveryStatus?.name) {
                ReceiveOrderStatus.ORDERED.name -> {
                    holder.recyclerItem.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white))
                }
                ReceiveOrderStatus.ON_ROUTE.name -> {
                    holder.recyclerItem.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.light_gray))
                }
                else -> {
                    holder.recyclerItem.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.dark_gray))
                }
            }

        }
        holder.recyclerItem.root.setOnClickListener {
            listener.clickItem(holder.recyclerItem.root, orders[position])
        }
    }

    override fun getItemCount() = orders.size
}