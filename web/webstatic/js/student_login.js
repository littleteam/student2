/**
 * Created by 斌 on 2014/10/26.
 */
$.ajaxSetup ({
    cache: false //关闭AJAX相应的缓存
});
function checkInput(errorCode) {

    if(errorCode) {
        accEmpty.hide();
        accErr.show();
        $("#upass").val("");
        return false;
    }

    var uid = $("#uid").val(),
        pass =  $("#upass").val();

    // 账号格式正确且密码不为空
    if($.isNumeric(uid) && pass != ""){
        return true;
    }
    // 表单未完成
    if(uid == "" || pass == "") {
        accErr.hide();
        accEmpty.show();
    }
    return false;
}


$().ready(function () {

    accErr = $("#wrongAccError");
    accEmpty = $("#wrongAccEmpty");

//    $("#loginForm").animate({ "margin-top": '130px' , duration: '1s'});
    setTimeout(function(){
            $("#loginForm").fadeIn(500);
        }, 300);
    $("#loginBtn").bind('click', function () {
        if(!checkInput()) {
            return;
        }
        $("#loginBtn").text("登陆中...");
        $.ajax({
            url: "/login_Ajax",
            type: "POST",
            data: {"account.accUid": $("#uid").val(), "account.accPass": $("#upass").val()},
            cache: false,
            asycn: true,
            error: function () {
                sweetAlert("Oops...", "请求异常", "error");
                $("#loginBtn").text("登陆");
            },
            success: function (data) {
                // redirect
                var result = JSON.parse(data);
                if (result.result == "ok") {
                    $("#loginForm").attr("class", "");
                    window.location = "/" + result.url;
                } else {
                    checkInput(1);
                }
                $("#loginBtn").text("登陆");
            }
        })
    });

    $("input", ".form-group").on("keydown", function(e){
        if(e.keyCode == 13){
            $("#loginBtn").click();
        }
    });
});