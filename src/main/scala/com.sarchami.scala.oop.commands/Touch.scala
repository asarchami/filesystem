package com.sarchami.scala.oop.commands

import com.sarchami.scala.oop.files.{ File, DirEntry }
import com.sarchami.scala.oop.filesystem.State


class Touch(name: String) extends CreateEntry(name) {

  override def createSpecificEntry(state: State): DirEntry =
    File.empty(state.wd.path, name)
}
