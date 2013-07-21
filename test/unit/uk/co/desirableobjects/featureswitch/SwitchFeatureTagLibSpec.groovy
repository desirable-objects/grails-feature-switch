package uk.co.desirableobjects.featureswitch

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(FeatureSwitchTagLib)
class SwitchFeatureTagLibSpec extends Specification {

    void setup() {

        tagLib.featureSwitchService = Mock(FeatureSwitchService)
        pageScope.features = null

    }

    @Unroll
    def 'Render content depending on whether the feature is enabled or not [#enabled]'() {

        when:
            String output = applyTemplate('<feature:enabled feature="colons">Colons!</feature:enabled>')

        then:
            1 * tagLib.featureSwitchService.hasFeature('colons', pageScope.features) >> { return enabled }
            0 * _

        and:
            output == expectedOutput

        where:
            enabled     | expectedOutput
            true        | 'Colons!'
            false       | ''

    }

    @Unroll
    def 'Render content depending on whether the feature is disabled or not [#enabled]'() {

        when:
            String output = applyTemplate('<feature:disabled feature="colons">Colons!</feature:disabled>')

        then:
            1 * tagLib.featureSwitchService.hasFeature('colons', pageScope.features) >> { return enabled }
            0 * _

        and:
            output == expectedOutput

        where:
            enabled     | expectedOutput
            true        | ''
            false       | 'Colons!'

    }

}
