package com.sarchami.scala.oop.filesystem
import java.util.Scanner
import com.sarchami.scala.oop.files.Directory
import com.sarchami.scala.oop.commands.Command

object FileSystem extends App {

    val root = Directory.ROOT
    var state = State(root, root)
    val scanner = new Scanner(System.in)

    while(true) {
        state.show
        val input = scanner.nextLine()
        state = Command.from(input).apply(state)
    }
}