package com.example.notas_app_sqlite

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.notas_app_sqlite.databinding.ActivityActualizarNotasBinding

class ActualizarNotasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityActualizarNotasBinding
    private lateinit var db: NotasDatabaseHelper
    private var notaId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActualizarNotasBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        db = NotasDatabaseHelper(this)
        notaId = intent.getIntExtra("id_nota", -1)

        if (notaId == -1) {
            finish()
            return
        }

        val nota = db.getNotaByID(notaId)
        binding.etActualizarTitulo.setText(nota.titulo)
        binding.etActualizarDescripcion.setText(nota.descripcion)

        binding.ivActualizarNota.setOnClickListener {
            val nuevoTitulo = binding.etActualizarTitulo.text.toString()
            val nuevaDescripcion = binding.etActualizarDescripcion.text.toString()

            if (nuevoTitulo.isNotEmpty() && nuevaDescripcion.isNotEmpty()) {
                val notaActualizada = Nota(notaId, nuevoTitulo, nuevaDescripcion)
                db.updateNota(notaActualizada)
                finish()
                Toast.makeText(this, "Nota actualizada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Llene los campos", Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}