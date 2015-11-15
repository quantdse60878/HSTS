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
var curFoodPhase = 0;
var curPracticePhase = 0;


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
                    var btnUpdate = '<a onclick="updateMedicineDialog('+ data +')" class="btn btn-success" style="margin-right: 20px">Update</a>';
                    var btnDelete = '<a onclick="deleteMedicineDialog('+ data +')" class="btn btn-danger">Delete</a>';
                    return btnUpdate + btnDelete;
                },
                "width": "20%"
            }
        ]
    } );
    // end medicine

    // food
    $('#foodContent').dataTable( {
        "processing": true,
        "pagingType": "full",
        "paging": true,
        "lengthChange": false,
        "ordering": true,
        "info": true,

        "ajax": {
            "url": "/phase/foods",
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
                "data": "food.name",
                "width": "25%"
            },
            // col 3
            {
                "data": "numberOfTime",
                "width": "10%"
            },
            // col 4
            {
                "data": "quantitative",
                "width": "10%"
            },
            // col 5
            {
                "data": "unitName",
                "width": "10%"
            },
            {
                "data": "advice",
                "width": "20%"
            },
            {
                "data": "id",
                "render": function (data, type, full, meta) {
                    var btnUpdate = '<a onclick="updateFoodDialog('+ data +')" class="btn btn-success" style="margin-right: 20px">Update</a>';
                    var btnDelete = '<a onclick="deleteFoodDialog('+ data +')" class="btn btn-danger">Delete</a>';
                    return btnUpdate + btnDelete;
                },
                "width": "20%"
            }
        ]
    } );
    // food

    // practice
    $('#practiceContent').dataTable( {
        "processing": true,
        "pagingType": "full",
        "paging": true,
        "lengthChange": false,
        "ordering": true,
        "info": true,

        "ajax": {
            "url": "/phase/practices",
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
                "data": "practice.name",
                "width": "25%"
            },
            // col 3
            {
                "data": "practice.intensity",
                "width": "5%"
            },
            // col 4
            {
                "data": "timeDuration",
                "width": "15%"
            },
            // col 5
            {
                "data": "numberOfTime",
                "width": "5%"
            },
            {
                "data": "advice",
                "width": "20%"
            },
            {
                "data": "id",
                "render": function (data, type, full, meta) {
                    var btnUpdate = '<a onclick="updatePracticeDialog('+ data +')" class="btn btn-success" style="margin-right: 20px">Update</a>';
                    var btnDelete = '<a onclick="deletePracticeDialog('+ data +')" class="btn btn-danger">Delete</a>';
                    return btnUpdate + btnDelete;
                },
                "width": "20%"
            }
        ]
    } );
    // practice

    // Load medicine list content
    $.ajax({
        method: "GET",
        url: "/medicine/list",
        data: {
            pageSize: 2147483647 // unlimited
        }
    }).done(function(data) {
        var html = '<option disable="disable" value="">Select a medicine</option>';
        $.each(data.dataList, function (key, element) {
            html += '<option value="' + element.id + '">' + element.name + '</option>';
        })
        var $medicineSelect = $("#insertMedicine");
        $medicineSelect.append(html);
    })
    // End load

    // Load food list content
    $.ajax({
        method: "GET",
        url: "/food/list",
        data: {
            pageSize: 2147483647 // unlimited
        }
    }).done(function(data) {
        var html = '<option disable="disable" value="">Select a food</option>';
        $.each(data.dataList, function (key, element) {
            html += '<option value="' + element.id + '">' + element.name + '</option>';
        })
        var $medicineSelect = $("#insertFood");
        $medicineSelect.append(html);
    })
    // end load

    // dynamic load food unit
    $('#insertFood').on("change", function (e) {
        var foodId = $('#insertFood').val();
        $.ajax({
            method: "GET",
            url: "/foodUnit",
            data: {
                foodId: foodId
            }
        }).done(function(data) {
            var html = '<option value="" disabled="disabled">Select a food</option>';
            $.each(data, function (key, element) {
                html += '<option value="' + element + '">' + element + '</option>';
            })
            var $unitSelect = $("#insertFoodUnitName");
            $unitSelect.html('');
            $unitSelect.append(html);
        })
    });

    // end load

    // load practice list content
    $.ajax({
        method: "GET",
        url: "/practice/list",
        data: {
            pageSize: 2147483647 // unlimited
        }
    }).done(function(data) {
        var html = '<option disable="disable" value="">Select a practice</option>';
        $.each(data.dataList, function (key, element) {
            html += '<option value="' + element.id + '">' + element.name + '</option>';
        })
        var $medicineSelect = $("#insertPractice");
        $medicineSelect.append(html);
    })
    // end load

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

