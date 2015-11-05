/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/28/2015.
 */

var newMRCheck = $("#isNewMedicalRecord").iCheck({
    checkboxClass: 'icheckbox_flat-blue',
    radioClass: 'iradio_flat-blue'
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

// Validator
var validator = $("#mainForm").validate({
    ignore: [],
    debug: true,
    rules: {
        // simple rule, converted to {required:true}
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
        if (element.attr("name") == "weight") {
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

// Init tag input for medical history, medicine history and symtoms
var medicalTagInput = $('#medicalHistory');
var symptomsTagInput = $('#symptoms');
var medicineHistoryTagInput = $('#medicineHistory');

// Add tag for symptoms
symptomsTagInput.tagsinput('add', 'RE-EXAMINATE');


// Upload file function
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
    // Add to tag input
    medicalTagInput.tagsinput('add', response.fileName + "");
});

fileUploader.on('filebatchuploadcomplete', function(event, data, previewId, index) {
    console.log('File batch upload successfully');
    fileUploader.fileinput('refresh');
    $('#uploadModal').modal('hide');
});

newMRCheck.on('ifChecked', function(event){
    console.log("test1");
    // Remove Re-exam tag
    symptomsTagInput.tagsinput('remove', 'RE-EXAMINATE');
    // Add New medical record tag
    symptomsTagInput.tagsinput('add', 'NEW MEDICAL RECORD');

});

newMRCheck.on('ifUnchecked', function(event){
    console.log("test2");
    // Add Re-exam tag
    symptomsTagInput.tagsinput('add', 'RE-EXAMINATE');
    // Remove New medical record tag
    symptomsTagInput.tagsinput('remove', 'NEW MEDICAL RECORD');

});