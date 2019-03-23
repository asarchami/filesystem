package com.sarchami.scala.oop.commands
import com.sarchami.scala.oop.filesystem.State

class UnknownCommand extends Command {

    override def apply(state: State): State =
        state.setMessage("Command not found!")
}
