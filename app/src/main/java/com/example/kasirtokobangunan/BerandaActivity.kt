// File: BerandaActivity.kt
// Halaman Utama/Beranda - Menerima data dari MainActivity via Intent
// Praktik (10): Menerima Data di Halaman Tujuan

package com.example.kasirtokobangunan

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kasirtokobangunan.databinding.ActivityBerandaBinding

class BerandaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBerandaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBerandaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Praktik (10): Menerima paket Intent yang masuk, cari Key "EXTRA_NAMA_KASIR"
        val namaKasir = intent.getStringExtra("EXTRA_NAMA_KASIR") ?: "Kasir"

        // Praktik (10): Memperbarui UI (TextView) dengan data yang diterima
        binding.txtWelcome.text = "Selamat Datang, $namaKasir"

        // Tombol Logout: kembali ke halaman Login
        binding.btnLogout.setOnClickListener {
            finish()
        }

        // Tombol Transaksi Baru (placeholder, bisa dikembangkan lebih lanjut)
        binding.btnTransaksiBaru.setOnClickListener {
            Toast.makeText(this, "Fitur Transaksi Baru akan segera hadir!", Toast.LENGTH_SHORT).show()
        }
    }
}