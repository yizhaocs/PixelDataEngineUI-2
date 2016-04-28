/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listCtrlKruxDpkey', function ($scope, pixelmappingService) {
    pixelmappingService.getMappings('krux-dpkey').then(function (data) {
        $scope.mappings = data.data;
    });
});

app.controller('editCtrlKruxDpkey', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, mapping) {
    var mappingID = ($routeParams.mappingID) ? $routeParams.mappingID : 0;

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
            pixelmappingService.deleteMapping($rootScope.base + 'krux-dpkey', mapping.krux_segment_id, 'krux-dpkey');
    };

    $scope.saveMapping = function (mapping) {
        // $location.path('/adobe');
        if (mappingID <= 0) {
            pixelmappingService.insertMapping($rootScope.base + 'krux-dpkey', mapping, 'krux-dpkey');
        }
        else {
            pixelmappingService.updateMapping($rootScope.base + 'krux-dpkey', mappingID, mapping, 'krux-dpkey');
        }
    };
});