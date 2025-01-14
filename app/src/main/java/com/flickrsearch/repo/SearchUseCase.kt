package com.flickrsearch.repo

class SearchUseCase(private val repository: FlickrSearchRepo) {
    suspend fun execute(tags: String) = repository.searchPhotos(tags)
}
