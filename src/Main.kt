class BahanBangunan(
    private val kodeBarang: String,
    private val namaBarang: String,
    private val satuan: String,
    private var harga: Int,
    private var stok: Int
) {
    fun cekStok(): Int {
        return stok
    }
    fun updateStok(jumlah: Int) {
        stok -= jumlah
    }
    fun tampilkanInfo() {
        println("$namaBarang | Rp$harga / $satuan | Stok: $stok $satuan")
    }
}

class Kasir(
    private val idKasir: String,
    private val namaKasir: String
) {
    fun login(): Boolean {
        // implementasi validasi login
        return true
    }
    fun prosesTransaksi(transaksi: Transaksi) {
        transaksi.buatTransaksi()
    }
}

class Transaksi(
    private val noTransaksi: String,
    private val tanggal: String,
    private var totalBayar: Int = 0
) {
    fun buatTransaksi(): Boolean {
        // implementasi logika transaksi
        return true
    }
    fun hitungTotal(): Int {
        return totalBayar
    }
}

fun main() {
    val semen = BahanBangunan("BB-01", "Semen Tiga Roda", "sak", 65000, 100)
    semen.tampilkanInfo()
    // Output: Semen Tiga Roda | Rp65000 / sak | Stok: 100 sak
}