
var searchStore = {};

var SearchStore = {
    
    set: function(state) {
        searchStore = state; 
    },

    get: function() {
        return searchStore;    
    }
}

module.exports = SearchStore;
