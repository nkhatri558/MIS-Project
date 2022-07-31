/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var seminarId;
function saveSeminar() {
    var seminarTitle = $("#inputSeminarTitle").val();
    var announcementDate = $("#inputAnnouncementDate").val();
    var dueDate = $("#inputDueDate").val();
    var batch = $("#inputBatch").val();
    alert(batch);
    $.ajax({
        type: "POST",
        url: "SeminarController",
        data: {
            action: "add",
            seminarTitle: seminarTitle,
            announcementDate: announcementDate,
            dueDate: dueDate,
            batch: batch
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
function populateBatches() {
    $.ajax({
        url: "BatchController",
        method: "POST",
        async: false,
        data: {
            action: "view"
        },
        success: function (data) {
            var data = JSON.parse(data);
            var html = "<option selected disabled value='0'>Select Batch</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='" + data[i].batchId + "'>" + data[i].batchName + "</option>";
            }
            $("#inputBatch").html(html);
        }
    });
}
function showData() {
    $("#submitBtn").val("Add");
    $.ajax({
        url: "SeminarController",
        method: "POST",
        async: false,
        data: {
            action: "view"
        },
        success: function (data) {
            var data = JSON.parse(data);
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr><td >" + data[i].seminarTitle + "</td>";
                html += "<td >" + data[i].announcementDate + "</td>";
                html += "<td >" + data[i].dueDate + "</td>";
                html += "<td >" + data[i].batch.batchName + "</td>";
                html += "<td class='text-center py-0 align-middle'><div class='btn-group btn-group-sm'>";
                html += "<a type='button' class='btn btn-info' onclick='getData(" + data[i].seminarId + ");'><i class='fas fa-pencil-alt'></i></a>";
                html += "<a type='button' class='btn btn-danger' onclick='deleteData(" + data[i].seminarId + ");'><i class='fas fa-trash'></i></a>";
                html += "</div></td>";
                html += "</tr>";
            }
            $("#tab").html(html);
        }
    });
}
function getData(id) {
    seminarId = id;
    $.ajax({
        url: "SeminarController",
        method: "POST",
        async: false,
        data: {
            action: "getSeminarRecord",
            seminarId: seminarId
        },
        success: function (data) {
            var data = JSON.parse(data);
            $("#inputSeminarTitle").val(data.seminarTitle);
            $("#inputAnnouncementDate").val(data.announcementDate);
            $("#inputDueDate").val(data.dueDate);
            $("#inputBatch").val(data.batch.batchId);
            $("#submitBtn").val("Update");
            $("html").animate({scrollTop: 0}, "slow");
        },
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function updateData() {
    var seminarTitle = $("#inputSeminarTitle").val();
    var announcementDate = $("#inputAnnouncementDate").val();
    var dueDate = $("#inputDueDate").val();
    var batch = $("#inputBatch").val();
    var id = seminarId;
    $.ajax({
        type: "POST",
        url: "SeminarController",
        data: {
            action: "update",
            seminarTitle: seminarTitle,
            announcementDate: announcementDate,
            dueDate: dueDate,
            batch: batch,
            seminarId: id
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
    seminarId = id;
    $.ajax({
        type: "POST",
        url: "SeminarController",
        data: {
            action: "delete",
            seminarId: seminarId
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
$(document).ready(function () {
    showData();
    populateBatches();
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
            },
            announcementDate: {
                required: true
            },
            dueDate: {
                required: true
            },
            batch: {
                required: true
            }
        },
        messages: {
            seminarTitle: {
                required: "Please Enter Fees Title"
            },
            announcementDate: {
                required: "Please select Announcement Date"
            },
            dueDate: {
                required: "Please Select Due Date"
            },
            batch: {
                required: "Please select Batch"
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
    $("#submitBtn").on("click", function () {
        if ($("#seminarForm").valid()) {
            if ($("#submitBtn").val() === "Add") {
                saveSeminar();
            } else if ($("#submitBtn").val() === "Update") {
                updateData();
            }
        }
    });

});

if (window.history.replaceState) {
    window.history.replaceState(null, null, window.location.href);
}