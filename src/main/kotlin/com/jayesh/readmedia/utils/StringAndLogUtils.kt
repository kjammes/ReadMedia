package com.jayesh.readmedia.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class StringAndLogUtils(private val LOG: Logger = LoggerFactory.getLogger("StringAndLogUtils")) {
    fun buildErrorMessageAndLog(message: String, e : Exception) : String {
        val result = "$message | $e.localizedMessage"
        LOG.error(result)
        return result
    }
}