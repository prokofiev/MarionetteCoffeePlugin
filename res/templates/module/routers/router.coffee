ModuleRouter = require('module-router')

class Router extends ModuleRouter
    initialize: (option) ->
        @controller = option.controller
        @on "before:enter", ()=>
            if @controller.firstEnter
                @controller.firstEnter()

    moduleRoutes: {
        "": "main"
    }

module.exports = Router