// File: DataModels.kt
// Tugas Kelompok - PBL Sprint M5
// Modul Pemrograman Berbasis Objek - TE14027
// Sistem Aplikasi Toko Bangunan
// Class Diagram: BahanBangunan (1..*) --berisi-- Transaksi (1..*) --melakukan-- Kasir

// ============================================================
// CLASS 1: BahanBangunan
// Atribut private: kodeBarang, namaBarang, satuan, harga, stok
// Method public: cekStok(), updateStok(), tampilkanInfo()
// Penerapan enkapsulasi: "private set" pada stok
// Validasi: stok TIDAK BOLEH minus (tidak bisa update melebihi stok tersedia).
// ============================================================
class BahanBangunan(
    private val kodeBarang: String,
    private val namaBarang: String,
    private val satuan: String,
    private var harga: Int,
    stokAwal: Int
) {
    // Properti Kotlin dengan private set:
    // - Bisa dibaca dari luar lewat cekStok()
    // - TIDAK BISA di-assign langsung dari luar class (mis. barang.stok = -50 -> ERROR COMPILER)
    var stok: Int = stokAwal
        private set

    fun ambilNamaBarang(): String {
        return namaBarang
    }

    fun ambilHarga(): Int {
        return harga
    }

    fun cekStok(): Int {
        return stok
    }

    // Method resmi untuk mengubah stok, DILENGKAPI VALIDASI.
    // jumlah > 0 -> mengurangi stok (barang terjual)
    // Return Boolean: true jika update berhasil, false jika ditolak (stok tidak cukup)
    fun updateStok(jumlah: Int): Boolean {
        val stokBaru = stok - jumlah
        return if (stokBaru < 0) {
            println("ERROR: Stok $namaBarang tidak mencukupi! (Sisa: $stok, diminta: $jumlah)")
            false
        } else {
            stok = stokBaru
            true
        }
    }

    fun tampilkanInfo() {
        println("$kodeBarang | $namaBarang | Rp$harga / $satuan | Stok: $stok $satuan")
    }
}

// ============================================================
// CLASS 2: Transaksi
// Atribut private: noTransaksi, tanggal, totalBayar, daftarBarang
// Method public: tambahBarang(), buatTransaksi(), hitungTotal(), cetakStruk()
// Relasi "berisi": 1 Transaksi bisa berisi banyak (1..*) BahanBangunan
// ============================================================
class Transaksi(
    private val noTransaksi: String,
    private val tanggal: String
) {
    private var totalBayar: Int = 0
    private val daftarBarang = mutableListOf<Pair<BahanBangunan, Int>>()

    // Menambahkan barang ke transaksi.
    // Barang hanya ditambahkan JIKA updateStok() pada BahanBangunan berhasil (stok cukup).
    fun tambahBarang(barang: BahanBangunan, jumlah: Int) {
        if (barang.updateStok(jumlah)) {
            daftarBarang.add(Pair(barang, jumlah))
            totalBayar += barang.ambilHarga() * jumlah
            println("Barang '${barang.ambilNamaBarang()}' x$jumlah ditambahkan ke transaksi $noTransaksi")
        } else {
            println("Gagal menambahkan ${barang.ambilNamaBarang()} ke transaksi $noTransaksi.")
        }
    }

    fun buatTransaksi(): Boolean {
        if (daftarBarang.isEmpty()) return false
        return true
    }

    fun hitungTotal(): Int {
        return totalBayar
    }

    fun cetakStruk() {
        println("===== STRUK PEMBELIAN =====")
        println("No Transaksi : $noTransaksi")
        println("Tanggal      : $tanggal")
        println("----------------------------")
        for ((barang, jumlah) in daftarBarang) {
            println("${barang.ambilNamaBarang()} x$jumlah = Rp${barang.ambilHarga() * jumlah}")
        }
        println("----------------------------")
        println("TOTAL BAYAR : Rp$totalBayar")
        println("============================\n")
    }
}

// ============================================================
// CLASS 3: Kasir
// Atribut private: idKasir, namaKasir
// Method public: login(), prosesTransaksi()
// ============================================================
class Kasir(
    private val idKasir: String,
    private val namaKasir: String
) {
    fun login(): Boolean {
        println("Kasir $namaKasir ($idKasir) berhasil login.\n")
        return true
    }

    fun prosesTransaksi(transaksi: Transaksi) {
        if (transaksi.buatTransaksi()) {
            println("[Kasir $namaKasir memproses transaksi...]")
            transaksi.cetakStruk()
        } else {
            println("Transaksi gagal, keranjang kosong.")
        }
    }
}

// ============================================================
// UJI COBA / TESTING (main function)
// ============================================================
fun main() {
    // Membuat beberapa data barang bangunan
    val semen = BahanBangunan("BB-01", "Semen Tiga Roda", "sak", 65000, 100)
    val besi = BahanBangunan("BB-02", "Besi Beton 10mm", "batang", 85000, 50)
    val cat = BahanBangunan("BB-03", "Cat Tembok Putih", "kaleng", 120000, 30)
    val paku = BahanBangunan("BB-04", "Paku 5cm", "kg", 25000, 200)

    println("===== DAFTAR BARANG BAHAN BANGUNAN =====")
    semen.tampilkanInfo()
    besi.tampilkanInfo()
    cat.tampilkanInfo()
    paku.tampilkanInfo()
    println()

    // Kasir login dulu (sesuai use case <<include>> Login)
    val kasir1 = Kasir("K-01", "Rendy")
    kasir1.login()

    // Simulasi transaksi 1: pelanggan beli semen dan besi
    val transaksi1 = Transaksi("TRX-001", "01-07-2026")
    transaksi1.tambahBarang(semen, 5)
    transaksi1.tambahBarang(besi, 10)
    kasir1.prosesTransaksi(transaksi1)

    // Simulasi transaksi 2: pelanggan beli cat dan paku
    val transaksi2 = Transaksi("TRX-002", "01-07-2026")
    transaksi2.tambahBarang(cat, 3)
    transaksi2.tambahBarang(paku, 2)
    kasir1.prosesTransaksi(transaksi2)

    // Simulasi transaksi 3 (UJI VALIDASI): coba beli besi melebihi stok yang tersisa
    println("===== UJI VALIDASI: BELI MELEBIHI STOK =====")
    val transaksi3 = Transaksi("TRX-003", "01-07-2026")
    transaksi3.tambahBarang(besi, 999) // Akan GAGAL karena stok besi tidak cukup
    kasir1.prosesTransaksi(transaksi3) // Transaksi gagal, keranjang kosong

    // Bukti enkapsulasi: baris di bawah ini akan ERROR COMPILER jika di-uncomment,
    // karena 'stok' menggunakan private set (tidak bisa di-assign langsung dari luar class).
    // semen.stok = -50

    // Cek stok terbaru setelah transaksi (bukti updateStok() jalan & tervalidasi)
    println("===== STOK SETELAH TRANSAKSI =====")
    semen.tampilkanInfo()
    besi.tampilkanInfo()
    cat.tampilkanInfo()
    paku.tampilkanInfo()
}