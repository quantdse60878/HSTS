/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 12/18/2015.
 */
var $appSelect = $("#appointmentSelect").select2({
    placeholder: "Choose an appointment",
    theme: "classic",
    width: "400px",
    ajax: {
        url: "/appointmentsByMR",
        dataType: 'json',
        delay: 250,
        data:{
            medicalRecordId: $("#medicalRecordId").val()
        },
        processResults: function (data, params) {
            var names = data.map(function (obj) {
                return {
                    id: obj.id,
                    text: obj.meetingDate
                }
            });
            return {results: names,
                pagination: {
                    more: false
                }
            };
        },
        cache: false
    },
    escapeMarkup: function (markup) {
        return markup; // let our custom formatter work
    }
});

$appSelect.on("change", function (e) {

    // TODO Draw template: medicine, food, name
    var appData = $appSelect.val();
    console.log("VAL: " + appData);
    loadPopupResult(appData);
});


function loadPopupResult(appointmentId) {
    $.ajax({
        method: "GET",
        url: "/inforOfAppointmentDate",
        data: {
            appointmentId: appointmentId
        }
    })
        .done(function (data) {
            console.log(data);
            if (data != null) {
                // Set data to html
                // Set information Date History
                $('#resultDate').html(data.dateInfor);
                var table = [];
                var row = [];
                // infor table medicines
                $('#resultMedicine').html('<tr><th style="width: 10px">#</th>'
                    + '<th>Medicine</th>'
                    + '<th>Times</th>'
                    + '<th>Quantity</th>'
                    + '<th>Unit</th>'
                    + '<th>Advice</th>'
                    + '</tr>');
                for (var i = 0; i < data.hms.length; i++) {
                    var tmp = data.hms[i];
                    row = "<tr>"
                        + "<td>" + (i + 1) + "</td>"
                        + "<td>" + tmp.name + "</td>"
                        + "<td>" + tmp.times + "</td>"
                        + "<td>" + tmp.quantity + "</td>"
                        + "<td>" + tmp.unit + "</td>"
                        + "<td>" + tmp.note + "</td>"
                        + "</tr>";
                    table = table + row;
                }
                ;
                //console.log(table);
                $(table).appendTo("#resultMedicine");

                table = [];
                row = [];
                // infor table food
                $('#resultFoods').html('<tr><th style="width: 10px">#</th>'
                    + '<th>Menu</th>'
                    + '<th>Times</th>'
                    + '<th>Quantity</th>'
                    + '<th>Unit</th>'
                    + '<th>Advice</th>'
                    + '</tr>');
                for (var i = 0; i < data.hfs.length; i++) {
                    var tmp = data.hfs[i];
                    row = "<tr>"
                        + "<td>" + (i + 1) + "</td>"
                        + "<td>" + tmp.name + "</td>"
                        + "<td>" + tmp.times + "</td>"
                        + "<td>" + tmp.quantity + "</td>"
                        + "<td>" + tmp.unit + "</td>"
                        + "<td>" + tmp.note + "</td>"
                        + "</tr>";
                    table = table + row;
                }
                ;
                //console.log(table);
                $(table).appendTo("#resultFoods");

                table = [];
                row = [];
                // infor table medicines
                $('#resultPractice').html('<tr><th style="width: 10px">#</th>'
                    + '<th>Name</th>'
                    + '<th>Times</th>'
                    + '<th>Quantity</th>'
                    + '<th>Advice</th>'
                    + '</tr>');
                for (var i = 0; i < data.hps.length; i++) {
                    var tmp = data.hps[i];
                    row = "<tr>"
                        + "<td>" + (i + 1) + "</td>"
                        + "<td>" + tmp.name + "</td>"
                        + "<td>" + tmp.times + "</td>"
                        + "<td>" + tmp.quantity + "</td>"
                        + "<td>" + tmp.note + "</td>"
                        + "</tr>";
                    table = table + row;
                }
                ;
                //console.log(table);
                $(table).appendTo("#resultPractice");
            }

        });
}