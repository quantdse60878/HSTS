/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/19/2015.
 */
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
            { "data": "name"
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
                    var btnUpdate = '<a onclick="updateDialog('+ data +')" class="btn btn-success" style="margin-right: 20px">Update</a>';
                    var btnDelete = '<a onclick="deleteDialog('+ data +')" class="btn btn-danger">Delete</a>';
                    return btnUpdate + btnDelete;
                },
                "width": "40%"
            }
        ]

    } );
});