fun main() {
    var checker: String
    var letters = ""
    var lifeLeft = 8
    var readLine: String
    println("print your name")
    val myName: String = readLine().toString()
    println("Hello! $myName, Let’s play Hangman!")
    println("Enter Word")
    val hangManWord: String = readLine().toString()
    println("Game is starting...")
    while (lifeLeft > 0) {
        checker = hangManWord
        println("Life remaining: $lifeLeft")
        readLine = readLine().toString()
        while (readLine.length > 1 || readLine.matches("[^A-Za-z]".toRegex())) {
            println("Please, enter a valid character!")
            readLine = readLine().toString()
        }
        if (letters.contains("[$readLine]".toRegex(RegexOption.IGNORE_CASE))) {
            println("You already tried this character")
        } else if (!checker.contains(readLine)) {
            lifeLeft--
        }
        letters += readLine
        checker = checker.replace("[^$letters]".toRegex(setOf(RegexOption.IGNORE_CASE)), "_")
        if (checker === hangManWord) {
            println("You win!, correct word is $hangManWord ")
            return
        }
        println(checker)
        println(lifeLeft)
    }
}