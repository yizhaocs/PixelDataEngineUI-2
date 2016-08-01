/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.factory("geoFileManagerService", ['$http', '$location', '$rootScope',
    function geoFileManagerService($http, $location, $rootScope) {
        var service = {};

        // data mapping services
/*        service.appendTable = appendTable;
        service.overrideTable = overrideTable;*/
        service.appendTable = appendTable;
        service.overrideTable = overrideTable;

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

      /*  function appendTable(csvData) {
            return $http.post($rootScope.base + 'appendLocationTable', {data: csvData}).success(function (status) {
                return status.data;
            });
        };

        function overrideTable(csvData) {
            return $http.post($rootScope.base + 'overrideLocationTable', {data: csvData}).success(function (status) {
                return status.data;
            });
        };
*/

        return service;
    }
])
;
