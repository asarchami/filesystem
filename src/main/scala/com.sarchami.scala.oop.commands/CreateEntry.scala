package com.sarchami.scala.oop.commands

import com.sarchami.scala.oop.files.{ Directory, DirEntry }
import com.sarchami.scala.oop.filesystem.State

abstract class CreateEntry(name: String) extends Command {
  override def apply(state: State): State = {
    val wd = state.wd
    if (wd.hasEntry(name)) {
      state.setMessage("Entry " + name + " already exists!")
    } else if (name.contains(Directory.SEPARATOR)) {
      // mkdir something/somethingElse
      state.setMessage(name + " must not contain separators!")
    } else if (checkIlligal(name)) {
      state.setMessage(name + ": illigal entry name!")
    } else {
      doCreateEntry(state, name)
    }
  }

  def checkIlligal(name: String): Boolean = {
    name.contains(".")
  }

  def doCreateEntry(state: State, entryName: String): State = {
    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: DirEntry): Directory = {

      if (path.isEmpty) currentDirectory.addEntry(newEntry)
      else {
        println(path)
        println(path.head)
        println(path.head.isEmpty)
        println(currentDirectory.findEntry(path.head).asDirectory)
        val oldEntry = currentDirectory.findEntry(path.head).asDirectory
        currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry, path.tail, newEntry))
      }
    }

    val wd = state.wd

    // 1. all the direcotries in the full path
    val allDirsInPath = wd.getAllFoldersInPath

    // 2. create new directory entry in the wd
    // TODO implement this
    val newEntry = createSpecificEntry(state, name)
    // val newDirectory = Directory.empty(wd.path, name)

    // 3. update the whole directory structure starting from the root
    // (the directory structure is IMMUTABLE)
    val newRoot = updateStructure(state.root, allDirsInPath, newEntry)

    // 4. find new working directory INSTANCE given wd's full  path, in the NEW directory structure
    val newWd = newRoot.findDescendant(allDirsInPath)

    State(newRoot, newWd)
  }

  def createSpecificEntry(state: State, entryName: String): DirEntry
}
