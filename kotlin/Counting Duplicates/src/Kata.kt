fun duplicateCount(text: String) =
    text.groupBy { it.lowercase() }
        .onEach { println(it) }
        .count { it.value.count() > 1 }