/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/28/2015.
 */

$("#gender").iCheck({
    checkboxClass: 'icheckbox_flat-blue',
    radioClass: 'iradio_flat-blue'
});

$("#birthday").datepicker({
    format: 'dd-mm-yyyy',
    startDate: '1950-01-01',
    endDate: 'today'
});

function changeTab (a, li) {
    console.log("a: " + a);
    console.log("li: " + li);
    $('.nav-tabs > li.active').removeClass('active');
    $(li).addClass('active');
    $('.tab-content > .tab-pane.active').removeClass('active');
    $(a).addClass('active');
};

$("#doctorSelect").select2({
    placeholder: "Choose a doctor",
    theme: "bootstrap",
    width: "400px",
    ajax: {
        url: "/doctor/list",
        dataType: 'json',
        delay: 250,
        data: function (params, page) {
            return {
                searchString: params.term, // search term
                page: params.page,
                pageSize: 5
            };
        },
        processResults: function (data, params) {
            params.page = params.page || 0;
            var names = data.dataList.map(function (doctor) {
                return {
                    id: doctor.id,
                    text: doctor.account.fullName
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
    }
});

var $selectPatient = $("#patientName").select2({
    placeholder: "Type patient name",
    theme: "bootstrap",
    width: "100%",
    allowClear: false,
    //placeholder: "Select a patient or input new",
    ajax: {
        url: "/patient/list",
        dataType: 'json',
        delay: 250,
        data: function (params) {
            return {
                searchString: params.term, // search term
                page: params.page
            };
        },
        processResults: function (data, params) {
            params.page = params.page || 0;
            var names = data.dataList.map(function (obj) {
                return {
                    id: obj.barcode,
                    text: (obj.account.fullName + " - " + obj.barcode)
                }
            });
            return {
                results: names,
                pagination: {
                    more: ( (data.pageNumber + 1)  * 5) < data.totalElements
                }
            };
        },
        cache: true
    },
    escapeMarkup: function (markup) { return markup; },
    tags: true,
    createTag: function (tag) {
        return {
            id: tag.term,
            text: tag.term,
            tag: true
        };
    },
    maximumSelectionLength: 2
});


$selectPatient.on("change", function (e) {
    var val = $selectPatient.val();
    var textData = $selectPatient.text().trim();
    console.log("Value: " + val);
    console.log("Text: " + textData);
    if (val == null) {
        return;
    }
    if (val == "") {
        return;
    }
    if (val.indexOf("404") == 0) {
        // Bind patient profile
        loadPatientProfile(val);
    };
});

// Validator
var validator = $("#mainForm").validate({
    ignore: [],
    debug: true,
    rules: {
        // simple rule, converted to {required:true}
        patientName: {
            required: true
        },
        birthday: {
            required: true
        },
        // compound rule
        email: {
            required: true,
            email: true,
            remote: {
                // Explicit
                url: "/validateEmail",
                type: "POST",
                data: {
                    email: function() {
                        return $( "#email" ).val();
                    }
                }
            }
        },
        weight: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        visceralFat: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        height: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        muscleMass: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        heartBeat: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        bodyWater: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        bloodPressure: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        phaseAngle: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        waists: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        impedance: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        bodyFat: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        basalMetabolicRate: {
            required: true,
            remote: {
                url: "/validateData",
                type: "POST"
            }
        },
        doctorId: {
            required: true
        },
        symptoms: {
            required: true
        }
    },
    messages: {
        //patientName: {
        //    maxlenght: "Name is too long, please modify it"
        //},
        birthday: "Please input valid birthday",
        email: {
            email: "This email is invalid format",
            remote: "This email is already use"
        },
        weight: {
            remote: "Invalid weight out of range, please fix"
        },
        visceralFat: {
            remote: "Invalid visceral fat out of range, please fix"
        },
        height: {
            remote: "Invalid height out of range, please fix"
        },
        muscleMass: {
            remote: "Invalid muscleMass out of range, please fix"
        },
        heartBeat: {
            remote: "Invalid heartBeat out of range, please fix"
        },
        bodyWater: {
            remote: "Invalid bodyWater out of range, please fix"
        },
        bloodPressure: {
            remote: "Invalid bloodPressure out of range, please fix"
        },
        phaseAngle: {
            remote: "Invalid phaseAngle out of range, please fix"
        },
        waists: {
            remote: "Invalid waists out of range, please fix"
        },
        impedance: {
            remote: "Invalid impedance out of range, please fix"
        },
        bodyFat: {
            remote: "Invalid bodyFat out of range, please fix"
        },
        basalMetabolicRate: {
            remote: "Invalid bodyFat out of range, please fix"
        },
        doctorId: {
            required: "Please choose at least one doctor"
        }
    },
    errorPlacement: function(error, element){
        if(element.attr("name") == "patientName"){
            error.appendTo($('#invalidPatientName'));
        }  else if (element.attr("name") == "birthday") {
            error.appendTo($('#invalidBirthday'));
        } else if (element.attr("name") == "email") {
            error.appendTo($('#invalidEmail'));
        } else if (element.attr("name") == "weight") {
            error.appendTo($('#invalidWeight'));
        } else if (element.attr("name") == "visceralFat") {
            error.appendTo($('#invalidVisceralFat'));
        } else if (element.attr("name") == "height") {
            error.appendTo($('#invalidHeight'));
        } else if (element.attr("name") == "muscleMass") {
            error.appendTo($('#invalidMuscleMass'));
        } else if (element.attr("name") == "heartBeat") {
            error.appendTo($('#invalidHeartBeat'));
        } else if (element.attr("name") == "bodyWater") {
            error.appendTo($('#invalidBodyWater'));
        } else if (element.attr("name") == "bloodPressure") {
            error.appendTo($('#invalidBloodPressure'));
        } else if (element.attr("name") == "phaseAngle") {
            error.appendTo($('#invalidPhaseAngle'));
        } else if (element.attr("name") == "phaseAngle") {
            error.appendTo($('#invalidPhaseAngle'));
        } else if (element.attr("name") == "waists") {
            error.appendTo($('#invalidWaists'));
        } else if (element.attr("name") == "impedance") {
            error.appendTo($('#invalidImpedance'));
        } else if (element.attr("name") == "bodyFat") {
            error.appendTo($('#invalidBodyFat'));
        } else if (element.attr("name") == "basalMetabolicRate") {
            error.appendTo($('#invalidBasalMetabolicRate'));
        } else if (element.attr("name") == "doctorId") {
            error.appendTo($('#invalidDoctorId'));
        } else if (element.attr("name") == "doctorId") {
            error.appendTo($('#invalidDoctorId'));
        } else if (element.attr("name") == "symptoms") {
            error.appendTo($('#invalidSymptoms'));
        }

        // Default
        else {
            error.appendTo( element.parent().next() );
        }
    },
    submitHandler: function(form) {
    form.submit();
    },
    invalidHandler: function(e, validator) {
        if (validator.errorList.length > 0) {
            console.log("Change to first tab has error");
            var targetTab = jQuery(validator.errorList[0].element).closest(".tab-pane").attr('id');
            changeTab('#' + targetTab, '#li_' + targetTab);
            console.log("end change");
        }
    }
});


function validateAndChangeTab(targetTab, targetLi) {
    var valid = true;
    var $fields = $('.tab-pane.active').find('input');
    console.log($fields);
    $fields.each (function() {
        if (!validator.element(this) && valid) {
            valid = false;
        }
    });

    $fields = $('.tab-pane.active').find('select');
    $fields.each (function() {
        if (!validator.element(this) && valid) {
            valid = false;
        }
    });
    console.log("Valid: " + valid);
    if (valid) {
        changeTab(targetTab, targetLi);
    }

};

$selectPatient.on("change", function (e) {
    var val = $selectPatient.val();
    var textData = $selectPatient.text().trim();
    console.log("Value: " + val);
    console.log("Text: " + textData);
    if (val.indexOf("404") == 0) {
        // Bind patient profile
        loadPatientProfile(val);
    };
});

function loadPatientProfile(patientBarcode) {
    $.ajax({
        method: "GET",
        url: "/patient/profile",
        data: {
            barcode: patientBarcode
        }
    })
    .done(function(data) {
        console.log(data);
        if(data != null) {
            // Set data to html
            var txtName = document.getElementById("txtName");
            var value = data.account.fullName;
            console.log(value);
            txtName.innerHTML = value;

            var txtBirthday = document.getElementById("txtBirthday");
            value = data.account.birthday;
            console.log(value);
            txtBirthday.innerHTML = value;

            var txtEmail = document.getElementById("txtEmail");
            value = data.account.email;
            console.log(value);
            txtEmail.innerHTML = value;

            // Set gender
            var btnGender = document.getElementById("btnGender");
            value = data.account.gender;
            if(value == "MALE") {
                btnGender.setAttribute("class", "btn btn-info");
                btnGender.setAttribute("value", "MALE");
            } else {
                btnGender.setAttribute("class", "btn btn-danger");
                btnGender.setAttribute("value", "FEMALE");
            }

            // Set href
            var btnLink = document.getElementById("btnLink");
            btnLink.setAttribute("href", "patient?patientID=" + data.id);

            // Show pop-up
            popup('popupPatient');
        }

    });
};

function popup(windowname) {
    blanket_size(windowname);
    window_pos(windowname);
    toggle('blanket');
    toggle(windowname);
};

function blanket_size(popUpDivVar) {
    if (typeof window.innerWidth != 'undefined') {
        viewportheight = window.innerHeight;
    } else {
        viewportheight = document.documentElement.clientHeight;
    }
    if (viewportheight > document.body.parentNode.scrollHeight) {
        if (viewportheight > document.body.parentNode.clientHeight) {
            blanket_height = viewportheight;
        } else {
            if (document.body.parentNode.clientHeight > document.body.parentNode.scrollHeight) {
                blanket_height = document.body.parentNode.clientHeight;
            } else {
                blanket_height = document.body.parentNode.scrollHeight;
            }
        }
    } else {
        if (document.body.parentNode.clientHeight > document.body.parentNode.scrollHeight) {
            blanket_height = document.body.parentNode.clientHeight;
        } else {
            blanket_height = document.body.parentNode.scrollHeight;
        }
    }
    var blanket = document.getElementById('blanket');
    blanket.style.height = blanket_height + 'px';
    var popUpDiv = document.getElementById(popUpDivVar);
    popUpDiv_height = blanket_height / 2 - 300;//150 is half popup's height
    popUpDiv.style.top = popUpDiv_height + 'px';
}
;
function window_pos(popUpDivVar) {
    if (typeof window.innerWidth != 'undefined') {
        viewportwidth = window.innerHeight;
    } else {
        viewportwidth = document.documentElement.clientHeight;
    }
    if (viewportwidth > document.body.parentNode.scrollWidth) {
        if (viewportwidth > document.body.parentNode.clientWidth) {
            window_width = viewportwidth;
        } else {
            if (document.body.parentNode.clientWidth > document.body.parentNode.scrollWidth) {
                window_width = document.body.parentNode.clientWidth;
            } else {
                window_width = document.body.parentNode.scrollWidth;
            }
        }
    } else {
        if (document.body.parentNode.clientWidth > document.body.parentNode.scrollWidth) {
            window_width = document.body.parentNode.clientWidth;
        } else {
            window_width = document.body.parentNode.scrollWidth;
        }
    }
    var popUpDiv = document.getElementById(popUpDivVar);
    window_width = window_width / 2 - 300;//200 is half popup's width
    popUpDiv.style.left = window_width + 'px';
}
;

function toggle(div_id) {
    var el = document.getElementById(div_id);
    if (el.style.display == 'none') {
        el.style.display = 'block';
    }
    else {
        el.style.display = 'none';
    }
}

var fileUploader = $("#fileUploader").fileinput({
    uploadUrl: "/uploadImage", // server upload action
    uploadAsync: true,
    maxFileCount: 5,
    allowedFileTypes: ['image'],
    allowedFileExtensions: ['jpg', 'gif', 'png'],
    maxFileSize: 5170 // 5 MB
});

fileUploader.on('fileuploaded', function(event, data, previewId, index) {
    var response = data.response;
    console.log('File uploaded triggered: ' + response.result);
    console.log('File uploaded triggered: ' + response.fileName);
});

fileUploader.on('filebatchuploadcomplete', function(event, data, previewId, index) {
    console.log('File batch upload successfully');
    fileUploader.fileinput('refresh');
    $('#uploadModal').modal('hide');
});