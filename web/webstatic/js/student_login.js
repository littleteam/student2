/**
* Created by 斌 on 2014/10/26.
*/
$().ready(function() {
   $("#loginBtn").bind('click', function() {
       $.ajax({
           url: "/login/login_Ajax",
           type: "POST",
           data: {"account.accUid":$("#uid").val(), "account.accPass":$("#upass").val()},
           cache: false,
           asycn: true,
           error: function () {
               //todo: 添加错误提示
               alert('请求异常');
           },
           success: function (data) {
               // redirect
               var result = JSON.parse(data);
               if(result.result == "ok")
                   alert("ok");
               else
                   alert("dsf");
           }
       })
   })
});