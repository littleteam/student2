/**
 * Created by 斌 on 2014/10/28.
 */
function fillInfo(tbody, data) {
    var rows =  tbody.selectAll("tr")
        .data(function () {
            var cells = [];
            for(var cell in data.userinfo){
                cells.push([cell, data.userinfo[cell]]);
            }
            return cells;
        })
        .enter()
        .append("tr");
    rows.selectAll("td")
        .data(function (row) {
            return row;
        })
        .enter()
        .append("td")
        .text(function (d) {
            if(wordImage[d]){
                return wordImage[d];
            }
            return d;
        });
}
wordImage = {
    admId: "用户ID",
    admName: "姓名",
    admSex: "性别",
    schName: "学院"
};

function dealUserinfo(_data) {
    isAdmin = _data.isAdmin;
    if(isAdmin) {
        $("#userNameDropdown").text(_data.userinfo["admId"]);
    } else {
        $("#userNameDropdown").text(_data.userinfo["stuId"]);
    }
    var tbody = initTable("#userInfoTable",[], _data);
    fillInfo(tbody, _data);
    main_right.empty();
    main_right.append(userInfo);
}

function fillListCourse(tbody, _data) {
    var rows = tbody.selectAll("tr")
        .data(function () {
            return _data.querylist;
        })
        .enter()
        .append("tr");
    rows.selectAll("td")
        .data(function (row) {
            var tmp = [];
            for(var cell in row) {
                tmp.push(row[cell]);
            }
            return tmp;
        })
        .enter()
        .append("td")
//        .text(function (d) {
//            return d;
//        })
        .append("input")
        .attr("class", "form-control");
//        .val()
}

function dealListCourse(_data) {
    var tbody = initTable("#userCourseTable", ["课程年级", "课程", "课程名ID", "所在学院"], _data);
    fillListCourse(tbody, _data);
    main_right.empty();
    main_right.append(userCourse);
}

function clearModifyPassForm() {
    var oldPass = $("#oldPass");
    var newPass = $("#newPass");
    oldPass.val("");
    newPass.val("");
}

// 修改密码
function dealModifyPass() {
    //清除表单内容
    var oldPass = $("#oldPass");
    var newPass = $("#newPass");

    $.ajax({
            url: "/QueryModifyPass",
            method: "POST",
            data: {"oldPass": oldPass.val(), "newPass": newPass.val()},
            error: function () {
                alert("请求异常");
            },
            success: function (data) {
                var _data = JSON.parse(data);
                var result = _data.result;
                alert(result);
            }
        }
    );
}

$().ready(function () {
    $("body").addClass("animated fadeInUpBig");

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
//            case "密码修改":
//                _url  = "/QueryModifyPass";
//                break;
            default:
                return;
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
                            dealListCourse(_data);
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
    $("a:first-child", "#main-left").click();
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
