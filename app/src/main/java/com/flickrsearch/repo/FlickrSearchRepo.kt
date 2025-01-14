package com.flickrsearch.repo

import com.flickrsearch.model.FlickrSearchImageDomain

interface FlickrSearchRepo {
    suspend fun searchPhotos(tags: String): List<FlickrSearchImageDomain>
}
