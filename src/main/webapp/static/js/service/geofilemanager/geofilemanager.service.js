/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.factory("geoFileManagerService", ['$http', '$location', '$rootScope',
    function geoFileManagerService($http, $location, $rootScope) {
        var service = {};

        // data mapping services
        service.getPixelDataEngineMaps = getPixelDataEngineMaps;
        service.getPdeMap = getPdeMap;
        service.appendTable = appendTable;
        service.overrideTable = overrideTable;


        function getPixelDataEngineMaps() {
            var body = $http.get($rootScope.base + 'getPixelDataEngineMaps');
            return body;
        };

        function getPdeMap(mapname) {
            var body = $http.get($rootScope.base + 'getPdeMap?mapname=' + mapname);
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
                    alert("Table appended successfully");
                })

                .error(function () {
                    alert("Table appended failed");
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
                    alert("Table overrided successfully");
                })

                .error(function () {
                    alert("Table overrided failed");
                });
        };

        return service;
    }
]);
