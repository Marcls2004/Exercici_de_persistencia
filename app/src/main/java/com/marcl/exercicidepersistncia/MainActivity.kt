package com.marcl.exercicidepersistncia

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var etNom: EditText
    private lateinit var etCognoms: EditText
    private lateinit var etTelefon: EditText
    private lateinit var etEmail: EditText
    private lateinit var btnGuardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Enlazar los componentes del XML
        etNom = findViewById(R.id.etNom)
        etCognoms = findViewById(R.id.etCognoms)
        etTelefon = findViewById(R.id.etTelefon)
        etEmail = findViewById(R.id.etEmail)
        btnGuardar = findViewById(R.id.btnGuardar)

        // Acción al pulsar el botón GUARDAR
        btnGuardar.setOnClickListener {
            guardarContacte()
        }
    }

    private fun guardarContacte() {
        val nom = etNom.text.toString().trim()
        val cognoms = etCognoms.text.toString().trim()
        val telefon = etTelefon.text.toString().trim()
        val email = etEmail.text.toString().trim()

        // Línea separada por punto y coma y salto de línea
        val linia = "$nom;$cognoms;$telefon;$email\n"

        try {
            // openFileOutput abre o crea "contactes.txt" en memoria interna en modo APPEND
            openFileOutput("contactes.txt", Context.MODE_APPEND).use { fos ->
                fos.write(linia.toByteArray())
            }

            // Limpiar los campos y avisar por pantalla
            etNom.text.clear()
            etCognoms.text.clear()
            etTelefon.text.clear()
            etEmail.text.clear()
            Toast.makeText(this, "Contacte guardat correctament", Toast.LENGTH_SHORT).show()

        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Error en guardar: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}
