template=require('./__file_name__ItemViewTemplate.html')

class __File_name__ItemView extends Marionette.ItemView
    template: _.template(template)
    tagName: "div"
    className: ""


module.exports = __File_name__ItemView