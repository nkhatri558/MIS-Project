
var studentId;
function saveStudent() {
    var studentName = $("#studentName").val();
    var studentRollNo = $("#studentRollNo").val();
    $.ajax({
        type: "post",
        url: "StudentController",
        data: {
            action: "add",
            studentName: studentName,
            studentRollNo: studentRollNo
        },
        success: function (data) {
            if (studentName === "" && studentRollNo === "") {
                alert("Fill all the fields");
            } else {
                alert("Working");
            }
        }
    });
}
function showData() {
    //  $("#submitBtn").val("Add New Faculty");
    $.ajax({
        url: "StudentController",
        method: "POST",
        async: false,
        data: {
            action: "view"
        },
        success: function (data) {
            var data = JSON.parse(data);
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr><td >" + data[i].fullName + "</td>";
                html += "<td >" + data[i].rollNumber + "</td>";
                html += "<td >" + data[i].registrationStatus + "</td>";
                html += "<td class='text-center py-0 align-middle'><div class='btn-group btn-group-sm'>";
                if (data[i].registrationStatus === "registered") {
                    html += "<a type='button' class='btn btn-info'  href='student_profile.jsp?id=" + data[i].studentId + "' onclick='getData(" + data[i].studentId + ");'><i class='fas fa-eye'></i></a>";
                }
                html += "<a type='button' class='btn btn-danger' onclick='deleteData(" + data[i].studentId + ");'><i class='fas fa-trash'></i></a>";
                html += "</div></td>";
                html += "</tr>";
            }
            $("#tab").html(html);
        }
    });
}
function deleteData(id) {
    studentId = id;
    $.ajax({
        type: "POST",
        url: "StudentController",
        data: {
            action: "delete",
            studentId: studentId
        },
        success: function (data, textStatus, jqXHR) {

            showData();
            $("html").animate({scrollTop: $(document).height()}, 1000);
            location.reload();
        },
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function getData(id) {
    studentId = id;
    $.ajax({
        url: "StudentController",
        method: "POST",
        async: false,
        data: {
            action: "getStudentRecord",
            studentId: studentId
        },
        success: function (data, textStatus, jqXHR) {

            showData();
            $("html").animate({scrollTop: $(document).height()}, 1000);
            location.reload();
        },
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function uploadStudentData() {
    var form = $("#studentUploadFormId")[0];
    var data = new FormData(form);
    $.ajax({
        type: "POST",
        url: 'StudentController',
        data: data,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function (data, textStatus, jqXHR) {
        }
        ,
        error: function (data, textStatus, jqXHR) {
            alert("Select Any Image");
        }
    });
}
$(document).ready(function () {
    $(function () {
        $("#example1").DataTable({
            "responsive": true,
            "autoWidth": false
        });
    });
    showData();
    $('#submitBtn1').click(function () {
       // saveStudent();
        uploadStudentData();
        showData();
    });
    
});