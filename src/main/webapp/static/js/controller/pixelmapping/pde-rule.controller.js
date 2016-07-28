/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listPixelRules', function ($scope, pixelmappingService) {
    pixelmappingService.getRules().success(function (backendData) {
        $scope.frontendData = backendData.list;
    });
});
