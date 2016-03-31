MainLayout = require("side-panels-layout")

class AppController extends Marionette.Controller

    initialize: (options) =>
        @mainRegion = options.mainRegion

    main: ()->
        console.log('Hello it is __module_name__!!!')

    firstEnter: ()->
        @init()

    init: ()->
        @mainLayout = new MainLayout right: false
        @mainRegion.show @mainLayout



module.exports = AppController