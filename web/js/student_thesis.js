/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function getBatches() {
    $.ajax({
        url: "StudentThesisController",
        method: "POST",
        async: false,
        data: {
            action: "viewBatches"
        },
        success: function (data) {

            var data = JSON.parse(data);
            console.info(data);
            var html = "";
            html += "<option>Select Any Batch</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option>" + data[i].batchName + "</option>";
            }
            $("#batchId").html(html);
        }
    });
}
function addData() {
    var form = $("#thesisForm")[0];
    var data = new FormData(form);
    $.ajax({
        type: "POST",
        url: "StudentThesisController",
        data: data,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function (data, textStatus, jqXHR) {
           
        }
        ,
        error: function (data, textStatus, jqXHR) {
            alert("Data is not added");
        }
    });
}
function getAllThesis() {
    var studentId = $('#studentSessionId').val();
    $.ajax({
        url: "StudentThesisController",
        type: "POST",
        aysnc: false,
        data: {
            action: "view",
            id: studentId
        },
        success: function (data) {
            var data = JSON.parse(data);
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr><td >" + data[i].student.rollNumber + "</td>";
                html += "<td >" + data[i].appliedDate + "</td>";
                if (data[i].finalResults != 0) {
                    html += "<td >" + data[i].finalResults + "</td>";
                } else {
                    html += "<td >In Process</td>";
                }
                html += "<td >" + data[i].remarks + "</td>";
                html += "<td >" + data[i].status + "</td>";
                html += "</td></tr>";
            }
            $("#tab").html(html);
        }
    });
}

$(document).ready(function () {
    $(function () {
        $("#example1").DataTable({
            "responsive": true,
            "autoWidth": false
        });
        $("#thesisForm").validate({
            rules: {
                thesisTitle: {
                    required: true
                },
                batch: {
                    required: true
                },
                examDate: {
                    required: true
                },
                uploadImage: {
                    required: true
                }
            },
            messages: {
                thesisTitle: {
                    required: "Please Write Title"
                },
                batch: {
                    required: "Please Select Batch"
                },
                examDate: {
                    required: "Please Select Date"
                },
                uploadImage: {
                    required: "Please Select File"
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
    $("#submitBtn").click(function () {
        var batchName = $("#batchId option:selected").val();
        $("#inputBatchName").val(batchName);
        addData();
    });
    getAllThesis();
    getBatches();
});

