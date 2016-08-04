/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listGeoMapsController', function ($routeParams, $rootScope, $scope, geoFileManagerService) {

    geoFileManagerService.getPixelDataEngineMaps().success(function (backendData) {
        $scope.frontendData = backendData.list;
    });


});


app.controller('getPdeMapController', function ($routeParams, $rootScope, $scope, geoFileManagerService) {
    var mapName = $routeParams.tableName;
    $rootScope.title = $routeParams.tableName;
    geoFileManagerService.getPdeMap(mapName).success(function (backendData) {
        $scope.frontendData = backendData.list;
    });
});


app.controller('editGeoMapController', function ($scope, $rootScope, $location, $routeParams, geoFileManagerService, backendData) {
    var mapName = ($routeParams.mapname != 0) ? $routeParams.mapname : 0;
    var tableName = 'pde_map_' + mapName;
    var action = ($routeParams.action != 0) ? $routeParams.action : 0;
   // $rootScope.title = (mapName != 0) ? 'Edit Geo Map' : 'Add New Geo Map';
  //  $scope.editMapButtonText = (mapName != 0) ? 'Update Geo Map' : 'Add New Geo Map';
    if(action == 'create'){
        $scope.isCreate = true;
        $scope.isUpdate = false;
        $scope.isUpload = false;
        $scope.disableDescription = false;
        $scope.mapNameDisable = false;
        $rootScope.title = 'Add New Geo Map';
        $scope.editMapButtonText = 'Add New Geo Map';
    } else if(action == 'edit') {
        $scope.isCreate = true;
        $scope.isUpdate = true;
        $scope.isUpload = false;
        $scope.disableDescription = false;
        $scope.editMapButtonText = 'Update Geo Map';
        $scope.mapNameDisable = true;
        $rootScope.title = 'Edit Geo Map';
        $scope.fileProcessButtonText = 'Append the Table'
    }else if(action == 'append'){
        $scope.isUpdate = false;
        $scope.isUpload = true;
        $scope.disableDescription = true;
        $scope.mapNameDisable = true;
        $rootScope.title = 'Append Geo Map';
        $scope.fileProcessButtonText = 'Append the Table'
    }else if(action == 'override'){
        $scope.isUpdate = false;
        $scope.isUpload = true;
        $scope.disableDescription = true;
        $scope.mapNameDisable = true;
        $rootScope.title = 'Override Geo Map';
        $scope.fileProcessButtonText = 'Override the Table'
    }

    //$scope.isUpdate = (mapName != 0) ? true : false;
   // $scope.mapNameDisable = (mapName != 0) ? true : false;
    //if(mapname != 0){
    //    $scope.frontendData = {map_name: ''};
    //    $scope.frontendData.map_name = mapname;
    //}

    //var mapInfo = geoFileManagerService.getPdeMap(mapname);
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

        if(action == 'export'){

        }else if(action == 'append'){
            var file = $scope.myFile;
            geoFileManagerService.appendTable($rootScope.base + 'geo-file-manager',file,tableName);
        }else if(action == 'override'){
            var file = $scope.myFile;
            geoFileManagerService.overrideTable($rootScope.base + 'geo-file-manager',file, tableName);
        }

    };
});


/*

app.controller('geoFileManagerController', ['$scope', 'geoFileManagerService', function($scope, geoFileManagerService){

}]);

*/
/*

app.directive("fileread", [function () {
    return {
        scope: {
            fileread: "="
        },
        link: function (scope, element, attributes) {
            element.bind("change", function (changeEvent) {
                var reader = new FileReader();
                reader.onload = function (loadEvent) {
                    scope.$apply(function () {
                        scope.fileread = loadEvent.target.result;
                    });
                }
                reader.readAsDataURL(changeEvent.target.files[0]);
            });
        }
    }
}]);
*/

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

/*

app.controller('geoFileManagerController', function ($scope, geoFileManagerService) {
    $scope.save = function (csvFile) {
        console.log("hahhaha:" + csvFile)
        //var fileSplitedByLine = $scope.processData(csvFile);
        geoFileManagerService.overrideTable(csvFile);
    };


    $scope.processData = function processData(csvFile) {
        var allTextLines = csvFile.split(/\r\n|\n/);
        var lines = [];
        for (var i = 0; i < allTextLines.length; i++) {
            var data = allTextLines[i].split(';');
            var tarr = [];
            for (var j = 0; j < data.length; j++) {
                tarr.push(data[j]);
            }
            lines.push(tarr);
        }

        return lines;
    };


})

app.directive('fileReader', function () {
    return {
        scope: {
            fileReader: "="
        },
        link: function (scope, element) {
            $(element).on('change', function (changeEvent) {
                var files = changeEvent.target.files;
                if (files.length) {
                    var r = new FileReader();
                    r.onload = function (e) {
                        var contents = e.target.result;
                        scope.$apply(function () {
                            scope.fileReader = contents;
                            //processData(contents);
                        });
                    };

                    r.readAsText(files[0]);
                }
            });
        }
    };
});

*/
