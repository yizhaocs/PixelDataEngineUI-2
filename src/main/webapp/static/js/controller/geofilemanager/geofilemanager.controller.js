/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listGeoMapsController', function ($routeParams, $rootScope, $scope, geoFileManagerService) {
    geoFileManagerService.getPixelDataEngineMaps().success(function (backendData) {
        $scope.frontendData = backendData.list;
    });
});


app.controller('getPdeMapController', function ($routeParams, $rootScope, $scope, $location, geoFileManagerService) {
    var mapName = $routeParams.tableName;
    $rootScope.title = "Export the " + $routeParams.tableName + " table";
    geoFileManagerService.createCSVFromTable("pde_map_" + mapName);
});


app.controller('editGeoMapController', function ($scope, $rootScope, $location, $routeParams, geoFileManagerService, backendData) {
    var mapName = ($routeParams.mapname != 0) ? $routeParams.mapname : 0;
    var tableName = 'pde_map_' + mapName;
    var action = ($routeParams.action != 0) ? $routeParams.action : 0;
    if (action == 'create') {
        $scope.showFileLocation = true;
        $scope.isCreate = true;
        $scope.isUpdate = false;
        $scope.isUpload = false;
        $scope.disableDescription = false;
        $scope.mapNameDisable = false;
        $rootScope.title = 'Add New Geo Map';
        $scope.editMapButtonText = 'Add New Geo Map';


    } else if (action == 'edit') {
        $scope.showFileLocation = false;
        $scope.isCreate = true;
        $scope.isUpdate = true;
        $scope.isUpload = false;
        $scope.disableDescription = false;
        $scope.editMapButtonText = 'Update Geo Map';
        $scope.mapNameDisable = true;
        $rootScope.title = 'Edit Geo Map';
        $scope.fileProcessButtonText = 'Append the Table'
    } else if (action == 'append') {
        $scope.showFileLocation = true;
        $scope.isUpdate = false;
        $scope.isUpload = true;
        $scope.disableDescription = true;
        $scope.mapNameDisable = true;
        $rootScope.title = 'Append Geo Map';
        $scope.fileProcessButtonText = 'Append the Table'
    } else if (action == 'override') {
        $scope.showFileLocation = true;
        $scope.isUpdate = false;
        $scope.isUpload = true;
        $scope.disableDescription = true;
        $scope.mapNameDisable = true;
        $rootScope.title = 'Override Geo Map';
        $scope.fileProcessButtonText = 'Override the Table'
    }

    $scope.frontendData = backendData.data;

    $scope.savePixelDataEngineMap = function (frontendData) {
        if ($scope.editMapButtonText == 'Add New Geo Map') {
            if (frontendData.map_name.indexOf('-') > -1) {
                alert("Failed to create the map, please make sure the Map Name doesn't contains '-'");
                return;
            }

            var file = $scope.myFile;
            geoFileManagerService.createPixelDataEngineMap($rootScope.base + 'geo-file-manager', file, frontendData);
        } else if ($scope.editMapButtonText == 'Update Geo Map') {
            geoFileManagerService.updatePixelDataEngineMap($rootScope.base + 'geo-file-manager', frontendData);
        }
    };

    $scope.deletePixelDataEngineMap = function (map_name) {
        geoFileManagerService.deletePixelDataEngineMap($rootScope.base + 'geo-file-manager', map_name);
    };


    $scope.fileProcess = function () {
        geoFileManagerService.getPixelDataEngineMap(mapName).success(function (backendData) {
            var isLoadingInProgress = backendData.loading_in_progress;

            /*
             * true to forbidden user from append/override file without waiting for the previous operation
             * */
            if (isLoadingInProgress) {
                var alertMessage = "previous file loading operation is still in progress, please wait";

                if (action == 'append') {
                    alertMessage = "Append file failed because " + alertMessage;
                } else {
                    alertMessage = "Override file failed because " + alertMessage;
                }

                alert(alertMessage);
                return;
            }

            if (action == 'append') {
                geoFileManagerService.updateLoadingInProgress(true, mapName)
                    .success(function () {
                        geoFileManagerService.appendTable($rootScope.base + 'geo-file-manager', $scope.myFile, tableName);
                    })
                    .error(function () {
                    });

            } else if (action == 'override') {
                geoFileManagerService.updateLoadingInProgress(true, mapName)
                    .success(function () {
                        geoFileManagerService.overrideTable($rootScope.base + 'geo-file-manager', $scope.myFile, tableName);
                    })
                    .error(function () {
                    });


                // geoFileManagerService.overrideTable($rootScope.base + 'geo-file-manager',$scope.myFile, tableName);
            }
        });
    };
});


app.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function () {
                scope.$apply(function () {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

