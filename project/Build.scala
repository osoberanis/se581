import sbt._
import Keys._
import PlayProject._
import cloudbees.Plugin._
import com.github.play2war.plugin._

object ApplicationBuild extends Build {

    val appName         = "se581"
    val appVersion      = "0.0.1"

   val appDependencies = Seq(
      "commons-validator" % "commons-validator" % "1.4.0",
      "org.apache.poi" % "poi" % "3.9",
      "org.apache.poi" % "poi-ooxml" % "3.9",
	  "xml-apis" % "xml-apis" % "2.0.2"
    )
    
    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA, settings = Defaults.defaultSettings) 
    		.settings(cloudBeesSettings :_*)
    		.settings(CloudBees.applicationId := Some("<se581>"))
}
