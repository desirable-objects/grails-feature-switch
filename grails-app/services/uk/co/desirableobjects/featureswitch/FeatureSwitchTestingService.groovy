package uk.co.desirableobjects.featureswitch

class FeatureSwitchTestingService {

    Boolean withFeature() {

        withFeature("alwaysOn") {
            return true
        }

    }

    Boolean withoutFeature() {

        withoutFeature("alwaysOff") {
            return true
        }

    }
}
