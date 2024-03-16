package basic.aktivity.widyamaulidaputri_quiz1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNama, etKodeBarang, etJumlahBarang;
    RadioButton rbGold, rbSilver, rbBiasa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = findViewById(R.id.etNama);
        etKodeBarang = findViewById(R.id.etKodeBarang);
        etJumlahBarang = findViewById(R.id.etJumlahBarang);
        rbGold = findViewById(R.id.rbGold);
        rbSilver = findViewById(R.id.rbSilver);
        rbBiasa = findViewById(R.id.rbBiasa);
        Button btnProses = findViewById(R.id.btnProses);
        btnProses.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String nama = etNama.getText().toString();
        String kodeBarang = etKodeBarang.getText().toString();
        String jumlahBarangStr = etJumlahBarang.getText().toString();

        // Validasi input
        if (nama.isEmpty() || kodeBarang.isEmpty() || jumlahBarangStr.isEmpty()) {
            Toast.makeText(this, "Harap lengkapi semua kolom", Toast.LENGTH_SHORT).show();
            return;
        }

        int jumlahBarang;
        try {
            jumlahBarang = Integer.parseInt(jumlahBarangStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Jumlah barang harus berupa angka", Toast.LENGTH_SHORT).show();
            return;
        }

        double totalHarga = hitungTotalHarga(kodeBarang, jumlahBarang);
        double diskon = hitungDiskon(totalHarga);
        double totalBayar = totalHarga - diskon;

        // Deklarasi dan inisialisasi namaBarang
        String namaBarang;
        switch (kodeBarang) {
            case "017":
                namaBarang = "Oppo A17";
                break;
            case "OAS":
                namaBarang = "Oppo a5s";
                break;
            case "AAE":
                namaBarang = "Acer Aspire E14";
                break;
            default:
                namaBarang = "Unknown";
                break;
        }

        // Memulai activity transaksi dan mengirim data hasil transaksi
        Intent intent = new Intent(this, transaksi.class);
        intent.putExtra("nama", nama);
        intent.putExtra("kodeBarang", kodeBarang);
        intent.putExtra("namaBarang", namaBarang);
        intent.putExtra("harga", totalHarga / jumlahBarang);
        intent.putExtra("totalHarga", totalHarga);
        intent.putExtra("diskonPelanggan", diskonPelanggan());
        intent.putExtra("diskon", diskon);
        intent.putExtra("totalBayar", totalBayar);

        // Menambahkan informasi nama member ke intent
        intent.putExtra("namaMember", getMemberTypeName());

        startActivity(intent);
    }

    private double hitungTotalHarga(String kodeBarang, int jumlahBarang) {
        switch (kodeBarang) {
            case "017":
                return 2500999 * jumlahBarang;
            case "OAS":
                return 1989123 * jumlahBarang;
            case "AAE":
                return 8676981 * jumlahBarang;
            default:
                Toast.makeText(this, "Kode barang tidak valid", Toast.LENGTH_SHORT).show();
                return 0;
        }
    }

    private double hitungDiskon(double totalHarga) {
        double diskon = 0;

        if (rbGold.isChecked()) {
            diskon = totalHarga * 0.1; // 10% diskon
        } else if (rbSilver.isChecked()) {
            diskon = totalHarga * 0.05; // 5% diskon
        } else if (rbBiasa.isChecked()) {
            diskon = totalHarga * 0.02; // 2% diskon
        }

        if (totalHarga > 10000000) {
            diskon += 100000;
        }

        return diskon;
    }

    // Mengembalikan diskon pelanggan
    private double diskonPelanggan() {
        if (rbGold.isChecked()) {
            return 0.1; // 10% diskon
        } else if (rbSilver.isChecked()) {
            return 0.05; // 5% diskon
        } else if (rbBiasa.isChecked()) {
            return 0.02; // 2% diskon
        }
        return 0;
    }

    // Method to get the name of the member type
    private String getMemberTypeName() {
        if (rbGold.isChecked()) {
            return "Gold"; // Return "Gold" if Gold RadioButton is checked
        } else if (rbSilver.isChecked()) {
            return "Silver"; // Return "Silver" if Silver RadioButton is checked
        } else if (rbBiasa.isChecked()) {
            return "Biasa"; // Return "Biasa" if Biasa RadioButton is checked
        }
        return "None"; // Return "None" if no RadioButton is checked
    }
}
