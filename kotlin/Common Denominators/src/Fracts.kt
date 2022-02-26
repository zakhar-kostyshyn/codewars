import java.util.*
import java.util.stream.Collectors

object Fracts {

    fun convertFrac(lst: Array<LongArray>): String {

        if (lst.isEmpty()) return ""

        val denominators: List<Long> = lst.map { it[1] }
        val number2ItsPrimes: MutableMap<Long, List<Long>> = mutableMapOf()
        var biggestPrime = 1L
        denominators.forEach {
            var divided = it
            var divisor = 2L
            val numberPrimes = mutableListOf<Long>()
            while (divided != 1L) {
                if (divided % divisor == 0L) {
                    divided /= divisor
                    numberPrimes += divisor
                    if (biggestPrime < divisor) biggestPrime = divisor
                } else divisor++
            }
            number2ItsPrimes += Pair(it, numberPrimes)
        }

        println(number2ItsPrimes)

        var leastCommonDivision = 1L
        for (i in biggestPrime downTo 1L) {
            val count = number2ItsPrimes.values.stream()
                .map { primes -> primes.filter { it == i }.count() }
                .max(Comparator.comparingInt { it })
                .orElse(0)

            for (j in count downTo 1) {
                leastCommonDivision *= i
            }
        }

        println(leastCommonDivision)

        val result: List<String> = lst.map {
            val needToMultiplyNumerator = leastCommonDivision / it[1]
            val numerator = needToMultiplyNumerator * it[0]
            "($numerator,$leastCommonDivision)"
        }

        println(result)

        val joinToString = result.stream().collect(Collectors.joining())

        println(joinToString)

        return joinToString
    }

}