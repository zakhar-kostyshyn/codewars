package growth

fun nbYear(pp0: Int, percent: Double, aug: Int, p: Int): Int {
    var lives = pp0
    var counter = 0
    while (lives < p) {
        lives += (lives * percent / 100 + aug).toInt()
        counter++
    }
    return counter
}

fun nbYear2(pp0: Int, percent: Double, aug: Int, p: Int) =
    generateSequence(pp0) { it + (percent / 100.0 * it).toInt() + aug }.indexOfFirst { it >= p }
