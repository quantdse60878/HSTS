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
            $("#medicalContent").html("");
            var bodyContent = "";
            // panel group
            bodyContent += '<div class="panel-group" id="accordion">';
            $.each(data.dataList, function (key, element) {
                // Each child collase
                bodyContent += '<div class="panel panel-default" id="panel' + count + '">';
                // Heading
                bodyContent += '<div class="panel-heading">';
                bodyContent += '<h4 class="panel-title">';
                bodyContent += '<a data-toggle="collapse" data-target="#collapse' + count + '" '
                + 'href="#collapse' + count + '">' + element.startTime + " - ";
                if (element.endTime != null) {
                    bodyContent += element.endTime;
                } else {
                    bodyContent += '?';
                }
                bodyContent += '</a>';
                bodyContent += '</h4>';
                bodyContent += '</div>';
                //. Heading


                // Body
                bodyContent += '<div class="panel-collapse collapse" id="collapse' + count + '">';
                bodyContent += '<div class="panel-body">';
                bodyContent += 'Full content here';
                bodyContent += '</div>';
                bodyContent += '</div>';
                // .Body
                bodyContent += '</div>';
                count++;
            });
            $("#medicalContent").html(bodyContent);
            // .panel group

        });
});