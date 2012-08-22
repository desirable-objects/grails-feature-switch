grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.release.scm.enabled = false

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'

    def seleniumVersion = "2.25.0"
    def gebVersion = "0.7.1"

    repositories {
        grailsCentral()
        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        mavenLocal
        mavenCentral()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        test("org.seleniumhq.selenium:selenium-firefox-driver:$seleniumVersion")
        test "org.codehaus.geb:geb-spock:$gebVersion"

    }

    plugins {

        test ':spock:0.6',
             ":geb:$gebVersion"

        build(":tomcat:$grailsVersion",
              ":release:2.0.4") {
            excludes 'http-builder', 'httpcore', 'httpclient'
            export = false
        }

    }
}
