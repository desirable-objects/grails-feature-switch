package uk.co.desirableobjects.featureswitch

import grails.core.GrailsApplication

class FeatureSwitchService {

    static transactional = false

    GrailsApplication grailsApplication

    boolean hasFeature(String feature, Map<String, Boolean> overrides = null) {
        overrides?.containsKey(feature) ? overrides[feature] : (conf[feature] && conf[feature].enabled)
    }

    def withFeature(String feature, overrides = null, Closure closure) {
        executeFeatureConditionally(feature, true, closure, overrides)
    }

    def withoutFeature(String feature, overrides = null, Closure closure) {
        executeFeatureConditionally(feature, false, closure, overrides)
    }

    private executeFeatureConditionally(String feature, boolean condition, Closure closure, overrides = null) {
        if (hasFeature(feature, overrides) == condition) {
            closure()
        }
    }

    private getConf() {
        grailsApplication.config.features
    }
}
