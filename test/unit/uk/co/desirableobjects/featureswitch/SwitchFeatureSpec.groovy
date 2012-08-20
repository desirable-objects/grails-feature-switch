package uk.co.desirableobjects.featureswitch

import spock.lang.Specification

import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Unroll
import grails.test.mixin.TestFor

@Mixin(GrailsUnitTestMixin)
@TestFor(FeatureSwitchService)
class SwitchFeatureSpec extends Specification {

    void setup() {

        InnocentClass.metaClass.withFeature = { String feature, Closure closure -> service.withFeature(feature, closure) }

    }

    @Unroll
    def 'A feature can be set to enabled = #enabled'() {

        given:
            service.grailsApplication.config.features.eggs.enabled = enabled

        expect:
            service.hasFeature('eggs') == enabled

        where:
            enabled << [true, false]

    }

    def 'When there is no configuration'() {

        given:
            service.grailsApplication.config = [:]

        expect:
            !service.hasFeature('peas')

    }

    def 'When a requested feature does not exist'() {

        given:
            service.grailsApplication.config.features.eggs.enabled = true

        expect:
            !service.hasFeature('dogs')

    }

    @Unroll
    def 'User can use withFeature in a class which is decorated with it, where feature = #enabled'() {

        given:
            service.grailsApplication.config.features.eggs.enabled = enabled

        expect:
            new InnocentClass().testWith() == enabled

        where:
            enabled << [true, false]

    }

}