function updateFoodDialog(element) {
    curFoodPhase = element;
    $.ajax({
        method: "GET",
        url: "/phase/food/detail",
        data: {
            id: element
        }
    }).done(function(data) {
        console.log(data);
        if (data != null) {

            var txtName = document.getElementById("updateFoodName");
            txtName.innerHTML = data.food.name;

            var txtTimes = document.getElementById("updateFoodTime");
            txtTimes.value =  data.numberOfTime;

            var txtQuantitative = document.getElementById("updateFoodQuantitative");
            txtQuantitative.value = data.quantitative;

            var txtAdvice = document.getElementById("updateFoodAdvice");
            txtAdvice.value = data.advice;

            var foodId = data.food.id;
            var unitName = data.unitName;
            // Unit select
            $.ajax({
                method: "GET",
                url: "/foodUnit",
                data: {
                    foodId: foodId
                }
            }).done(function(data) {
                var html = '';
                $.each(data, function (key, element) {
                    if (element == unitName) {
                        html += '<option selected="selected" value="' + element + '">' + element + '</option>';
                    } else {
                        html += '<option value="' + element + '">' + element + '</option>';
                    }
                })
                var $unitSelect = $("#updateFoodUnit");
                $unitSelect.append(html);
                $unitSelect.combobox();
            })
            // Unit select
            // Show diaglog
            $("#updateFoodDialog").modal('show');
        }
    });
}

function updatePracticeDialog(element) {
    curPracticePhase = element;
    $.ajax({
        method: "GET",
        url: "/phase/practice/detail",
        data: {
            id: element
        }
    }).done(function(data) {
        console.log(data);
        if (data != null) {

            var txtName = document.getElementById("updatePracticeName");
            txtName.innerHTML = data.practice.name;

            var txtDuration = document.getElementById("updatePracticeTimeDuration");
            txtDuration.value =  data.timeDuration;

            var txtTimes = document.getElementById("updatePracticeNumberOfTime");
            txtTimes.value = data.numberOfTime;

            var txtAdvice = document.getElementById("updatePracticeAdvice");
            txtAdvice.value = data.advice;

            // Show diaglog
            $("#updatePracticeDialog").modal('show');
        }
    });
}

function deleteMedicineDialog(element) {
    curMedicinePhase = element;
    //confirmMessageLabel.innerHTML = "Are you wish to delete this medicine?";
    $("#deleteMedicineDialog").modal('show');
}

function deleteFoodDialog(element) {
    curFoodPhase = element;
    $("#deleteFoodDialog").modal('show');
}

