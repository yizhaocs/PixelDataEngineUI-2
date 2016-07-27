/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

app.run(['$location', '$rootScope', '$cookieStore', '$http', function ($location, $rootScope, $cookieStore, $http) {
    // keep user logged in after page refresh
    $rootScope.globals = $cookieStore.get('globals') || {};
    if ($rootScope.globals.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
    }


    // true if reenter the "http://localhost:8080/pdeui/" as root user, so that we allow "Manage-Users" page still appear on the top
    if ($rootScope.globals != '' && $rootScope.globals.currentUser != undefined && $rootScope.globals.currentUser.username != undefined && $rootScope.globals.currentUser.username != 'root') {
        $rootScope.isroot = false;
    } else {
        $rootScope.isroot = true;
    }

    $rootScope.base = '';

    $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
        $rootScope.title = current.$$route.title;

        // redirect to login page if not logged in and trying to access a restricted page
        var restrictedPage = $.inArray($location.path(), [$rootScope.base + 'login', $rootScope.base + 'register']) === -1;
        var loggedIn = $rootScope.globals.currentUser;
        if (restrictedPage && !loggedIn) {
            $location.path($rootScope.base + 'login');
        }
    });
}]);