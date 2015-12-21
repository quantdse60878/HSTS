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
    //showModalAndDraw();
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


function showModalAndDraw () {
    // Get practice Data
    $.ajax({
        method: "POST",
        url: "/practiceDataByAppointment",
        data: {
            appointmentId: $appSelect.val()
        }
    })
        .done(function (data) {
            if (null != data) {
                // Show pop-up
                $("#avgKcalConsumed").val(data.avgKcalConsumed);
                $("#kcalEstimate").val(data.kcalEstimate);
                var text = '';
                if(data.status == 1) {
                    text = '<input type="text" class="form-control" readonly="readonly" style="text-align: center;background-color: #f0ad4e;color: white;" value="Under Required"/>';
                } else if (data.status == 2) {
                    text = '<input type="text" class="form-control" readonly="readonly" style="text-align: center;background-color: #5bc0de;color: white;" th:if="*{status == 2}" value="Completed"/>';
                } else if (data.status == 3) {
                    text = '<input type="text" class="form-control" readonly="readonly" style="text-align: center;background-color: #d9534f;color: white;" th:if="*{status == 3}" value="Excessive"/>';
                }
                $(text).appendTo("#status");

                //-------------
                //- BAR CHART -
                //-------------
                // Get context with jQuery - using jQuery's .get() method.
                var barChartCanvas = $("#barChart").get(0).getContext("2d");
                // This will get the first returned node in the jQuery collection.
                var barChart = new Chart(barChartCanvas);
                var barChartData = {
                    labels: data.lables,
                    datasets: [
                        {
                            label: "Calories Consumed",
                            fillColor: "rgba(220,220,220,0.2)",
                            strokeColor: "#00a65a",
                            pointColor: "#00a65a",
                            pointStrokeColor: "rgba(60,141,188,1)",
                            pointHighlightFill: "#fff",
                            pointHighlightStroke: "rgba(60,141,188,1)",
                            data: data.kcalConsumeds
                        },
                        {
                            label: "Calories Required",
                            fillColor: "rgba(151,187,205,0.2)",
                            strokeColor: "#ff851b",
                            pointColor: "#ff851b",
                            pointStrokeColor: "#c1c7d1",
                            pointHighlightFill: "#fff",
                            pointHighlightStroke: "rgba(220,220,220,1)",
                            data: data.kcalEstimets
                        }
                    ]
                };

                var barChartOptions = {

                    ///Boolean - Whether grid lines are shown across the chart
                    scaleShowGridLines : true,

                    //String - Colour of the grid lines
                    scaleGridLineColor : "rgba(0,0,0,.05)",

                    //Number - Width of the grid lines
                    scaleGridLineWidth : 1,

                    //Boolean - Whether to show horizontal lines (except X axis)
                    scaleShowHorizontalLines: true,

                    //Boolean - Whether to show vertical lines (except Y axis)
                    scaleShowVerticalLines: true,

                    //Boolean - Whether the line is curved between points
                    bezierCurve : true,

                    //Number - Tension of the bezier curve between points
                    bezierCurveTension : 0.4,

                    //Boolean - Whether to show a dot for each point
                    pointDot : true,

                    //Number - Radius of each point dot in pixels
                    pointDotRadius : 4,

                    //Number - Pixel width of point dot stroke
                    pointDotStrokeWidth : 1,

                    //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
                    pointHitDetectionRadius : 20,

                    //Boolean - Whether to show a stroke for datasets
                    datasetStroke : true,

                    //Number - Pixel width of dataset stroke
                    datasetStrokeWidth : 2,

                    //Boolean - Whether to fill the dataset with a colour
                    datasetFill : true,

                    //String - A legend template
                    legendTemplate : "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].strokeColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>"

                };

                barChartOptions.datasetFill = true;
                barChart.Line(barChartData, barChartOptions);
            }
        })
};