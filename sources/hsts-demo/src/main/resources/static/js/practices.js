/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/19/2015.
 */
var enableDeletion = false;
var curPractice = 0;
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
            "url": "/practice/list",
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
            {   "data": "intensity",
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
    curPractice = element;
    $.ajax({
        method: "GET",
        url: "/practice/detail",
        data: {
            id: element
        }
    }).done(function(data) {
        console.log(data);
        if (data != null) {
            curMedicinePhase = data.id;
            var txtName = document.getElementById("updatePracticeName");
            txtName.value = data.name;

            var txtUnit = document.getElementById("updatePracticeIntensity");
            txtUnit.value =  data.intensity;
            // Show diaglog
            $("#updateDialog").modal('show');
        }
    });
}

function deleteDialog(element) {
    console.log("-- begin delete --");
    if (!enableDeletion) {
        $("#messageLabel").html("Deletion function is disable. Delete practice may cause old prescription became wrong!!!");
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
        updatePracticeName: {
            required: true
        },
        updatePracticeIntensity: {
            required: true,
            min: 1
        }
    },
    errorPlacement: function(error, element){
        if(element.attr("name") == "updatePracticeName"){
            error.appendTo($('#invalidUpdatePracticeName'));
        } else if (element.attr("name") == "updatePracticeIntensity") {
            error.appendTo($('#invalidUpdatePracticeIntensity'));
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
            url: "/practice/update",
            data: {
                id: curPractice,
                name: $("#updatePracticeName").val(),
                intensity: $("#updatePracticeIntensity").val()
            }
        }).done(function(data) {
            console.log(data);
            var txtMessage = document.getElementById("messageLabel");
            if (data.status == "fail") {
                txtMessage.innerHTML = "Error while update practice data";
                $("#messageModal").modal('show');
            } else {
                console.log("-- reload page --");
                window.location.href = "practices";
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
        insertPracticeName: {
            required: true
        },
        insertIntensity: {
            required: true,
            min: 1
        }
    },
    errorPlacement: function(error, element){
        if(element.attr("name") == "insertPracticeName"){
            error.appendTo($('#invalidInsertPracticeName'));
        } else if (element.attr("name") == "insertIntensity") {
            error.appendTo($('#invalidInsertIntensity'));
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
            url: "/practice/create",
            data: {
                name: $("#insertPracticeName").val(),
                intensity: $("#insertIntensity").val()
            }
        }).done(function(data) {
            console.log(data);
            var txtMessage = document.getElementById("messageLabel");
            if (data.status == "fail") {
                txtMessage.innerHTML = "Error while create practice data";
                $("#messageModal").modal('show');
            } else {
                console.log("-- reload page --");
                window.location.href = "practices";
            }
        });
        console.log("end create");
        return false; // required to block normal submit since you used ajax
    }
});