package kz.yerbol.myapplication12_5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout

class CustomAdapter(private val items: List<ListItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ListItem.CurrencyItem) {
            val tilName: TextInputLayout = itemView.findViewById(R.id.til)
            val etSum: EditText = itemView.findViewById(R.id.et_sum)
            val tvName: TextView = itemView.findViewById(R.id.tv_name)

            tilName.hint = item.name
            etSum.setText(item.sum.toString())
            tvName.text = item.name
        }
    }

    inner class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ListItem.ButtonItem) {
            val btnAdd: Button = itemView.findViewById(R.id.btn_add)

            btnAdd.text = item.name
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ListItem.CurrencyItem -> 0
            else -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> CurrencyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.rv_item_currency, parent, false)
            )

            1 -> ButtonViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.rv_item_button, parent, false)
            )

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is ListItem.CurrencyItem -> (holder as CurrencyViewHolder).bind(item)
            is ListItem.ButtonItem -> (holder as ButtonViewHolder).bind(item)
        }
    }

    override fun getItemCount() = items.size
}