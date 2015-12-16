/**
 * Created by Aking on 9/28/2015.
 */



// Validator
$("#suggestForm").validate({
    ignore: [],
    debug: true,
    rules: {
        diagnostic: {
            required: true
        }
    },
    messages: {
        diagnostic: {
            required: "Please choose diagnostic."
        }
    },
    errorPlacement: function (error, element) {
        if(element.attr("name") == "diagnostic"){
            error.appendTo($('#invalidDiagnostic'));
        } else {
            error.appendTo(element.parent());
        }
    },
    submitHandler: function (form) {
        form.submit();
    },
    invalidHandler: function (e, validator) {
        if (validator.errorList.length > 0) {
            console.log("Change to first tab has error");
            console.log(validator.errorList);
            var targetTab = jQuery(validator.errorList[0].element).closest(".tab-pane").attr('id');
            changeTab('#' + targetTab, '#li_' + targetTab);
            console.log("end change");
        }
    }
});
var validator = $("#mainForm").validate({
    ignore: [],
    debug: true,
    rules: {
        diagnostic: {
            required: true
        },
        kcalRequire: {
            remote: {
                url: "/validateData",
                type: "POST"
            }
        }
    },
    messages: {
        diagnostic: {
            required: "Please choose diagnostic."
        },
        kcalRequire: {
            remote: "Wrong input"
        }
    },
    errorPlacement: function (error, element) {
        if(element.attr("name") == "diagnostic"){
            error.appendTo($('#invalidDiagnostic'));
        } else {
            error.appendTo(element.parent());
        }
    },
    submitHandler: function (form) {
        //form.submit();
        var diagno = $("#select2Box").val();
        if(diagno == "No illness"){
            $('#patientName').html($("#txtpatientName").text());
            // Hide pop-up
            $('#myModal').modal('hide');
            // Show pop-up
            $('#noIllness').modal('show');
        } else {
            // get val of table medicine
            var rows = document.getElementsByClassName('rowMedi');
            var mPresModels = [];
            for (var i = 0; i <rows.length; i++){
                var row = rows[i];
                var el = {
                    m: row.cells[1].firstElementChild.value,
                    mTime: row.cells[2].firstElementChild.value,
                    mQuantity: row.cells[3].firstElementChild.value,
                    mUnit: row.cells[4].firstElementChild.value,
                    mNote: row.cells[5].firstElementChild.value
                }
                mPresModels.push(el);
            }
            console.log(mPresModels);
            // get val of table food
            var rows = document.getElementsByClassName('rowFood');
            var fPresModels = [];
            for (var i = 0; i <rows.length; i++){
                var row = rows[i];
                var el = {
                    f: row.cells[1].firstElementChild.value,
                    fTime: row.cells[2].firstElementChild.value,
                    fQuantity: row.cells[3].firstElementChild.value,
                    fUnit: row.cells[4].firstElementChild.value,
                    fNote: row.cells[5].firstElementChild.value
                }
                fPresModels.push(el);
            }
            console.log(fPresModels);
            // get val of table practice
            var rows = document.getElementsByClassName('rowPrac');
            var pPresModels = [];
            for (var i = 0; i <rows.length; i++){
                var row = rows[i];
                var el = {
                    p: row.cells[1].firstElementChild.value,
                    pTime: row.cells[2].firstElementChild.value,
                    pIntensity: row.cells[3].firstElementChild.value,
                    pNote: row.cells[4].firstElementChild.value
                }
                pPresModels.push(el);
            }
            console.log(pPresModels);
            // JSON data
            var postData = {
                appointmentId: $("#appointmentId").val(),
                appointmentDate: $("#Appointment").val(),
                diagnostic: $("#select2Box").val(),
                kcalRequire: $("#kcalRequire").val(),
                note: $("#note").val(),
                mPresModels: mPresModels,
                fPresModels: fPresModels,
                pPresModels: pPresModels
            }
            // JSON data

            $.ajax({
                method: "POST",
                url: "/makePrescription2",
                contentType: "application/json",
                data: JSON.stringify(postData)
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

                        // Hide pop-up
                        $('#myModal').modal('hide');
                        // Show pop-up
                        $('#resultPre').modal('show');
                        changeTabb("#tabRe_1", "#li_tabRe_1");
                    }

                });

            return false;
        }

    },
    invalidHandler: function (e, validator) {
        if (validator.errorList.length > 0) {
            $(".modal").modal('hide');
            console.log("Change to first tab has error");
            console.log(validator.errorList);
            var targetTab = jQuery(validator.errorList[0].element).closest(".tab-pane").attr('id');
            changeTabb('#' + targetTab, '#li_' + targetTab);
            console.log("end change");
        }
    }
});

