package uk.co.desirableobjects.featureswitch

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(FeatureSwitchService)
class FeatureSwitchServiceSpec extends Specification {

    def setup() {
        config.features = [:]
    }

    @Unroll
    def "Returns enabled true/false depending on application config"() {
        given:
        config.features[featureName] = [enabled: featureEnabled]

        when:
        boolean enabled = service.hasFeature(featureName)

        then:
        enabled == expectedOutput

        where:
        featureName | featureEnabled  | expectedOutput
        'f1'        | true            | true
        'f2'        | false           | false
    }

    @Unroll
    def "Returns enabled true/false depending on override if specified, falling back to application config"() {
        given:
        config.features[featureName] = [enabled: featureEnabled]

        when:
        boolean enabled = service.hasFeature(featureName, featureOverride)

        then:
        enabled == expectedOutput

        where:
        featureName | featureEnabled  | expectedOutput  | featureOverride
        'f1'        | false           | true            | ['f1': true]
        'f2'        | true            | false           | ['f2': false]
        'f3'        | true            | true            | null
        'f4'        | true            | true            | [:]
    }

    @Unroll
    def "Executes closure if feature enabled in application config"() {
        given:
        config.features[featureName] = [enabled: featureEnabled]

        when:
        String output
        service.withFeature(featureName) {
            output = 'assigned!'
        }

        then:
        output == expectedOutput

        where:
        featureName | featureEnabled  | expectedOutput
        'f1'        | false           | null
        'f2'        | true            | 'assigned!'
    }

    @Unroll
    def "Executes closure if feature enabled in override, falling back to application config"() {
        given:
        config.features[featureName] = [enabled: featureEnabled]

        when:
        String output
        service.withFeature(featureName, featureOverride) {
            output = 'assigned!'
        }

        then:
        output == expectedOutput

        where:
        featureName | featureEnabled  | expectedOutput  | featureOverride
        'f1'        | false           | 'assigned!'     | ['f1': true]
        'f2'        | true            | null            | ['f2': false]
        'f3'        | true            | 'assigned!'     | null
        'f4'        | true            | 'assigned!'     | [:]
    }

    @Unroll
    def "Executes closure if feature disabled in application config"() {
        given:
        config.features[featureName] = [enabled: featureEnabled]

        when:
        String output
        service.withoutFeature(featureName) {
            output = 'assigned!'
        }

        then:
        output == expectedOutput

        where:
        featureName | featureEnabled  | expectedOutput
        'f1'        | false           | 'assigned!'
        'f2'        | true            | null
    }

    @Unroll
    def "Executes closure if feature disabled in override, falling back to application config"() {
        given:
        config.features[featureName] = [enabled: featureEnabled]

        when:
        String output
        service.withoutFeature(featureName, featureOverride) {
            output = 'assigned!'
        }

        then:
        output == expectedOutput

        where:
        featureName | featureEnabled  | expectedOutput  | featureOverride
        'f1'        | false           | 'assigned!'     | ['f1': false]
        'f2'        | true            | null            | ['f2': true]
        'f3'        | false           | 'assigned!'     | null
        'f4'        | false           | 'assigned!'     | [:]
    }
}
