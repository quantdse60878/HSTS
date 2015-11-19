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
            "url": "/foodUnit",
            "data": {
                foodId: 1
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