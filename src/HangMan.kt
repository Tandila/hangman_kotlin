import kotlin.system.exitProcess

var winners: MutableList<String> = ArrayList()
fun main() {
    chosedY()
}

fun chosedH() {
    println(winners)
    println("Want to play again? (Y/N/H)")
    var readLine = readLine().toString().toUpperCase()
    while(!readLine.matches("^[YNHynh]$".toRegex())) {
        println("Enter correct letter (Y/N/H)")
        readLine = readLine().toString().toUpperCase()
    }
    option(readLine)
}

fun chosedN() {
    exitProcess(status = 0)
}
fun checker(checker: String, hangManWord: String, myName:String, lifeLeft:Int) {
    if (checker === hangManWord) {
        winOrLose(true, myName, lifeLeft)
    }
    if (lifeLeft == 0) {
        winOrLose(false, myName, lifeLeft)
    }
}

fun winOrLose(isWin: Boolean, myName: String, lifeLeft: Int) {
    when (isWin) {
        true -> {
            winners.add("$myName: $lifeLeft")
            println("Congratulations! Want to play again? (Y/N/H)")
            var readLine = readLine().toString().toUpperCase()
            while(!readLine.matches("^[YNHynh]$".toRegex())) {
                println("Enter correct letter (Y/N/H)")
                readLine = readLine().toString().toUpperCase()
            }
            option(readLine)
        }
        false -> {
            println("you lose! Want to play again? (Y/N/H)")
            var readLine = readLine().toString().toUpperCase()
            while(!readLine.matches("^[YNHynh]$".toRegex())) {
                println("Enter correct letter (Y/N/H)")
                readLine = readLine().toString().toUpperCase()
            }
            option(readLine)
        }
    }

}

fun option(readLine: String) {
    when (readLine) {
        "Y" -> chosedY()
        "N" -> chosedN()
        "H" -> chosedH()
    }
}

fun chosedY() {
    var checker: String
    var letters = ""
    var readLine: String
    println("print your name")
    var myName: String = readLine().toString()
    while(myName.trim() == "") {
        println("print your name")
        myName = readLine().toString()
    }
    println("Hello! $myName, Let’s play Hangman!")
    println("Enter Word")
    val hangManWord: String = readLine().toString()
    var lifeLeft = 8
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
        checker(checker, hangManWord,myName, lifeLeft)
        println(checker)
        println(lifeLeft)
    }
}
