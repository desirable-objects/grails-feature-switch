class FeatureSwitchUrlMappings {

	static mappings = {
		"/admin/features"(controller: 'featureSwitchAdmin', action: 'switches')
        "/admin/features/toggle"(controller: 'featureSwitchAdmin', action: 'toggle')
        "/admin/features/$feature/$action"(controller: 'featureSwitchAdmin')
	}
}
