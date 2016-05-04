/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listPixelRules', function ($scope, pixelmappingService) {
    pixelmappingService.getRules().then(function (backendData) {
        $scope.frontendData = backendData.data;
    });
});

//
//app.controller('editPixelRule', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, backendData) {
//    /*
//     *
//     * Add rule
//     *
//     * */
//    $scope.debug = true;
//    $scope.myDropDown = 'none';
//
//    /*
//     *
//     * Edit rule
//     *
//     * */
//    var keyIdParam = ($routeParams.keyId) ? $routeParams.keyId : 0;
//    $rootScope.title = (keyIdParam > 0) ? 'Edit Rule' : 'Add Rule';
//    $scope.buttonText = (keyIdParam > 0) ? 'Update Rule' : 'Add Rule';
//    $scope.isUpdate = (keyIdParam > 0) ? true : false; // false to get rid of "Delete" button
//    $scope.groupIdInputDisable = (keyIdParam > 0) ? true : false;
//    $scope.keyIdDisable = (keyIdParam > 0) ? true : false;
//    var responseBackupRuleData = backendData.data;
//
//    if (responseBackupRuleData != '') {
//        responseBackupRuleData._id = keyIdParam;
//    }
//
//
//    var processedResponseBackupRuleData = {
//        "parseRule": null,
//        "conditionRule": null,
//        "actionRule": null,
//        "gid": null,
//        "keyId": null,
//        "priority": null,
//        "type": null,
//        "split1": {
//            "column1": null
//        },
//        "split2": {
//            "column1": null,
//            "column2": null
//        },
//        "len": {
//            "column1": null
//        },
//        "range": {
//            "column1": null,
//            "column2": null
//        },
//        "substr": {
//            "column1": null,
//            "column2": null,
//            "column3": null
//        },
//        "dec": {
//            "column1": null
//        },
//        "inElementArray": [],
//        "setRuleArray": []
//    };
//
//    // true if user click on the "edit button"
//    if (keyIdParam != 0) {
//        processedResponseBackupRuleData.gid = responseBackupRuleData.gid;
//        processedResponseBackupRuleData.keyId = responseBackupRuleData.key_id;
//        processedResponseBackupRuleData.type = responseBackupRuleData.type;
//        processedResponseBackupRuleData.priority = responseBackupRuleData.priority;
//
//        var parseRuleSplit = responseBackupRuleData.parse_rule.split("|");
//        processedResponseBackupRuleData.parseRule = parseRuleSplit[0];
//        if (parseRuleSplit[0] == 'split1') {
//            // true if -> -> split1|"|"
//            if (parseRuleSplit.length == 3) {
//                processedResponseBackupRuleData.split1.column1 = "\"|\"";
//            } else {
//                processedResponseBackupRuleData.split1.column1 = parseRuleSplit[1];
//            }
//        } else if (parseRuleSplit[0] == 'split2') {
//            /*
//             * handle case of -> split2|"|"|"|"
//             * handle case of -> split2|"|"|,
//             * handle case of -> split2|,|"|"
//             * */
//            if (parseRuleSplit.length == 5) { // true if -> split2|"|"|"|"
//                processedResponseBackupRuleData.split2.column1 = "\"|\"";
//                processedResponseBackupRuleData.split2.column2 = "\"|\"";
//            } else if (parseRuleSplit.length == 4) {
//                // true if -> split2|"|"|,
//                // false if -> split2|,|"|"
//                if (parseRuleSplit[1] == "\"") {
//                    processedResponseBackupRuleData.split2.column1 = "\"|\"";
//                    processedResponseBackupRuleData.split2.column2 = parseRuleSplit[3];
//                } else {
//                    processedResponseBackupRuleData.split2.column1 = parseRuleSplit[1];
//                    processedResponseBackupRuleData.split2.column2 = "\"|\"";
//                }
//            } else {
//                processedResponseBackupRuleData.split2.column1 = parseRuleSplit[1];
//                processedResponseBackupRuleData.split2.column2 = parseRuleSplit[2];
//            }
//        }
//
//
//        var conditionRuleSplit = responseBackupRuleData.condition_rule.split("|");
//        processedResponseBackupRuleData.conditionRule = conditionRuleSplit[0];
//        if (conditionRuleSplit[0] == 'len') {
//            processedResponseBackupRuleData.len.column1 = conditionRuleSplit[1];
//        } else if (conditionRuleSplit[0] == 'range') {
//            processedResponseBackupRuleData.range.column1 = conditionRuleSplit[1];
//            processedResponseBackupRuleData.range.column2 = conditionRuleSplit[2];
//        } else if (conditionRuleSplit[0] == 'in') {
//            for (var i = 1; i < conditionRuleSplit.length; i++) {
//                var inObject = {
//                    column1: i - 1,
//                    column2: conditionRuleSplit[i]
//                }
//                processedResponseBackupRuleData.inElementArray.push(inObject);
//            }
//        }
//
//        var actionRuleSplit = responseBackupRuleData.action_rule.split("|");
//        processedResponseBackupRuleData.actionRule = actionRuleSplit[0];
//        if (actionRuleSplit[0] == 'substr') {
//            processedResponseBackupRuleData.substr.column1 = actionRuleSplit[1];
//            processedResponseBackupRuleData.substr.column2 = actionRuleSplit[2];
//            processedResponseBackupRuleData.substr.column3 = actionRuleSplit[3];
//        } else if (actionRuleSplit[0] == 'set') {
//            for (var i = 1; i < actionRuleSplit.length; i++) {
//                var setObject = {
//                    column1: i - 1,
//                    column2: actionRuleSplit[i]
//                }
//                processedResponseBackupRuleData.setRuleArray.push(setObject);
//            }
//        } else if (actionRuleSplit[0] == 'dec') {
//            processedResponseBackupRuleData.dec.column1 = actionRuleSplit[1];
//        }
//    }
//
//    $scope.frontendData = angular.copy(processedResponseBackupRuleData);
//    if (responseBackupRuleData != '') {
//        $scope.frontendData._id = keyIdParam;
//    }
///*
//
//    if (responseBackupRuleData == '') {
//        $scope.frontendData = {};
//        responseBackupRuleData = {};
//    }
//*/
//
//
//    // if no inElementArray || setRuleArray data from backend, then init them
//    if ($scope.frontendData.inElementArray.length == 0) {
//        $scope.frontendData.inElementArray = [{
//            column1: '0',
//            column2: ''
//        }];
//    }
//
//    if ($scope.frontendData.setRuleArray.length == 0) {
//        $scope.frontendData.setRuleArray = [{
//            column1: '0',
//            column2: ''
//        }];
//    }
//
//
//    /*
//     *
//     * functions
//     * */
//    // button fuctions
//    $scope.addSetRule = function () {
//        if ($scope.debug) {
//            console.log('addSetRule');
//        }
//        $scope.frontendData.setRuleArray.push({
//            column1: $scope.frontendData.setRuleArray.length,
//            column2: ''
//        });
//
//    };
//
//    $scope.removeSetRule = function () {
//        if ($scope.debug) {
//            console.log('removeSetRule');
//        }
//        $scope.frontendData.setRuleArray.pop();
//    };
//
//    $scope.addInElement = function () {
//        if ($scope.debug) {
//            console.log('addInElement');
//        }
//        $scope.frontendData.inElementArray.push({
//            column1: $scope.frontendData.inElementArray.length,
//            column2: ''
//        });
//    };
//
//    $scope.removeInElement = function () {
//        if ($scope.debug) {
//            console.log('removeInElement');
//        }
//        $scope.frontendData.inElementArray.pop();
//    };
//
//
//    $scope.isClean = function () {
//        return angular.equals(responseBackupRuleData, $scope.frontendData);
//    }
//
//    $scope.deleteRule = function (frontendData) {
//        if (confirm("Are you sure to delete rule number: " + frontendData.keyId) == true) {
//            pixelmappingService.deleteRule($rootScope.base + 'pixel-data-engine-rule', frontendData.keyId);
//        }
//    };
//
//    $scope.saveRule = function (frontendData) {
//        if ($routeParams.keyId == '0') {
//            pixelmappingService.insertRule($rootScope.base + 'pixel-data-engine-rule', frontendData.parseRule, frontendData.conditionRule, frontendData.actionRule, frontendData.gid, frontendData.keyId, frontendData.priority, frontendData.type, frontendData.split1, frontendData.split2, frontendData.len, frontendData.range, frontendData.substr, frontendData.dec, frontendData.inElementArray, frontendData.setRuleArray);
//        }
//        else {
//            pixelmappingService.updateRule($rootScope.base + 'pixel-data-engine-rule', frontendData.parseRule, frontendData.conditionRule, frontendData.actionRule, frontendData.gid, frontendData.keyId, frontendData.priority, frontendData.type, frontendData.split1, frontendData.split2, frontendData.len, frontendData.range, frontendData.substr, frontendData.dec, frontendData.inElementArray, frontendData.setRuleArray);
//        }
//    };
//});