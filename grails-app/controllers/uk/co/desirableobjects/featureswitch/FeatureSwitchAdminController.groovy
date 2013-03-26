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

    private def modifyFeature(String feature, boolean enabled, Closure renderSuccess) {
        if (grailsApplication.config.featuresConfig.allowToggling) {
            grailsApplication.config.features[feature].enabled = enabled
            renderSuccess()
        } else {
            response.status = 403
            render "Modifying features at run time has been disabled"
        }
    }

	def toggle(String feature) {
        boolean toggled = !grailsApplication.config.features[feature].enabled
        modifyFeature feature, toggled, {
            redirect action: 'switches'
        }
	}

    def enable(String feature) {
        modifyFeature feature, true, {
            render text: [(feature): 'enabled'] as JSON
        }
    }

    def disable(String feature) {
        modifyFeature feature, false, {
            render text: [(feature): 'disabled'] as JSON
        }
    }
}
