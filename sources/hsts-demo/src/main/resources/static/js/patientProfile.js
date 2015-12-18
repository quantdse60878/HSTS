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

                bodyContent += '<div class="col-md-12">';


                bodyContent += '<div class="form-group">';
                bodyContent += '<label class="col-sm-3 control-label">Doctor:</label>';
                bodyContent += '<div class="col-sm-3">';
                bodyContent += '<input class = "hsts" type="text" readonly="readonly" value="' + element.doctor.account.fullName + '" />';
                bodyContent += '</div>';
                bodyContent += '</div>';

                bodyContent += '<div class="form-group">';
                bodyContent += '<label class="col-sm-3 control-label">Illness:</label>';
                bodyContent += '<div class="col-sm-3">';
                bodyContent += '<input class = "hsts" type="text" readonly="readonly" value="' + element.illness.name + '" />';
                bodyContent += '</div>';
                bodyContent += '</div>';

                bodyContent += '<div class="form-group">';
                bodyContent += '<label class="col-sm-3 control-label">Symptoms:</label>';
                bodyContent += '<div class="col-sm-5">';
                bodyContent += '<input class = "hsts" type="text" readonly="readonly" value="' + element.symptoms + '" />';
                bodyContent += '</div>';
                bodyContent += '</div>';

                //status
                bodyContent += '<div class="form-group">';
                bodyContent += '<label class="col-sm-3 control-label">Symptoms:</label>';
                bodyContent += '<div class="col-sm-5">';
                bodyContent += '<input class = "hsts" type="text" readonly="readonly" value="' + element.symptoms + '" />';
                bodyContent += '</div>';
                bodyContent += '</div>';

                // view button
                var btnClass = 'btn btn-warning';
                var btnInfo = 'AWAITING TO EXAM';
                if (element.status == 2) {
                    btnClass = 'btn btn-danger';
                    btnInfo = 'ON TREATING';
                } else if (element.status == 3) {
                    btnClass = 'btn btn-success';
                    btnInfo = 'NO ILLNESS';
                } else if (element.status == 4) {
                    btnClass = 'btn btn-success';
                    btnInfo = 'FINISHED';
                }
                bodyContent += '<div class="form-group">';
                bodyContent += '<label class="col-sm-3 control-label">Symptoms:</label>';
                bodyContent += '<div class="col-sm-5">';
                bodyContent += '<a href = "/appointmentsByMR?id='+ element.id + '" class="'+ btnClass + '">'+ btnInfo + '</a>';
                bodyContent += '</div>';
                bodyContent += '</div>';

                bodyContent += '</div>';
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