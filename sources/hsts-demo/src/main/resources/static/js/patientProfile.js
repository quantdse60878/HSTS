/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 12/17/2015.
 */
$(document).ready(function(){

    var count = 1;
    $.ajax({
        method: "GET",
        url: "/medicalRecords",
        data: {
            patientId: $("#patientId").val()
        }
    })
        .done(function(data) {
            console.log(data);
        });
});