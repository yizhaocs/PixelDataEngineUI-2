/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listPixelGroups', function ($scope, pixelmappingService) {
    pixelmappingService.getGroups().then(function (data) {
        $scope.frontendData = data.data;
    });
});


app.controller('editPixelGroup', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, mapping) {
    var keyId = ($routeParams.keyId) ? parseInt($routeParams.keyId) : 0;
    $rootScope.title = (keyId > 0) ? 'Edit Group' : 'Add Group';
    $scope.buttonText = (keyId > 0) ? 'Update Group' : 'Add New Group';
    var original = mapping.data;
    if (original != '') {
        original._id = keyId;
    }
    $scope.frontendData = angular.copy(original);
    if (original != '') {
        $scope.frontendData._id = keyId;
    }

    $scope.isClean = function () {
        return angular.equals(original, $scope.frontendData);
    }

    $scope.deleteGroup = function (mapping) {
        if (confirm("Are you sure to delete mapping number: " + $scope.frontendData._id) == true)
            pixelmappingService.deleteGroup($rootScope.base + 'Pixel-Data-Engine-Groups', mapping.key_id);
    };

    $scope.saveGroup = function (mapping) {
        if (keyId <= 0) {
            pixelmappingService.insertGroup($rootScope.base + 'Pixel-Data-Engine-Groups', mapping);
        }
        else {
            pixelmappingService.updateGroup($rootScope.base + 'Pixel-Data-Engine-Groups', keyId, mapping);
        }
    };
});