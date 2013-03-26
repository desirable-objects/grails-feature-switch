package uk.co.desirableobjects.featureswitch

import spock.lang.Specification
import grails.test.mixin.TestFor
import spock.lang.Unroll

@Unroll
@TestFor(FeatureSwitchAdminController)
class FeatureSwitchAdminControllerSpec extends Specification {

    def "ability to toggle features can be disabled" (boolean allowToggling, boolean startState, Closure endpoint, boolean endState, int responseCode) {
        given:
            def featureName = "theFeature"
            grailsApplication.config.features[featureName].enabled = startState
        and:
            grailsApplication.config.featuresConfig.allowToggling = allowToggling
        when:
            endpoint(featureName)
        then:
            response.status == responseCode
        and:
            grailsApplication.config.features[featureName].enabled == endState
        where:
            allowToggling | startState | endpoint                 | endState | responseCode
            false         | true       | {controller.disable(it)} | true     | 403
            false         | false      | {controller.enable(it)}  | false    | 403
            false         | true       | {controller.toggle(it)}  | true     | 403
            true          | true       | {controller.disable(it)} | false    | 200
            true          | false      | {controller.enable(it)}  | true     | 200
            true          | true       | {controller.toggle(it)}  | false    | 302
    }
}
