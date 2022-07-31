
var id;
var imageMoveId = 0;
function saveImages() {
    var form = $("#imageUploadForm")[0];
    var data = new FormData(form);
    $.ajax({
        type: "POST",
        url: "UploadImagesController",
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
function getAllImages() {
    $.ajax({

        url: "UploadImagesController",
        type: "POST",
        enctype: "multipart/form-data",
        async: false,
        data: {
            action: "view"
        },
        success: function (data) {
            var data = JSON.parse(data);
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr><td ><a href='" + data[i].link + "' target='_blank'>" + data[i].link + "</a></td>";
                html += "<td >" + data[i].path + "</td>";
                html += "<td class='text-center py-0 align-middle'><div class='btn-group btn-group-sm'>";
                html += "<a type='button' class='btn btn-danger' onclick='deleteData(" + data[i].id + ");'><i class='fas fa-trash'></i></a>";
                html += "</div></td>";
                html += "</tr>";
            }
            $("#tab").html(html);
        }
    });
}
function deleteData(id) {
    var imageId = id;
    $.ajax({
        url: "UploadImagesController",
        type: "POST",
        async: false,
        data: {
            action: "delete",
            imageId: imageId,
        },
        success: function () {
            getAllImages();
        }

    });
}
function uploadImages() {
    $.ajax({
        url: "UploadImagesController",
        type: "POST",
        enctype: "multipart/form-data",
        async: false,
        data: {
            action: "view"
        },
        success: function (data) {
            var data = JSON.parse(data);
            var html = "";
            for (var i = 0; i < data.length; i++) {

                $('<div class="carousel-item"><img src="' + data[i].link + '" width="1400" height="500"><div class="carousel-caption"></div></div>').appendTo('.carousel-inner');
                $('<li data-target="#carouselExampleIndicators" data-slide-to="' + i + '"></li>').appendTo('.carousel-indicators')
            }
            $('.carousel-item').first().addClass('active');
            $('.carousel-indicators > li').first().addClass('active');
            $('#carouselExampleIndicators').carousel();
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
    $('#submitBtn').click(function () {
        saveImages();
    });
    getAllImages();
    uploadImages();
});
