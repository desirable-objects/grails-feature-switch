package uk.co.desirableobjects.featureswitch

import grails.converters.JSON

class FeatureSwitchAdminController {

    def beforeInterceptor = [action: this.&validateFeature, except: 'switches']

    private validateFeature() {

        String feature = params.feature
        if (!grailsApplication.config.features.containsKey(feature)) {
            throw new IllegalArgumentException("Feature ${feature} does not exist.")
        }

    }

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

    def enable(String feature) {
        grailsApplication.config.features[feature].enabled = true
        render text: [(feature): 'enabled'] as JSON
    }

    def disable(String feature) {
        grailsApplication.config.features[feature].enabled = false
        render text: [(feature): 'disabled'] as JSON
    }
}