function deletePracticeDialog(element) {
    curPracticePhase = element;
    $("#deletePracticeDialog").modal('show');
}

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
            required: "Please input valid times"
        },
        insertQuantitative: {
            required: "Please input valid quantitative"
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
                medicineId: $("#insertMedicine").val(),
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
$("#insertPracticeForm").validate({
    ignore: [],
    debug: true,
    rules: {
        // simple rule, converted to {required:true}
        insertPractice: {
            required: true
        },
        insertPracticeTimeDuration: {
            required: true
        },
        insertPracticeNumberOfTime: {
            required: true,
            min: 1,
            max: 5
        }
    },
    errorPlacement: function(error, element){
        if(element.attr("name") == "insertPractice"){
            error.appendTo($('#invalidInsertPractice'));
        }  else if (element.attr("name") == "insertPracticeTimeDuration") {
            error.appendTo($('#invalidInsertTimeDuration'));
        } else if (element.attr("name") == "insertPracticeNumberOfTime") {
            error.appendTo($('#invalidInsertPracticeNumberOfTime'));
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
            url: "/phase/practice/add",
            data: {
                phaseId: $("#phaseId").val(),
                practiceId: $("#insertPractice").val(),
                timeDuration: $("#insertPracticeTimeDuration").val(),
                numberOfTime: $("#insertPracticeNumberOfTime").val(),
                advice: $("#insertPracticeAdvice").val()
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


// update practice validator
$("#updatePracticeForm").validate({
    ignore: [],
    debug: true,
    rules: {
        // simple rule, converted to {required:true}
        updatePracticeTimeDuration: {
            required: true
        },
        updatePracticeNumberOfTime: {
            required: true,
            min: 1,
            max: 5
        }
    },
    errorPlacement: function(error, element){
        if(element.attr("name") == "updatePracticeTimeDuration"){
            error.appendTo($('#invalidTimeDuration'));
        }  else if (element.attr("name") == "updatePracticeNumberOfTime") {
            error.appendTo($('#invalidUpdatePracticeNumberOfTime'));
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
            url: "/phase/practice/update",
            data: {
                id: curPracticePhase,
                timeDuration: $("#updatePracticeTimeDuration").val(),
                numberOfTime: $("#updatePracticeNumberOfTime").val(),
                advice: $("#updatePracticeAdvice").val()
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

// delete practice validator
$( "#btnDeletePractice" ).click(function() {
    console.log("begin delete");
    $.ajax({
        method: "POST",
        url: "/phase/practice/delete",
        data: {
            id: curPracticePhase
        }
    }).done(function(data) {
        console.log(data);
        var txtMessage = document.getElementById("messageLabel");
        if (data.status == "fail") {
            txtMessage.innerHTML = "Error while delete practice phase data";
        } else {
            console.log("-- reload page --");
            window.location.href = "/detailPhase?id=" + $("#phaseId").val();
        }
    });
    console.log("end delete");
});

// insert food validator
$("#insertFoodForm").validate({
    ignore: [],
    debug: true,
    rules: {
        // simple rule, converted to {required:true}
        insertFood: {
            required: true
        },
        insertFoodUnitName: {
            required: true,
        },
        insertFoodTime: {
            required: true,
            min: 1,
            max: 7
        },
        insertFoodQuantitative: {
            required: true,
            min: 1,
            max: 5
        },
        insertFoodAdvice: {
            required: true
        }
    },
    errorPlacement: function(error, element){
        if(element.attr("name") == "insertFood"){
            error.appendTo($('#invalidInsertFood'));
        }  else if (element.attr("name") == "insertFoodUnitName") {
            error.appendTo($('#invalidInsertFoodUnitName'));
        } else if (element.attr("name") == "insertFoodTime") {
            error.appendTo($('#invalidInsertFoodTime'));
        } else if (element.attr("name") == "insertFoodQuantitative") {
            error.appendTo($('#invalidInsertFoodQuantitative'));
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
            url: "/phase/food/add",
            data: {
                phaseId: $("#phaseId").val(),
                foodId: $("#insertFood").val(),
                numberOfTime: $("#insertFoodTime").val(),
                quantitative: $("#insertFoodQuantitative").val(),
                unitName: $("#insertFoodUnitName").val(),
                advice: $("#insertFoodAdvice").val()
            }
        }).done(function(data) {
            console.log(data);
            var txtMessage = document.getElementById("messageLabel");
            if (data.status == "fail") {
                txtMessage.innerHTML = "Error while insert regimen data";
            } else {
                console.log("-- reload page --");
                window.location.href = "/detailPhase?id=" + $("#phaseId").val();
            }
        });
        console.log("end update");
        return false; // required to block normal submit since you used ajax
    }
});


// update food validator
$("#updateFoodForm").validate({
    ignore: [],
    debug: true,
    rules: {
        // simple rule, converted to {required:true}
        updateFoodTime: {
            required: true,
            min: 1,
            max: 7
        },
        updateFoodQuantitative: {
            required: true,
            min: 1,
            max: 5
        },
        updateFoodUnit: {
            required: true
        }
    },
    messages: {
        //patientName: {
        //    maxlenght: "Name is too long, please modify it"
        //},
        updateFoodTime: {
            required: "Please input valid illness name",
        },
        updateFoodQuantitative: {
            required: "Please input valid description"
        },
        updateFoodUnit: {
            required: "Please choose an unit"
        }
    },
    errorPlacement: function(error, element){
        if(element.attr("name") == "updateFoodTime"){
            error.appendTo($('#invalidUpdateFoodTime'));
        }  else if (element.attr("name") == "updateFoodQuantitative") {
            error.appendTo($('#invalidUpdateFoodQuantitative'));
        } else if (element.attr("name") == "updateFoodUnit") {
            error.appendTo($('#invalidUpdateFoodUnit'));
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
            url: "/phase/food/update",
            data: {
                id: curFoodPhase,
                numberOfTime: $("#updateFoodTime").val(),
                quantitative: $("#updateFoodQuantitative").val(),
                unitName: $("#updateFoodUnit").val(),
                advice: $("#updateFoodAdvice").val()
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

// delete food validator
$( "#btnDeleteFood" ).click(function() {
    console.log("begin delete");
    $.ajax({
        method: "POST",
        url: "/phase/food/delete",
        data: {
            id: curFoodPhase
        }
    }).done(function(data) {
        console.log(data);
        var txtMessage = document.getElementById("messageLabel");
        if (data.status == "fail") {
            txtMessage.innerHTML = "Error while delete food phase data";
        } else {
            console.log("-- reload page --");
            window.location.href = "/detailPhase?id=" + $("#phaseId").val();
        }
    });
    console.log("end delete");
});
