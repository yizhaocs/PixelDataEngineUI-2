/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.factory("geoFileManagerService", ['$http', '$location', '$rootScope',
    function geoFileManagerService($http, $location, $rootScope) {
        var service = {};

        service.getPixelDataEngineMaps = getPixelDataEngineMaps;
        service.getPdeMap = getPdeMap;
        service.downloadTheMap = downloadTheMap;
        service.appendTable = appendTable;
        service.overrideTable = overrideTable;
        service.updateLoadingInProgress = updateLoadingInProgress;
        service.createPixelDataEngineMap = createPixelDataEngineMap;
        service.deletePixelDataEngineMap = deletePixelDataEngineMap;
        service.updatePixelDataEngineMap = updatePixelDataEngineMap;
        service.getPixelDataEngineMap = getPixelDataEngineMap;

        function createPixelDataEngineMap(redirectPath, file, frontendData) {
            var fd = new FormData();
            fd.append('file', file);
            return $http.post($rootScope.base + 'createPixelDataEngineMap', {
                    mapName: frontendData.map_name,
                    description: frontendData.description

                })
                .success(function () {
                    alert("New Geo Map Created Successfully");
                    if (file != undefined) {
                        $http.post($rootScope.base + 'appendTable?table=' + "pde_map_" + frontendData.map_name + '&appendWhenCreatingTable=' + 'yes', fd, {
                                transformRequest: angular.identity,
                                headers: {'Content-Type': undefined}
                            })

                            .success(function () {
                                alert("Table Appended Successfully");
                                $location.path(redirectPath);
                            })

                            .error(function () {
                                alert("Table Appended Failed");
                                $location.path(redirectPath);
                            });
                    } else {
                        $location.path(redirectPath);
                    }

                })

                .error(function () {
                    alert("New Geo Map Created Failed");
                });
        };

        function updateLoadingInProgress(loadingInProgress, map_name) {
            return $http.put($rootScope.base + 'updateLoadingInProgress', {
                    loading_in_progress: loadingInProgress,
                    map_name: map_name

                })
                .success(function () {
                })
                .error(function () {
                });
        }

        function deletePixelDataEngineMap(redirectPath, mapName) {
            return $http.delete($rootScope.base + 'deletePixelDataEngineMap?mapname=' + mapName)
                .success(function () {
                    alert("Map Deleted Successfully");
                    $location.path(redirectPath);
                })
                .error(function () {
                    alert("Map Deleted Failed");
                });
        };

        function updatePixelDataEngineMap(redirectPath, frontendData) {
            return $http.put($rootScope.base + 'updatePixelDataEngineMap', {
                    mapName: frontendData.map_name,
                    description: frontendData.description

                })
                .success(function () {
                    alert("Geo Map Updated Successfully");
                    $location.path(redirectPath);
                })
                .error(function () {
                    alert("Geo Map Updated Failed");
                });
        }

        function getPixelDataEngineMaps() {
            return $http.get($rootScope.base + 'getPixelDataEngineMaps');
        };

        function getPixelDataEngineMap(mapname) {
            return $http.get($rootScope.base + 'getPixelDataEngineMap?mapname=' + mapname);
        };

        function getPdeMap(mapname) {
            return $http.get($rootScope.base + 'getPdeMap?mapname=' + mapname);

        };

        function downloadTheMap(mapname) {
            return $http.get($rootScope.base + 'downloadTheMap?mapname=' + mapname);

        };

        function appendTable(redirectPath, file, table) {
            var fd = new FormData();
            fd.append('file', file);

            $http.post($rootScope.base + 'appendTable?table=' + table + '&appendWhenCreatingTable=' + 'no', fd, {
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                })

                .success(function () {
                    updateLoadingInProgress(false, table.substring(8, table.length)).success(function () {
                        alert("Table Appended Successfully");
                        $location.path(redirectPath);
                    }).error(function () {
                        updateLoadingInProgress(false, table.substring(8, table.length));
                        alert("Table Appended Successfully");
                        $location.path(redirectPath);
                    });

                })

                .error(function () {
                    updateLoadingInProgress(false, table.substring(8, table.length)).success(function () {
                        alert("Table Appended Failed");
                        $location.path(redirectPath);
                    }).error(function () {
                        updateLoadingInProgress(false, table.substring(8, table.length));
                        alert("Table Appended Failed");
                        $location.path(redirectPath);
                    });
                });
        };

        function overrideTable(redirectPath, file, table) {
            var fd = new FormData();
            fd.append('file', file);
            var mapName = table.substring(8, table.length);

            $http.post($rootScope.base + 'overrideTable?table=' + table, fd, {
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                })

                .success(function () {
                    updateLoadingInProgress(false, mapName).success(function () {
                        alert("Table overrided Successfully");
                        $location.path(redirectPath);
                    }).error(function () {
                        updateLoadingInProgress(false, mapName);
                        alert("Table overrided Successfully");
                        $location.path(redirectPath);
                    });

                })

                .error(function () {
                    updateLoadingInProgress(false, mapName).success(function () {
                        alert("Table overrided Failed");
                        $location.path(redirectPath);
                    }).error(function () {
                        updateLoadingInProgress(false, mapName);
                        alert("Table overrided Failed");
                        $location.path(redirectPath);
                    });
                });


            /*
             $http.post($rootScope.base + 'overrideTable?table=' + table, fd, {
             transformRequest: angular.identity,
             headers: {'Content-Type': undefined}
             })

             .success(function () {
             alert("Table overrided successfully");
             $location.path(redirectPath);
             })

             .error(function () {
             alert("Table overrided failed");
             $location.path(redirectPath);
             });*/

        };

        return service;
    }
]);
