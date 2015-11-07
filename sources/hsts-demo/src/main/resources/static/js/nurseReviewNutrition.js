/**
 * Created by Man Huynh Khuong on 11/6/2015.
 */

var foodingredient = {
    bf_result : "",
    btm_result : "",
    l_result : "",
    bta_result : "",
    dinner_result : "",
    en_result : "",
    bf_starch : "",
    bf_lipid : "",
    bf_protein : "",
    bf_fiber : "",
    btm_starch : "",
    btm_lipid : "",
    btm_protein : "",
    btm_fiber : "",
    l_starch : "",
    l_lipid : "",
    l_protein : "",
    l_fiber : "",
    bta_starch : "",
    bta_lipid : "",
    bta_protein : "",
    bta_fiber : "",
    d_starch : "",
    d_lipid : "",
    d_protein : "",
    d_fiber : "",
    en_starch : "",
    en_lipid : "",
    en_protein : "",
    en_fiber : ""
};

function validation1(){
    alert("OK");
    var bf_result = document.getElementById("bf_result").innerHTML;
    var btm_result = document.getElementById("btm_result").innerHTML;
    var l_result = document.getElementById("l_result").innerHTML;
    var bta_result = document.getElementById("bta_result").innerHTML;
    var dinner_result = document.getElementById("dinner_result").innerHTML;
    var en_result = document.getElementById("en_result").innerHTML;
    var bf_starch = document.getElementById("bf_starch").value;
    var bf_lipid = document.getElementById("bf_lipid").value;
    var bf_protein = document.getElementById("bf_protein").value;
    var bf_fiber = document.getElementById("bf_fiber").value;
    var btm_starch = document.getElementById("btm_starch").value;
    var btm_lipid = document.getElementById("btm_lipid").value;
    var btm_protein = document.getElementById("btm_protein").value;
    var btm_fiber = document.getElementById("btm_fiber").value;
    var l_starch = document.getElementById("l_starch").value;
    var l_lipid = document.getElementById("l_lipid").value;
    var l_protein = document.getElementById("l_protein").value;
    var l_fiber = document.getElementById("l_fiber").value;
    var bta_starch = document.getElementById("bta_starch").value;
    var bta_lipid = document.getElementById("bta_lipid").value;
    var bta_protein = document.getElementById("bta_protein").value;
    var bta_fiber = document.getElementById("bta_fiber").value;
    var dinner_starch = document.getElementById("dinner_starch").value;
    var dinner_lipid = document.getElementById("dinner_lipid").value;
    var dinner_protein = document.getElementById("dinner_protein").value;
    var dinner_fiber = document.getElementById("dinner_fiber").value;
    var en_starch = document.getElementById("en_starch").value;
    var en_lipid = document.getElementById("en_lipid").value;
    var en_protein = document.getElementById("en_protein").value;
    var en_fiber = document.getElementById("en_fiber").value;
    var json = JSON.stringify(foodingredient);
    $.ajax({
        method: "GET",
        url: "sendNutrition",
        data: {"foodingredient" : json}
    }).done(function (data) {
        alert("KHUONGGNGNGNGNG");
    });
}