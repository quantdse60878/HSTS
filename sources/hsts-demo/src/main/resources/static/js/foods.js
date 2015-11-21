/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/19/2015.
 */
var enableDeletion = false;
var curFood = 0;
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
            "url": "/food/list",
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
            {   "data": {
                    "id" : "id",
                    "name": "name"
                },
                "render": function (data, type, full, meta) {
                    return '<a href="/food?id=' + data.id + '">'+ data.name + '</a>';
                },
                "width": "20%"
            },
            { "data": "units",
                "render": function (data, type, full, meta) {
                    if (data != null && data.length > 0) {
                        var render = '';
                        $.each(data, function (key, element) {
                           render += element.foodUnit + ", ";
                        });
                        var index = render.lastIndexOf(", ");
                        render = render.substring(0, index);
                        return render;
                    }
                    return 'N/A';
                },
                "width": "20%"
            },
            {
                "data": "id",
                "render": function ( data, type, full, meta ) {
                    var btnUpdate = '<a onclick="updateDialog('+ data +')"class="btn btn-success" style="margin-right: 20px;">Update</a>';
                    var btnDelete =  '<a onclick="deleteDialog('+ data +')" class="btn btn-danger">Delete</a>';
                    return btnUpdate + btnDelete;
                },
                "width": "40%"
            }
        ]

    } );
});

function updateDialog(element) {
    curFood = element;
    $.ajax({
        method: "GET",
        url: "/food/detail",
        data: {
            id: element
        }
    }).done(function(data) {
        console.log(data);
        if (data != null) {
            var txtName = document.getElementById("updateFoodName");
            txtName.value = data.name;

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


// insert  validator
$("#createForm").validate({
    ignore: [],
    debug: true,
    rules: {
        // simple rule, converted to {required:true}
        insertFoodName: {
            required: true
        },
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
        if(element.attr("name") == "insertFoodName"){
            error.appendTo($('#invalidInsertFoodName'));
        } else if(element.attr("name") == "insertUnitName"){
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
        var foodNutriValModel = [
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
        }];
        console.log(foodNutriValModel);
        var postData = {
            name: $("#insertFoodName").val(),
            foodNutriValModel: foodNutriValModel,
            units: [
                {
                    foodUnit: $("#insertUnitName").val(),
                    caloriesEstimate: $("#insertCaloriesEstimate").val()
                }
            ]
        };

        $.ajax({
            method: "POST",
            url: "/food/create",
            contentType: "application/json",
            data: JSON.stringify(postData)
        }).done(function(data) {
            console.log(data);
            var txtMessage = document.getElementById("messageLabel");
            if (data.status == "fail") {
                txtMessage.innerHTML = "Error while update food data";
                $("#messageModal").modal('show');
            } else {
                console.log("-- reload page --");
                window.location.href = "foods";
            }
        });
        console.log("end create");
        return false; // required to block normal submit since you used ajax
    }
});


// update  validator
$("#updateForm").validate({
    ignore: [],
    debug: true,
    rules: {
        // simple rule, converted to {required:true}
        updateFoodName: {
            required: true
        }
    },
    errorPlacement: function(error, element){
        if(element.attr("name") == "updateFoodName"){
            error.appendTo($('#invalidUpdateFoodName'));
        }

        // Default
        else {
            error.appendTo( element.parent().next() );
        }
    },
    submitHandler: function () {
        console.log("begin insert");
        $.ajax({
            method: "POST",
            url: "/food/update",
            data: {
                foodId: curFood,
                foodName: $("#updateFoodName").val()
            }
        }).done(function(data) {
            console.log(data);
            var txtMessage = document.getElementById("messageLabel");
            if (data.status == "fail") {
                txtMessage.innerHTML = "Error while update food data";
                $("#messageModal").modal('show');
            } else {
                console.log("-- reload page --");
                window.location.href = "foods";
            }
        });
        console.log("end create");
        return false; // required to block normal submit since you used ajax
    }
});