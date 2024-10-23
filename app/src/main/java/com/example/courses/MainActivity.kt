package com.example.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courses.data.DataSource
import com.example.courses.model.Topic
import com.example.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Mengaktifkan mode edge-to-edge agar aplikasi menggunakan seluruh layar
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        // Mengatur tampilan antarmuka dengan Compose
        setContent {
            // Menggunakan tema aplikasi yang ditentukan
            CoursesTheme {
                // Surface adalah kontainer utama yang menggunakan warna latar belakang dari tema
                Surface(
                    modifier = Modifier
                        .fillMaxSize() // Mengisi seluruh ukuran layar
                        .statusBarsPadding(), // Menambahkan padding pada status bar
                    color = MaterialTheme.colorScheme.background // Menggunakan warna latar belakang dari tema
                ) {
                    // Memanggil fungsi TopicGrid yang akan menampilkan grid topik
                    TopicGrid(
                        modifier = Modifier.padding(
                            start = dimensionResource(R.dimen.padding_small),
                            top = dimensionResource(R.dimen.padding_small),
                            end = dimensionResource(R.dimen.padding_small),
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun TopicGrid(modifier: Modifier = Modifier) {
    // LazyVerticalGrid digunakan untuk membuat grid vertikal yang bisa di-scroll
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Mengatur jumlah kolom menjadi 2
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)), // Jarak vertikal antar item
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)), // Jarak horizontal antar item
        modifier = modifier // Menerapkan modifier yang diteruskan
    ) {
        // Menggunakan items untuk menampilkan daftar topik
        items(DataSource.topics) { topic ->
            // Menampilkan setiap item dalam bentuk kartu
            TopicCard(topic)
        }
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    // Membuat tampilan dalam bentuk kartu
    Card {
        // Membuat layout dalam bentuk baris untuk gambar dan teks
        Row {
            Box {
                // Menampilkan gambar dengan ukuran tetap 68x68 dp dan menjaga rasio aspek 1:1
                Image(
                    painter = painterResource(id = topic.imageRes),
                    contentDescription = null, // Konten deskripsi tidak diperlukan
                    modifier = modifier
                        .size(width = 68.dp, height = 68.dp)
                        .aspectRatio(1f), // Menjaga rasio aspek 1:1
                    contentScale = ContentScale.Crop // Menyesuaikan gambar sesuai ukuran box
                )
            }

            // Kolom untuk teks yang mendeskripsikan topik
            Column {
                // Menampilkan nama topik
                Text(
                    text = stringResource(id = topic.name),
                    style = MaterialTheme.typography.bodyMedium, // Menggunakan style teks dari tema
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_small)
                    )
                )
                // Baris untuk ikon dan jumlah kursus
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Menampilkan ikon di sebelah jumlah kursus
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null, // Deskripsi tidak diperlukan
                        modifier = Modifier
                            .padding(start = dimensionResource(R.dimen.padding_medium)) // Menambahkan padding kiri
                    )
                    // Menampilkan jumlah kursus yang tersedia
                    Text(
                        text = topic.availableCourses.toString(),
                        style = MaterialTheme.typography.labelMedium, // Menggunakan style label dari tema
                        modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small)) // Menambahkan padding kiri
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopicPreview() {
    // Preview untuk menampilkan komposisi UI pada editor
    CoursesTheme {
        // Contoh data untuk topik yang ditampilkan pada preview
        val topic = Topic(R.string.photography, 321, R.drawable.photography)
        // Menampilkan kartu topik dalam kolom yang diatur di tengah layar
        Column(
            modifier = Modifier.fillMaxSize(), // Mengisi seluruh ukuran layar
            verticalArrangement = Arrangement.Center, // Mengatur item secara vertikal di tengah
            horizontalAlignment = Alignment.CenterHorizontally // Mengatur item secara horizontal di tengah
        ) {
            // Menampilkan kartu topik pada preview
            TopicCard(topic = topic)
        }
    }
}
