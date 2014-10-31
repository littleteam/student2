/**
 * Created by 斌 on 2014/10/28.
 */
$.ajaxSetup({cache:false});
// 处理 个人信息 表格填充
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
// 处理查看 个人信息
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
// 填充 查看课程 表格
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
            $(_tds[i]).after('<td><input type="checkbox" /></td>');
        }
    } else {
        tds.text(function (d) {
            return d;
        });
    }
}

CourseDeleted = [];
CourseModified = [];
// 处理 查看课程 表格 删除行 操作
// 如有 存在空值的行 ,设置其class 为dropped
function courseDelete() {
    // 获取所有选中元素 行级元素
    var checkedboxs = $('input[type=checkbox]:checked', userCourseTable);
    var trs = checkedboxs.parent().parent();
    for(var isNul = 0; isNul< trs.length; isNul++){
        // 否则继续执行
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
}
var arrayUnique = function (a) {
    return a.reduce(function (p, c) {
        if (p.indexOf(c) < 0) p.push(c);
        return p;
    }, []);
};
/**
 * Course实体,添加操作类型
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

// 存在空值 返回TRUE, 否则FALSE
function isLastTrHasNull(selector) {
    var lastNewTr;
    if(selector) {
        lastNewTr = $(selector);
    } else {
        lastNewTr = $("tr[class=added]:last-child", "#userCourseTable");
    }

    if(lastNewTr.length == 1) {
        var tr = lastNewTr[0];
        var inputs = $('input[type!=checkbox]', tr);
        for(var i = 0; i < inputs.length; i++){
            if(inputs[i].value == ""){
                return true;
            }
        }
        return false;
    }
}
// 处理 课程表格 数据生成
function dealCourseDataTrans() {
    var course = [];
    // 获取 修改过的 行
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
    //获取 删除的 行, 删除请求中只需要id足矣.
    var deletedTrs = $(".bounceOutRight", "#userCourseTable");
    for (var ii = 0; ii < deletedTrs.length; ii++) {
        var _tr = deletedTrs[ii];
        if(isLastTrHasNull(_tr)){
            // 存在空值,跳过提交
            continue;
        }
        var _inputs = $('input', _tr);
        var _cou_id = _inputs[1].value;
        var _modifiedRow = new CourseEntity("delete", _cou_id);

        course.push(_modifiedRow);
    }
    // 获取添加的行
    var addedTrs = $(".added", "#userCourseTable");
    for (var added_i = 0; ii < addedTrs.length; ii++) {
        var _added_tr = addedTrs[added_i];
        if(isLastTrHasNull(_added_tr)){
            // 存在空值,跳过提交
            continue;
        }
        var _added_inputs = $('input', _added_tr);
        var _added_cou_id = _added_inputs[1].value;
        var _cou_grade = _added_inputs[0].value;
        var _cou_name = _added_inputs[2].value;
        var _cou_sch_name = _added_inputs[3].value;
        var _added_modifiedRow = new CourseEntity("added", _added_cou_id, _cou_grade, _cou_name, _cou_sch_name);

        course.push(_added_modifiedRow);
    }

    return course;
}
// 处理 课程 提交事件
function courseSubmit() {
    if(isLastTrHasNull()){
        //todo: 显示提示,不能为空;
        sweetAlert("Oops...", "尚未完成此记录添加", "error");
        return ;
    }

    var data = dealCourseDataTrans();
    if(data.length < 1){
        return;
    }
    swal({
            title: "要提交么?",
            text: "所做更改将无法撤销！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "是的，我要提交",
            closeOnConfirm: false
        },
        function(){
            $.ajax({
                url: "/QueryModifyCourse",
                data: {stringJson: JSON.stringify(data)},
                method: "POST",
                async: true,
                error: function () {
                    alert("请求异常");
                },
                success: function (data) {
                    var _data = JSON.parse(data);
                    var result = _data.result;

                    if (result == "error") {
                        swal("Oops...", "出了点问题，提交失败。", "error")
                    } else {
                        swal("已提交", "请求已成功提交。", "success");
                    }
                }
            });
        }
    );


}
// 处理 添加按钮
function courseAdd() {
    if(isLastTrHasNull()){
        //todo: 显示提示,不能为空;
        sweetAlert("Oops...", "尚未完成此记录添加", "error");
        return ;
    }

    var th_num = $("th", "#userCourseTable").length - 1;
    var tr_html = '<tr class="added">';
    for(var i = 0; i < th_num; i++){
        tr_html += '<td><input class="form-control" value=""/></td>';
    }
    tr_html += '<td><input type="checkbox" /></td>' +
        '</tr>';

    $("tbody tr:last-child", "#userCourseTable").after(tr_html);
}
// 处理查看课表请求
function dealListCourse(_data) {
    // 将右侧div清空
    main_right.empty();

    var tbody = initTable($("#userCourseTable", userCourse)[0], ["课程年级", "课程ID", "课程名", "所在学院", ""], _data);
    fillListCourse(tbody, _data);
    main_right.append(userCourse);

    // 是管理员 且 删除按钮并未添加
    if(isAdmin && $("#courseDelete").length == 0){
        $("#userCourseTable").after('<button id="courseAdd" class="btn btn-default">添加</button>' +
                '<button id="courseDelete" class="btn btn-danger">删除</button>' +
            '<button id="courseSubmit" class="btn btn-default">提交</button>');
    }
    var btn_cou_del = $("#courseDelete");
    var btn_cou_sub = $("#courseSubmit");
    var btn_cou_add = $("#courseAdd");
    // 为按钮绑定点击事件, 每次元素从页面清除都必须重新绑定.
    btn_cou_sub.bind("click", courseSubmit);
    btn_cou_del.bind("click", courseDelete);
    btn_cou_add.bind("click", courseAdd);
    // 为输入框添加输入事件绑定, 每次修改器父级元素 tr class为 modified
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
                method: "get",
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
