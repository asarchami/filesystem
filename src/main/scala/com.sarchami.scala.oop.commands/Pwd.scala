package com.sarchami.scala.oop.commands

import com.sarchami.scala.oop.filesystem.State

class Pwd extends Command {
  override def apply(state: State): State =
    state.setMessage(state.wd.path)
}
