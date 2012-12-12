package uk.co.desirableobjects.featureswitch

class FeatureSwitchAdminController {

	def switches() {

		Map<String, Boolean> model = [:]

		grailsApplication.config.features.each { String name, Map<String, Boolean> details ->
			model.put(name, details.enabled)
		}

		render view: 'switches', model: [features: model]

	}

	def toggle(String feature) {

		grailsApplication.config.features[feature].enabled = !grailsApplication.config.features[feature].enabled
		redirect action: 'switches'

	}
}
