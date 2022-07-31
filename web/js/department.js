/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var departmentId;
function saveDepartment() {
        var departmentName = $("#inputDepartment").val();
        var facultyId = $("#inputFaculty").val();
        //if(departmentName !== null) {
            $.ajax({
            type: "POST",
            url: "DepartmentController",
            data: {
                action: "add",
                departmentName: departmentName,
                facultyId: facultyId
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
        //}
        
    //}

}
function showData() {
    $("#submitBtn").val("Add New Department");
    $.ajax({
        url: "DepartmentController",
        method: "POST",
        async: false,
        data: {
            action: "view"
        },
        success: function (data) {
            var data = JSON.parse(data);
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr><td >" + data[i].departmentName + "</td>";
                html += "<td >" + data[i].faculty.facultyName + "</td>";
                html += "<td class='text-center py-0 align-middle'><div class='btn-group btn-group-sm'>";
                html += "<a type='button' class='btn btn-info' onclick='getData(" + data[i].departmentId + ");'><i class='fas fa-pencil-alt'></i></a>";
                html += "<a type='button' class='btn btn-danger' onclick='deleteData(" + data[i].departmentId + ");'><i class='fas fa-trash'></i></a>";
                html += "</div></td>";
                html += "</tr>";
            }
            $("#tab").html(html);
        }
    });
}
function getData(id) {
    departmentId = id;
    $.ajax({
        url: "DepartmentController",
        method: "POST",
        async: false,
        data: {
            action: "getDepartmentRecord",
            departmentId: departmentId
        },
        success: function (data) {
            var data = JSON.parse(data);
            $("#inputFaculty").val(data.faculty.facultyId);
            $("#inputDepartment").val(data.departmentName);
            $("#submitBtn").val("Update Department");
            $("html").animate({scrollTop: 0}, "slow");
        },
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function updateData() {
    var departmentName = $("#inputDepartment").val();
    var facultyId = $("#inputFaculty").val();
    var id = departmentId;
    $.ajax({
        type: "POST",
        url: "DepartmentController",
        data: {
            action: "update",
            departmentName: departmentName,
            facultyId: facultyId,
            departmentId: id
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
    departmentId = id;
    $.ajax({
        type: "POST",
        url: "DepartmentController",
        data: {
            action: "delete",
            departmentId: departmentId
        },
        success: function (data, textStatus, jqXHR) {
            resetFields();
            showData();
            location.reload();
            $("html").animate({scrollTop: $(document).height()}, 1000);
            alert("Data Deleted Successfully!");
        },
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function populateFaculties() {
    $.ajax({
        url: "FacultyController",
        method: "POST",
        async: false,
        data: {
            action: "view"
        },
        success: function (data) {
            var data = JSON.parse(data);
            var html = "<option value='' selected disabled>Select Faculty</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='" + data[i].facultyId + "'>" + data[i].facultyName + "</option>";
            }
            $("#inputFaculty").html(html);
        }
    });
}
function resetFields() {
    $("#inputDepartment").val('');
    $("#inputFaculty").val('1');
    showData();
    populateFaculties();
}
$(document).ready(function () {
    showData();
    populateFaculties();
    $(function () {
        $("#example1").DataTable({
            "responsive": true,
            "autoWidth": false
        });
    });
    $("#departmentForm").validate({
        rules: {
            departmentName: {
                required: true
            },
            facultyId: {
                required: true
            }
        },
        messages: {
            departmentName: {
                required: "Please Enter Department Name"
            },
            facultyId: {
                required: "Please Select Faculty"
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
    if($("#departmentForm").valid()) {
        if($("#submitBtn").val() === "Add New Department") {
            saveDepartment();
        }
        else if($("#submitBtn").val() === "Update Department"){
            updateData();
        }
    }
});
if (window.history.replaceState) {
    window.history.replaceState(null, null, window.location.href);
}
