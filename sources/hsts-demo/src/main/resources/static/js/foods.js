/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/19/2015.
 */
var enableDeletion = false;
var curFood = 0;
var listFood;
var foodNutrition;
var mName = document.getElementById("mName");
var mUnit = document.getElementById("mUnit");
var mKcal = document.getElementById("mKcal");
var autoComplete = document.getElementById("autoCompleteItem");

$(document).ready(loadPage());

function loadPage() {
    var inner = '';
    $.ajax({
        method: "GET",
        url: "/food/list",
        data: {}
    }).done(function (data) {
        listFood = data;
        for (var i = 0; i < data.length; i++) {
            inner += '<tr><td>' + i + '</td><td>' + data[i].name + '</td><td>' + data[i].unit + '</td><td>' +
                '<a href="#" onclick="viewUpdate(' + "'" + data[i].id + "'" + ')" class="btn btn-primary">Update</a>' +
                '<a href="#" onclick="deleteFood(' + "'" + data[i].id + "'" + ')" class="btn btn-danger">Delete</a>' +
                '</td></tr>';
        }
        document.getElementById('table-body').innerHTML = inner;
        $('#dataContent').DataTable();
    });
}

function viewCreate() {
    foodNutrition = undefined
    document.getElementById("superParent").style.display = "";
    document.getElementById("child").style.display = "";
    document.getElementById("update").style.display = "none";
    document.getElementById("create").style.display = "";
    document.getElementById("headerh2").innerHTML = "Create New Food";
    mName.value = "";
    mKcal.value = "";
    mUnit.value = "";
    document.getElementById("errorName").style.display = "none";
    document.getElementById("errorUnit").style.display = "none";
    document.getElementById("errorKcal").style.display = "none";
    document.getElementById("animalFat").value = 0.0;
    document.getElementById("animalProtein").value = 0.0;
    document.getElementById("calcium").value = 0.0;
    document.getElementById("starch").value = 0.0;
    document.getElementById("protein").value = 0.0;
    document.getElementById("fat").value = 0.0;
    document.getElementById("fiber").value = 0.0;
    document.getElementById("iron").value = 0.0;
    document.getElementById("sodium").value = 0.0;
    document.getElementById("vitaminB1").value = 0.0;
    document.getElementById("vitaminC").value = 0.0;
    document.getElementById("vitaminB2").value = 0.0;
    document.getElementById("vitaminPP").value = 0.0;
    document.getElementById("zinc").value = 0.0;
}

function viewUpdate(id) {
    document.getElementById("superParent").style.display = "";
    document.getElementById("child").style.display = "";
    document.getElementById("update").style.display = "";
    document.getElementById("create").style.display = "none";
    document.getElementById("headerh2").innerHTML = "Update Food";
    document.getElementById("foodId").value = id;
    $.ajax({
        method: "GET",
        url: "/food/get",
        data: {"foodId": id}
    }).done(function (data) {
        foodNutrition = data;
        mName.value = data.foodName;
        loadNutrition(0);
    });
}
var flag = 0;

function fillInput(number) {
    autoComplete.innerHTML = "";
    flag = -1;
    loadNutrition(number);
}

function loadNutrition(number) {
    mUnit.value = foodNutrition.unitName[number];
    mKcal.value = foodNutrition.kCal[number];
    var val = foodNutrition.value[number].split(",");
    for (var i = 0; i < val.length; i++) {
        if (i == 0) {
            document.getElementById("animalFat").value = val[i];
        }
        if (i == 1) {
            document.getElementById("animalProtein").value = val[i];
        }
        if (i == 2) {
            document.getElementById("calcium").value = val[i];
        }
        if (i == 3) {
            document.getElementById("fat").value = val[i];
        }
        if (i == 4) {
            document.getElementById("starch").value = val[i];
        }
        if (i == 5) {
            document.getElementById("protein").value = val[i];
        }
        if (i == 6) {
            document.getElementById("fiber").value = val[i];
        }
        if (i == 7) {
            document.getElementById("iron").value = val[i];
        }
        if (i == 8) {
            document.getElementById("sodium").value = val[i];
        }
        if (i == 9) {
            document.getElementById("vitaminB1").value = val[i];
        }
        if (i == 10) {
            document.getElementById("vitaminC").value = val[i];
        }
        if (i == 11) {
            document.getElementById("vitaminB2").value = val[i];
        }
        if (i == 12) {
            document.getElementById("vitaminPP").value = val[i];
        }
        if (i == 13) {
            document.getElementById("zinc").value = val[i];
        }
    }
}

function hideMesurement() {
    document.getElementById("superParent").style.display = "none";
    document.getElementById("child").style.display = "none";
}

function checkValidateMeasurement(element, error) {
    autoComplete.innerHTML = "";
    for (var i = 0; i < foodNutrition.unitName.length; i++) {
        if (foodNutrition.unitName[i].toLowerCase().indexOf(mUnit.value.toLowerCase()) > -1) {
            var btn = document.createElement("p");
            var t = document.createTextNode(foodNutrition.unitName[i]);
            var att = document.createAttribute("onclick");
            att.value = "fillInput('" + i + "')";
            btn.appendChild(t);
            btn.setAttributeNode(att);
            autoComplete.appendChild(btn);
        }
    }
    if (element.id == "mName") {
        for (var i = 0; i < listFood.length; i++) {
            if (element.value.toLowerCase() == listFood[i].name.toLowerCase()) {
                document.getElementById(error).innerHTML = "Food Name is existed!";
                document.getElementById(error).style.display = "";
                return;
            }
        }
    }
    if (element.value == "") {
        document.getElementById(error).innerHTML = "Field cannot be empty";
        document.getElementById(error).style.display = "";
    } else {
        document.getElementById(error).style.display = "none";
    }
}

