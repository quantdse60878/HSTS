/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/28/2015.
 */

$("#gender").iCheck({
    checkboxClass: 'icheckbox_flat-red',
    radioClass: 'iradio_flat-red'
});

$("#Birthday").datepicker({
    format: 'dd-mm-yyyy',
    startDate: '1950-01-01',
    endDate: 'today'
});
function changeTab (a, li) {
    console.log("a: " + a);
    console.log("li: " + li);
    $('li.active').removeClass('active');
    $(li).addClass('active');
    $('.tab-pane.active').removeClass('active');
    $(a).addClass('active');
};

$("#doctorSelect").select2({
    theme: "bootstrap",
    width: "inherit",
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

var validator = $("#mainForm").validate({
    ignore: []
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
        //loadPatientProfile(val);
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

