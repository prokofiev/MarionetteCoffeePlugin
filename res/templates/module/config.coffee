ModuleClass = require './module'

module.exports =
    checkRoles: (roles)->
#        return false if !roles || roles.length == 0
#        return true if roles.indexOf('some_roles')>-1
#        false
        true
    urlPrefix: '__module_name__'

    label: '__module_name__'

    moduleClass: ModuleClass
