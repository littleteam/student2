/**
 * Created by 斌 on 2014/10/28.
 */
function fillInfo(tbody, data) {
    var rows = tbody.selectAll("tr")
        .data(function () {
            var cells = [];
            for (var cell in data.userinfo) {
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
            if (wordImage[d]) {
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
    if (isAdmin) {
        $("#userNameDropdown").text(_data.userinfo["admId"]);
    } else {
        $("#userNameDropdown").text(_data.userinfo["stuId"]);
    }
    var tbody = initTable("#userInfoTable", [], _data);
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
    var tds = rows.selectAll("td")
        .data(function (row) {
            var tmp = [];
            for (var cell in row) {
                tmp.push(row[cell]);
            }
            return tmp;
        })
        .enter()
        .append("td");

    if (isAdmin) {
        tds.append("input")
            .attr("class", "form-control")
            .attr("value", function (d) {
                return d;
            });

        var _tds = $("td:last-child", userCourseTable);
        for (var i = 0; i < _tds.length; i++) {
            $(_tds[i]).after('<td><input type="checkbox" style="zoom: 1.7; margin-top: 4px;"/></td>');
        }
    } else {
        tds.text(function (d) {
            return d;
        });
    }
}

CourseDeleted = [];
CourseModified = [];

function courseDelete() {
    // 获取所有选中元素 行级元素
    var checkedboxs = $('input[type=checkbox]:checked', userCourseTable);
    var trs = checkedboxs.parent().parent();
    var tds = $('td', trs);
    trs.attr("class", "animated bounceOutRight");

    // 动画结束,隐藏元素
    $(trs).one('webkitAnimationEnd', function () {
        tds.hide();
    });

    // 添加课程id到 CourseDeleted
    // 获取thead列数
    var thead_cols_num = $("th", userCourseTable).length;
    for (var i = 0; i < tds.length; i += thead_cols_num) {
        var courseId = $('input', tds[i + 1]).val();
        CourseDeleted.push(courseId);
    }

}
var arrayUnique = function (a) {
    return a.reduce(function (p, c) {
        if (p.indexOf(c) < 0) p.push(c);
        return p;
    }, []);
};
/**
 *
 * @param couID
 * @param couGrade
 * @param couName
 * @param couSchName
 * @param couOperate 操作类型,修改or删除
 * @constructor
 */
function CourseEntity(couOperate, couID, couGrade, couName, couSchName) {
    this.couOperate = couOperate;
    this.couID = couID;
    this.couGrade = couGrade;
    this.couName = couName;
    this.couSchName = couSchName;
}
$.ajaxSetup({cache:false})
function dealCourseDataTrans() {
    var course = [];
    var modifiedTrs = $("tr[class=modified]", "#userCourseTable");
    for (var i = 0; i < modifiedTrs.length; i++) {
        var tr = modifiedTrs[i];
        var inputs = $('input', tr);
        var cou_grade = inputs[0].value;
        var cou_id = inputs[1].value;
        var cou_name = inputs[2].value;
        var cou_sch_name = inputs[3].value;
        var modifiedRow = new CourseEntity("modify", cou_id, cou_grade, cou_name, cou_sch_name);

        course.push(modifiedRow);
    }

    var deletedTrs = $(".bounceOutRight", "#userCourseTable");
    for (var ii = 0; ii < deletedTrs.length; ii++) {
        var _tr = deletedTrs[ii];
        var _inputs = $('input', _tr);
//        var _cou_grade = tr[0];
        var _cou_id = _inputs[1].value;
//        var _cou_name = tr[2];
//        var _cou_sch_name = tr[3];
        var _modifiedRow = new CourseEntity("delete", _cou_id);

        course.push(_modifiedRow);
    }
    return course;
}

function courseSubmit() {
    var data = dealCourseDataTrans();
    if(data.length < 1){
        return;
    }
    // todo:添加确认confirm

    $.ajax({
        url: "/QueryModifyCourse",
        data: {stringJson: JSON.stringify(data)},
        method: "POST",
        error: function () {
            alert("请求异常");
        },
        success: function (data) {
            var _data = JSON.parse(data);
            var result = _data.result;

            if (result == "error") {
                return;
            } else {
                var operate = _data.case;
                alert("success");
            }
        }
    });
}

function dealListCourse(_data) {
    // 将右侧div清空
    main_right.empty();

    var tbody = initTable($("#userCourseTable", userCourse)[0], ["课程年级", "课程ID", "课程名", "所在学院", ""], _data);
    fillListCourse(tbody, _data);
    main_right.append(userCourse);

    if(isAdmin && $("#courseDelete").length == 0){
        $("#userCourseTable").after('<button id="courseDelete" class="btn btn-danger" style="float:right; margin-right: 9px; margin-bottom: 10px;">删除</button>' +
            '<button id="courseSubmit" class="btn btn-default" style="float:right; margin-right: 9px; margin-bottom: 10px;">提交</button>');
    }

    $("#courseSubmit").bind("click", courseSubmit);
    $("#courseDelete").bind("click", courseDelete);
    $("input", "#userCourseTable").bind("input", function (e) {
        var target = e.target;
        var tr = $(target).parent().parent();
        tr.attr("class", "modified");
    });
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
    userCourseTable = $(".table", userCourse);

    console.log("ready");

    $("a", "#main-left").on("click", function (e) {

        $("a", "#main-left").removeClass("active");
        $(e.target).addClass("active");
        var elem = e.target.innerText;
        var _url;
        switch (elem) {
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
                method: "POST",
                error: function () {
                    alert("请求异常");
                },
                success: function (data) {
                    console.log("success");
//                    alert(data);
                    var _data = JSON.parse(data);
                    var caseid = _data.case;
                    switch (caseid) {
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
RequestType = {
    PerRequest: "PerRequest",
    ListCourse: "ListCourse",
    ModifyCourse: "ModifyCourse",
    ModifyPass: "ModifyPass"
};

function initTable(selector, disPlayHeader, data, caseID) {
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
