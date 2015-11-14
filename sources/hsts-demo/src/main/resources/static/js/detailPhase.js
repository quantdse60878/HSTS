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

var curMedicinePhase = 0;

$(document).ready(function(){
    console.log("-- begin --");
    var count = 1;
    // medicine
    $('#medicineContent').dataTable( {
        "processing": true,
        "pagingType": "full",
        "paging": true,
        "lengthChange": false,
        "ordering": true,
        "info": true,

        "ajax": {
            "url": "/phase/medicines",
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
                "data": "id",
                "render": function (data, type, full, meta) {
                    var btnUpdate = '<a onclick="updateMedicineDialog('+ data +')" class="btn btn-warning">Update</a>';
                    var btnDelete = '<a onclick="deleteMedicineDialog('+ data +')" class="btn btn-danger">Delete</a>';
                    return btnUpdate + btnDelete;
                },
                "width": "20%"
            }
        ]
    } );
    // end medicine

    // food

    // food

    // practice

    // practice

    console.log("-- end --");
});

function updateMedicineDialog(element) {
    $.ajax({
        method: "GET",
        url: "/phase/medicine/detail",
        data: {
            id: element
        }
    }).done(function(data) {
        console.log(data);
        if (data != null) {
            curMedicinePhase = data.id;
            var txtName = document.getElementById("updateMedicineName");
            txtName.innerHTML = data.medicine.name;

            var txtTimes = document.getElementById("updateTimes");
            txtTimes.value =  data.numberOfTime;

            var txtQuantitative = document.getElementById("updateQuantitative");
            txtQuantitative.value = data.quantitative;

            var txtAdvice = document.getElementById("updateNote");
            txtAdvice.value = data.advice;

            // Show diaglog
            $("#updateMedicineDialog").modal('show');
        }
    });
}

function deleteMedicineDialog(element) {
    curMedicinePhase = element;
    confirmMessageLabel.innerHTML = "Are you wish to delete this medicine?";
    $("#deleteMedicineDialog").modal('show');
}

// medicine select
$("#medicineSelect").select2({
    placeholder: "Choose a medicine",
    theme: "classic",
    width: "100%",
    ajax: {
        url: "/medicine/list",
        dataType: 'json',
        delay: 250,
        data: function (params, page) {
            return {
                name: params.term, // search term
                page: params.page,
                pageSize: 5
            };
        },
        processResults: function (data, params) {
            params.page = params.page || 0;
            var names = data.dataList.map(function (obj) {
                return {
                    id: obj.id,
                    text: obj.name
                }
            });
            return {results: names,
                pagination: {
                    more: ( (data.pageNumber + 1)  * 5) < data.totalElements
                }
            };
        },
        cache: false
    },
    escapeMarkup: function (markup) {
        return markup; // let our custom formatter work
    },
    tag: true
});

// insert medicine validator
$("#insertMedicineForm").validate({
    ignore: [],
    debug: true,
    rules: {
        // simple rule, converted to {required:true}
        insertMedicine: {
            required: true
        },
        insertTimes: {
            required: true,
            min: 1,
            max: 7
        },
        insertQuantitative: {
            required: true,
            min: 1,
            max: 5
        }
    },
    messages: {
        //patientName: {
        //    maxlenght: "Name is too long, please modify it"
        //},
        insertMedicine: {
            required: "Please choose a medicine"
        },
        insertTimes: {
            required: "Please input valid illness name",
        },
        insertQuantitative: {
            required: "Please input valid description"
        }
    },
    errorPlacement: function(error, element){
        if(element.attr("name") == "insertMedicine"){
            error.appendTo($('#invalidInsertMedicine'));
        }  else if (element.attr("name") == "insertTimes") {
            error.appendTo($('#invalidInsertTimes'));
        } else if (element.attr("name") == "insertQuantitative") {
            error.appendTo($('#invalidInsertQuantitative'));
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
            url: "/phase/medicine/add",
            data: {
                phaseId: $("#phaseId").val(),
                medicineId: $("#medicineSelect").val(),
                numberOfTime: $("#insertTimes").val(),
                quantitative: $("#insertQuantitative").val(),
                advice: $("#insertNote").val()
            }
        }).done(function(data) {
            console.log(data);
            var txtMessage = document.getElementById("messageLabel");
            if (data.status == "fail") {
                txtMessage.innerHTML = "Error while insert data";
            } else {
                console.log("-- reload page --");
                window.location.href = "/detailPhase?id=" + $("#phaseId").val();
            }
        });
        console.log("end insert");
        return false; // required to block normal submit since you used ajax
    }
});

// update medicine validator
$("#updateMedicineForm").validate({
    ignore: [],
    debug: true,
    rules: {
        // simple rule, converted to {required:true}
        updateTimes: {
            required: true,
            min: 1,
            max: 7
        },
        updateQuantitative: {
            required: true,
            min: 1,
            max: 5
        }
    },
    messages: {
        //patientName: {
        //    maxlenght: "Name is too long, please modify it"
        //},
        updateTimes: {
            required: "Please input valid illness name",
        },
        updateQuantitative: {
            required: "Please input valid description"
        }
    },
    errorPlacement: function(error, element){
        if(element.attr("name") == "updateTimes"){
            error.appendTo($('#invalidUpdateTimes'));
        }  else if (element.attr("name") == "updateQuantitative") {
            error.appendTo($('#invalidUpdateQuantitative'));
        }

        // Default
        else {
            error.appendTo( element.parent().next() );
        }
    },
    submitHandler: function () {
        console.log("begin update");
        $.ajax({
            method: "POST",
            url: "/phase/medicine/update",
            data: {
                id: curMedicinePhase,
                numberOfTime: $("#updateTimes").val(),
                quantitative: $("#updateQuantitative").val(),
                advice: $("#updateNote").val()
            }
        }).done(function(data) {
            console.log(data);
            var txtMessage = document.getElementById("messageLabel");
            if (data.status == "fail") {
                txtMessage.innerHTML = "Error while update regimen data";
            } else {
                console.log("-- reload page --");
                window.location.href = "/detailPhase?id=" + $("#phaseId").val();
            }
        });
        console.log("end update");
        return false; // required to block normal submit since you used ajax
    }
});

// delete medicine
$( "#btnDeleteMedicine" ).click(function() {
    console.log("begin delete");
    $.ajax({
        method: "POST",
        url: "/phase/medicine/delete",
        data: {
            id: curMedicinePhase
        }
    }).done(function(data) {
        console.log(data);
        var txtMessage = document.getElementById("messageLabel");
        if (data.status == "fail") {
            txtMessage.innerHTML = "Error while delete regimen data";
        } else {
            console.log("-- reload page --");
            window.location.href = "/detailPhase?id=" + $("#phaseId").val();
        }
    });
    console.log("end delete");
});

// insert practice validator

// update practice validator

// delete practice validator