object Stat3 {

    fun stat(s: String): String =
        """(\d{1,2})\|(\d{1,2})\|(\d{1,2})""".toRegex()
            .findAll(s)
            .map { it.groupValues[1].toInt()*3600 + it.groupValues[2].toInt()*60 + it.groupValues[3].toInt() }
            .sorted()
            .toList()
            .run {
                val range = last() - first()
                val average = average().toInt()
                val median =
                    if (size % 2 == 1)
                        get(size / 2)
                    else
                        (get(size / 2) + get(size / 2 - 1)) / 2
                "Range: ${range.toHHMMSS()} Average: ${average.toHHMMSS()} Median: ${median.toHHMMSS()}"
            }

    private fun Int.toHHMMSS(): String =
        "%02d|%02d|%02d".format(this / 3600, this / 60 % 60, this % 60)
}