/**
 * Created by Aking on 9/28/2015.
 */
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
for(var i=0; i<= $('#medics').val(); i++){
    loadSelect('#mPresModelsM'+i+'');
}


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
                if (this.value && ( !request.term || matcher.test(text) ))
                    return {
                        label: text,
                        value: text,
                        url: url,
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
    var r = confirm('Are you sure to make this prescription?');
    if (r == true) {
        // Hidden field for diagnostic
        //Get
        var bla = $('#select2Box').val().trim();
        console.log(bla);
        if (bla != null) {
            //Set
            $('#diagnostic').val(bla);
            console.log($('#diagnostic').val());
        }
        return true;
    } else {
        return false;
    }
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

