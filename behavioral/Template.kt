/**
 * Template pattern is an class (abstract) that exposes in defined way a template to execute its methods. Subclasses of
 * the Template can override implementation methods if needed.
 * */

abstract class Game {
    abstract fun initialize(): Unit
    abstract fun startGame(): Unit
    abstract fun gameOver(): Unit

    fun play(): Unit {
        initialize()
        startGame()
        gameOver()
    }
}

class Warcraft : Game() {
    override fun initialize() = println("WarCraft - initialization completed!")

    override fun startGame() = println("WarCraft - playing!")

    override fun gameOver() = println("WarCraft - Game Over!")
}

class Starcraft : Game() {
    override fun initialize() = println("StarCraft - initialization completed!")

    override fun startGame() = println("StarCraft - playing!")

    override fun gameOver() = println("StarCraft - Game Over!")
}

class Test {
    @Test
    fun test(){
        var game: Game = Warcraft()
        game.play()

        game = Starcraft()
        game.play()
    }
}