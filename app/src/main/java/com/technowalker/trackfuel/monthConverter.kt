package com.technowalker.trackfuel

class monthConverter {
    var mMonth: String = ""
    private fun monthConverter(month: String) {
        val monthList = listOf<String>(
            "Ocak",
            "Şubat",
            "Mart",
            "Nisan",
            "Mayıs",
            "Haziran",
            "Temmuz",
            "Ağustos",
            "Eylül",
            "Ekim",
            "Kasım",
            "Aralık"
        )

        if (month == "Jan") {
            mMonth = monthList[0]
        }
        if (month == "Feb") {
            mMonth = monthList[1]
        }
        if (month == "Mar") {
            mMonth = monthList[2]
        }
        if (month == "Apr") {
            mMonth = monthList[3]
        }
        if (month == "May") {
            mMonth = monthList[4]
        }
        if (month == "Jun") {
            mMonth = monthList[5]
        }
        if (month == "Jul") {
            mMonth = monthList[6]
        }
        if (month == "Aug") {
            mMonth = monthList[7]
        }
        if (month == "Sep") {
            mMonth = monthList[8]
        }
        if (month == "Oct") {
            mMonth = monthList[9]
        }
        if (month == "Nov") {
            mMonth = monthList[10]
        }
        if (month == "Dec") {
            mMonth = monthList[11]
        }

    }
}