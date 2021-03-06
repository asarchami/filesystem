package com.sarchami.scala.oop.commands

import com.sarchami.scala.oop.files.Directory
import com.sarchami.scala.oop.filesystem.State
import scala.annotation.tailrec


class Echo(args: Array[String]) extends Command {

  override def apply(state: State): State = {
    /*
     if no args, state
     else if just one arg, print to console
     else if multiple args
     {
         operator = next to last argument
         if >
             echo to a file (may create afile if not there)
         if >>
             append to a file
         else
             just echo everything to console
     }
    */
    if (args.isEmpty) state
    else if (args.length == 1) state.setMessage(args(0))
    else {
      val operator = args(args.length - 2)
      val filename = args(args.length - 1)
      val contents = createContent(args, args.length - 2)

      if (">>".equals(operator))
        doEcho(state, contents, filename, append = true)
      else if (">".equals(operator))
        doEcho(state, contents, filename, append = false)
      else
        state.setMessage(createContent(args, args.length))
    }
  }

  def doEcho(state: State, contens: String, filename: String, append: Boolean): State = ???
  // def doEcho(state: State, contens: String, filename: String, append: Boolean): State = {
  //   if (filename.contains(Directory.SEPARATOR))
  //     state.setMessage("Echo: filename must not contain separators")
  //   else {
  //     val newRoot: Directory = ???
  //       if (newRoot == state.root)
  //         state.setMessage(filename + ": no such file")
  //   }
  // }

  // topIndex NON-INCLUSIVE
  def createContent(args: Array[String], topIndex: Int): String = {
    @tailrec
    def createContentHelper(currentIndex: Int, accumulator: String): String = {
      if (currentIndex >= topIndex) accumulator
      else createContentHelper(currentIndex + 1, accumulator + " " + args(currentIndex))
    }
    createContentHelper(0, "")
  }
}
