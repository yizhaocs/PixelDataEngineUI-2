/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';


app.controller('geoFileManagerController', ['$scope', function ($scope) {
    $scope.save = function (csvFile) {
        console.log("hahhaha:" + csvFile)
        $scope.processData(csvFile);
    }


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

        for (var i = 0; i < lines.length; i++) {
        }
        console.log(lines[0]);
    };


}])

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

