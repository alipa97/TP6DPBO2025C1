# TP6DPBO2025C1
Saya Alifa Salsabila dengan NIM 2308138 mengerjakan soal Tugas Praktikum 6 dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin

## Struktur Kelas
Program terdiri dari tiga kelas utama:

a. Player.java
Merepresentasikan karakter burung.
Atribut:
- posX, posY: posisi burung.
- width, height: ukuran burung.
- image: gambar burung.
- velocityY: kecepatan vertikal burung (untuk gravitasi dan loncatan).

b. Pipe.java
Merepresentasikan pipa (atas dan bawah).

Atribut:
- posX, posY: posisi pipa.
- width, height: ukuran pipa.
- image: gambar pipa.
- velocityX: kecepatan horizontal pipa (bergerak ke kiri).
- passed: status apakah burung sudah melewati pipa ini (untuk skor).

## Alur Program
### Inisialisasi:
- App.java membuat jendela dan memuat FlappyBird sebagai panel.
- Gambar (burung, background, pipa) dimuat dari folder assets.

### Mulai Game:
Saat panel FlappyBird diaktifkan, Timer dijalankan untuk update layar secara berkala.

### Pergerakan Burung:
- Burung terpengaruh gravitasi (nilai velocityY bertambah).
- Menekan tombol SPACE akan mengurangi velocityY (burung terbang ke atas).

### Gerakan Pipa:
- Pipa bergerak ke kiri (velocityX negatif).
- Pipa baru dibuat setiap beberapa interval waktu.

### Deteksi Tabrakan:
- Jika burung menyentuh pipa atau tanah → Game Over.
- Jika burung berhasil melewati pipa bawah → Skor bertambah.

### Game Over dan Restart:
- Jika game over, pengguna bisa menekan R untuk restart permainan.
