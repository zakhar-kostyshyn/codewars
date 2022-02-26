object Stat2 {

    fun stat(s: String): String {

        val duration: List<Long> = s.splitToSequence(", ")
            .map { teamTime ->
                val timeValues = teamTime.split("|").map { it.toLong() }
                timeValues[0] * 3600 + timeValues[1] * 60 + timeValues[2]
            }.onEach {
                println(fromSeconds(it))
            }.toList()

        val range = fromSeconds(calRange(duration))
        println(range)

        val average = fromSeconds(calAverage(duration))
        println(average)

        val median = fromSeconds(calMedian(duration))
        println(median)

        return "Range: $range Average: $average Median: $median"
    }

    private fun calMedian(duration: List<Long>): Long {
        return duration.sorted().let {
            if (it.size % 2 == 1) {
                it[it.size / 2]
            } else {
                (it[it.size / 2] + it[it.size / 2 - 1]) / 2
            }
        }
    }

    private fun calAverage(duration: List<Long>): Long {
        return duration.average().toLong()
    }

    private fun calRange(duration: List<Long>): Long {
        val max = duration.stream().max(Comparator.comparingLong { it }).orElse(null)
        val min = duration.stream().min(Comparator.comparingLong { it }).orElse(null)
        return max - min
    }

    private fun fromSeconds(seconds: Long): String {
        val hours = seconds / 3600
        val minutes = (seconds - hours * 3600) / 60
        val seconds = seconds - hours * 3600 - minutes * 60
        return "${validFormat(hours)}|${validFormat(minutes)}|${validFormat(seconds)}"
    }

    private fun validFormat(v: Long) = if (v < 10) "0$v" else "$v"
}