/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listCtrlDeriveConversion', function ($scope, pixelmappingService) {
    pixelmappingService.getMappings('derive-conversion').then(function (data) {
        $scope.mappings = data.data;
    });
});

app.controller('editCtrlDeriveConversion', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, mapping) {
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
        // $location.path('/adobe');
        if (confirm("Are you sure to delete mapping number: " + $scope.mapping._id) == true)
            pixelmappingService.deleteMapping($rootScope.base + 'derive-conversion', mapping.dp_key_id, 'derive-conversion');
    };

    $scope.saveMapping = function (mapping) {
        // $location.path('/adobe');
        if (mappingID <= 0) {
            pixelmappingService.insertMapping($rootScope.base + 'derive-conversion', mapping, 'derive-conversion');
        }
        else {
            pixelmappingService.updateMapping($rootScope.base + 'derive-conversion', mappingID, mapping, 'derive-conversion');
        }
    };
});