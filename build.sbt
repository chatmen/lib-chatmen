
/*
 * This file is part of the KIDSNA-SITTER service.
 *
 * For the full copyright and license information,
 * please view the LICENSE file that was distributed with this source code.
 */

import scala.sys.process._
lazy val branch  = ("git branch".lineStream_!).find{_.head == '*'}.map{_.drop(2)}.getOrElse("")
lazy val release = (branch == "master" || branch.startsWith("release"))
lazy val commonSettings = Seq(
  organization := "chatmen-app",
  scalaVersion := "2.12.8",
  resolvers ++= Seq(
    "Typesafe Releases"  at "http://repo.typesafe.com/typesafe/releases/",
    "Sonatype Release"   at "https://oss.sonatype.org/content/repositories/releases/",
    "Sonatype Snapshot"  at "https://oss.sonatype.org/content/repositories/snapshots/",
    "IxiaS Releases"     at "http://maven.ixias.net.s3-ap-northeast-1.amazonaws.com/releases",
    "IxiaS Snapshots"    at "http://maven.ixias.net.s3-ap-northeast-1.amazonaws.com/snapshots",
    "keyczar"            at "https://raw.githubusercontent.com/google/keyczar/master/java/maven/"
  ),
  // Scala compile options
  scalacOptions ++= Seq(
    "-deprecation",            // Emit warning and location for usages of deprecated APIs.
    "-feature",                // Emit warning and location for usages of features that should be imported explicitly.
    "-unchecked",              // Enable additional warnings where generated code depends on assumptions.
    "-Xfatal-warnings",        // Fail the compilation if there are any warnings.m
    "-Xlint",                  // Enable recommended additional warnings.
    "-Ywarn-adapted-args",     // Warn if an argument list is modified to match the receiver.
    "-Ywarn-dead-code",        // Warn when dead code is identified.
    "-Ywarn-inaccessible",     // Warn about inaccessible types in method signatures.
    "-Ywarn-nullary-override", // Warn when non-nullary overrides nullary, e.g. def foo() over def foq
    "-Ywarn-numeric-widen"     // Warn when numerics are widened.
  ),
  libraryDependencies ++= Seq(

    // --[ UnitTest ]-----------------------------------------
    "net.ixias" %% "ixias"      % "1.1.11",
    "net.ixias" %% "ixias-aws"  % "1.1.11",
    "net.ixias" %% "ixias-play" % "1.1.11",
    "org.specs2"     %% "specs2-core"          % "3.9.1"  % Test,
    "org.specs2"     %% "specs2-matcher-extra" % "3.9.1"  % Test,
    "ch.qos.logback"  % "logback-classic"      % "1.1.3"  % Test,
    "mysql"           % "mysql-connector-java" % "5.1.39" % Test
  ),
  fork in run := true,
  javaOptions ++= Seq(
    "-Dconfig.file=conf/env.dev/application.conf",
    "-Dlogger.file=conf/env.dev/logback.xml"
  )
)


// Service libraries
//~~~~~~~~~~~~~~~~~~
lazy val libUDB = (project in file("framework/chatmen-udb"))
  .settings(name := "chatmen-udb")
  .settings(commonSettings:    _*)

// Service libraries
//~~~~~~~~~~~~~~~~~~
lazy val libCORE = (project in file("framework/chatmen-core"))
  .settings(name := "chatmen-core")
  .settings(commonSettings:    _*)
  .aggregate(libUDB)
  .dependsOn(libUDB)


// Service META-package
//~~~~~~~~~~~~~~~~~~~~~~~
lazy val libMain = (project in file("."))
  .settings(name := "chatmen")
  .settings(commonSettings:    _*)
  .aggregate(libUDB, libCORE)
  .dependsOn(libUDB, libCORE)

// Setting for Prompt
//~~~~~~~~~~~~~~~~~~~~
import com.scalapenos.sbt.prompt._
val defaultTheme = PromptTheme(List(
  text("[SBT] ", fg(green)),
  text(state => { Project.extract(state).get(organization) + "@" }, fg(magenta)),
  text(state => { Project.extract(state).get(name) },               fg(magenta)),
  text(":", NoStyle),
  gitBranch(clean = fg(green), dirty = fg(yellow)).padLeft("[").padRight("]"),
  text(" > ", NoStyle)
))
promptTheme := defaultTheme
shellPrompt := (implicit state => promptTheme.value.render(state))
