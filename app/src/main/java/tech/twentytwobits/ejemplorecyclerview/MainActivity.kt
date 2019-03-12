package tech.twentytwobits.ejemplorecyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Crear el ArrayList que contiene la información
        // Data Source
        val platillos = ArrayList<Platillo>()

        platillos.add(Platillo("Platillo 1", 250.00, 4.0, R.drawable.platillo01))
        platillos.add(Platillo("Platillo 2", 250.00, 4.0, R.drawable.platillo02))
        platillos.add(Platillo("Platillo 3", 250.00, 4.0, R.drawable.platillo03))
        platillos.add(Platillo("Platillo 4", 250.00, 4.0, R.drawable.platillo04))
        platillos.add(Platillo("Platillo 5", 250.00, 4.0, R.drawable.platillo05))
        platillos.add(Platillo("Platillo 6", 250.00, 4.0, R.drawable.platillo06))
        platillos.add(Platillo("Platillo 7", 250.00, 4.0, R.drawable.platillo07))
        platillos.add(Platillo("Platillo 8", 250.00, 4.0, R.drawable.platillo08))
        platillos.add(Platillo("Platillo 9", 250.00, 4.0, R.drawable.platillo09))
        platillos.add(Platillo("Platillo 10", 250.00, 4.0, R.drawable.platillo10))

        // Definir la variable que se encargar de manejar el widget del RecyclerView
        val laLista = findViewById<RecyclerView>(R.id.rvLista)
        laLista.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        laLista.layoutManager = layoutManager

        // Adaptador
        val adapter = CustomAdapter(this, platillos, object: ClickListener {
                override fun onClick(view: View, index: Int) {
                    Toast.makeText(applicationContext, platillos[index].nombre, Toast.LENGTH_SHORT).show()
                }
            }, object: LongClickListener {
                override fun longClickListener(view: View, index: Int) {
                    Log.d("LONG", "Long click listener")
                }
        })

        laLista.adapter = adapter

        // Swipe to refresh
        // Actualizaría los valores de la lista
        val swipeToRefresh = findViewById<SwipeRefreshLayout>(R.id.swipeToRefresh)
        swipeToRefresh.setOnRefreshListener {
            Log.d("REFRESH", "La información se ha refrescado")
            for (i in 1..10000000) {

            }
            swipeToRefresh.isRefreshing = false

            // Agregar un nuevo platillo (repetido)
            platillos.add(Platillo("Platillo 11", 300.00, 4.0, R.drawable.platillo01))
            adapter.notifyDataSetChanged()
        }
    }
}
