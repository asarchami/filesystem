ThisBuild / scalaVersion := "2.12.8"
ThisBuild / organization := "com.sarchami.ali"

lazy val FileSystem = (project in file("."))
    .settings(
        name := "File System"
    )