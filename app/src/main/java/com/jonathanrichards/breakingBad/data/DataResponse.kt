package com.jonathanrichards.breakingBad.data

interface  DataResponse<T>{
    fun getResponseData(): T
}