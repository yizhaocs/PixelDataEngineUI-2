/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listGeoMapsController', function ($routeParams, $rootScope, $scope, geoFileManagerService) {
    geoFileManagerService.getPixelDataEngineMaps().success(function (backendData) {
        $scope.frontendData = backendData.list;
    });

/*    $scope.exportTable = function(mapName) {
        $scope.filename = mapName + '.csv';
        geoFileManagerService.getPdeMap(mapName).success(function (backendData) {
            $scope.getArray = backendData.list;
        });
    }*/
});


app.controller('getPdeMapController', function ($routeParams, $rootScope, $scope, geoFileManagerService) {
    var mapName = $routeParams.tableName;
    $rootScope.title = $routeParams.tableName;
    $scope.filename = mapName + '.csv';
    geoFileManagerService.getPdeMap(mapName).success(function (backendData) {
        $scope.frontendData = backendData.list;
        $scope.getArray = backendData.list;
    });
});


app.controller('editGeoMapController', function ($scope, $rootScope, $location, $routeParams, geoFileManagerService, backendData) {
    var mapName = ($routeParams.mapname != 0) ? $routeParams.mapname : 0;
    var tableName = 'pde_map_' + mapName;
    var action = ($routeParams.action != 0) ? $routeParams.action : 0;
    if(action == 'create'){
        $scope.showFileLocation = true;
        $scope.isCreate = true;
        $scope.isUpdate = false;
        $scope.isUpload = false;
        $scope.disableDescription = false;
        $scope.mapNameDisable = false;
        $rootScope.title = 'Add New Geo Map';
        $scope.editMapButtonText = 'Add New Geo Map';
    } else if(action == 'edit') {
        $scope.showFileLocation = false;
        $scope.isCreate = true;
        $scope.isUpdate = true;
        $scope.isUpload = false;
        $scope.disableDescription = false;
        $scope.editMapButtonText = 'Update Geo Map';
        $scope.mapNameDisable = true;
        $rootScope.title = 'Edit Geo Map';
        $scope.fileProcessButtonText = 'Append the Table'
    }else if(action == 'append'){
        $scope.showFileLocation = true;
        $scope.isUpdate = false;
        $scope.isUpload = true;
        $scope.disableDescription = true;
        $scope.mapNameDisable = true;
        $rootScope.title = 'Append Geo Map';
        $scope.fileProcessButtonText = 'Append the Table'
    }else if(action == 'override'){
        $scope.showFileLocation = true;
        $scope.isUpdate = false;
        $scope.isUpload = true;
        $scope.disableDescription = true;
        $scope.mapNameDisable = true;
        $rootScope.title = 'Override Geo Map';
        $scope.fileProcessButtonText = 'Override the Table'
    }

    $scope.frontendData = backendData.data;

    $scope.savePixelDataEngineMap = function(frontendData){
        if($scope.editMapButtonText == 'Add New Geo Map'){
            var file = $scope.myFile;
            geoFileManagerService.createPixelDataEngineMap($rootScope.base + 'geo-file-manager',file, frontendData);
        }else if($scope.editMapButtonText == 'Update Geo Map'){
            geoFileManagerService.updatePixelDataEngineMap($rootScope.base + 'geo-file-manager',frontendData);
        }
    };



    $scope.deletePixelDataEngineMap = function(map_name){
        geoFileManagerService.deletePixelDataEngineMap($rootScope.base + 'geo-file-manager', map_name);
    };


    $scope.fileProcess = function(){
        if(action == 'append'){
            geoFileManagerService.appendTable($rootScope.base + 'geo-file-manager',$scope.myFile,tableName);
        }else if(action == 'override'){
            geoFileManagerService.overrideTable($rootScope.base + 'geo-file-manager',$scope.myFile, tableName);
        }
    };



    /*$scope.getHeader = function(mapName,tableName){
        $scope.filename = mapName + ".csv";
        $scope.getArray = geoFileManagerService.getPdeMap(mapName).success(function (backendData) {
            var data = backendData.list;
            return data;
        });
    }*/
});


app.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

