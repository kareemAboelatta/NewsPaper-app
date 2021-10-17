package com.example.newspaper.repository


import com.example.newspaper.api.RetrofitInstance

class NewsRepository(
) {
    //all news
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    //sport
    suspend fun getSportNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getSpecificNews(countryCode,"sports",pageNumber)

    //Technology
    suspend fun getTechNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getSpecificNews(countryCode,"technology",pageNumber)

    //Entertainment
    suspend fun getEntertainmentNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getSpecificNews(countryCode,"entertainment",pageNumber)


    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)


}