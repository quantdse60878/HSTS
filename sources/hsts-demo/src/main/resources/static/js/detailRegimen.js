/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/10/2015.
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
            "url": "/phase/list",
            "dataSrc": "dataList",
            "page": "pageNumber",
            "pages": "pageSize",
            "recordsTotal": "totalElements",
            "recordsFiltered": "numberOfElements",
            "type": "GET",
            "data": {
                regimenId: $("#regimenId").val()
            }
        },

        "columns": [
            // col 1
            {

                "data": "null",
                "render": function (data, type, full, meta) {
                    return count++;
                },
                "width": "15%"
            },
            {
                "data": "numberOfDay",
                "width": "25%"
            },
            // col 3
            {
                "data": "updateTime",
                "width": "40%"
            },
            // col 4
            {
                "data": "id",
                "render": function ( data, type, full, meta ) {
                    return '<a href="/detailPhase?id='+ data +  '" class="btn btn-danger">Update</a>';
                },
                "width": "20%"
            }
        ]
    } );
    console.log("-- end --");
});
