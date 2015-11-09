/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/9/2015.
 */
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
                    return '<a href="/regimen?id='+ data.id +  '" >' + data.illness.name + '</a>';
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
                    return '<a href="/regimen?id='+ data.id +  '" class="btn btn-danger">Delete</a>';
                },
                "width": "20%"
            }
        ]
    } );
    console.log("-- end --");
});
