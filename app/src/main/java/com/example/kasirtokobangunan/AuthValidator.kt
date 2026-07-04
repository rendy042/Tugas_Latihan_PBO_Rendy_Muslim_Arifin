// File: AuthValidator.kt
// Praktik (4): Integrasi PBO (Validator)
// Wadah logika validasi terpisah dari Activity (sesuai prinsip PBO)

package com.example.kasirtokobangunan

class AuthValidator {

    // Mengecek apakah ID Kasir dan Password yang dimasukkan sah
    fun isValid(idKasir: String, password: String): Boolean {
        // Syarat 1: Tidak boleh kosong
        if (idKasir.isEmpty() || password.isEmpty()) {
            return false
        }
        // Syarat 2: Password minimal 6 karakter
        if (password.length < 6) {
            return false
        }
        return true
    }
}