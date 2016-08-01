/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.factory("geoFileManagerService", ['$http', '$location', '$rootScope',
    function geoFileManagerService($http, $location, $rootScope) {
        var service = {};

        // data mapping services
        service.appendTable = appendTable;
        service.overrideTable = overrideTable;
        service.uploadFileToUrl = uploadFileToUrl;

        function uploadFileToUrl(file) {
            var fd = new FormData();
            fd.append('file', file);

            $http.post($rootScope.base + 'fileUpload', fd, {
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                })

                .success(function () {
                })

                .error(function () {
                });
        }
        ;

        function appendTable(csvData) {
            return $http.post($rootScope.base + 'appendLocationTable', {data: csvData}).success(function (status) {
                return status.data;
            });
        };

        function overrideTable(csvData) {
            return $http.post($rootScope.base + 'overrideLocationTable', {data: csvData}).success(function (status) {
                return status.data;
            });
        };


        return service;
    }
])
;
