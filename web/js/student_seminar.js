/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function showStudentSeminarRecords() {
    var studentId = $("#studentSessionId").val();
    $.ajax({
        url: "SeminarGroupController",
        method: "POST",
        async: false,
        data: {
            action: "showStudentSeminarRecords",
            studentId : studentId
        },
        success: function (data) {
            var data = JSON.parse(data);
            console.log(data);
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr><td >" + data[i].seminar.seminarTitle + "</td>";
                //html += "<td >" + data[i].thesis.thesisTitle + "</td>";
//                html += "<td >" + data[i].student.rollNumber + "</td>";
                html += "<td >" + data[i].dateApplied + "</td>";
                html += "<td >" + data[i].dateConducted + "</td>";
                html += "<td >" + data[i].remarks + "</td>";
                html += "<td >" + data[i].comments + "</td>";
                //html += "<td class='text-center py-0 align-middle'><div class='btn-group btn-group-sm'>";
                //html += "<a type='button' class='btn btn-info' onclick='getData(" + data[i].feesId + ");'><i class='fas fa-pencil-alt'></i></a>";
                //html += "<a type='button' class='btn btn-danger' onclick='deleteData(" + data[i].feesId + ");'><i class='fas fa-trash'></i></a>";
                //html += "</div></td>";
                html += "</tr>";
            }
            $("#tab").html(html);
        }
    });
}

function populateSeminarTitles() {
    var studentId = $("#studentSessionId").val();
    var batchId = $("#batchId").val();
    $.ajax({
        url: "SeminarGroupController",
        method: "POST",
        async: false,
        data : {
            action: "getStudentSeminarRecords",
            studentId: studentId,
            batchId: batchId
        },
        success: function (data) {
            var data = JSON.parse(data);
            var html = "<option selected disabled>Select Seminar</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value="+data[i].seminarId+">"+data[i].seminarTitle+"</option>";
            }
            $("#inputSeminar").html(html);
        } 
    });
}

function applySeminar() {
    var seminarId = $("#inputSeminar").val();
    var studentId = $("#studentSessionId").val();
    $.ajax({
        url: "SeminarGroupController",
        method: "POST",
        async: false,
        data : {
            action: "applySeminar",
            seminarId: seminarId,
            studentId: studentId
        },
        success: function (data) {
            
        } 
    });
}

$(document).ready( function() {
    showStudentSeminarRecords();
    populateSeminarTitles();
    $(function () {
        $("#example1").DataTable({
            "responsive": true,
            "autoWidth": false
        });
    });
    $("#seminarForm").validate({
        rules: {
            seminarTitle: {
                required: true
            }
        },
        messages: {
            seminarTitle: {
                required: "Select Seminar Title"
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
    $("#applyBtn").on("click", function() {
        if($("#seminarForm").valid()) {
            applySeminar();
        }
    });
    $("#applySeminarBtn").on("click", function() {
        $("#myModal").modal("show");
    });
});