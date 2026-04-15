import java.util.Scanner;

public class SistemPeminjamanMain20 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // MODIFIKASI 1: Data daftar menerima input pengguna
        
        // Input data Mahasiswa
        System.out.print("Masukkan jumlah mahasiswa: ");
        int jumlahMhs = sc.nextInt();
        sc.nextLine(); // flush newline
        Mahasiswa20[] daftarMhs = new Mahasiswa20[jumlahMhs];
        for (int i = 0; i < jumlahMhs; i++) {
            System.out.println("\n--- Mahasiswa ke-" + (i + 1) + " ---");
            System.out.print("NIM      : ");
            String nim = sc.nextLine();
            System.out.print("Nama     : ");
            String nama = sc.nextLine();
            System.out.print("Prodi    : ");
            String prodi = sc.nextLine();
            daftarMhs[i] = new Mahasiswa20(nim, nama, prodi);
        }

        // Input data Buku
        System.out.print("\nMasukkan jumlah buku: ");
        int jumlahBuku = sc.nextInt();
        sc.nextLine(); // flush newline
        Buku20[] daftarBuku = new Buku20[jumlahBuku];
        for (int i = 0; i < jumlahBuku; i++) {
            System.out.println("\n--- Buku ke-" + (i + 1) + " ---");
            System.out.print("Kode Buku    : ");
            String kode = sc.nextLine();
            System.out.print("Judul        : ");
            String judul = sc.nextLine();
            System.out.print("Tahun Terbit : ");
            int tahun = sc.nextInt();
            sc.nextLine(); // flush newline
            daftarBuku[i] = new Buku20(kode, judul, tahun);
        }

        // Input data Peminjaman
        System.out.print("\nMasukkan jumlah peminjaman: ");
        int jumlahPinjam = sc.nextInt();
        sc.nextLine(); // flush newline
        Peminjaman20[] pinjam = new Peminjaman20[jumlahPinjam];
        for (int i = 0; i < jumlahPinjam; i++) {
            System.out.println("\n--- Peminjaman ke-" + (i + 1) + " ---");

            // Tampilkan daftar mahasiswa sebagai referensi
            System.out.println("Daftar Mahasiswa:");
            for (int k = 0; k < daftarMhs.length; k++) {
                System.out.println("  [" + k + "] " + daftarMhs[k].nim + " - " + daftarMhs[k].nama);
            }
            System.out.print("Pilih index mahasiswa: ");
            int idxMhs = sc.nextInt();
            sc.nextLine();

            // Tampilkan daftar buku sebagai referensi
            System.out.println("Daftar Buku:");
            for (int k = 0; k < daftarBuku.length; k++) {
                System.out.println("  [" + k + "] " + daftarBuku[k].kodeBuku + " - " + daftarBuku[k].judul);
            }
            System.out.print("Pilih index buku: ");
            int idxBuku = sc.nextInt();
            sc.nextLine();

            System.out.print("Lama pinjam (hari): ");
            int lama = sc.nextInt();
            sc.nextLine();

            pinjam[i] = new Peminjaman20(daftarMhs[idxMhs], daftarBuku[idxBuku], lama);
        }

        
        // MENU UTAMA
        
        int pilih;
        do {
            System.out.println("\n=== SISTEM PEMINJAMAN RUANG BACA JTI ===");
            System.out.println("1. Tampilkan Mahasiswa");
            System.out.println("2. Tampil Buku");
            System.out.println("3. Tampilkan Peminjaman");
            System.out.println("4. Urutkan Berdasarkan Denda");
            System.out.println("5. Cari Berdasarkan NIM");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            pilih = sc.nextInt();

            switch (pilih) {

                case 1:
                    System.out.println("\nDaftar Mahasiswa:");
                    for (Mahasiswa20 m : daftarMhs) m.tampilMahasiswa();
                    break;

                case 2:
                    System.out.println("\nDaftar Buku:");
                    for (Buku20 b : daftarBuku) b.tampilBuku();
                    break;

                case 3:
                    System.out.println("\nDaftar Peminjaman:");
                    for (Peminjaman20 p : pinjam) p.tampilPeminjaman();
                    break;

                // MODIFIKASI 2: Menu pengurutan dengan pilihan algoritma

                case 4:
                    System.out.println("\nPilih Algoritma Pengurutan:");
                    System.out.println("  1. Insertion Sort");
                    System.out.println("  2. Selection Sort");
                    System.out.println("  3. Bubble Sort");
                    System.out.print("Pilih: ");
                    int pilihanSort = sc.nextInt();

                    switch (pilihanSort) {

                        case 1: // Insertion Sort (Denda Terbesar ke Terkecil)
                            for (int i = 1; i < pinjam.length; i++) {
                                Peminjaman20 key = pinjam[i];
                                int j = i - 1;
                                while (j >= 0 && pinjam[j].denda < key.denda) {
                                    pinjam[j + 1] = pinjam[j];
                                    j--;
                                }
                                pinjam[j + 1] = key;
                            }
                            System.out.println("\n[Insertion Sort] Diurutkan dari denda terbesar:");
                            break;

                        case 2: // Selection Sort (Denda Terbesar ke Terkecil)
                            for (int i = 0; i < pinjam.length - 1; i++) {
                                int maxIdx = i;
                                for (int j = i + 1; j < pinjam.length; j++) {
                                    if (pinjam[j].denda > pinjam[maxIdx].denda) {
                                        maxIdx = j;
                                    }
                                }
                                Peminjaman20 temp = pinjam[maxIdx];
                                pinjam[maxIdx] = pinjam[i];
                                pinjam[i] = temp;
                            }
                            System.out.println("\n[Selection Sort] Diurutkan dari denda terbesar:");
                            break;

                        case 3: // Bubble Sort (Denda Terbesar ke Terkecil)
                            for (int i = 0; i < pinjam.length - 1; i++) {
                                for (int j = 0; j < pinjam.length - 1 - i; j++) {
                                    if (pinjam[j].denda < pinjam[j + 1].denda) {
                                        Peminjaman20 temp = pinjam[j];
                                        pinjam[j] = pinjam[j + 1];
                                        pinjam[j + 1] = temp;
                                    }
                                }
                            }
                            System.out.println("\n[Bubble Sort] Diurutkan dari denda terbesar:");
                            break;

                        default:
                            System.out.println("Pilihan tidak valid.");
                            break;
                    }

                    if (pilihanSort >= 1 && pilihanSort <= 3) {
                        for (Peminjaman20 p : pinjam) p.tampilPeminjaman();
                    }
                    break;

                
                // MODIFIKASI 3: Menu pencarian dengan indeks tempat data ditemukan
                
                case 5:
                    // Urutkan dulu berdasarkan NIM (Insertion Sort) sebelum Binary Search
                    for (int i = 1; i < pinjam.length; i++) {
                        Peminjaman20 key = pinjam[i];
                        int j = i - 1;
                        while (j >= 0 && pinjam[j].mhs.nim.compareTo(key.mhs.nim) > 0) {
                            pinjam[j + 1] = pinjam[j];
                            j--;
                        }
                        pinjam[j + 1] = key;
                    }

                    System.out.print("Masukkan NIM yang dicari: ");
                    String cariNim = sc.next();

                    int low = 0, high = pinjam.length - 1;
                    boolean found = false;

                    while (low <= high) {
                        int mid = (low + high) / 2;
                        if (pinjam[mid].mhs.nim.equals(cariNim)) {
                            // Tampilkan data yang ditemukan beserta indeksnya
                            System.out.println("\nData ditemukan:");
                            System.out.println("[Indeks " + mid + "] ");
                            pinjam[mid].tampilPeminjaman();
                            found = true;

                            // Cek ke kiri (NIM sama)
                            int temp = mid - 1;
                            while (temp >= 0 && pinjam[temp].mhs.nim.equals(cariNim)) {
                                System.out.println("[Indeks " + temp + "] ");
                                pinjam[temp--].tampilPeminjaman();
                            }

                            // Cek ke kanan (NIM sama)
                            temp = mid + 1;
                            while (temp < pinjam.length && pinjam[temp].mhs.nim.equals(cariNim)) {
                                System.out.println("[Indeks " + temp + "] ");
                                pinjam[temp++].tampilPeminjaman();
                            }
                            break;

                        } else if (pinjam[mid].mhs.nim.compareTo(cariNim) < 0) {
                            low = mid + 1;
                        } else {
                            high = mid - 1;
                        }
                    }

                    if (!found) System.out.println("Data dengan NIM \"" + cariNim + "\" tidak ditemukan.");
                    break;
            }
        } while (pilih != 0);

        System.out.println("Program selesai.");
        sc.close();
    }
}