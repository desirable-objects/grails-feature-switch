package uk.co.desirableobjects.featureswitch

import geb.spock.GebReportingSpec
import grails.test.mixin.integration.Integration
import spock.lang.Stepwise
import spock.lang.Unroll
import uk.co.desirableobjects.featureswitch.page.SwitchesPage

@Integration
@Stepwise
class AdminSwitchesSpec extends GebReportingSpec {

    void setup() {
        to SwitchesPage
    }

    def 'a user can visit the switches page'() {
        expect:
        at SwitchesPage
    }

    def 'a user can view the current state of the switches'() {
        expect:
        switches.size() == 3
        switches(0).label.text() == 'alwaysOn'
        switches(0).enabled
        switches(1).label.text() == 'alwaysOff'
        !switches(1).enabled
        switches(2).label.text() == 'toggle_me'
        switches(2).enabled
    }

    def 'a user can modify the state of a switch'() {
        expect:
        switches(2).label.text() == 'toggle_me'
        switches(2).enabled

        when:
        switches(2).toggle.click()

        then:
        at SwitchesPage
        !switches(2).enabled

        when:
        switches(2).toggle.click()

        then:
        at SwitchesPage
        switches(2).enabled
    }

    def 'a user can modify a switch state by hitting a url'() {
        expect:
        switches(2).label.text() == 'toggle_me'
        switches(2).enabled

        when:
        to SwitchesPage, 'toggle', feature: 'toggle_me'

        and:
        to SwitchesPage

        then:
        switches(2).label.text() == 'toggle_me'
        !switches(2).enabled
    }

    @Unroll
    def 'a user can #action a feature by simply hitting an endpoint'() {
        given:
        String url = browser.baseUrl + SwitchesPage.url + '/toggle_me/' + action

        when:
        String response = new URL(url).text

        then:
        response == """{"toggle_me":"${action}d"}"""

        where:
        action << ['enable', 'disable']
    }

    def 'a user cannot toggle an unknown feature'() {
        given:
        String url = browser.baseUrl + SwitchesPage.url + '/non-existent/enable'

        when:
        new URL(url).text

        then:
        thrown IOException
    }
}
