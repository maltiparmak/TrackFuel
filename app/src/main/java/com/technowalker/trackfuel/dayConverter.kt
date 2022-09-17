package com.technowalker.trackfuel

class dayConverter {
    var mDay: String = ""
    private fun dayConverter(day: String) {
        val daylist = listOf<String>(
            "Pazartesi",
            "Salı",
            "Çarşamba",
            "Perşembe",
            "Cuma",
            "Cumartesi",
            "Pazar"
        )

        if (day == "Mon") {
            mDay = daylist[0]
        }
        if (day == "Tue") {
            mDay = daylist[1]
        }
        if (day == "Wed") {
            mDay = daylist[2]
        }
        if (day == "Thu") {
            mDay = daylist[3]
        }
        if (day == "Fri") {
            mDay = daylist[4]
        }
        if (day == "Sat") {
            mDay = daylist[5]
        }
        if (day == "Sun") {
            mDay = daylist[6]
        }


    }
}