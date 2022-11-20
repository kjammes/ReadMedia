package com.jayesh.readmedia.utils

data class CustomResponse<T> constructor(val message: String, val obj: T) {}