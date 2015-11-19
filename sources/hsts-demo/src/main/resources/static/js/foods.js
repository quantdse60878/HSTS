/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/19/2015.
 */
var enableDeletion = false;
$(document).ready(function(){

    var count = 1;
    var table = $('#dataContent').dataTable( {
        "processing": true,
        "pagingType": "full",
        "paging": true,
        "lengthChange": false,
        "ordering": true,
        "info": true,

        "ajax": {
            "url": "/food/list",
            "dataSrc": "dataList",
            "page": "pageNumber",
            "pages": "pageSize",
            "recordsTotal": "totalElements",
            "recordsFiltered": "numberOfElements",
            "type": "GET"
        },

        "columns": [
            { "data": "null",
                "render": function (data, type, full, meta) {
                    return count++;
                },
                "width": "10%"
            },
            {   "data": {
                    "id" : "id",
                    "name": "name"
                },
                "render": function (data, type, full, meta) {
                    return '<a href="/food?id=' + data.id + '">'+ data.name + '</a>';
                },
                "width": "20%"
            },
            { "data": "units",
                "render": function (data, type, full, meta) {
                    if (data != null && data.length > 0) {
                        var render = '';
                        $.each(data, function (key, element) {
                           render += element.foodName + ", ";
                        });
                        var index = render.lastIndexOf(", ");
                        render = render.substring(0, index);
                        return render;
                    }
                    return 'N/A';
                },
                "width": "20%"
            },
            {
                "data": "id",
                "render": function ( data, type, full, meta ) {
                    var btnUpdate = '<a href="/food?id=' + data + '" class="btn btn-success">Update</a>';
                    var btnDelete =  '<a onclick="deleteDialog('+ data +')" class="btn btn-danger">Delete</a>';
                    return btnUpdate + btnDelete;
                },
                "width": "40%"
            }
        ]

    } );
});

function deleteDialog(element) {
    console.log("-- begin delete --");
    if (!enableDeletion) {
        $("#messageLabel").html("Deletion function is disable. Delete food may cause old prescription became wrong!!!");
        $("#messageModal").modal('show');
        console.log("-- disable delete --");
    } else {
        // TODO
    }
    console.log("-- end delete --");
}