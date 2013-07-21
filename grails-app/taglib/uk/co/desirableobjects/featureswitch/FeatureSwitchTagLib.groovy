package uk.co.desirableobjects.featureswitch

class FeatureSwitchTagLib {

    static namespace = 'feature'

    FeatureSwitchService featureSwitchService

    def enabled = { attrs, body ->

        renderByFeature(attrs, true, body)

    }

    private renderByFeature(Map attrs, boolean condition, Closure body) {

        String feature = attrs.feature

        if (featureSwitchService.hasFeature(feature, pageScope.features) == condition) {
            out << body()
        }
    }

    def disabled = { attrs, body ->

        renderByFeature(attrs, false, body)

    }

}
