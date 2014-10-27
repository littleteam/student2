/**
 * Created by 斌 on 2014/10/26.
 */
function errorInput () {
    $("#loginForm").attr("class", "has-error");
}

function checkInput() {
    if($.isNumeric($("#uid").val()) &&  $("#upass").val()!=""){
        return true;
    }
    errorInput();
    return false;
}

$().ready(function () {
//    $("#loginForm").animate({ "margin-top": '130px' , duration: '1s'});
    setTimeout(function(){
            $("#loginForm").fadeIn(500);
        }, 300);
    $("#loginBtn").bind('click', function () {
        if(!checkInput()) {
            return;
        }
        $.ajax({
            url: "/login/login_Ajax",
            type: "POST",
            data: {"account.accUid": $("#uid").val(), "account.accPass": $("#upass").val()},
            cache: false,
            asycn: true,
            error: function () {
                alert('请求异常');
            },
            success: function (data) {
                // redirect
                var result = JSON.parse(data);
                if (result.result == "ok") {
                    $("#loginForm").attr("class", "");
                    window.location = "http://localhost:8080/" + result.url;
                } else {
                    errorInput();
                }
            }
        })
    });
});