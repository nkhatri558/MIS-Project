/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global batchName */

var batchId;
function saveBatch() {
    var batchName = $("#inputBatch").val();
    $.ajax({
        type: "POST",
        url: "BatchController",
        data: {
            action: "add",
            batchName: batchName
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
    $("#submitBtn").val("Add New Batch");
    $.ajax({
        url: "BatchController",
        method: "POST",
        async: false,
        data: {
            action: "view"
        },
        success: function (data) {
            var data = JSON.parse(data);
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr><td >" + data[i].batchName + "</td>";
                html += "<td class='text-center py-0 align-middle'><div class='btn-group btn-group-sm'>";
                html += "<a type='button' class='btn btn-info' onclick='getData(" + data[i].batchId + ");'><i class='fas fa-pencil-alt'></i></a>";
                html += "<a type='button' class='btn btn-danger' onclick='deleteData(" + data[i].batchId + ");'><i class='fas fa-trash'></i></a>";
                html += "</div></td>";
                html += "</tr>";
            }
            $("#tab").html(html);
        }
    });
}
function getData(id) {
    batchId = id;
    $.ajax({
        url: "BatchController",
        method: "POST",
        async: false,
        data: {
            action : "getBatchRecord",
            batchId : batchId
        },
        success: function (data) {
            var data = JSON.parse(data);
            $("#inputBatch").val(data.batchName);
            $("#submitBtn").val("Update Batch");
            $("html").animate({scrollTop: 0}, "slow");
        },
        error: function(data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function updateData() {
    var batchName = $("#inputBatch").val();
    var id = batchId;
    $.ajax({
        type: "POST",
        url: "BatchController",
        data: {
            action: "update",
            batchId : id,
            batchName: batchName
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
    batchId = id;
    $.ajax({
        type: "POST",
        url: "BatchController",
        data: {
            action: "delete",
            batchId: batchId
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
    $("#inputBatch").val('');
}
$(document).ready(function () {
    showData();
    $(function () {
        $("#example1").DataTable({
            "responsive": true,
            "autoWidth": false
        });
    });
    $("#batchForm").validate({
        rules: {
            batchName: {
                required: true
            }
        },
        messages: {
            batchName: {
                required: "Please Enter Batch Name"
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
    if($("#batchForm").valid()) {
        if($("#submitBtn").val() === "Add New Batch") {
            saveBatch();
        }
        else if($("#submitBtn").val() === "Update Batch"){
            updateData();
        }
    }
});
if (window.history.replaceState) {
    window.history.replaceState(null, null, window.location.href);
}

