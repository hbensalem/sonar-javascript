define(function () {

  return Backbone.Model.extend({
    idAttribute: 'property',

    defaults: {
      enabled: false
    },

    getValues: function () {
      return this.get('values') || [];
    },

    toggle: function () {
      var enabled = this.get('enabled');
      return this.set({ enabled: !enabled });
    }
  });

});
