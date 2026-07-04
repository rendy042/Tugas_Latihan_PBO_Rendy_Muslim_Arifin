// File: MainActivity.kt
// Halaman Login - Menerapkan ViewBinding, Event Handling, Validasi, dan Intent
// Praktik (2), (3), (5), (6), (8), (9)

package com.example.kasirtokobangunan

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kasirtokobangunan.databinding.ActivityLoginBinding

class MainActivity : AppCompatActivity() {

    // Praktik (2): Deklarasi objek binding (Enkapsulasi tingkat lanjut)
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Praktik (2): Inflate (menghidupkan) XML activity_login.xml menjadi objek Kotlin
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Praktik (3): Memasang 'Telinga' (Listener) pada tombol btnLogin
        binding.btnLogin.setOnClickListener {

            // Praktik (3): Membaca input dari kolom EditText
            val idKasirInput = binding.inputIdKasir.text.toString()
            val passInput = binding.inputPassword.text.toString()

            // Praktik (4): Instansiasi Object PBO validator
            val validator = AuthValidator()

            // Praktik (5): Eksekusi validasi
            if (validator.isValid(idKasirInput, passInput)) {

                // Praktik (8): Buat Surat Perintah (Intent) menuju BerandaActivity
                val intentPindah = Intent(this, BerandaActivity::class.java)

                // Praktik (9): Titipkan data (ID Kasir) ke halaman tujuan
                intentPindah.putExtra("EXTRA_NAMA_KASIR", idKasirInput)

                // Praktik (8): Eksekusi perpindahan halaman
                startActivity(intentPindah)

                // Matikan MainActivity agar tidak bisa diakses lagi lewat tombol Back
                finish()

            } else {
                // Praktik (6): Feedback UI (Toast) jika validasi GAGAL
                Toast.makeText(
                    this,
                    "ID Kasir / Password tidak valid!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}