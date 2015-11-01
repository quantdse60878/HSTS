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
            //$.ajax({
            //    method: "GET",
            //    url: "/patientList",
            //    data: {}
            //})
            //.done(function(data) {
            //    console.log(data);
            //    $("#table-body").html("");
            //    var bodyContent = "";
            //    if(data.dataList != null) {
            //
            //        $.each( data.dataList , function( key, element ) {
            //            bodyContent += "<tr><td>" + (key + 1) + "</td>" +
            //                    "<td>" + element.account.fullName + "</td>" +
            //                    "<td>" + element.account.birthday + "</td>" +
            //                    "<td>" + element.account.gender + "</td>" +
            //                    "<td></td></tr>";
            //
            //        });
            //    }
            //    $("#table-body").html(bodyContent);
            //});

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
            { "data": {
                "account.fullName": "account.fullName",
                "id": "id"
            },
                "render": function ( data, type, full, meta ) {
                    return '<a href="/patient?patientID='+ data.id +  '" >' + data.account.fullName + '</a>';
                },
                "width": "30%"
            },
            { "data": "account.birthday",
                "width": "20%"},
            { "data": "account.gender",
                "render": function (data, type, full, meta) {
                    var className = "btn btn-info";
                    if(data == "FEMALE") {
                        className = "btn btn-danger";
                    }
                    return '<input type ="text" class="'+ className +'" value="' + data + '" style="width: 90px"/>';
                },
                "width": "20%"
            },
            {
                "data": "id",
                "render": function ( data, type, full, meta ) {
                    return '<a onclick="printPrescription('+ data +')" class="btn btn-info" id = "printPres">Print prescription</a>';
                },
                "width": "20%"
            }
        ]
    } );
});

function printPrescription(patientId) {
    console.log(patientId);
    $.ajax({
        method: "GET",
        url: "/checkPrescription",
        data: {
            patientId: patientId
        }
    })
        .done(function(data) {
            console.log(data);
            // Check if the
            if (data == FAIL_STATUS) {
                alert('No ON_TREATING prescription data!!!');
                return;
            }
            var url = "/printPrescriptionPage?patientId=" + patientId;
            var w = window.open(url, "_blank");
        });
}