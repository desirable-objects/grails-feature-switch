package uk.co.desirableobjects.featureswitch

import spock.lang.Specification

import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Unroll
import grails.test.mixin.TestFor

@Mixin(GrailsUnitTestMixin)
@TestFor(FeatureSwitchService)
class SwitchFeatureSpec extends Specification {

    void setup() {

        InnocentClass.metaClass.withFeature = { String feature, Closure closure, overrides = [:]-> service.withFeature(feature, closure, overrides) }
        InnocentClass.metaClass.withoutFeature = { String feature, Closure closure, overrides = [:] -> service.withoutFeature(feature, closure, overrides) }

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

    def 'checking status for a feature does not add it to config'() {

        when:
            !service.hasFeature('boys')
		then:	
			!service.grailsApplication.config.features.boys

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

    @Unroll
    def 'User can use withoutFeature in a class which is decorated with it, where feature = #enabled'() {

        given:
            service.grailsApplication.config.features.eggs.enabled = enabled

        expect:
            new InnocentClass().testWithout() == !enabled

        where:
            enabled << [true, false]

    }

    @Unroll
    def 'grailsConfiguration can be overriden enabled = #enabled' (boolean enabled) {
        given:
            service.grailsApplication.config.features.eggs.enabled = !enabled
        expect:
            service.hasFeature('eggs', ['eggs': enabled]) == enabled
        and:
            new InnocentClass().testWithOverride(enabled)
        where:
            enabled << [true, false]
    }

}