function changeTabb(a, li) {
    console.log("a: " + a);
    console.log("li: " + li);
    $('.nav-tabs > li.active').removeClass('active');
    $(li).addClass('active');
    $('.tab-content > .tab-pane.active').removeClass('active');
    $(a).addClass('active');
};

function validateAndChangeTab(targetTab, targetLi) {
    var valid = true;
    var $fields = $('.tab-pane.active').find('input');
    console.log($fields);
    $fields.each(function () {
        if (!validator.element(this) && valid) {
            valid = false;
        }
    });

    $fields = $('.tab-pane.active').find('select');
    $fields.each(function () {
        if (!validator.element(this) && valid) {
            valid = false;
        }
    });
    console.log("Valid: " + valid);
    if (valid) {
        changeTabb(targetTab, targetLi);
    }

};

function validateAuto() {
        var $fields = $('.error').find('lable');
        if($fields.prevObject.length > 0){
            console.log(this);
            //$('<span class="error fa fa-exclamation-circle"></span>').appendTo(licheck);
        }
    $fields.prevObject.each(function () {
        console.log(this);
        var p = this.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode;
        //console.log(p);
    });
};
//var isValidAuto = setInterval(validateAuto, 5000);
function validateAndOpenModal(m) {

    var a = document.getElementById("medicine");
    var b = document.getElementById("Foods");
    var c = document.getElementById("Practice");

    if(a.rows.length <=1 && b.rows.length <= 1 && c.rows.length <= 1) {
        alert("Please select No illness in Diagnostic, if patient does not have illness");
        return;
    }

    var valid = true;
    var $fields = $('.tab-pane.active').find('input');
    console.log($fields);
    $fields.each(function () {
        if (!validator.element(this) && valid) {
            valid = false;
        }
    });

    $fields = $('.tab-pane.active').find('select');
    $fields.each(function () {
        if (!validator.element(this) && valid) {
            valid = false;
        }
    });
    console.log("Valid: " + valid);
    if (valid) {
        $(m).modal('show')
    }

};
function viewAutoCompleteP(input) {
    //console.log(input);
    var id = input.getAttribute('id');
    var item = id + "item";
    viewAutoComplete(id, item);
}
function viewAutoComplete(id, item) {
    var val = document.getElementById(id).value;
    var it = document.getElementById(item);
    it.style.visibility = "hidden";
    it.style.height = "0px";
    if (val != "") {
        it.innerHTML = "";
        for (var i = 0; i < listPracticeName.length; i++) {
            if (listPracticeName[i].toLowerCase().indexOf(val.toLowerCase()) > -1) {
                it.style.visibility = "visible";
                it.style.height = "auto";
                var btn = document.createElement("p");
                var t = document.createTextNode(listPracticeName[i]);
                var att = document.createAttribute("onclick");
                att.value = "fillInput('" + id + "','" + item + "','" + listPracticeName[i] + "')";
                btn.appendChild(t);
                btn.setAttribute("class", "form-control");
                btn.setAttribute("style", "margin: 0px;border: 0px;box-shadow: none;");
                btn.setAttributeNode(att);
                it.appendChild(btn);
            }
        }
    }
}
function fillInput(id, item, c) {
    var it = document.getElementById(item);
    it.style.visibility = "hidden";
    it.style.height = "0px";
    document.getElementById(id).value = c;
}

