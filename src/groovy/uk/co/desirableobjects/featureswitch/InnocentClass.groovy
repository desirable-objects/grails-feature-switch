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

}
