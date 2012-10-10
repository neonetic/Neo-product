define([
    "views/part_metadata_view"
], function (
    PartMetadataView
) {

    var PartItemView = Backbone.View.extend({

        tagName:'li',

        template: _.template("<input type='checkbox' value=''><a href='#'><label class='checkbox'><%= number %></label></a>"),

        events: {
            "click a": "showPartMetadata"
        },

        initialize: function() {
            _.bindAll(this, ["onChangeInput"]);
        },

        onChangeInput: function(e) {
            this.$("input").prop("checked", e.currentTarget.checked);
        },

        render: function() {

            this.$el.html(this.template({number: this.model.attributes.number}));

            this.$el.children("input").on("change", this.onChangeInput);

            if(this.model.isNode()){
                this.$('label').addClass("isNode");
            }

            return this;
        },

        showPartMetadata:function(e) {
            e.stopPropagation();

            $("#part_metadata_container").empty();
            new PartMetadataView({model: this.model}).render();

            $("#bottom_controls_container").hide();
            $("#part_metadata_container").show();
        }

    });

    return PartItemView;

});