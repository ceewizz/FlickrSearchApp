package com.flickrsearch.repo

import com.flickrsearch.network.FlickrSearchApiService
import com.flickrsearch.model.FlickrSearchImageDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Implementation of FlickrRepository.
 * Fetches and maps data from the Flickr API.
 */
class FlickrSearchRepoImpl(
    private val apiService: FlickrSearchApiService
) : FlickrSearchRepo {

    override suspend fun searchPhotos(tag: String): List<FlickrSearchImageDomain> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getPhotosByTag(tag)
                if (response.items.isNotEmpty()) {
                    response.items.map { item ->
                        FlickrSearchImageDomain(
                            title = item.title,
                            link = item.link,
                            imageUrl = item.media.imageUrl,
                            dateTaken = item.date_taken,
                            description = item.description,
                            published = item.published,
                            author = item.author,
                            tags = item.tags
                        )
                    }
                } else {
                    emptyList()  // Return empty list if no photos found
                }
            } catch (e: Exception) {
                emptyList()  // Return empty list if an error occurs
            }
        }
    }
}
