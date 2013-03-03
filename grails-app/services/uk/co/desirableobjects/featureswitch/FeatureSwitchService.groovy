package uk.co.desirableobjects.featureswitch

import org.codehaus.groovy.grails.commons.GrailsApplication

class FeatureSwitchService {

    static transactional = false

    GrailsApplication grailsApplication

    boolean hasFeature(String feature) {

        return grailsApplication.config.features[feature] && grailsApplication.config.features[feature].enabled

    }

    def withFeature(String feature, Closure closure) {

        executeFeatureConditionally(feature, true, closure)

    }

    private executeFeatureConditionally(String feature, boolean condition, Closure closure) {
        if (hasFeature(feature) == condition) {
            closure()
        }
    }

    def withoutFeature(String feature, Closure closure) {

        executeFeatureConditionally(feature, false, closure)

    }

}
