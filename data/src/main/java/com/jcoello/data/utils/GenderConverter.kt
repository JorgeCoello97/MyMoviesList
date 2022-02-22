package com.jcoello.data.utils

import androidx.room.TypeConverter
import java.util.*

class GenderConverter {
    @TypeConverter
    fun fromStringToIntArray(value: String): MutableList<Int> {
        val tokenizer = StringTokenizer(value, ".")
        val listAux = mutableListOf<Int>()
        while (tokenizer.hasMoreTokens()) {
            var genderId = tokenizer.nextToken()
            listAux.add(genderId.toInt())
        }
        return listAux
    }

    @TypeConverter
    fun fromIntArrayToString(value: MutableList<Int>): String {
        var index = 0
        var result = ""
        while (index < value.size) {
            var genderId = value[index]
            if (index == value.lastIndex) {
                result += "$genderId"
            } else {
                result += "$genderId."
            }
            index++
        }
        return result
    }
}