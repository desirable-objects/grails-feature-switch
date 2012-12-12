package uk.co.desirableobjects.featureswitch

import grails.plugin.spock.IntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class SwitchFeatureDecorationSpec extends IntegrationSpec {

    @Autowired
    FeatureSwitchingDummyController featureSwitchingDummyController
    FeatureSwitchTestingService featureSwitchTestingService

    def 'User can use withFeature in a class which is decorated with it'() {

        when:
            featureSwitchingDummyController.alwaysOn()

        then:
            featureSwitchingDummyController.response.text == 'this works'

        expect:
            featureSwitchTestingService.withFeature() == true

    }

    def 'User can use withoutFeature in a class which is decorated with it'() {

        when:
            featureSwitchingDummyController.alwaysOff()

        then:
            featureSwitchingDummyController.response.text == 'this works'

        expect:
            featureSwitchTestingService.withoutFeature() == true

    }

}
