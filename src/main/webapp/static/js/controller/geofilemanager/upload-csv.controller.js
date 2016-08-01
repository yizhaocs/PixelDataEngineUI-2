/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';


app.controller('geoFileManagerController', ['$scope', 'geoFileManagerService', function($scope, geoFileManagerService){
    $scope.appendTable = function(){
        var file = $scope.myFile;

        console.log('file is ' );
        console.dir(file);

        geoFileManagerService.appendTable(file);
    };


    $scope.overrideTable = function(){
        var file = $scope.myFile;

        console.log('file is ' );
        console.dir(file);

        geoFileManagerService.overrideTable(file);
    };
}]);


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
