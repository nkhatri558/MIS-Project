/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var userId;
function saveUser() {
    var fullName = $("#inputFullName").val();
    var email = $("#inputEmail").val();
    var password = $("#inputPassword").val();
    $.ajax({
        type: "POST",
        url: "UserController",
        data: {
            action: "add",
            fullName: fullName,
            email: email,
            password: password
        },
        success: function (data, textStatus, jqXHR) {
            showData();
            $("html").animate({scrollTop: $(document).height()}, 1000);
        },
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function showData() {
    $("#submitBtn").val("Add New User");
    $.ajax({
        url: "UserController",
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
                html += "<td >" + data[i].email + "</td>";
                html += "<td >" + data[i].password + "</td>";
                html += "<td class='text-center py-0 align-middle'><div class='btn-group btn-group-sm'>";
                html += "<a type='button' class='btn btn-info' onclick='getData(" + data[i].userId + ");'><i class='fas fa-pencil-alt'></i></a>";
                html += "<a type='button' class='btn btn-danger' onclick='deleteData(" + data[i].userId + ");'><i class='fas fa-trash'></i></a>";
                html += "</div></td>";
                html += "</tr>";
            }
            $("#tab").html(html);
        }
    });
}
function getData(id) {
    userId = id;
    $.ajax({
        url: "UserController",
        method: "POST",
        async: false,
        data: {
            action : "getUserRecord",
            userId : userId
        },
        success: function (data) {
            var data = JSON.parse(data);
            $("#inputFullName").val(data.fullName);
            $("#inputEmail").val(data.email);
            $("#inputPassword").val(data.password);
            $("#submitBtn").val("Update User");
            $("html").animate({scrollTop: 0}, "slow");
        },
        error: function(data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function updateData() {
    var fullName = $("#inputFullName").val();
    var email = $("#inputEmail").val();
    var password = $("#inputPassword").val();
    var id = userId;
    $.ajax({
        type: "POST",
        url: "UserController",
        data: {
            action: "update",
            fullName: fullName,
            email: email,
            password: password,
            userId: id
        },
        success: function (data, textStatus, jqXHR) {
            showData();
            $("html").animate({scrollTop: $(document).height()}, 1000);
        },
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function deleteData(id) {
    userId = id;
    $.ajax({
        type: "POST",
        url: "UserController",
        data: {
            action: "delete",
            userId: userId
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
function changePassword() {
    var password = $("#inputNewPassword").val();
    var studentId = $("#studentId").val();
    $.ajax({
        type: "POST",
        url: "UserController",
        data: {
            action: "changePassword",
            password: password,
            studentId: studentId
        },
        success: function (data, textStatus, jqXHR) {
            window.location.href = "student_dashboard.jsp";
        },
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function updateAdminPassword() {
    var password = $("#inputNewPassword").val();
    var userId = $("#userId").val();
    $.ajax({
        type: "POST",
        url: "UserController",
        data: {
            action: "changeAdminPassword",
            password: password,
            userId: userId
        },
        success: function (data, textStatus, jqXHR) {
            window.location.href = "index.jsp";
        },
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
$(document).ready(function () {
    showData();
    $(function () {
        $("#example1").DataTable({
            "responsive": true,
            "autoWidth": false
        });
    });
    $("#userForm").validate({
        rules: {
            fullName: {
                required: true
            },
            email: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            fullName: {
                required: "Please Enter Full Name"
            },
            email: {
                required: "Please Enter Email"
            },
            password: {
                required: "Please Enter Password"
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
    $("#changePasswordForm").validate({
        rules: {
            changePassword: {
                required: true
            },
            newPassword: {
                required: true
            },
            confirmPassword: {
                required: true
            }
        },
        messages: {
            changePassword: {
                required: "Please Enter Current Password"
            },
            newPassword: {
                required: "Please Enter New Password"
            },
            confirmPassword: {
                required: "Please Re-enter New Password"
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
    if($("#userForm").valid()) {
        if($("#submitBtn").val() === "Add New User") {
            saveUser();
        }
        else if($("#submitBtn").val() === "Update User"){
            updateData();
        }
    }
});
if (window.history.replaceState) {
    window.history.replaceState(null, null, window.location.href);
}
$("#changeBtn").on("click", function() {
    if($("#changePasswordForm").valid()) {
        if($("#inputNewPassword").val() !== $("#inputConfirmPassword").val()) {
            alert("New Password and Confirm Password Should be same");
        }
        else {
            if($("#form").val() === 'studentPasswordChange') {
                changePassword();
            } else if($("#form").val() === 'adminPasswordChange') {
                updateAdminPassword();
            }
        }
    }
});

