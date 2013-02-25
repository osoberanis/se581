// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("play" % "sbt-plugin" % "2.0.4")

resolvers += "Play2war plugins release" at "http://repository-play-war.forge.cloudbees.com/release/"

addSbtPlugin("com.github.play2war" % "play2-war-plugin" % "0.8.1")

resolvers += "Sonatype OSS Snasphots" at "https://oss.sonatype.org/content/repositories/snapshots"

addSbtPlugin("com.cloudbees.deploy.play" % "sbt-cloudbees-play-plugin" % "0.3-SNAPSHOT")

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.1.1")