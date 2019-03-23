package com.sarchami.scala.oop.commands
import com.sarchami.scala.oop.filesystem.State

trait Command {

    def apply(state: State): State
}

object Command {

    def from(input: String): Command =
        new UnknownCommand
}
