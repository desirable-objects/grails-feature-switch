package uk.co.desirableobjects.featureswitch

import org.codehaus.groovy.grails.commons.GrailsApplication

class FeatureSwitchService {

    GrailsApplication grailsApplication

    boolean hasFeature(String feature) {

        return grailsApplication.config.features[feature].enabled

    }

    def withFeature(String feature, Closure closure) {

        if (hasFeature(feature)) {
            closure()
        }

    }

}
