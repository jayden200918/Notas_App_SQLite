package com.example.notas_app_sqlite

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.notas_app_sqlite.databinding.ActivityAgregarNotaBinding

class AgregarNotaACTIVITY : AppCompatActivity() {
    private lateinit var binding: ActivityAgregarNotaBinding
    private lateinit var db : NotasDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarNotaBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        db = NotasDatabaseHelper(this )
        binding.ivGuardarNota.setOnClickListener {
            val titulo = binding.etTitulo.text.toString()
            val descripcion = binding.etDescripcion.text.toString()
            val nota = Nota(0, titulo, descripcion)
            db.insertNota(nota)
            startActivity(Intent(applicationContext,
                MainActivity:: class.java ))
            finish()
            Toast.makeText(applicationContext, "se ha agregado la nota", 
                Toast.LENGTH_SHORT).show()
        }
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
    }
}