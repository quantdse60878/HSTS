/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/19/2015.
 */
var enableDeletion = false;
var curMedicne = 0;
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
            "url": "/medicine/list",
            "dataSrc": "dataList",
            "dataSrc": "dataList",
            "page": "pageNumber",
            "pages": "pageSize",
            "recordsTotal": "totalElements",
            "recordsFiltered": "numberOfElements"
        },

        "columns": [
            {   "data": "null",
                "render": function (data, type, full, meta) {
                    return count++;
                },
                "width": "20%"
            },
            {   "data": "name",
                "width": "30%"
            },
            {   "data": "unit",
                "width": "20%"
            },
            {
                "data": "id",
                "render": function (data, type, full, meta) {
                    var btnUpdate = '<a onclick="updateDialog('+ data +')" class="btn btn-success" style="margin-right: 20px">Update</a>';
                    var btnDelete = '<a onclick="deleteDialog('+ data +')" class="btn btn-danger">Delete</a>';
                    return btnUpdate + btnDelete;
                },
                "width": "30%"
            }
        ]

    } );
});

function updateDialog(element) {
    curMedicne = element;
    $.ajax({
        method: "GET",
        url: "/medicine/detail",
        data: {
            id: element
        }
    }).done(function(data) {
        console.log(data);
        if (data != null) {
            curMedicinePhase = data.id;
            var txtName = document.getElementById("updateMedicineName");
            txtName.value = data.name;

            var txtUnit = document.getElementById("updateMedicineUnit");
            txtUnit.value =  data.unit;
            // Show diaglog
            $("#updateDialog").modal('show');
        }
    });
}

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

// update  validator
$("#updateForm").validate({
    ignore: [],
    debug: true,
    rules: {
        // simple rule, converted to {required:true}
        updateMedicineName: {
            required: true
        },
        updateMedicineUnit: {
            required: true
        }
    },
    errorPlacement: function(error, element){
        if(element.attr("name") == "updateMedicineName"){
            error.appendTo($('#invalidUpdateMedicineName'));
        } else if (element.attr("name") == "updateMedicineUnit") {
            error.appendTo($('#invalidUpdateMedicineUnit'));
        }

        // Default
        else {
            error.appendTo( element.parent());
        }
    },
    submitHandler: function () {
        console.log("begin update");
        $.ajax({
            method: "POST",
            url: "/medicine/update",
            data: {
                id: curMedicne,
                name: $("#updateMedicineName").val(),
                unit: $("#updateMedicineUnit").val()
            }
        }).done(function(data) {
            console.log(data);
            var txtMessage = document.getElementById("messageLabel");
            if (data.status == "fail") {
                txtMessage.innerHTML = "Error while update medicine data";
                $("#messageModal").modal('show');
            } else {
                console.log("-- reload page --");
                window.location.href = "medicines";
            }
        });
        console.log("end update");
        return false; // required to block normal submit since you used ajax
    }
});

// create  validator
$("#createForm").validate({
    ignore: [],
    debug: true,
    rules: {
        // simple rule, converted to {required:true}
        insertMedicineName: {
            required: true
        },
        insertMedicineUnit: {
            required: true
        }
    },
    errorPlacement: function(error, element){
        if(element.attr("name") == "insertMedicineName"){
            error.appendTo($('#invalidInsertMedicineName'));
        } else if (element.attr("name") == "insertMedicineName") {
            error.appendTo($('#invalidInsertUnitName'));
        }

        // Default
        else {
            error.appendTo( element.parent());
        }
    },
    submitHandler: function () {
        console.log("begin create");
        $.ajax({
            method: "POST",
            url: "/medicine/create",
            data: {
                name: $("#insertMedicineName").val(),
                unit: $("#insertMedicineUnit").val()
            }
        }).done(function(data) {
            console.log(data);
            var txtMessage = document.getElementById("messageLabel");
            if (data.status == "fail") {
                txtMessage.innerHTML = "Error while create medicine data";
                $("#messageModal").modal('show');
            } else {
                console.log("-- reload page --");
                window.location.href = "medicines";
            }
        });
        console.log("end create");
        return false; // required to block normal submit since you used ajax
    }
});