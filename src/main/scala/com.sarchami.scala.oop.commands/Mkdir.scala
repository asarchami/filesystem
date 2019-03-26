package com.sarchami.scala.oop.commands

import com.sarchami.scala.oop.files.{ Directory, DirEntry }
import com.sarchami.scala.oop.filesystem.State


class Mkdir(name: String) extends Command {

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
            doMkdir(state, name)
        }
    }

    def checkIlligal(name: String): Boolean = {
        name.contains(".")
    }

    def doMkdir(state:State, name: String): State = {
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
        val newDirectory = Directory.empty(wd.path, name)
        // 3. update the whole directory structure starting from the root
        // (the directory structure is IMMUTABLE)
        val newRoot = updateStructure(state.root, allDirsInPath, newDirectory)
        // 4. find new working directory INSTANCE given wd's full  path, in the NEW directory structure
        val newWd = newRoot.findDescendant(allDirsInPath)

        State(newRoot, newWd)
    }
}