function checkValidateValue(element, error) {
    if (element.value == "") {
        document.getElementById(error).innerHTML = "Field cannot be empty";
        document.getElementById(error).style.display = "";
        return;
    }
    if (parseFloat(element.value) < 0) {
        document.getElementById(error).innerHTML = "Value must be over 0";
        document.getElementById(error).style.display = "";
        return;
    }

    document.getElementById(error).innerHTML = "";
    document.getElementById(error).style.display = "none";
}

function createMeasurement() {
    var mName = document.getElementById("mName");
    var mUnit = document.getElementById("mUnit");
    var mKcal = document.getElementById("mKcal");
    var errorName = document.getElementById("errorName");
    var errorUnit = document.getElementById("errorUnit");
    var errorKcal = document.getElementById("errorKcal");
    if (validateAllField()) {
        $.ajax({
            method: "GET",
            url: "/food/createFood",
            data: {
                "foodName": mName.value,
                "unitOfFood": mUnit.value,
                "Kcal": mKcal.value,
                "animalFat": document.getElementById("animalFat").value,
                "animalProtein": document.getElementById("animalProtein").value,
                "calcium": document.getElementById("calcium").value,
                "starch": document.getElementById("starch").value,
                "protein": document.getElementById("protein").value,
                "lipid": document.getElementById("fat").value,
                "fiber": document.getElementById("fiber").value,
                "iron": document.getElementById("iron").value,
                "sodium": document.getElementById("sodium").value,
                "vitaminB1": document.getElementById("vitaminB1").value,
                "vitaminC": document.getElementById("vitaminC").value,
                "vitaminB2": document.getElementById("vitaminB2").value,
                "vitaminPP": document.getElementById("vitaminPP").value,
                "zinc": document.getElementById("zinc").value
            }
        }).done(function (data) {
            if (data == "200") {
                alert("Insert food successfully!");
                hideMesurement();
                location.reload();
            } else {
                alert("Insert Food Fail !!!");
            }
        });
    }
}

function validateAllField() {
    if (mName.value == "") {
        errorName.innerHTML = "Field cannot be empty";
        errorName.style.display = "";
        return false;
    }
    if (mUnit.value == "") {
        errorUnit.innerHTML = "Field cannot be empty";
        errorUnit.style.display = "";
        return false;
    }
    if (mKcal.value == "") {
        errorKcal.innerHTML = "Field cannot be empty";
        errorKcal.style.display = "";
        return false;
    }
    if (errorName.style.display != "none") {
        return false;
    }
    if (errorUnit.style.display != "none") {
        return false;
    }
    if (errorKcal.style.display != "none") {
        return false;
    }
    if (document.getElementById("invalidstarch").innerHTML != "") {
        return false;
    }
    if (document.getElementById("invalidiron").innerHTML != "") {
        return false;
    }
    if (document.getElementById("invalidprotein").innerHTML != "") {
        return false;
    }
    if (document.getElementById("invalidzinc").innerHTML != "") {
        return false;
    }
    if (document.getElementById("invalidfat").innerHTML != "") {
        return false;
    }
    if (document.getElementById("invalidvitaminB1").innerHTML != "") {
        return false;
    }
    if (document.getElementById("invalidanimalProtein").innerHTML != "") {
        return false;
    }
    if (document.getElementById("invalidvitaminC").innerHTML != "") {
        return false;
    }
    if (document.getElementById("invalidanimalFat").innerHTML != "") {
        return false;
    }
    if (document.getElementById("invalidvitaminB2").innerHTML != "") {
        return false;
    }
    if (document.getElementById("invalidcalcium").innerHTML != "") {
        return false;
    }
    if (document.getElementById("invalidvitaminPP").innerHTML != "") {
        return false;
    }
    if (document.getElementById("invalidsodium").innerHTML != "") {
        return false;
    }
    if (document.getElementById("invalidfiber").innerHTML != "") {
        return false;
    }
    return true;
}

function updateMeasurement() {
    var id = document.getElementById("foodId").value;
    if (validateAllField()) {
        $.ajax({
            method: "GET",
            url: "/food/updateFood",
            data: {
                "foodId": id,
                "foodName": mName.value,
                "unitOfFood": mUnit.value,
                "Kcal": mKcal.value,
                "animalFat": document.getElementById("animalFat").value,
                "animalProtein": animalProtein,
                "calcium": document.getElementById("calcium").value,
                "starch": document.getElementById("starch").value,
                "protein": document.getElementById("protein").value,
                "lipid": document.getElementById("fat").value,
                "fiber": document.getElementById("fiber").value,
                "iron": document.getElementById("iron").value,
                "sodium": document.getElementById("sodium").value,
                "vitaminB1": document.getElementById("vitaminB1").value,
                "vitaminC": document.getElementById("vitaminC").value,
                "vitaminB2": document.getElementById("vitaminB2").value,
                "vitaminPP": document.getElementById("vitaminPP").value,
                "zinc": document.getElementById("zinc").value
            }
        }).done(function (data) {
            if (data == "200") {
                alert("Update food successfully!");
                hideMesurement();
                location.reload();
            } else {
                alert("Update Food Fail !!!");
            }
        });
    }
}

function deleteFood(id){
    var x = confirm("Are sure to delete this food ?");
    if(x) {
        $.ajax({
            method: "GET",
            url: "/food/deleteFood",
            data: {"foodId": id}
        }).done(function (data) {
            if (data == "200") {
                alert("Delete food successfully!");
                location.reload();;
            } else {
                alert("Food is using. You cannot delete this food!");
            }
        });

    }
}