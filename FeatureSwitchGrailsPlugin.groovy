class FeatureSwitchGrailsPlugin {

    def version = "0.7.SENSIS.3"
    def grailsVersion = "2.0 > *"
    def dependsOn = [:]
    def observe = ["controllers", "services"]

    def pluginExcludes = [
        "grails-app/views/error.gsp",
        "grails-app/services/uk/co/desirableobjects/featureswitch/FeatureSwitchTestingService.groovy",
        "grails-app/controllers/uk/co/desirableobjects/featureswitch/FeatureSwitchingDummyController.groovy"
    ]

    def title = "Feature Switch Plugin"
    def author = "Antony Jones, Matt Tortolani, Tomas Lin, Marcin Erdmann"
    def authorEmail = ""
    def description = 'Allows turning on and off of features'

    def documentation = "https://github.com/aiten/grails-feature-switch/blob/master/README.markdown"

    def license = "APACHE"

    def developers = [
        [ name: "Antony Jones", email: "aj@dsrbl.dudmail.com" ],
        [ name: "Matt Tortolani", email: "matt@dsrbl.dudmail.com"],
        [ name: "Tomas Lin" ],
        [ name: "Marcin Erdmann"]
    ]

    def issueManagement = [ system: "WEB", url: "https://github.com/aiten/grails-feature-switch" ]

    def scm = [ url: "https://github.com/aiten/grails-feature-switch" ]

    def doWithDynamicMethods = { ctx ->
        application.controllerClasses.each { decorate(it.clazz, application) }
        application.serviceClasses.findAll {
            it.name != "FeatureSwitch"
        }.each { decorate(it.clazz, application) }
    }
    
    def onChange = { event ->
        if (application.isControllerClass(event.source) || application.isServiceClass(event.source)) {
            decorate(event.source, application)
        }
    }

    private void decorate(Class c, def application) {
        def ctx = application.mainContext
        c.metaClass.withFeature = { String feature, Closure closure ->
            ctx.featureSwitchService.withFeature(feature, closure)
        }
        c.metaClass.withoutFeature = { String feature, Closure closure ->
            ctx.featureSwitchService.withoutFeature(feature, closure)
        }
    }

}
