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
                "data": {
                    "numberOfDay": "numberOfDay",
                    "id" : "id"
                },
                "render": function ( data, type, full, meta ) {
                    return '<a href="/phase?id='+ data.id +  '">' + data.numberOfDay + '</a>';
                },
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
                    return '<a href="/phase/delete?id='+ data +  '" class="btn btn-danger">Delete</a>';
                },
                "width": "20%"
            }
        ]
    } );
    console.log("-- end --");
});

// Insert regimen validator
// Validator
var validator = $("#createForm").validate({
    ignore: [],
    debug: true,
    rules: {
        numberDay: {
            required: true,
            min: 1,
            max: 30
        }
    },
    messages: {
        numberDay: {
            required: "Please input valid illness name"
        }
    },
    errorPlacement: function(error, element){
        if(element.attr("name") == "numberDay"){
            error.appendTo($('#invalidNumberDay'));
        }

        // Default
        else {
            error.appendTo( element.parent().next() );
        }
    },
    submitHandler: function () {
        console.log("--- begin ---");
        $.ajax({
            method: "POST",
            url: "/phase/create",
            data: {
                regimenId: $("#regimenId").val(),
                numberDay: $("#numberDay").val()
            }
        })
            .done(function(data) {
                console.log(data);
                if (data.status != "ok") {
                    // Show error modal
                    var resultText = document.getElementById("messageLabel");
                    resultText.innerHTML = "Error while create new regimen";
                    $('#messageModal').modal('show');
                } else {
                    console.log("-- reload page --");
                    window.location.href = "/detailRegimen?id=" + $("#regimenId").val();
                }
            });
        console.log("--- end ---");
        return false; // required to block normal submit since you used ajax
    }
});