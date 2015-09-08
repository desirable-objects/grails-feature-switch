package uk.co.desirableobjects.featureswitch

class FeatureSwitchInterceptor {

	FeatureSwitchInterceptor() {
		match(controller: 'featureSwitchAdmin').except(action: 'switches')
	}

	boolean before() {
		String feature = params.feature
		if (!grailsApplication.config.features.containsKey(feature)) {
			throw new IllegalArgumentException("Feature $feature does not exist.")
		}

		true
	}

	boolean after() { true }

	void afterView() {}
}
