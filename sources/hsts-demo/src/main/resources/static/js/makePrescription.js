/**
 * Created by Aking on 9/28/2015.
 */

// Validator
var validator = $("#mainForm").validate({
    ignore: [],
    debug: true,
    rules: {
    },
    messages: {
    },
    errorPlacement: function(error, element){
            error.appendTo( element.next() );
    },
    submitHandler: function(form) {
        form.submit();
    },
    invalidHandler: function(e, validator) {
        if (validator.errorList.length > 0) {
            console.log("Change to first tab has error");
            console.log(validator.errorList);
            var targetTab = jQuery(validator.errorList[0].element).closest(".tab-pane").attr('id');
            changeTab('#' + targetTab, '#li_' + targetTab);
            console.log("end change");
        }
    }
});

function changeTabb (a, li) {
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
        changeTabb(targetTab, targetLi);
    }

};

function validateAndOpenModal(m) {
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
        $(m).modal('show')
    }

};
function viewAutoCompleteP(input){
    //console.log(input);
    var id = input.getAttribute('id');
    var item = id + "item";
    viewAutoComplete(id,item);
}
function viewAutoComplete(id,item){
    var val = document.getElementById(id).value;
    var it = document.getElementById(item);
    if(val!=""){
        it.style.visibility = "visible";
        it.style.height = "auto";
        it.innerHTML = "";
        for(var i=0; i< listPracticeName.length; i++){
            if(listPracticeName[i].toLowerCase().indexOf(val.toLowerCase()) > -1){
                var btn = document.createElement("p");
                var t = document.createTextNode(listPracticeName[i]);
                var att = document.createAttribute("onclick");
                att.value = "fillInput('"+id+"','"+item+"','" + listPracticeName[i] + "')";
                btn.appendChild(t);
                btn.setAttribute("class","form-control");
                btn.setAttribute("style","margin: 0px;border: 0px;box-shadow: none;");
                btn.setAttributeNode(att);
                it.appendChild(btn);
            }
        }
    } else {
        it.style.visibility = "hidden";
        it.style.height = "0px";
    }

}
function fillInput(id,item,c){
    var it = document.getElementById(item);
    it.style.visibility = "hidden";
    it.style.height = "0px";
    document.getElementById(id).value = c;
}

function findUnits(food) {
    var id = food.getAttribute('id');
    var preID = id.split('.')[0];
    var unitIdN = preID + 'fUnit';
    var unitID = "#"+preID + 'fUnit';
    var foodId = food.value;
    $.ajax({
        dataType: "json",
        url: 'getFoodUnits',
        data: {
            'foodId': foodId
        },
        success: function (json) {
            //console.log("success");
            var options = '<option value="0">Select</option>';
            document.getElementById(unitIdN).innerHTML = "";
            for (var i = 0; i < json.length; i++){
                var tmp = json[i];
                //console.log(tmp);
                options= options + '<option value="'+ tmp.foodUnitId +'">'+ tmp.unitName +'</option>';
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

function loadPopupAppointment(appointmentId) {
    showWatting();
    $.ajax({
        method: "GET",
        url: "/inforOfAppointmentDate",
        data: {
            appointmentId: appointmentId
        }
    })
        .done(function(data) {
            console.log(data);
            if(data != null) {
                // Set data to html

                hideWatting();
                // Show pop-up
                $('#hisAppointment').modal('show');
            }

        });
};

// Select2 for diagnostic
$("#select2Box").select2({
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
                    window.location = ui.item.url;
                    //loadPopupAppointment(ui.item.id);
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
$("#infordate").comboboxx();
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

var nextAppointmentDate = $('#nextApDate').val();
nextAppointmentDate++;
nextAppointmentDate--;
var appointmentDate = new Date();
appointmentDate.setDate(appointmentDate.getDate() + nextAppointmentDate);
$('#Appointment').datepicker("setDate", appointmentDate);

var currentValue = "";
function setMedicineUnit(ev) {
    var target = ev.target;
    var targetValue = target.options[target.selectedIndex].text;;
    for(var i =  0; listMedicine.length; i++) {
        var item = listMedicine[i];
        var a = JSON.stringify(item);
        var b = JSON.parse(a);
        if(targetValue == b.name) {
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

function showWatting(){
    // Show pop-up
    $('#waitting').modal('show');
}

function hideWatting(){
    // Hide pop-up
    $('#waitting').modal('hide');
}