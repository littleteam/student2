/**
 * Created by 斌 on 2014/10/28.
 */
$().ready(function () {
    $("a", "#main-left").on("click", function(e) {
        $("a", "#main-left").removeClass("active");
        $(e.target).addClass("active");
        var elem = e.target.innerText;
        var _url;
        switch (elem){
            case "个人信息":
                _url = "/queryInfo";
                break;
            case "课表查询":
                _url = "/queryCourse";
                break;
            case "密码修改":
                _url  = "/modifyInfo";
                break;
        }

        $.ajax({
                url: _url,
                method: "get",
                error: function () {
                    alert("请求异常");
                },
                success: function (data) {
                    alert(data);
                }
            }
        )
    })
});