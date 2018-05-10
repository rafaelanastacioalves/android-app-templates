package com.example.rafaelanastacioalves.moby.util

import android.content.Context

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


object RestServiceTestHelper {
    @Throws(IOException::class)
    fun getStringFromFile(context: Context, filpePath: String): String {
        val stream = context.resources.assets.open(filpePath)
        val ret = convertStreamToString(stream)
        stream.close()
        return ret
    }

    @Throws(IOException::class)
    private fun convertStreamToString(`is`: InputStream): String {
        val reader = BufferedReader(InputStreamReader(`is`))
        val sb = StringBuilder()
        var line: String?
        line = reader.readLine()
        while (line != null) {
            sb.append(line).append("\n")
            line = reader.readLine()
        }
        reader.close()
        return sb.toString()
    }
}
