package tech.twentytwobits.ejemplorecyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView

class CustomAdapter(var context: Context, var items: ArrayList<Platillo>, var listener: ClickListener, var longClickListener: LongClickListener): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CustomAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.template_platillo, parent, false)

        return ViewHolder(view, listener, longClickListener)
    }

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        val item = items.get(position)
        holder.foto.setImageResource(item.foto)
        holder.nombre.text = item.nombre
        holder.precio.text = item.precio.toString()
        holder.rating.rating = item.rating.toFloat()
    }

    class ViewHolder(var view: View, var listener: ClickListener, var longClickListener: LongClickListener): RecyclerView.ViewHolder(view), View.OnClickListener, View.OnLongClickListener {
        override fun onClick(v: View?) {
            listener.onClick(view, adapterPosition)
        }

        override fun onLongClick(v: View?): Boolean {
            longClickListener.longClickListener(view, adapterPosition)

            return true
        }

        val foto = view.findViewById<ImageView>(R.id.ivPlatillo)
        val nombre = view.findViewById<TextView>(R.id.tvNombre)
        val precio = view.findViewById<TextView>(R.id.tvPrecio)
        val rating = view.findViewById<RatingBar>(R.id.rbPuntuacion)

        init {
            view.setOnClickListener(this)
        }
    }
}