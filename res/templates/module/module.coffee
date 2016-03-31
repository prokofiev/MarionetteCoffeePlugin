AppController = require './controllers/module-controller.coffee'
ModuleRouter = require './routers/router'

class __Module_name__Module extends Marionette.Module
    initialize: (urlPrefix)=>
        @appController = new AppController mainRegion: @app.layout.content
        @router = new ModuleRouter urlPrefix, controller: @appController

module.exports = __Module_name__Module