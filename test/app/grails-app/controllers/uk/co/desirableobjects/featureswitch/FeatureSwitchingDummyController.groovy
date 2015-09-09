package uk.co.desirableobjects.featureswitch

class FeatureSwitchingDummyController {

    def alwaysOn() {
        withFeature('alwaysOn') {
            render(text: 'this works')
        }
        withoutFeature('alwaysOn') {
            render(text: "this doesn't work")
        }
    }

    def alwaysOff() {
        withFeature('alwaysOff') {
            render(text: "this doesn't work")
        }
        withoutFeature('alwaysOff') {
            render(text: 'this works')
        }
    }
}
