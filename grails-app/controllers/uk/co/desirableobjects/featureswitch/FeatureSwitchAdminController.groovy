package uk.co.desirableobjects.featureswitch

import grails.converters.JSON

class FeatureSwitchAdminController {

    def switches() {

        Map<String, Boolean> model = [:]
        features.each { String name, Map<String, Boolean> details ->
            model[name] = details.enabled
        }

        [features: model]
    }

    def toggle(String feature) {
        boolean toggled = !features[feature].enabled
        modifyFeature feature, toggled, {
            redirect action: 'switches'
        }
    }

    def enable(String feature) {
        modifyFeature feature, true, {
            render(text: [(feature): 'enabled'] as JSON)
        }
    }

    def disable(String feature) {
        modifyFeature feature, false, {
            render(text: [(feature): 'disabled'] as JSON)
        }
    }

    private void modifyFeature(String feature, boolean enabled, Closure renderSuccess) {
        if (grailsApplication.config.featuresConfig.allowToggling) {
            features[feature].enabled = enabled
            renderSuccess()
        }
        else {
            response.status = 403
            render "Modifying features at run time has been disabled"
        }
    }

    private Map<String, Map<String, Boolean>> getFeatures() {
        grailsApplication.config.features
    }
}
