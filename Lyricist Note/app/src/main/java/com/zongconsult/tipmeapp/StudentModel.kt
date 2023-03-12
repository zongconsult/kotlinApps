package com.zongconsult.tipmeapp

import java.util.*

data class StudentModel(
    var id: Int = getAutoId(),
    var title: String = "",
    var desc: String = "",
    var lyrics: String = ""
) {

    companion object {

        fun getAutoId(): Int {
            val random = Random()
            return random.nextInt(100)

        }
    }
}