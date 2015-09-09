package uk.co.desirableobjects.featureswitch

import grails.plugins.Plugin

class FeatureSwitchGrailsPlugin extends Plugin {

	def grailsVersion = '3.0.0 > *'
	def observe = ["controllers", "services"]
	def title = "Feature Switch Plugin"
	def description = 'Allows turning on and off of features'
	def documentation = "https://github.com/aiten/grails-feature-switch/blob/master/README.markdown"
	def license = "APACHE"
	def developers = [
		[name: "Antony Jones", email: "aj@dsrbl.dudmail.com"],
		[name: "Burt Beckwith"],
		[name: "Matt Tortolani", email: "matt@dsrbl.dudmail.com"],
		[name: "Tomas Lin"],
		[name: "Marcin Erdmann"]
	]
	def issueManagement = [url: "https://github.com/antony/grails-feature-switch/issues"]
	def scm = [url: "https://github.com/antony/grails-feature-switch"]

	void doWithDynamicMethods() {
		grailsApplication.controllerClasses.each { decorate(it.clazz) }
		grailsApplication.serviceClasses.findAll { it.name != "FeatureSwitch" }.each { decorate(it.clazz) }
	}

	void onChange(Map<String, Object> event) {
		if (grailsApplication.isControllerClass(event.source) || grailsApplication.isServiceClass(event.source)) {
			decorate(event.source)
		}
	}

	private void decorate(Class c) {
		c.metaClass.withFeature = { String feature, Closure closure ->
			applicationContext.featureSwitchService.withFeature(feature, closure)
		}
		c.metaClass.withoutFeature = { String feature, Closure closure ->
			applicationContext.featureSwitchService.withoutFeature(feature, closure)
		}
	}
}
