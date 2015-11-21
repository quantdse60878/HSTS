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
            document.getElementById("animalFatUP").value = data.foodNutriValModel.animalFat;
            document.getElementById("animalProteinUP").value = data.foodNutriValModel.animalProtein;
            document.getElementById("calciumUP").value = data.foodNutriValModel.calcium;
            document.getElementById("fatUP").value = data.foodNutriValModel.fat;
            document.getElementById("starchUP").value = data.foodNutriValModel.starch;
            document.getElementById("proteinUP").value = data.foodNutriValModel.protein;
            document.getElementById("fiberUP").value = data.foodNutriValModel.fiber;
            document.getElementById("ironUP").value = data.foodNutriValModel.iron;
            document.getElementById("sodiumUP").value = data.foodNutriValModel.sodium;
            document.getElementById("vitaminB1UP").value = data.foodNutriValModel.vitaminB1;
            document.getElementById("vitaminB2UP").value = data.foodNutriValModel.vitaminB2;
            document.getElementById("vitaminCUP").value = data.foodNutriValModel.vitaminC;
            document.getElementById("vitaminPPUP").value = data.foodNutriValModel.vitaminPP;
            document.getElementById("zincUP").value = data.foodNutriValModel.zinc;

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
            error.appendTo( element.parent() );
        }
    },
    submitHandler: function () {
        console.log("begin update");
        var foodNutriValModel =
        {
            animalFat: $('#animalFatUP').val(),
            animalProtein: $('#animalProteinUP').val(),
            calcium: $('#calciumUP').val(),
            fat: $('#fatUP').val(),
            starch: $('#starchUP').val(),
            protein: $('#proteinUP').val(),
            fiber: $('#fiberUP').val(),
            iron: $('#ironUP').val(),
            sodium: $('#sodiumUP').val(),
            vitaminB1: $('#vitaminB1UP').val(),
            vitaminB2: $('#vitaminB2UP').val(),
            vitaminC: $('#vitaminCUP').val(),
            vitaminPP: $('#vitaminPPUP').val(),
            zinc: $('#zincUP').val()
        };
        var postData = {
            id: curUnit,
            caloriesEstimate: $("#updateCaloriesEstimate").val(),
            foodNutriValModel: foodNutriValModel
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
            error.appendTo( element.parent() );
        }
    },
    submitHandler: function () {
        console.log("begin insert");
        var foodNutriValModel =
        {
            animalFat: $('#animalFat').val(),
            animalProtein: $('#animalProtein').val(),
            calcium: $('#calcium').val(),
            fat: $('#fat').val(),
            starch: $('#starch').val(),
            protein: $('#protein').val(),
            fiber: $('#fiber').val(),
            iron: $('#iron').val(),
            sodium: $('#sodium').val(),
            vitaminB1: $('#vitaminB1').val(),
            vitaminB2: $('#vitaminB2').val(),
            vitaminC: $('#vitaminC').val(),
            vitaminPP: $('#vitaminPP').val(),
            zinc: $('#zinc').val()
        };
        var postData = {
            foodId: $("#foodId").val(),
            foodUnit: $("#insertUnitName").val(),
            caloriesEstimate: $("#insertCaloriesEstimate").val(),
            foodNutriValModel: foodNutriValModel
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