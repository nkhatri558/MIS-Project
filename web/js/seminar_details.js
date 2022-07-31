
var seminarGroupId;

function showData() {
    $.ajax({
        url: "SeminarGroupController",
        method: "POST",
        async: false,
        data: {
            action: "view"
        },
        success: function (data) {
          
            var data = JSON.parse(data);
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr><td >" + (i + 1) + "</td>";
                html += "<td >" + data[i].seminar.seminarTitle + "</td>";
//                html += "<td >" + data[i].thesis.thesisTitle + "</td>";
                html += "<td >" + data[i].student.rollNumber + "</td>";
                html += "<td >" + data[i].dateApplied + "</td>";
                html += "<td >" + data[i].dateConducted + "</td>";
                html += "<td >" + data[i].remarks + "</td>";
                html += "<td >" + data[i].comments + "</td>";
                html += "<td >" + data[i].status + "</td>";
                html += "<td class='text-center py-0 align-middle'><div class='btn-group btn-group-sm'>";
                html += "<a type='button' class='btn btn-info' onclick='getData(" + data[i].seminarGroupId + ");'  data-toggle='modal'  data-target='#myModal' ><i class='fas fa-pencil-alt'></i></a>";
                html += "<a type='button' class='btn btn-danger' onclick='deleteData(" + data[i].seminarGroupId + ");'><i class='fas fa-trash'></i></a>";
                html += "</div></td>";
                html += "</tr>";
            }
            $("#tab").html(html);
        }
    });
}
function deleteData(id) {
    seminarGroupId = id;
    $.ajax({
        type: "POST",
        url: "SeminarGroupController",
        data: {
            action: "delete",
            seminarGroupId: seminarGroupId
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
    seminarGroupId = id;
    $.ajax({
        url: "SeminarGroupController",
        method: "POST",
        async: false,
        data: {
            action: "getSeminarGroupRecord",
            seminarGroupId: seminarGroupId
        },
        success: function (data) {
            var data = JSON.parse(data);
            $('#inputSeminarId').val(data.seminar.seminarId);
            //$('#inputThesisId').val(data.thesis.thesisId);
            $('#inputSeminarTitle').val(data.seminar.seminarTitle);
            //$('#inputThesisTitle').val(data.thesis.thesisTitle);
            $('#inputAppliedDate').val(data.dateApplied);
            alert(data.dateConducted);
            $('#inputDateOfConduct').val(data.dateConducted);
            $('#inputRemarks').val(data.remarks);
            $('#inputComments').val(data.comments);
            $('#inputStatus').val(data.status);
            showData();
            $("html").animate({scrollTop: $(document).height()}, 1000);
            location.reload();
        },
        error: function (data, textStatus, jqXHR) {
            alert("Error");
        }
    });
}
function updateData() {
    var seminarId = $('#inputSeminarId').val();
    //var thesisId=$('#inputThesisId').val();
    var seminarTitle = $('#inputSeminarTitle').val();
    //var thesisTitle = $('#inputThesisTitle').val();
    var dateApplied = $('#inputAppliedDate').val();
    var dateConducted = $('#inputDateOfConduct').val();
    var remarks = $('#inputRemarks').val();
    var comments = $('#inputComments').val();
    var status = $('#inputStatus').val();
    var id = seminarGroupId;
    $.ajax({
        type: "POST",
        url: "SeminarGroupController",
        data: {
            action: "update",
            seminarId: seminarId,
            //thesisId: thesisId,
            seminarGroupId: id,
            seminarTitle: seminarTitle,
            //thesisGroup: thesisTitle,
            dateApplied: dateApplied,
            dateConducted: dateConducted,
            remarks: remarks,
            comments: comments,
            status: status
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
$(document).ready(function () {
    showData();
    $(function () {
        $("#example1").DataTable({
            "responsive": true,
            "autoWidth": false
        });
    });
    $(window).on('shown.bs.modal', function (e) {

        $('#myModal').modal('show');
        window.stop();
    });
    $('#updateModal').click(function(){
        updateData();
        alert("Data is updated");
        location.reload();
    });
    
});
//$(document).ready(function(){
// 
//});
//function updateAuto(){
//    getData(2);
//}

