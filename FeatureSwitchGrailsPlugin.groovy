class FeatureSwitchGrailsPlugin {

    def version = "0.1"
    def grailsVersion = "2.0 > *"
    def dependsOn = [:]

    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    def title = "Feature Switch Plugin"
    def author = "Antony Jones and Matt Tortolani"
    def authorEmail = ""
    def description = 'Allows turning on and off of features'

    def documentation = "https://github.com/aiten/grails-feature-switch"

    def license = "APACHE"

    def developers = [
        [ name: "Antony Jones", email: "aj/desirableobjects.co.uk" ],
        [ name: "Matt Tortolani", email:  "hello/doodlemoonch.com", ]
    ]

    def issueManagement = [ system: "WEB", url: "https://github.com/aiten/grails-feature-switch" ]

    def scm = [ url: "https://github.com/aiten/grails-feature-switch" ]

    def doWithApplicationContext = { applicationContext ->

        for (controllerClass in application.controllerClasses) {
            controllerClass.metaClass.withFeature = { String feature, Closure closure ->
                applicationContext.featureSwitchService.withFeature(feature, closure)
            }
            controllerClass.metaClass.withoutFeature = { String feature, Closure closure ->
                applicationContext.featureSwitchService.withoutFeature(feature, closure)
            }
        }

    }

}
