package uk.co.desirableobjects.featureswitch.page

import geb.Page
import uk.co.desirableobjects.featureswitch.modules.SwitchModule

class SwitchesPage extends Page {

    static url = 'admin/features'

    static at = {
        title == 'Feature Switches Admin Page'
    }

    static content = {
        switches { index -> moduleList SwitchModule, $('.feature'), index }
    }
}
