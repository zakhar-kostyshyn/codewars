import java.time.Duration

object Stat {

    fun stat(s: String): String {

        val duration = s.splitToSequence(", ")
            .map { teamTime ->
                val timeValues = teamTime.split("|").map { it.toLong() }
                Duration.ofSeconds(timeValues[0] * 3600 + timeValues[1] * 60 + timeValues[2])
            }

        val range = calRange(duration)
        val average = calAverage(duration)
        val median = calMedian(duration)

        val result = "$range $average $median"
        println(result)
        return result
    }

    private fun calRange(duration: Sequence<Duration>): String {
        val max = duration.toList().stream().max { d1, d2 -> d1.compareTo(d2) }.orElse(null)
        val min = duration.toList().stream().min { d1, d2 -> d1.compareTo(d2) }.orElse(null)
        return max.minus(min)?.let {
            val hours = it.toHours()
            val minutes = it.toMinutes() - hours * 60
            val seconds = it.toSeconds() - minutes * 60 - hours * 3600
            val stringHours = hours.let { i -> if (i < 10) "0$i" else "$i" }
            val stringMinutes = minutes.let { i -> if (i < 10) "0$i" else "$i" }
            val stringSecond = seconds.let { i -> if (i < 10) "0$i" else "$i" }
            "Range: $stringHours|$stringMinutes|$stringSecond"
        } ?: ""
    }


    private fun calAverage(duration: Sequence<Duration>) =
        duration.map { it.toSeconds() }.toList().average().let {
            val hours = it.toInt() / 3600
            val minutes = (it.toInt() - hours * 3600) / 60
            val seconds = it.toInt() - hours * 3600 - minutes * 60
            val stringHours = hours.let { i -> if (i < 10) "0$i" else "$i" }
            val stringMinutes = minutes.let { i -> if (i < 10) "0$i" else "$i" }
            val stringSecond = seconds.let { i -> if (i < 10) "0$i" else "$i" }
            "Average: $stringHours|$stringMinutes|$stringSecond"
        }

    private fun calMedian(duration: Sequence<Duration>) =
        duration.sorted().toList().let {
            val median = if (it.size % 2 == 1) {
                it[it.size / 2].toSeconds().toInt()
            } else {
                val left = it[it.size / 2]
                val right = it[it.size / 2 + 1]
                (left.plus(right).toSeconds() / 2).toInt()
            }
            val hours = median / 3600
            val minutes = (median - hours * 3600) / 60
            val seconds = median - hours * 3600 - minutes * 60
            val stringHours = hours.let { i -> if (i < 10) "0$i" else "$i" }
            val stringMinutes = minutes.let { i -> if (i < 10) "0$i" else "$i" }
            val stringSecond = seconds.let { i -> if (i < 10) "0$i" else "$i" }
            "Median: $stringHours|$stringMinutes|$stringSecond"
        }

}