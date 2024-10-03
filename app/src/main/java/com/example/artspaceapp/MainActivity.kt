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
        Artwork(R.drawable.the_starry_night, "The Starry Night", "Vincent van Gogh", "It depicts the view from the east-facing window of his asylum room at Saint-Rémy-de-Provence, just before sunrise, with the addition of an imaginary village. "),
        Artwork(R.drawable.sea_of_fog, "Wanderer above the Sea of Fog", "Casper David Friedrich","The painting has been interpreted as an emblem of self-reflection or contemplation of life's path."),
        Artwork(R.drawable.lady_of_sharlott, "The Lady of shalott", "Jhon William Waterhouse", "The Lady, in her tower on Shalott, is surrounded by lilies, a frequent symbol of chastity and purity."),
        Artwork(R.drawable.folies_bergere, "A Bar at the Folies-Bergere", "Edouard Manet", " A Parisian music hall founded in 1869 and noted for the lavish spectacle and mildly risqué content of its entertainments."),
        Artwork(R.drawable.blue_dancer, "The Blue Dancers", "Edgar Degas"," This picture immediately evokes an emotional response due to the fact that it reflects the unusual reality of the backstage life of ballerinas."),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find views
        val imageView: ImageView = findViewById(R.id.image_view)
        val artTitleTextView: TextView = findViewById(R.id.art_title_text) // Updated ID here
        val artistNameTextView: TextView = findViewById(R.id.artis_name)
        val tagStateDescriptionTextView: TextView = findViewById(R.id.tag_state_description)
        val previousButton: Button = findViewById(R.id.previousButton)
        val nextButton: Button = findViewById(R.id.nextButton)

        // Display initial artwork
        displayArtwork(imageView, artTitleTextView, artistNameTextView, tagStateDescriptionTextView)

        // Set up button click listeners
        previousButton.setOnClickListener {
            currentImageIndex = if (currentImageIndex > 0) {
                currentImageIndex - 1
            } else {
                artworks.size - 1
            }
            displayArtwork(
                imageView,
                artTitleTextView,
                artistNameTextView,
                tagStateDescriptionTextView
            )
        }

        nextButton.setOnClickListener {
            currentImageIndex = (currentImageIndex + 1) % artworks.size
            displayArtwork(
                imageView,
                artTitleTextView,
                artistNameTextView,
                tagStateDescriptionTextView
            )
        }
    }

    // Function to display the current artwork
    private fun displayArtwork(
        imageView: ImageView,
        artTitle: TextView,
        artistName: TextView,
        tagStateDescriptionTextView: TextView
    ) {
        val artwork = artworks[currentImageIndex]
        imageView.setImageResource(artwork.imageResId)
        artTitle.text = artwork.title
        artistName.text = artwork.artist
        tagStateDescriptionTextView.text = artwork.description
    }

    // Handle configuration changes such as orientation
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Re-apply the current artwork to the new layout
        val imageView: ImageView = findViewById(R.id.image_view)
        val artTitleTextView: TextView = findViewById(R.id.art_title_text)
        val artistNameTextView: TextView = findViewById(R.id.artis_name)
        val tagStateDescriptionTextView: TextView = findViewById(R.id.tag_state_description)

        // Display the current artwork
        displayArtwork(imageView, artTitleTextView, artistNameTextView, tagStateDescriptionTextView)
    }
}