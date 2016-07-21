///**
// * @author YI ZHAO[yi.zhao@adara.com]
// */
//
//'use strict';
//
//app.service('fileUpload', ['$http', function ($http) {
//    this.uploadFileToUrl = function(file, uploadUrl){
//        var fd = new FormData();
//        console.log(file);
//        fd.append('file', file);
//
//        var allTextLines = file.split(/\r\n|\n/);
//        var headers = allTextLines[0].split(',');
//        var lines = [];
//
//        for ( var i = 0; i < allTextLines.length; i++) {
//            // split content based on comma
//            var data = allTextLines[i].split(',');
//            if (data.length == headers.length) {
//                var tarr = [];
//                for ( var j = 0; j < headers.length; j++) {
//                    tarr.push(data[j]);
//                }
//                lines.push(tarr);
//            }
//        }
//
//        console.log(lines);
//
//
//        //$http.post(uploadUrl, fd, {
//        //        transformRequest: angular.identity,
//        //        headers: {'Content-Type': undefined}
//        //    })
//        //
//        //    .success(function(){
//        //    })
//        //
//        //    .error(function(){
//        //    });
//    }
//}]);