function findUnits(food) {
    var id = food.getAttribute('id');
    var preID = id.split('.')[0];
    var unitIdN = preID + 'fUnit';
    var unitID = "#" + preID + 'fUnit';
    var foodId = food.value;
    console.log("Food " + foodId);
    $.ajax({
        dataType: "json",
        url: 'getFoodUnits',
        data: {
            'foodId': foodId
        },
        success: function (json) {
            //console.log("success");
            var options = '';
            document.getElementById(unitIdN).innerHTML = "";
            for (var i = 0; i < json.length; i++) {
                var tmp = json[i];
                console.log(tmp);
                options = options + '<option value="' + tmp.foodUnitId + '">' + tmp.unitName + '</option>';
            }
            $(options).appendTo(unitID);
        }
    });
}

function loadSelect(id) {
    $(id).select2({
        placeholder: "Select",
        width: "200px",
        ajax: {
            url: "/medicineName/list",
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    searchString: params.term, // search term
                    page: params.page,
                };
            },
            processResults: function (data, page) {
                var names = data.map(function (name) {
                    return {
                        id: name,
                        text: name
                    }
                });
                return {
                    results: names, more: false
                };
            },
            cache: false
        },
        escapeMarkup: function (markup) {
            return markup;
        },
        tags: true,
        createTag: function (tag) {
            return {
                id: tag.term,
                text: tag.term,
                tag: true
            };
        }
    });
};
// create select2 medicine
//for(var i=0; i<= $('#medics').val(); i++){
//    loadSelect('#mPresModelsM'+i+'');
//}
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

                //hideWatting();
                // Show pop-up
                $('#resultPre').modal('show');
            }

        });
}

function loadPopupAppointment(appointmentId) {
    //showWatting();
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
                $('#inforDateHis').html(data.dateInfor);
                var table = [];
                var row = [];
                // infor table medicines
                $('#hisMedicine').html('<tr><th style="width: 10px">#</th>'
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
                $(table).appendTo("#hisMedicine");

                table = [];
                row = [];
                // infor table medicines
                $('#hisFoods').html('<tr><th style="width: 10px">#</th>'
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
                $(table).appendTo("#hisFoods");

                table = [];
                row = [];
                // infor table medicines
                $('#hisPractice').html('<tr><th style="width: 10px">#</th>'
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
                $(table).appendTo("#hisPractice");

                //hideWatting();
                // Show pop-up
                $('#hisAppointment').modal('show');
            }

        });
};

// Select2 for diagnostic
var $illne = $("#select2Box").select2({
    width: "200px",
    ajax: {
        url: "/illnessName/list",
        dataType: 'json',
        delay: 250,
        data: function (params) {
            return {
                searchString: params.term, // search term
                page: params.page,
            };
        },
        processResults: function (data, page) {
            var names = data.map(function (name) {
                return {
                    id: name,
                    text: name
                }
            });
            return {
                results: names, more: false
            };
        },
        cache: false
    },
    escapeMarkup: function (markup) {
        return markup;
    },
    tags: true,
    createTag: function (tag) {
        return {
            id: tag.term,
            text: tag.term,
            tag: true
        };
    }
});
$illne.on("change", function (e) {
    var val = $illne.val();
    var textData = $illne.text().trim();
    console.log("Value: " + val);
    console.log("Text: " + textData);
    if (val != null) {
        if(val == "No illness"){
            $('#patientName').html($("#txtpatientName").text());
            // Show pop-up
            $('#noIllness').modal('show');
        }
    }

});

