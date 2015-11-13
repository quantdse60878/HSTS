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
                "width": "20%"
            },
            // col 3
            {
              "data": "illness.description",
                "width": "50%"
            },
            // col 4

            {
                "data": "id",
                "render": function ( data, type, full, meta ) {
                    var updateBtn = '<a onclick="updateDialog('+ data +')" class="btn btn-success">Update</a>';
                    var deleteBtn = '<a onclick="deleteDialog('+ data +')" class="btn btn-danger">Delete</a>'
                    return updateBtn + deleteBtn;
                },
                "width": "20%"
            }
        ]
    } );
    console.log("-- end --");
});

function updateDialog(element) {
    $.ajax({
        method: "GET",
        url: "/regimen",
        data: {
            regimenId: element
        }
    }).done(function(data) {
        console.log(data);
        if (data != null) {
            currentRegimen = data.id;
            var txtName = document.getElementById("updateName");
            txtName.value = data.illness.name;

            var txtDesc = document.getElementById("updateDescription");
            txtDesc.value =  data.illness.description;
            // Show diaglog
            $("#updateModal").modal('show');
        }
    });
}

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

// Insert regimen validator
// Validator
var validator = $("#createForm").validate({
    ignore: [],
    debug: true,
    rules: {
        // simple rule, converted to {required:true}
        name: {
            required: true,
            minlength: 5,
            maxlength: 30
        },
        description: {
            required: true,
            minlength: 5,
            maxlength: 50
        },
        numberPhase: {
            required: true,
            min: 1,
            max: 20
        }
    },
    messages: {
        //patientName: {
        //    maxlenght: "Name is too long, please modify it"
        //},
        name: {
            required: "Please input valid illness name"
        },
        description: {
            required: "Please input valid description"
        },
        numberPhase: {
            required: "Please input valid number of phase"
        }
    },
    errorPlacement: function(error, element){
        if(element.attr("name") == "name"){
            error.appendTo($('#invalidName'));
        }  else if (element.attr("name") == "description") {
            error.appendTo($('#invalidDescription'));
        } else if (element.attr("name") == "numberPhase") {
            error.appendTo($('#invalidNumberPhase'));
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
            url: "/regimen/create",
            data: {
                name: $("#name").val(),
                description: $("#description").val(),
                numberPhase: $("#numberPhase").val()
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
                    window.location.href = "regimens";
                }
            });
        console.log("--- end ---");
        return false; // required to block normal submit since you used ajax
    }
});

var updateValidator = $("#updateForm").validate({
    ignore: [],
    debug: true,
    rules: {
        // simple rule, converted to {required:true}
        updateName: {
            required: true,
            minlength: 5,
            maxlength: 30
        },
        updateDescription: {
            required: true,
            minlength: 5,
            maxlength: 50
        }
    },
    errorPlacement: function(error, element){
        if(element.attr("name") == "updateName"){
            error.appendTo($('#invalidUpdateName'));
        }  else if (element.attr("name") == "updateDescription") {
            error.appendTo($('#invalidUpdateDescription'));
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
            url: "/regimen/update",
            data: {
                id: currentRegimen,
                name: $("#updateName").val(),
                description: $("#updateDescription").val()
            }
        })
            .done(function(data) {
                console.log(data);
                if (data.status != "ok") {
                    // Show error modal
                    var resultText = document.getElementById("messageLabel");
                    resultText.innerHTML = "Error while update regimen";
                    $('#messageModal').modal('show');
                } else {
                    console.log("-- reload page --");
                    window.location.href = "regimens";
                }
            });
        console.log("--- end ---");
        return false; // required to block normal submit since you used ajax
    }
});