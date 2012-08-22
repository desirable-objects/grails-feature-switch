package uk.co.desirableobjects.featureswitch.modules

import geb.Module

class SwitchModule extends Module {

    static content = {

        label      { $('.label') }
        toggle     { $('.toggle') }
        classes     { $().classes() }

    }

    boolean isEnabled() {

        return classes.contains('switched-on')

    }
}
