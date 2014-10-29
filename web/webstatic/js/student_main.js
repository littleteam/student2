/**
 * Created by 斌 on 2014/10/28.
 */
//function dealResult(data) {
//    if(undefined == data) {
//        return;
//    }
//
//    // 确定调用者
//    var obj = JSON.parse(String(data));
//    if(undefined == obj.userinfo) {
//        dealUserinfo(data);
//        return;
//    }
////    if(undefined == obj)
//}

function fillInfo(tbody, data) {
    var rows =  tbody.selectAll("tr")
        .data(function () {
            var cells = [];
            for(var cell in data.userinfo){
//                cells.push({value: data.userinfo[cell]});
                cells.push([cell, data.userinfo[cell]]);
            }
            return cells;
        })
        .enter()
        .append("tr");
    var cells = rows.selectAll("td")
        .data(function (row) {
            return row;
        })
        .enter()
        .append("td")
        .text(function (d) {
            return d;
        });
}
function dealUserinfo(_data) {

    var tbody = initTable("#userInfoTable",[], _data);
    fillInfo(tbody, _data);
    main_right.empty();
    main_right.append(userInfo);
}

function fillListCourse(tbody, _data) {

}

function dealListCourse(_data) {
    var tbody = initTable("#userListCourse", ["课程id", "课程名", "课程年级", "学院id"], _data);
    fillListCourse(tbody, _data);
    main_right.empty();
    main_right.append(userCourse);
}

$().ready(function () {

    main_right = $("#main-right");

    userInfo = $("#userInfo");
    userInfoTable = $(".table", userInfo);

    userCourse = $("#userCourse");
    userCourseTable = $(".table", userCourseTable);

    $("a", "#main-left").on("click", function(e) {
        $("a", "#main-left").removeClass("active");
        $(e.target).addClass("active");
        var elem = e.target.innerText;
        var _url;
        switch (elem){
            case "个人信息":
                _url = "/QueryPerInfo";
                break;
            case "课表查询":
                _url = "/QueryListCourse";
                break;
            case "密码修改":
                _url  = "/QueryModifyPass";
                break;
        }

        $.ajax({
                url: _url,
                method: "get",
                error: function () {
                    alert("请求异常");
                },
                success: function (data) {
//                    alert(data);
                    var _data = JSON.parse(data);
                    var caseid = _data.case;
                    switch(caseid){
                        case RequestType.PerRequest:
                            dealUserinfo(_data);
                            break;
                        case RequestType.ListCourse:

                            break;
                        case RequestType.ModifyCourse:
                            break;
                        case RequestType.ModifyPass:
                            break;
                    }
                }
            }
        );
    });
});
RequestType={
    PerRequest	: "PerRequest",
    ListCourse	: "ListCourse",
    ModifyCourse: "ModifyCourse",
    ModifyPass	: "ModifyPass"
};

function initTable(selector, disPlayHeader,data, caseID ) {
    $(selector).empty();
    var table = d3.select(selector);

    var thead = table.append("thead");
    var tbody = table.append("tbody");

    thead.append("tr")
        .selectAll("th")
        .data(disPlayHeader)
        .enter()
        .append("th")
        .text(function (column) {
            return column;
        });

    return tbody;
}
