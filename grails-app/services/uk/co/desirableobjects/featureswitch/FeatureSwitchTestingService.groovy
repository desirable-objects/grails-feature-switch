package uk.co.desirableobjects.featureswitch

class FeatureSwitchTestingService {

    static transactional = false

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
