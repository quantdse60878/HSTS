/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/12/2015.
 */
function changeTab (a, li) {
    console.log("a: " + a);
    console.log("li: " + li);
    $('.nav-tabs > li.active').removeClass('active');
    $(li).addClass('active');
    $('.tab-content > .tab-pane.active').removeClass('active');
    $(a).addClass('active');
};

$(document).ready(function(){
    console.log("-- begin --");
    var count = 1;
    var table = $('#medicineContent').dataTable( {
        "processing": true,
        "pagingType": "full",
        "paging": true,
        "lengthChange": false,
        "ordering": true,
        "info": true,

        "ajax": {
            "url": "/phase/medicine",
            "dataSrc": "dataList",
            "page": "pageNumber",
            "pages": "pageSize",
            "recordsTotal": "totalElements",
            "recordsFiltered": "numberOfElements",
            "type": "GET",
            "data": {
                phaseId: $("#phaseId").val()
            }
        },

        "columns": [
            // col 1
            {

                "data": "null",
                "render": function (data, type, full, meta) {
                    return count++;
                },
                "width": "5%"
            },
            // col 2
            {
                "data": "medicine.name",
                "width": "20%"
            },
            // col 3
            {
                "data": "numberOfTime",
                "width": "5%"
            },
            // col 4
            {
                "data": "quantitative",
                "width": "5%"
            },
            // col 5
            {
                "data": "medicine.unit",
                "width": "10%"
            },
            {
                "data": "advice",
                "width": "20%"
            },
            {
                "data": "advice",
                "width": "20%"
            }
        ]
    } );
    console.log("-- end --");
});