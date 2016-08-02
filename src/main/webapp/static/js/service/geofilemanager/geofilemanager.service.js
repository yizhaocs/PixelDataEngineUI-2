/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.factory("geoFileManagerService", ['$http', '$location', '$rootScope',
    function geoFileManagerService($http, $location, $rootScope) {
        var service = {};

        // data mapping services
        service.getGeo = getGeo;
        service.appendTable = appendTable;
        service.overrideTable = overrideTable;


        function getGeo(mapname) {
            var body = $http.get($rootScope.base + 'geo?mapname=' + mapname);
            return body;
        };

        function appendTable(file, table) {
            var fd = new FormData();
            fd.append('file', file);

            $http.post($rootScope.base + 'appendTable?table=' + table, fd, {
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                })

                .success(function () {
                })

                .error(function () {
                });
        };

        function overrideTable(file, table) {
            var fd = new FormData();
            fd.append('file', file);

            $http.post($rootScope.base + 'overrideTable?table=' + table, fd, {
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                })

                .success(function () {
                })

                .error(function () {
                });
        };

        return service;
    }
]);
