var thesisId = 0;
function addThesis() {
    var form = $('#thesisForm')[0];
    var data = new FormData(form);

    $.ajax({

        url: "ThesisController",
        method: "POST",
        data: data,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        async: false,
        success: function (data, textStatus, jqXHR) {
           
        }
        ,
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function getBatches() {
    $.ajax({
        url: "ThesisController",
        method: "POST",
        async: false,
        data: {
            action: "viewBatches"
        },
        success: function (data) {       
            var data = JSON.parse(data);
            var html = "";
             html += "<option>Select Any Batch</option>";
            for (var i = 0; i < data.length; i++) {         
                html += "<option>" + data[i].batchName + "</option>";
            }
            $("#batchId").html(html);
        }
    });
}
function getAllThesis() {
    $.ajax({
        url: "ThesisController",
        type: "POST",
        aysnc: false,
        data: {
            action: "view"
        },
        success: function (data) {
            var data = JSON.parse(data);
            var html = "";
            for (var i = 0; i < data.length; i++) {

                html += "<tr><td >" + data[i].batch.batchName + "</td>";
                html += "<td >" + data[i].announcementDate + "</td>";
                html += "<td >" + data[i].dueDate + "</td>";
                html += "<td><a type='button' class='btn btn-info' onclick='getData(" + data[i].thesisId + ");'><i class='fas fa-pencil-alt'></i></a>";
                html += "<a type='button' class='btn btn-danger' onclick='deleteData(" + data[i].thesisId + ");'><i class='fas fa-trash'></i></a>";
                html += "</td></tr>";
            }
            $("#tab").html(html);
        }
    });
}
function getData(id) {
    thesisId = id;
    $.ajax({
        url: "ThesisController",
        type: "POST",
        aysnc: false,
        data: {
            action: "getData",
            thesisId: thesisId
        },
        success: function (data) {
            var data = JSON.parse(data);
            for (var i = 0; i < data.length; i++) {
                $("#selectedId").val(data[i].batch.batchId);
                $("#batchId").val(data[i].batch.batchName);
                $("#inputAnnouncementDate").val(data[i].announcementDate);
                $("#inputDueDate").val(data[i].dueDate);
            }

            $("#submitBtn").val("Update Thesis");

        }
    });
}
function updateThesis(){
   var announcementDate=$("#inputAnnouncementDate").val();
   var dueDate=$("#inputDueDate").val();
   var batchName=$("#batchId option:selected").val();
   var batchId=$("#selectedId").val();
   alert(thesisId);
       $.ajax({
        url: "ThesisController",
        type: "POST",
        async: false,
        data:{
          action:"updateThesis" ,
          announcementDate:announcementDate,
          dueDate:dueDate,
          batchName:batchName,
          batchId:batchId,
          thesisId:thesisId
        },
        success: function (data, textStatus, jqXHR) {
            
        }
        ,
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function deleteData(id){
    thesisId=id;
    $.ajax({
        url: "ThesisController",
        type: "POST",
        async: false,
        data:{
          action:"delete",
          thesisId:thesisId
        },
        success: function (data, textStatus, jqXHR) {
            getAllThesis();
        }
        ,
        error: function (data, textStatus, jqXHR) {
            alert("Error");
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
            batch: {
                required: true
            },
            announcementDate: {
                required: true
            },
            dueDate: {
                required: true
            }
        },
        messages: {
            batch: {
                required: "Please Select Batch"
            },
            announcementDate: {
                required: "Please Select Date"
            },
            dueDate: {
                required: "Please Select Date"
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
    
$("#submitBtn").on("click", function () {
    if ($("#submitBtn").val() === "New Thesis Announcement") {
       
            var batchName=$("#batchId option:selected").val();
            $("#selectedBatchOption").val(batchName);
            addThesis();
            getAllThesis();
        } else if ($("#submitBtn").val() === "Update Thesis") {
           
            updateThesis() ;
            getAllThesis();
        }
});
    getBatches();
    getAllThesis();
   
});