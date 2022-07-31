/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var facultyId;
function saveFaculty() {
    var facultyName = $("#inputFaculty").val();
    $.ajax({
        type: "POST",
        url: "FacultyController",
        data: {
            action: "add",
            facultyName: facultyName
        },
        success: function (data, textStatus, jqXHR) {
            resetFields();
            showData();
            $("html").animate({scrollTop: $(document).height()}, 1000);
        },
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function showData() {
    $("#submitBtn").val("Add New Faculty");
    $.ajax({
        url: "FacultyController",
        method: "POST",
        async: false,
        data: {
            action: "view"
        },
        success: function (data) {
            var data = JSON.parse(data);
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr><td >" + data[i].facultyName + "</td>";
                html += "<td class='text-center py-0 align-middle'><div class='btn-group btn-group-sm'>";
                html += "<a type='button' class='btn btn-info' onclick='getData(" + data[i].facultyId + ");'><i class='fas fa-pencil-alt'></i></a>";
                html += "<a type='button' class='btn btn-danger' onclick='deleteData(" + data[i].facultyId + ");'><i class='fas fa-trash'></i></a>";
                html += "</div></td>";
                html += "</tr>";
            }
            $("#tab").html(html);
        }
    });
}
function getData(id) {
    facultyId = id;
    $.ajax({
        url: "FacultyController",
        method: "POST",
        async: false,
        data: {
            action : "getFacultyRecord",
            facultyId : facultyId
        },
        success: function (data) {
            var data = JSON.parse(data);
            $("#inputFaculty").val(data.facultyName);
            $("#submitBtn").val("Update Faculty");
            $("html").animate({scrollTop: 0}, "slow");
        },
        error: function(data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function updateData() {
    var facultyName = $("#inputFaculty").val();
    var id = facultyId;
    $.ajax({
        type: "POST",
        url: "FacultyController",
        data: {
            action: "update",
            facultyId : id,
            facultyName : facultyName
        },
        success: function (data, textStatus, jqXHR) {
            resetFields();
            showData();
            $("html").animate({scrollTop: $(document).height()}, 1000);
        },
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function deleteData(id) {
    facultyId = id;
    $.ajax({
        type: "POST",
        url: "FacultyController",
        data: {
            action: "delete",
            facultyId: facultyId
        },
        success: function (data, textStatus, jqXHR) {
            resetFields();
            showData();
            $("html").animate({scrollTop: $(document).height()}, 1000);
            location.reload();
        },
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function resetFields() {
    $("#inputFaculty").val('');
}
$(document).ready(function () {
    showData();
    $(function () {
        $("#example1").DataTable({
            "responsive": true,
            "autoWidth": false
        });
    });
    $("#facultyForm").validate({
        rules: {
            facultyName: {
                required: true
            }
        },
        messages: {
            facultyName: {
                required: "Please Enter Faculty Name"
            }
        },
        errorElement: 'span',
        errorPlacement: function (error, element) {
            error.addClass('invalid-feedback');
            element.closest('.form-group').append(error);
        },
        highlight: function (element, errorClass, validClass) {
            $(element).addClass('is-invalid');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass('is-invalid');
        }
    });
});
$("#submitBtn").on("click", function() {
    if($("#facultyForm").valid()) {
        if($("#submitBtn").val() === "Add New Faculty") {
            saveFaculty();
        }
        else if($("#submitBtn").val() === "Update Faculty"){
            updateData();
        }
    }
});
if (window.history.replaceState) {
    window.history.replaceState(null, null, window.location.href);
}

