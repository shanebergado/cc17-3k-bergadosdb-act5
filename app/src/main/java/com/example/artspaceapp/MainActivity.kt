package com.example.artspaceapp

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var currentImageIndex = 0

    // List of artworks
    private val artworks = listOf(
        Artwork(R.drawable.the_starry_night, "The Starry Night", "Vincent van Gogh"),
        Artwork(R.drawable.sea_of_fog, "Wanderer above the Sea of Fog", "Casper David Friedrich"),
        Artwork(R.drawable.lady_of_sharlott, "The Lady of shalott", "Jhon William Waterhouse"),
        Artwork(R.drawable.folies_bergere, "A Bar at the Folies-Bergere", "Edouard Manet"),
        Artwork(R.drawable.blue_dancer, "The Blue Dancers", "Edgar Degas")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find views
        val imageView: ImageView = findViewById(R.id.image_view)
        val artTitleTextView: TextView = findViewById(R.id.art_title_text) // Updated ID here
        val artistNameTextView: TextView = findViewById(R.id.artis_name)
        val previousButton: Button = findViewById(R.id.previousButton)
        val nextButton: Button = findViewById(R.id.nextButton)

        // Display initial artwork
        displayArtwork(imageView, artTitleTextView, artistNameTextView)

        // Set up button click listeners
        previousButton.setOnClickListener {
            currentImageIndex = if (currentImageIndex > 0) {
                currentImageIndex - 1
            } else {
                artworks.size - 1
            }
            displayArtwork(imageView, artTitleTextView, artistNameTextView)
        }

        nextButton.setOnClickListener {
            currentImageIndex = (currentImageIndex + 1) % artworks.size
            displayArtwork(imageView, artTitleTextView, artistNameTextView)
        }
    }

    // Function to display the current artwork
    private fun displayArtwork(imageView: ImageView, artTitle: TextView, artistName: TextView) {
        val artwork = artworks[currentImageIndex]
        imageView.setImageResource(artwork.imageResId)
        artTitle.text = artwork.title
        artistName.text = artwork.artist
    }

    // Handle configuration changes such as orientation
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Re-apply the current artwork to the new layout
        val imageView: ImageView = findViewById(R.id.image_view)
        val artTitleTextView: TextView = findViewById(R.id.art_title_text)
        val artistNameTextView: TextView = findViewById(R.id.artis_name)

        // Display the current artwork
        displayArtwork(imageView, artTitleTextView, artistNameTextView)
    }
}