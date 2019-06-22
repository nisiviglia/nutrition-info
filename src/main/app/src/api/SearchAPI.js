
var baseUrl = 'http://192.168.1.5:5000';

module.exports = {

    searchLongName: function (longName) {
        return fetch(baseUrl + '/api/v1/search/name/' + longName)
            .then(response => {
                return response.json();
            })
    },

    searchLongNameAfter: function (longName, after) {
        return fetch(baseUrl + '/api/v1/search/name/' + longName + '?first_result=' + after)
            .then(response => {
                return response.json();
            })
    },

    getProduct: function (ndbNumber) {
        return fetch(baseUrl + '/api/v1/product/ndbnumber/' + ndbNumber)
            .then(response => {
                return response.json();
            })
    }
};
