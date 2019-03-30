package com.sarchami.scala.oop.commands

import com.sarchami.scala.oop.files.{ Directory, DirEntry }
import com.sarchami.scala.oop.filesystem.State


class Mkdir(name: String) extends CreateEntry(name) {

  override def createSpecificEntry(state: State, entryName: String): DirEntry =
    Directory.empty(state.wd.path, entryName)
}
