fun meeting(s: String) = s.splitToSequence(";")
    .map { it.uppercase() }   //  uppercase
    .sortedWith(
        compareBy(
            { it.substringAfter(":") },
            { it.substringBefore(":") }
        )
    ).map { "(${it.substringAfter(":")}, ${it.substringBefore(":")})" }
    .joinToString("")

fun meeting2(s: String) =  s.uppercase().splitToSequence(';')
    .map { it.split(":") }
    .sortedWith(compareBy({it[1]}, {it[0]}))
    .joinToString(""){ (firstName, lastName) -> "($lastName, $firstName)"}