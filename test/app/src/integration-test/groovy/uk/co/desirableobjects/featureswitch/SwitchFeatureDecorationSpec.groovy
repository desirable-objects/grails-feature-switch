package uk.co.desirableobjects.featureswitch

import javax.servlet.ServletContext

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse

import grails.core.GrailsApplication
import grails.test.mixin.integration.Integration
import grails.util.GrailsWebMockUtil
import spock.lang.Specification

@Integration
class SwitchFeatureDecorationSpec extends Specification {

	@Autowired FeatureSwitchingDummyController featureSwitchingDummyController
	FeatureSwitchTestingService featureSwitchTestingService
	GrailsApplication grailsApplication
	ServletContext servletContext

	private MockHttpServletRequest request
	private MockHttpServletResponse response

	void setup() {
		request = new MockHttpServletRequest(servletContext, 'GET', '')
		response = new MockHttpServletResponse()
		GrailsWebMockUtil.bindMockWebRequest grailsApplication.mainContext, request, response
	}

	def 'User can use withFeature in a class which is decorated with it'() {
		when:
		featureSwitchingDummyController.alwaysOn()

		then:
		response.contentAsString == 'this works'

		expect:
		featureSwitchTestingService.withFeature() == true
	}

	def 'User can use withoutFeature in a class which is decorated with it'() {
		when:
		featureSwitchingDummyController.alwaysOff()

		then:
		response.contentAsString == 'this works'

		expect:
		featureSwitchTestingService.withoutFeature() == true
	}
}
