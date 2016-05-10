/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.factory("pixelmappingService", ['$http', '$location', '$rootScope',
    function ($http, $location, $rootScope) {
        var obj = {};

        /**
         *   Rest API Services
         **/
        obj.insertRule = function (username, password, redirectPath) {
            // , inElementArray: inElementArray, setRuleArray: setRuleArray
            return $http.post($rootScope.base + 'login', {username: username, password: password}).then(function (status) {
                $location.path($rootScope.base + 'adobe');
                return status.data;
            });
        };

        obj.getMappings = function (type) {
            return $http.get($rootScope.base + 'mappings?type=' + type);
        }

        obj.getMapping = function (mappingID, type) {
            return $http.get($rootScope.base + 'mapping?id=' + mappingID + '&type=' + type);
        }

        obj.insertMapping = function (redirectPath, frontendData, type) {
            return $http.post($rootScope.base + 'insertMapping', {mapping: frontendData, type: type}).then(function (results) {
                $location.path(redirectPath);
                return results;
            });
        };

        obj.updateMapping = function (redirectPath, id, frontendData, type) {
            return $http.post($rootScope.base + 'updateMapping', {id: id, mapping: frontendData, type: type}).then(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        obj.deleteMapping = function (redirectPath, id, type) {
            return $http.delete($rootScope.base + 'deleteMapping?id=' + id + '&type=' + type).then(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        /*
         * group service begin
         * */
        obj.getGroups = function () {
            return $http.get($rootScope.base + 'getGroups');
        }

        obj.getGroup = function (keyId) {
            return $http.get($rootScope.base + 'group?id=' + keyId);
        }

        obj.insertGroup = function (redirectPath, frontendData) {
            return $http.post($rootScope.base + 'insertGroup', {mapping: frontendData}).then(function (results) {
                $location.path(redirectPath);
                return results;
            });
        };

        obj.updateGroup = function (redirectPath, id, frontendData) {
            return $http.post($rootScope.base + 'updateGroup', {mapping: frontendData}).then(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        obj.deleteGroup = function (redirectPath, id) {
            return $http.delete($rootScope.base + 'deleteGroup?id=' + id).then(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        obj.getSameGroup = function (gid) {
            return $http.get($rootScope.base + 'samegroup?id=' + gid);
        }





        /*
         * rule service begin
         * */
        obj.getRules = function () {
            return $http.get($rootScope.base + 'getRules');
        }

        obj.getRule = function (gid, keyID, priority) {
            return $http.get($rootScope.base + 'getRule?gid=' + gid +'&keyid=' + keyID + '&priority=' + priority);
        }

        obj.updateRule = function (redirectPath, parseRule, conditionRule, actionRule, gid, keyId, priority, newPriority, type, split1, split2, len, seg, range, substr, dec, inElementArray, setRuleArray) {
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
                len: len,
                seg: seg,
                range: range,
                actionRule: actionRule,
                substr: substr,
                dec: dec,
                inElementArray: inElementArray,
                setRuleArray: setRuleArray
            }).then(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        obj.deleteRule = function (redirectPath, gid, keyID, priority) {
            return $http.delete($rootScope.base + 'deleteRule?gid=' + gid +'&keyid=' + keyID + '&priority=' + priority).then(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        obj.insertRule = function (redirectPath, parseRule, conditionRule, actionRule, gid, keyId, priority, type, split1, split2, len, seg, range, substr, dec, inElementArray, setRuleArray) {
            return $http.post($rootScope.base + 'insertRule', {
                gid: gid,
                keyId: keyId,
                priority: priority,
                type: type,
                parseRule: parseRule,
                split1: split1,
                split2: split2,
                conditionRule: conditionRule,
                len: len,
                seg: seg,
                range: range,
                actionRule: actionRule,
                substr: substr,
                dec: dec,
                inElementArray: inElementArray,
                setRuleArray: setRuleArray
            }).then(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };


        obj.testRule = function (redirectPath, parseRule, conditionRule, actionRule, gid, keyId, priority, newPriority, type, split1, split2, len, seg, range, substr, dec, inElementArray, setRuleArray, testValue) {
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
                len: len,
                seg: seg,
                range: range,
                actionRule: actionRule,
                substr: substr,
                dec: dec,
                inElementArray: inElementArray,
                setRuleArray: setRuleArray,
                testValue: testValue
            }).then(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        return obj;
    }
]);
