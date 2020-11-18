package com.jonathanrichards.breakingBad.data

sealed class Status {
    object Error : Status()
    object Loading : Status()
    object Success : Status()
}