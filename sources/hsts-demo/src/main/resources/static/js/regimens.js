/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/9/2015.
 */
var currentRegimen = 0;
var OK_STATUS = "{\"status\":\"ok\"}";
var FAIL_STATUS = "{\"status\":\"fail\"}";
$(document).ready(function(){
    console.log("-- begin --");
    var count = 1;
    var table = $('#dataContent').dataTable( {
        "processing": true,
        "pagingType": "full",
        "paging": true,
        "lengthChange": false,
        "ordering": true,
        "info": true,

        "ajax": {
            "url": "/regimenList",
            "dataSrc": "dataList",
            "page": "pageNumber",
            "pages": "pageSize",
            "recordsTotal": "totalElements",
            "recordsFiltered": "numberOfElements",
            "type": "GET"
        },

        "columns": [
            // col 1
            {

                "data": "null",
                "render": function (data, type, full, meta) {
                    return count++;
                },
                "width": "10%"
            },
            // col 2
            { "data": {
                "illness.name": "illness.name",
                "id": "id"
            },
                "render": function ( data, type, full, meta ) {
                    return '<a href="/detailRegimen?id='+ data.id +  '" >' + data.illness.name + '</a>';
                },
                "width": "30%"
            },
            // col 3
            {
              "data": "illness.description",
                "width": "30%"
            },
            // col 4
            {
              "data": "numberOfPhase",
                "width": "10%"
            },
            // col 5
            {
                "data": "id",
                "render": function ( data, type, full, meta ) {
                    var updateBtn = '<a href="/updateRegimen?id='+ data.id +  '" class="btn btn-success">Update</a>';
                    var deleteBtn = '<a onclick="deleteDialog('+ data +')" class="btn btn-danger">Delete</a>'
                    return updateBtn + deleteBtn;
                },
                "width": "20%"
            }
        ]
    } );
    console.log("-- end --");
});

function deleteDialog(regimenId) {
    console.log("-- begin --");
    currentRegimen = regimenId;
    $("#confirmDeleteModal").modal('show');
    console.log("-- end --");
}

$("#btnDelete").click(function() {
    console.log("begin delete");
    $.ajax({
        method: "POST",
        url: "/regimen/delete",
        data: {
            regimenId: currentRegimen
        }
    }).done(function(data) {
            console.log(data);
            var txtMessage = document.getElementById("messageLabel");
            if (data == FAIL_STATUS) {
                txtMessage.innerHTML = "Error while delete regimen data";
            } else {
                txtMessage.innerHTML = "Delete regimen data successfully";
            }
            $("#messageModal").modal('show');
        });
    console.log("end delete");
});