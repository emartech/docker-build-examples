package util

final class Util(block: => Unit) {
    println("This is a bad example")
}

object Util {
    def apply(block: => Unit): Util = new Util(block)
}