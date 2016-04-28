/**
 * @author JASON GAO[jason.gao@adara.com ]
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listCtrlLiveramp', function ($scope, pixelmappingService) {
    pixelmappingService.getMappings('liveramp-dp').then(function (data) {
        $scope.mappingsDp = data.data;
    });
    pixelmappingService.getMappings('liveramp-key').then(function (data) {
        $scope.mappingsKey = data.data;
    });
});

app.controller('editCtrlLiverampDp', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, mapping) {
    var mappingID = ($routeParams.mappingID) ? $routeParams.mappingID : '0';
    $rootScope.title = (mappingID != '0') ? 'Edit Liveramp DP Mapping' : 'Add Liveramp DP Mapping';
    $scope.buttonText = (mappingID != '0') ? 'Update Liveramp DP Mapping' : 'Add New Liveramp DP Mapping';
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
        // $location.path('/liveramp');
        if (confirm("Are you sure to delete mapping number: " + $scope.mapping._id) == true)
            pixelmappingService.deleteMapping($rootScope.base + 'liveramp', mapping.dp_name, 'liveramp-dp');
    };

    $scope.saveMapping = function (mapping) {
        if (mapping.threshold_mb > 100) {
            alert("Threshold can't be greater than 100MB");
        }
        else {
            // $location.path('/liveramp');
            if (mappingID == '0') {
                pixelmappingService.insertMapping($rootScope.base + 'liveramp', mapping, 'liveramp-dp');
            }
            else {
                pixelmappingService.updateMapping($rootScope.base + 'liveramp', mappingID, mapping, 'liveramp-dp');
            }
        }
    };
});

app.controller('editCtrlLiverampKey', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, mapping) {
    var mappingID = ($routeParams.mappingID) ? $routeParams.mappingID : '0';
    $rootScope.title = (mappingID != '0') ? 'Edit Liveramp Key Mapping' : 'Add Liveramp Key Mapping';
    $scope.buttonText = (mappingID != '0') ? 'Update Liveramp Key Mapping' : 'Add New Liveramp Key Mapping';
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
        // $location.path('/liveramp');
        if (confirm("Are you sure to delete mapping number: " + $scope.mapping._id) == true)
            pixelmappingService.deleteMapping($rootScope.base + 'liveramp', mapping.liveramp_segment_id, 'liveramp-key');
    };

    $scope.saveMapping = function (mapping) {
        // $location.path('/liveramp');
        if (mappingID == '0') {
            pixelmappingService.insertMapping($rootScope.base + 'liveramp', mapping, 'liveramp-key');
        }
        else {
            pixelmappingService.updateMapping($rootScope.base + 'liveramp', mappingID, mapping, 'liveramp-key');
        }
    };
});