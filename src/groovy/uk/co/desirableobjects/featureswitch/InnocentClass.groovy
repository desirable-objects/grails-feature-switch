package uk.co.desirableobjects.featureswitch

class InnocentClass {

    def testWith() {
        boolean result = false
        withFeature('eggs') {
            result = true
        }
        return result
    }

    def testWithout() {
        boolean result = false
        withoutFeature('eggs') {
            result = true
        }
        return result
    }

    def testWithOverride(boolean override) {
        boolean result = !override
        withFeature('eggs', {
            result = true
        }, [eggs:override])
        return result
    }
}
