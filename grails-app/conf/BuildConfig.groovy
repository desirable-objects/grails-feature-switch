grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.release.scm.enabled = false

version {
    spock = '0.7'
    geb = '0.9.0'
    selenium = '2.33.0'
}

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'

    repositories {
        grailsCentral()
        mavenLocal
        mavenCentral()
        mavenRepo 'https://oss.sonatype.org/content/repositories/snapshots'
    }
    dependencies {

        test "org.seleniumhq.selenium:selenium-firefox-driver:${version.selenium}",
             "org.gebish:geb-spock:${version.geb}",
             "org.spockframework:spock-grails-support:${version.spock}-groovy-2.0", {
            export = false
        }

	    build 'org.codehaus.groovy.modules.http-builder:http-builder:0.6'
    }

    plugins {

        test ":spock:${version.spock}", {
            exclude "spock-grails-support"
            export = false
        }

        test ":geb:${version.geb}", {
            export = false
        }

        build ":tomcat:$grailsVersion",
		      ":rest-client-builder:1.0.3",
              ":release:2.2.0", {
            export = false
        }

    }
}
