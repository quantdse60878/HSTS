/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/19/2015.
 */
var curUnit = 0;
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
            "url": "/foodUnit",
            "data": {
                foodId: $("#foodId").val()
            },
            "dataSrc": ""
        },

        "columns": [
            {   "data": "null",
                "render": function (data, type, full, meta) {
                    return count++;
                },
                "width": "5%"
            },
            {   "data": "foodUnit",
                "width": "15%"
            },
            {   "data": "caloriesEstimate",
                "width": "15%"
            },
            {   "data": "foodNutritionName",
                "width": "15%"
            },
            {   "data": "foodNutritionValue",
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
    curUnit = element;
    $.ajax({
        method: "GET",
        url: "/unitOfFood/detail",
        data: {
            id: element
        }
    }).done(function(data) {
        console.log(data);
        if (data != null) {
            curMedicinePhase = data.id;
            var txtName = document.getElementById("unitName");
            txtName.innerHTML = data.foodUnit;

            var updateCaloriesEstimate = document.getElementById("updateCaloriesEstimate");
            updateCaloriesEstimate.value =  data.caloriesEstimate;

            var updateFoodNutritionName = document.getElementById("updateFoodNutritionName");
            updateFoodNutritionName.value = data.foodNutritionName;

            var updateFoodNutritionValue = document.getElementById("updateFoodNutritionValue");
            updateFoodNutritionValue.value = data.foodNutritionValue;

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

// update medicine validator
$("#updateForm").validate({
    ignore: [],
    debug: true,
    rules: {
        // simple rule, converted to {required:true}
        updateCaloriesEstimate: {
            required: true,
            min: 1
        },
        updateFoodNutritionName: {
            required: true
        },
        updateFoodNutritionValue: {
            required: true
        }
    },
    errorPlacement: function(error, element){
        if(element.attr("name") == "updateCaloriesEstimate"){
            error.appendTo($('#invalidUpdateCaloriesEstimate'));
        }  else if (element.attr("name") == "updateFoodNutritionName") {
            error.appendTo($('#invalidUpdateFoodNutritionName'));
        } else if (element.attr("name") == "updateFoodNutritionValue") {
            error.appendTo($('#invalidUpdatefoodNutritionValue'));
        }

        // Default
        else {
            error.appendTo( element.parent().next() );
        }
    },
    submitHandler: function () {
        console.log("begin update");
        var postData = {
            id: curUnit,
            caloriesEstimate: $("#updateCaloriesEstimate").val(),
            foodNutritionName: $("#updateFoodNutritionName").val(),
            foodNutritionValue: $("#updateFoodNutritionValue").val()
            };
        $.ajax({
            method: "POST",
            url: "/unitOfFood/update",
            contentType: "application/json",
            data: JSON.stringify(postData)
        }).done(function(data) {
            console.log(data);
            var txtMessage = document.getElementById("messageLabel");
            if (data.status == "fail") {
                txtMessage.innerHTML = "Error while update regimen data";
                $("#messageModal").modal('show');
            } else {
                console.log("-- reload page --");
                window.location.href = "/food?id=" + $("#foodId").val();
            }
        });
        console.log("end update");
        return false; // required to block normal submit since you used ajax
    }
});


// update medicine validator
$("#createForm").validate({
    ignore: [],
    debug: true,
    rules: {
        // simple rule, converted to {required:true}
        insertUnitName: {
            required: true
        },
        insertCaloriesEstimate: {
            required: true,
            min: 1
        },
        insertFoodNutritionName: {
            required: true
        },
        insertFoodNutritionValue: {
            required: true
        }
    },
    errorPlacement: function(error, element){
        if(element.attr("name") == "insertUnitName"){
            error.appendTo($('#invalidInsertUnitName'));
        } else if(element.attr("name") == "insertCaloriesEstimate"){
            error.appendTo($('#invalidInsertCaloriesEstimate'));
        }  else if (element.attr("name") == "insertFoodNutritionName") {
            error.appendTo($('#invalidInsertFoodNutritionName'));
        } else if (element.attr("name") == "insertFoodNutritionValue") {
            error.appendTo($('#invalidInsertFoodNutritionValue'));
        }

        // Default
        else {
            error.appendTo( element.parent().next() );
        }
    },
    submitHandler: function () {
        console.log("begin insert");
        var postData = {
            foodId: $("#foodId").val(),
            foodUnit: $("#insertUnitName").val(),
            caloriesEstimate: $("#insertCaloriesEstimate").val(),
            foodNutritionName: $("#insertFoodNutritionName").val(),
            foodNutritionValue: $("#insertFoodNutritionValue").val()
        };
        $.ajax({
            method: "POST",
            url: "/unitOfFood/create",
            contentType: "application/json",
            data: JSON.stringify(postData)
        }).done(function(data) {
            console.log(data);
            var txtMessage = document.getElementById("messageLabel");
            if (data.status == "fail") {
                txtMessage.innerHTML = "Error while update regimen data";
                $("#messageModal").modal('show');
            } else {
                console.log("-- reload page --");
                window.location.href = "/food?id=" + $("#foodId").val();
            }
        });
        console.log("end create");
        return false; // required to block normal submit since you used ajax
    }
});