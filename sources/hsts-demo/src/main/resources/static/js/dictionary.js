/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/28/2015.
 */
var OK_STATUS = "{\"status\":\"ok\"}";
var FAIL_STATUS = "{\"status\":\"fail\"}";
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
            "url": "/patientList",
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
            {   "data": "account.fullName",
                "width": "30%"
            },
            { "data": "account.birthday",
                "width": "20%"},
            { "data": "account.gender",
                "render": function (data, type, full, meta) {
                    var checkbox = '<input type="checkbox" checked="checked" class="checkB" disabled="disabled"/>';
                    if(data == "FEMALE") {
                        checkbox = '<input type="checkbox" class="checkB" disabled="disabled"/>';
                    }
                    return checkbox;
                },
                "width": "20%"
            },
            {
                "data": "id",
                "render": function ( data, type, full, meta ) {
                    return '<a href="patientProfile?patientId='+ data + '" class="btn btn-info">View</a>';
                },
                "width": "20%"
            }
        ]

    } );
});