template = require('./__file_name__CompositeTemplate.html')
__File_name__ItemView = require('./__file_name__ItemView')

class __File_name__CompositeView extends Marionette.CompositeView
    template: _.template(template)
    tagName: "div"
    className: ""
    childViewContainer: ""
    childView: __File_name__ItemView
    reorderOnSort: true

    initialize: (options) ->


module.exports = __File_name__CompositeView