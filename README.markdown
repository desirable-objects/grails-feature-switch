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