function finishAndChangePage() {
    $.ajax({
        method: "POST",
        url: "/finishTreatment",
        data: {
            appointmentId: $("#appointmentId").val()
        }
    }).done(function (data) {
        console.log(data);
        window.location.href = "doctorPatients";
    });
}
function deleteRowFood(t) {
    var row = t.parentNode.parentNode;
    var nextRow = row.nextElementSibling;
    document.getElementById('Foods').deleteRow(row.rowIndex);
    countF = reCounterRow(nextRow);
}
function deleteRowMedicine(t) {
    var row = t.parentNode.parentNode;
    //console.log(row.rowIndex);
    var nextRow = row.nextElementSibling;
    //console.log(nextRow);
    document.getElementById('medicine').deleteRow(row.rowIndex);
    //console.log(row);
    countM = reCounterRow(nextRow);
}

function deleteRowPractice(t) {
    var row = t.parentNode.parentNode;
    var nextRow = row.nextElementSibling;
    document.getElementById('Practice').deleteRow(row.rowIndex);
    countP = reCounterRow(nextRow);
}
function reCounterRow(row) {
    var count = row.rowIndex;
    row.firstElementChild.textContent = count;
    var nextRow = row.nextElementSibling;
    //console.log(nextRow);
    if (nextRow != undefined) {
        count = reCounterRow(nextRow);
    }
    return count;
}
/**
 * combobox
 */
(function ($) {
    $.widget("custom.combobox", {
        _create: function () {
            this.wrapper = $("<span>")
                .addClass("custom-combobox")
                .insertAfter(this.element);

            this.element.hide();
            this._createAutocomplete();
            this._createShowAllButton();
        },

        _createAutocomplete: function () {
            var selected = this.element.children(":selected"),
                value = selected.val() ? selected.text() : "";

            this.input = $("<input>")
                .appendTo(this.wrapper)
                .val(value)
                .attr("title", "")
                .addClass("custom-combobox-input ui-widget ui-widget-content ui-corner-left")
                .autocomplete({
                    delay: 0,
                    minLength: 0,
                    source: $.proxy(this, "_source")
                })
                .tooltip({
                    tooltipClass: "ui-state-highlight"
                });

            this._on(this.input, {
                autocompleteselect: function (event, ui) {
                    ui.item.option.selected = true;
                    this._trigger("select", event, {
                        item: ui.item.option
                    });
                },

                autocompletechange: "_removeIfInvalid"
            });
        },

        _createShowAllButton: function () {
            var input = this.input,
                wasOpen = false;

            $("<a>")
                .attr("tabIndex", -1)
                .attr("title", "Show All Items")
                .tooltip()
                .appendTo(this.wrapper)
                .button({
                    icons: {
                        primary: "ui-icon-triangle-1-s"
                    },
                    text: false
                })
                .removeClass("ui-corner-all")
                .addClass("custom-combobox-toggle ui-corner-right btn btn-default")
                .mousedown(function () {
                    wasOpen = input.autocomplete("widget").is(":visible");
                })
                .click(function () {
                    input.focus();

                    // Close if already visible
                    if (wasOpen) {
                        return;
                    }

                    // Pass empty string as value to search for, displaying all results
                    input.autocomplete("search", "");
                });
        },

        _source: function (request, response) {
            var matcher = new RegExp($.ui.autocomplete.escapeRegex(request.term), "i");
            response(this.element.children("option").map(function () {
                var text = $(this).text();
                if (this.value && ( !request.term || matcher.test(text) ))
                    return {
                        label: text,
                        value: text,
                        option: this
                    };
            }));
        },

        _removeIfInvalid: function (event, ui) {

            // Selected an item, nothing to do
            if (ui.item) {
                return;
            }

            // Search for a match (case-insensitive)
            var value = this.input.val(),
                valueLowerCase = value.toLowerCase(),
                valid = false;
            this.element.children("option").each(function () {
                if ($(this).text().toLowerCase() === valueLowerCase) {
                    this.selected = valid = true;
                    return false;
                }
            });

            // Found a match, nothing to do
            if (valid) {
                return;
            }

            // Remove invalid value
            //this.input
            //    .val( "" )
            //    .attr( "title", value + " didn't match any item" )
            //    .tooltip( "open" );
            //this.element.val( "" );
            //this._delay(function() {
            //    this.input.tooltip( "close" ).attr( "title", "" );
            //}, 2500 );
            //this.input.autocomplete( "instance" ).term = "";
        },

        _destroy: function () {
            this.wrapper.remove();
            this.element.show();
        }
    });
})(jQuery);

