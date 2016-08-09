/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.factory("pixelmappingService", ['$http', '$location', '$rootScope',
    function ($http, $location, $rootScope) {
        var service = {};

        // data mapping services
        service.getMappings = getMappings;
        service.getMapping = getMapping;
        service.insertMapping = insertMapping;
        service.updateMapping = updateMapping;
        service.deleteMapping = deleteMapping;

        // group services
        service.getGroups = getGroups;
        service.getGroup = getGroup;
        service.insertGroup = insertGroup;
        service.updateGroup = updateGroup;
        service.deleteGroup = deleteGroup;
        service.getSameGroup = getSameGroup;

        // rule services
        service.getRules = getRules;
        service.getRule = getRule;
        service.updateRule = updateRule;
        service.deleteRule = deleteRule;
        service.insertRule = insertRule;
        service.testRule = testRule;


        /**
         *   data mapping services
         **/
        function getMappings(type) {
            var body = $http.get($rootScope.base + 'mappings?type=' + type);
            return body;
        }

        function getMapping(mappingID, type) {
            return $http.get($rootScope.base + 'mapping?id=' + mappingID + '&type=' + type);
        }

        function insertMapping(redirectPath, frontendData, type) {
            return $http.post($rootScope.base + 'insertMapping', {mapping: frontendData, type: type}).success(function (results) {
                $location.path(redirectPath);
                return results;
            });
        };

        function updateMapping(redirectPath, id, frontendData, type) {
            return $http.post($rootScope.base + 'updateMapping', {id: id, mapping: frontendData, type: type}).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        function deleteMapping(redirectPath, id, type) {
            return $http.delete($rootScope.base + 'deleteMapping?id=' + id + '&type=' + type).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        /*
         * group services
         * */
        function getGroups() {
            return $http.get($rootScope.base + 'getGroups');
        }

        function getGroup(keyId) {
            return $http.get($rootScope.base + 'group?id=' + keyId);
        }

        function insertGroup(redirectPath, frontendData) {
            return $http.post($rootScope.base + 'insertGroup', {mapping: frontendData}).success(function (results) {
                $location.path(redirectPath);
                return results;
            });
        };

        function updateGroup(redirectPath, id, frontendData) {
            return $http.post($rootScope.base + 'updateGroup', {mapping: frontendData}).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        function deleteGroup(redirectPath, triggerKeyId, gid) {
            return $http.delete($rootScope.base + 'deleteGroup?triggerkeyid=' + triggerKeyId + '&gid=' + gid).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        function getSameGroup(triggerKeyId, gid) {
            return $http.get($rootScope.base + 'samegroup?id=' + gid);
        }


        /*
         * rule services
         * */
        function getRules() {
            return $http.get($rootScope.base + 'getRules');
        }

        function getRule(gid, keyID, priority) {
            return $http.get($rootScope.base + 'getRule?gid=' + gid + '&keyid=' + keyID + '&priority=' + priority);
        }

        function updateRule(redirectPath, parseRule, conditionRule, actionRule, gid, keyId, priority, newPriority, type, split1, split2, lenArray, seg, containsArray, rangeArray, substrArray, decArray, inElementArray, setRuleArray) {
            return $http.post($rootScope.base + 'updateRule', {
                gid: gid,
                keyId: keyId,
                priority: priority,
                newPriority: newPriority,
                type: type,
                parseRule: parseRule,
                split1: split1,
                split2: split2,
                conditionRule: conditionRule,
                lenArray: lenArray,
                seg: seg,
                containsArray: containsArray,
                rangeArray: rangeArray,
                actionRule: actionRule,
                substrArray: substrArray,
                decArray: decArray,
                inElementArray: inElementArray,
                setRuleArray: setRuleArray
            }).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        function deleteRule(redirectPath, gid, keyID, priority) {
            return $http.delete($rootScope.base + 'deleteRule?gid=' + gid + '&keyid=' + keyID + '&priority=' + priority).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        function insertRule(redirectPath, parseRule, conditionRule, actionRule, gid, keyId, priority, type, split1, split2, lenArray, seg, containsArray, rangeArray, substrArray, decArray, inElementArray, setRuleArray) {
            return $http.post($rootScope.base + 'insertRule', {
                gid: gid,
                keyId: keyId,
                priority: priority,
                type: type,
                parseRule: parseRule,
                split1: split1,
                split2: split2,
                conditionRule: conditionRule,
                lenArray: lenArray,
                seg: seg,
                containsArray: containsArray,
                rangeArray: rangeArray,
                actionRule: actionRule,
                substrArray: substrArray,
                decArray: decArray,
                inElementArray: inElementArray,
                setRuleArray: setRuleArray
            }).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };


        function testRule(redirectPath, parseRule, conditionRule, actionRule, gid, keyId, priority, newPriority, type, split1, split2, lenArray, seg, containsArray, rangeArray, substrArray, decArray, inElementArray, setRuleArray, testValue, testOption) {
            return $http.post($rootScope.base + 'testRule', {
                gid: gid,
                keyId: keyId,
                priority: priority,
                newPriority: newPriority,
                type: type,
                parseRule: parseRule,
                split1: split1,
                split2: split2,
                conditionRule: conditionRule,
                lenArray: lenArray,
                seg: seg,
                containsArray: containsArray,
                rangeArray: rangeArray,
                actionRule: actionRule,
                substrArray: substrArray,
                decArray: decArray,
                inElementArray: inElementArray,
                setRuleArray: setRuleArray,
                testValue: testValue,
                testOption: testOption
            }).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        return service;
    }
]);
