/**
 * @author JASON GAO[jason.gao@adara.com ]
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listCtrlAdobe', function ($scope, pixelmappingService) {
    pixelmappingService.getMappings('adobe').then(function (data) {
        $scope.mappings = data.data;
    });
});

app.controller('editCtrlAdobe', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, mapping) {
    var mappingID = ($routeParams.mappingID) ? parseInt($routeParams.mappingID) : 0;
    $rootScope.title = (mappingID > 0) ? 'Edit Mapping' : 'Add Mapping';
    $scope.buttonText = (mappingID > 0) ? 'Update Mapping' : 'Add New Mapping';
    var original = mapping.data;
    if (original != '') {
        original._id = mappingID;
    }
    $scope.mapping = angular.copy(original);
    if (original != '') {
        $scope.mapping._id = mappingID;
    }

    $scope.isClean = function () {
        return angular.equals(original, $scope.mapping);
    }

    $scope.deleteMapping = function (mapping) {
        if (confirm("Are you sure to delete mapping number: " + $scope.mapping._id) == true)
            pixelmappingService.deleteMapping($rootScope.base + 'adobe', mapping.adobe_segment_id, 'adobe');
    };

    $scope.saveMapping = function (mapping) {
        if (mappingID <= 0) {
            pixelmappingService.insertMapping($rootScope.base + 'adobe', mapping, 'adobe');
        }
        else {
            pixelmappingService.updateMapping($rootScope.base + 'adobe', mappingID, mapping, 'adobe');
        }
    };
});