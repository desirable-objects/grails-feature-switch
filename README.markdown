[![Build Status](https://travis-ci.org/aiten/grails-feature-switch.png?branch=master)](https://travis-ci.org/aiten/grails-feature-switch)
Feature Switcher Plugin for Grails
==================================

Configuration
-------------

Features can be turned on and off in your configuration using the following format

```groovy
  features {
    myfeature {
      enabled = true
    }
  }
```

Where myfeature is the name of the feature you are configuring.

Usage in Controllers and Services
---------------------------------

To execute a block of code when the feature 'myfeature' is enabled:

```groovy
  withFeature('myfeature') {
    // do something
  }
```

When the feature 'myfeature' is not enabled:

```groovy
  withoutFeature('myfeature') {
    // do something
  }
```

Usage in GSPs
-------------

To execute some markup when the feature 'myfeature' is enabled:

```html
  <feature:enabled feature="myfeature">
     <h1>The feature is enabled!</h1>
  </feature:enabled>
```

And the same only when the feature is not enabled:

```html
  <feature:disabled feature="myfeature">
    <h1>The feature is disabled!</h1>
  </feature:disabled>
```

Toggling switches via the admin page
------------------------------------

The admin page is accessible at

```
http://localhost:8080/your-app/admin/features
```

Features highlighted in green are enabled, and those in red are disabled. Clicking the 'toggle' button on any feature will disable or enable that particular feature.

If you want to toggle features easily in a test, you can simply hit the url

```
http://localhost:8080/your-app/admin/feature/myfeature
```

which will toggle that feature on or off depending on its current state.

These state changes will only last as long as the application is running, the state is not persisted and is read from configuration on start-up.

Toggling switches via a restful URL
-----------------------------------

The restful urls for enabling and disabling features are:

To enable:

```
http://localhost:8080/feature-switch/admin/features/<your-feature-name>/enable
```

To disable:

```
http://localhost:8080/feature-switch/admin/features/<your-feature-name>/disable
```

The response from these is a JSON result explaining if the feature has been enabled or disabled:

```
{"your-feature-name": "disabled"}
```

Toggling of a non-existent feature will cause a 500 with an IllegalArgumentException including the name of the feature.
