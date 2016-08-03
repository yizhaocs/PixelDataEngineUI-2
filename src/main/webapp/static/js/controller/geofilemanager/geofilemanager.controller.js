/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listGeoMapsController', function ($routeParams, $rootScope, $scope, geoFileManagerService) {

    geoFileManagerService.getPixelDataEngineMaps().success(function (backendData) {
        $scope.frontendData = backendData.list;
    });

    $scope.appendTable = function(tableName){
        var file = $scope.myFile;
        geoFileManagerService.appendTable(file,tableName);
    };


    $scope.overrideTable = function(tableName){
        var file = $scope.myFile;
        geoFileManagerService.overrideTable(file, tableName);
    };
});


app.controller('getPdeMapController', function ($routeParams, $rootScope, $scope, geoFileManagerService) {
    var mapName = $routeParams.tableName;
    $rootScope.title = $routeParams.tableName;
    geoFileManagerService.getPdeMap(mapName).success(function (backendData) {
        $scope.frontendData = backendData.list;
    });
});


app.controller('createNewGeoMapController', function ($routeParams, $rootScope, $scope, geoFileManagerService) {

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
