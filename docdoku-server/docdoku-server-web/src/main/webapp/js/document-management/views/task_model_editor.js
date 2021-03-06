define([
    "i18n!localization/nls/document-management-strings",
    "models/task_model",
    "text!templates/task_model_editor.html"
], function (
    i18n,
    TaskModel,
    template
    ) {
    var TaskModelEditorView = Backbone.View.extend({

        tagName: "li",

        className: "task-section",

        events: {
            "click button.delete-task" : "deleteTaskAction",
            "click p.task-name" : "gotoUnfoldState",
            "click i.icon-minus" : "gotoFoldState",
            "change input.task-name" : "titleChanged",
            "change textarea.instructions" : "instructionsChanged",
            "change select.worker": "workerSelected"
        },

        States : {
            FOLD: 0,
            UNFOLD: 1
        },

        initialize: function () {
            var self = this;

            this.state = this.States.FOLD;

            if(_.isUndefined(this.model.get("worker"))){
                this.model.set({
                    worker: this.options.users.at(0)
                });
            }

            var users = [];
            _.each(this.options.users.models, function(user){
                if(self.model.get("worker") && self.model.get("worker").get("name") == user.get("name")){
                    users.push({name: user.get("name"), selected: true});
                }
                else{
                    users.push({name: user.get("name"), selected: false});
                }
            });

            this.template = Mustache.render(template, {cid: this.model.cid, task: this.model.attributes, users: users, i18n: i18n});
        },

        deleteTaskAction: function(){
            this.model.collection.remove(this.model);
            this.unbindAllEvents();
            this.remove();
        },

        titleChanged: function(e){
          this.model.set({
              title: this.inputTitle.val()
          });
          if(this.inputTitle.val().length == 0){
            this.pTitle.html(i18n.TASK_NAME_PLACEHOLDER);
          }
          else{
            this.pTitle.html(this.inputTitle.val());
          }
        },

        instructionsChanged: function(){
            this.model.set({
                instructions: this.textareaInstructions.val()
            });
        },

        workerSelected: function(e){
            var nameSelected = e.target.value;
            var userSelected = _.find(this.options.users.models, function(user){
                return nameSelected == user.get("name");
            });
            this.model.set({
                worker: userSelected
            });
        },

        gotoFoldState: function() {
            this.state = this.States.FOLD;
            this.divTask.removeClass("unfold");
            this.divTask.addClass("fold");
            this.inputTitle.prop('readonly', true);
        },

        gotoUnfoldState: function() {
            this.state = this.States.UNFOLD;
            this.divTask.removeClass("fold");
            this.divTask.addClass("unfold");
            this.inputTitle.prop('readonly', false);
        },

        render: function() {
            this.$el.html(this.template);

            this.bindDomElements();

            return this;
        },

        bindDomElements: function(){
            this.pTitle = this.$("p.task-name");
            this.inputTitle = this.$('input.task-name');
            this.textareaInstructions = this.$('textarea.instructions');
            this.divTask = this.$('div.task');
        },

        unbindAllEvents: function(){
            this.undelegateEvents();
        }

    });
    return TaskModelEditorView;
});
