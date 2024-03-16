package basic.aktivity.widyamaulidaputri_quiz1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class transaksi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        // Get data from intent
        String nama = getIntent().getStringExtra("nama");
        String kodeBarang = getIntent().getStringExtra("kodeBarang");
        String namaBarang = getIntent().getStringExtra("namaBarang");
        double harga = getIntent().getDoubleExtra("harga", 0);
        double totalHarga = getIntent().getDoubleExtra("totalHarga", 0);
        double diskonPelanggan = getIntent().getDoubleExtra("diskonPelanggan", 0);
        double diskon = getIntent().getDoubleExtra("diskon", 0);
        double totalBayar = getIntent().getDoubleExtra("totalBayar", 0);

        String namaMember = getIntent().getStringExtra("namaMember");

        // Display data
        TextView tvNama = findViewById(R.id.TvNama);
        TextView tvTipePelanggan = findViewById(R.id.tvTipePelanggan);
        TextView tvKodeBarang = findViewById(R.id.tvKodeBarang);
        TextView tvNamaBarang = findViewById(R.id.tvNamaBarang);
        TextView tvHarga = findViewById(R.id.tvHarga);
        TextView tvTotalHarga = findViewById(R.id.tvTotalHarga);
        TextView tvDiskonPelanggan = findViewById(R.id.tvDiscPelanggan);
        TextView tvDiskon = findViewById(R.id.tvDiscHarga);
        TextView tvTotalBayar = findViewById(R.id.tvJumlahBayar);

        tvNama.setText("Nama: " + nama);
        tvTipePelanggan.setText("Tipe Pelanggan " + namaMember);
        tvKodeBarang.setText("Kode Barang: " + kodeBarang);
        tvNamaBarang.setText("Nama Barang: " + namaBarang);
        tvHarga.setText("Harga Satuan: " + harga);
        tvTotalHarga.setText("Total Harga: " + totalHarga);
        tvDiskonPelanggan.setText("Diskon Pelanggan: " + diskonPelanggan);
        tvDiskon.setText("Diskon: " + diskon);
        tvTotalBayar.setText("Total Bayar: " + totalBayar);


        // Mengakses tombol Share
        Button btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareTransactionInfo(nama, namaMember, kodeBarang, namaBarang, harga,
                        totalHarga, diskonPelanggan, diskon, totalBayar);
            }
        });
    }

    // Method untuk berbagi informasi transaksi
    private void shareTransactionInfo(String nama, String namaMember, String kodeBarang,
                                      String namaBarang, double harga, double totalHarga,
                                      double diskonPelanggan, double diskon, double totalBayar) {
        String message = "Detail Transaksi:\n" +
                "Nama: " + nama + "\n" +
                "Tipe Member: " + namaMember + "\n" +
                "Kode Barang: " + kodeBarang + "\n" +
                "Nama Barang: " + namaBarang + "\n" +
                "Harga Barang: " + harga + "\n" +
                "Total Harga: " + totalHarga + "\n" +
                "Discount Harga: " + diskonPelanggan + "\n" +
                "Discount Member: " + diskon + "\n" +
                "Jumlah Bayar: " + totalBayar;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(intent, "Bagikan melalui"));
    }
}