/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listCtrlDbm', function ($scope, pixelmappingService) {
    pixelmappingService.getMappings('dbm').then(function (data) {
        $scope.frontendData = data.data;
    });
});

app.controller('editCtrlDbm', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, mapping) {
    var mappingID = ($routeParams.mappingID) ? parseInt($routeParams.mappingID) : 0;
    $rootScope.title = (mappingID > 0) ? 'Edit Mapping' : 'Add Mapping';
    $scope.buttonText = (mappingID > 0) ? 'Update Mapping' : 'Add New Mapping';
    var original = mapping.data;
    if (original != '') {
        original._id = mappingID;
    }
    $scope.frontendData = angular.copy(original);
    if (original != '') {
        $scope.frontendData._id = mappingID;
    }

    $scope.isClean = function () {
        return angular.equals(original, $scope.frontendData);
    }

    $scope.deleteMapping = function (mapping) {
        if (confirm("Are you sure to delete mapping number: " + $scope.frontendData._id) == true)
            pixelmappingService.deleteMapping($rootScope.base + 'dbm', mapping.conversion_pixel_id, 'dbm');
    };

    $scope.saveMapping = function (mapping) {
        if (mappingID <= 0) {
            pixelmappingService.insertMapping($rootScope.base + 'dbm', mapping, 'dbm');
        }
        else {
            pixelmappingService.updateMapping($rootScope.base + 'dbm', mappingID, mapping, 'dbm');
        }
    };
});