/**
 * comboboxx
 */
(function ($) {
    $.widget("custom.comboboxx", {
        _create: function () {
            this.wrapper = $("<span>")
                .addClass("custom-combobox")
                .insertAfter(this.element);

            this.element.hide();
            this._createAutocomplete();
            this._createShowAllButton();
        },

        _createAutocomplete: function () {
            var selected = this.element.children(":selected"),
                value = selected.val() ? selected.text() : "";

            this.input = $("<input>")
                .appendTo(this.wrapper)
                .val(value)
                .attr("title", "")
                .addClass("custom-combobox-input ui-widget ui-widget-content ui-corner-left")
                .autocomplete({
                    delay: 0,
                    minLength: 0,
                    source: $.proxy(this, "_source")
                })
                .tooltip({
                    tooltipClass: "ui-state-highlight"
                });

            this._on(this.input, {
                autocompleteselect: function (event, ui) {
                    //window.location = ui.item.url;
                    loadPopupAppointment(ui.item.id);
                },

                autocompletechange: "_removeIfInvalid"
            });
        },

        _createShowAllButton: function () {
            var input = this.input,
                wasOpen = false;

            $("<a>")
                .attr("tabIndex", -1)
                .attr("title", "Show All Items")
                .tooltip()
                .appendTo(this.wrapper)
                .button({
                    icons: {
                        primary: "ui-icon-triangle-1-s"
                    },
                    text: false
                })
                .removeClass("ui-corner-all")
                .addClass("custom-combobox-toggle ui-corner-right btn btn-default")
                .mousedown(function () {
                    wasOpen = input.autocomplete("widget").is(":visible");
                })
                .click(function () {
                    input.focus();

                    // Close if already visible
                    if (wasOpen) {
                        return;
                    }

                    // Pass empty string as value to search for, displaying all results
                    input.autocomplete("search", "");
                });
        },

        _source: function (request, response) {
            var matcher = new RegExp($.ui.autocomplete.escapeRegex(request.term), "i");
            response(this.element.children("option").map(function () {
                var text = $(this).text();
                var url = this.getAttribute('value');
                var id = this.getAttribute('id');
                if (this.value && ( !request.term || matcher.test(text) ))
                    return {
                        label: text,
                        value: text,
                        url: url,
                        id: id,
                        option: this
                    };
            }));
        },

        _removeIfInvalid: function (event, ui) {

            // Selected an item, nothing to do
            if (ui.item) {
                return;
            }

            // Search for a match (case-insensitive)
            var value = this.input.val(),
                valueLowerCase = value.toLowerCase(),
                valid = false;
            this.element.children("option").each(function () {
                if ($(this).text().toLowerCase() === valueLowerCase) {
                    this.selected = valid = true;
                    return false;
                }
            });

            // Found a match, nothing to do
            if (valid) {
                return;
            }

            // Remove invalid value
            this.input
                .val("")
                .attr("title", value + " didn't match any item")
                .tooltip("open");
            this.element.val("");
            this._delay(function () {
                this.input.tooltip("close").attr("title", "");
            }, 2500);
            this.input.autocomplete("instance").term = "";
        },

        _destroy: function () {
            this.wrapper.remove();
            this.element.show();
        }
    });
})(jQuery);
//$("#infordate").comboboxx();
var $selectDate = $("#infordate").select2({
    placeholder: "Choose a Date",
    width: "200px",

    ajax: {
        url: "appointmentListByPatientId",
        dataType: 'json',
        delay: 250,
        data: function (params, page) {
            return {
                patientID: $('#patientID').val(), // search term
                page: params.page,
            };
        },
        processResults: function (data, params) {
            params.page = params.page || 0;
            var names = data.dataList.map(function (appointment) {
                return {
                    id: appointment.id,
                    text: appointment.meetingDate
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

//$(".bigdrop .select2-results").css("max-height","300px");

$selectDate.on("change", function (e) {
    var val = $selectDate.val();
    var textData = $selectDate.text().trim();
    console.log("Value: " + val);
    console.log("Text: " + textData);
    if (val != null) {
            // Bind History Medical
            loadPopupAppointment(val);
    }

});
/**
 * popup
 * @param div_id
 */
function toggle(div_id) {
    var el = document.getElementById(div_id);
    if (el.style.display == 'none') {
        el.style.display = 'block';
    }
    else {
        el.style.display = 'none';
    }
}
;
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
function popup(windowname) {
    blanket_size(windowname);
    window_pos(windowname);
    toggle('blanket');
    toggle(windowname);
}
;
function changeTab(a) {
    console.log(a);
    $('.nav-tabs > li.active').removeClass('active');
    $('.nav-tabs > li > a[href="' + a + '"]').parent().addClass('active');
}
;
var check = $('#phase').val();
function confirmBox(form) {
    //if (check == '') {
    //    alert('Please suggest diagnostic before make prescription!!!');
    //    return false;
    //} else {
    //
    //}
    //var r = confirm('Are you sure to make this prescription?');
    //if (r == true) {
    // Hidden field for diagnostic
    //Get
    var bla = $('#select2Box').val().trim();
    console.log(bla);
    if (bla != null) {
        //Set
        $('#diagnosticValue').val(bla);
        console.log($('#diagnosticValue').val());
    }
    return true;
    //} else {
    //    return false;
    //}
}

$('#Appointment').datepicker({
    format: 'dd-mm-yyyy'
});

//var nextAppointmentDate = $('#nextApDate').val();
//nextAppointmentDate++;
//nextAppointmentDate--;
//var appointmentDate = new Date();
//appointmentDate.setDate(appointmentDate.getDate() + nextAppointmentDate);
//$('#Appointment').datepicker("setDate", appointmentDate);

var currentValue = "";
function setMedicineUnit(ev) {
    var target = ev.target;
    var targetValue = target.options[target.selectedIndex].text;
    ;
    for (var i = 0; listMedicine.length; i++) {
        var item = listMedicine[i];
        var a = JSON.stringify(item);
        var b = JSON.parse(a);
        if (targetValue == b.name) {
            currentValue = currentValue + targetValue;
            var tdTag = ev.target.parentElement;
            var unitTag = tdTag.nextElementSibling.nextElementSibling.nextElementSibling;
            var unitInput = unitTag.children[0];
            unitInput.value = b.unit;
        }
    }
}

function addValidate(element) {
    validator.validate();
    $(element).rules("add", "required");
}

function showWatting() {
    // Show pop-up
    $('#waitting').modal('show');
}

function hideWatting() {
    // Hide pop-up
    $('#waitting').modal('hide');
}

(function () {
    // your page initialization code here
    // the DOM will be available here
    for (var i = 0; i < 1000; i++) {
        var foodElement = document.getElementById("fPresModels" + i + ".f");
        if (foodElement == null) return;
        findUnits(foodElement);
    }
})();
(function(){
    var a = document.getElementsByClassName("btn btn-link");
    var b = a[0];
    if(b.textContent == "View Detail") {
    } else {
        document.getElementById("patientHasToMakeFI").style.display = "";
        document.getElementsByClassName("btn btn-primary pull-right")[4].style.display = "none";
    }
})();

function showModalAndDraw () {
    // Get practice Data
    $.ajax({
        method: "POST",
        url: "/practiceDataByAppointment",
        data: {
            appointmentId: $("#appointmentId").val()
        }
    })
        .done(function (data) {
            // Show pop-up
            $('#exampleModal').modal('show');
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
        })
};

(function(){
    var a = document.getElementById("appointmentId").value;
    var b = document.getElementById("finishMedicalRecord");
    b.href = "/finishMedicalRecord?appointmentId=" + a;
})();