package uk.co.desirableobjects.featureswitch

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
@TestFor(FeatureSwitchAdminController)
class FeatureSwitchAdminControllerSpec extends Specification {

    def "ability to toggle features can be disabled" (boolean allowToggling, boolean startState, Closure endpoint, boolean endState, int responseCode) {
        given:
        String featureName = "theFeature"
        config.features[featureName].enabled = startState

        and:
        config.featuresConfig.allowToggling = allowToggling

        when:
        endpoint(featureName)

        then:
        response.status == responseCode

        and:
        config.features[featureName].enabled == endState

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
