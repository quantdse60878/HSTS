/*! Health Support Tracking System hsts.js
 * ================
 *
 * @Author  A.King
 * @version 1.0.0
 */

/* register patient
 */
(function( $ ) {
    $.widget( "custom.combobox", {
        _create: function() {
            this.wrapper = $( "<span>" )
                .addClass( "custom-combobox" )
                .insertAfter( this.element );

            this.element.hide();
            this._createAutocomplete();
            this._createShowAllButton();
        },

        _createAutocomplete: function() {
            var selected = this.element.children( ":selected" ),
                value = selected.val() ? selected.text() : "";

            this.input = $( "<input>" )
                .appendTo( this.wrapper )
                .val( value )
                .attr( "title", "" )
                .addClass( "custom-combobox-input ui-widget ui-widget-content ui-corner-left" )
                .autocomplete({
                    delay: 0,
                    minLength: 0,
                    source: $.proxy( this, "_source" )
                })
                .tooltip({
                    tooltipClass: "ui-state-highlight"
                });

            this._on( this.input, {
                autocompleteselect: function( event, ui ) {
                    ui.item.option.selected = true;
                    this._trigger( "select", event, {
                        item: ui.item.option
                    });
                },

                autocompletechange: "_removeIfInvalid"
            });
        },

        _createShowAllButton: function() {
            var input = this.input,
                wasOpen = false;

            $( "<a>" )
                .attr( "tabIndex", -1 )
                .attr( "title", "Show All Items" )
                .tooltip()
                .appendTo( this.wrapper )
                .button({
                    icons: {
                        primary: "ui-icon-triangle-1-s"
                    },
                    text: false
                })
                .removeClass( "ui-corner-all" )
                .addClass( "custom-combobox-toggle ui-corner-right btn btn-default" )
                .mousedown(function() {
                    wasOpen = input.autocomplete( "widget" ).is( ":visible" );
                })
                .click(function() {
                    input.focus();

                    // Close if already visible
                    if ( wasOpen ) {
                        return;
                    }

                    // Pass empty string as value to search for, displaying all results
                    input.autocomplete( "search", "" );
                });
        },

        _source: function( request, response ) {
            var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
            response( this.element.children( "option" ).map(function() {
                var text = $( this ).text();
                if ( this.value && ( !request.term || matcher.test(text) ) )
                    return {
                        label: text,
                        value: text,
                        option: this
                    };
            }) );
        },

        _removeIfInvalid: function( event, ui ) {

            // Selected an item, nothing to do
            if ( ui.item ) {
                return;
            }

            // Search for a match (case-insensitive)
            var value = this.input.val(),
                valueLowerCase = value.toLowerCase(),
                valid = false;
            this.element.children( "option" ).each(function() {
                if ( $( this ).text().toLowerCase() === valueLowerCase ) {
                    this.selected = valid = true;
                    return false;
                }
            });

            // Found a match, nothing to do
            if ( valid ) {
                return;
            }

            // Remove invalid value
            this.input
                .val( "" )
                .attr( "title", value + " didn't match any item" )
                .tooltip( "open" );
            this.element.val( "" );
            this._delay(function() {
                this.input.tooltip( "close" ).attr( "title", "" );
            }, 2500 );
            this.input.autocomplete( "instance" ).term = "";
        },

        _destroy: function() {
            this.wrapper.remove();
            this.element.show();
        }
    });
})( jQuery );
/* make prescription patient
 */
var countMedicine = 2;
var countFoods = 5;
var countPractice = 3;

function addRowMedicine(tableID) {
    var tableElement = document.getElementById(tableID);
    var newRow = tableElement.insertRow(tableElement.rows.length);
    var newCell;

    newCell = newRow.insertCell(newRow.cells.length);
    newCell.innerHTML = countMedicine + "/";
    newCell = newRow.insertCell(newRow.cells.length);
    newCell.innerHTML = '<select class="form-control" th:field="*{medical}">'
        + '<option>Thuoc 1</option>'
        + '<option>Thuoc 2</option>'
        + '<option>Thuoc 3</option>'
        + '<option>Thuoc 4</option>'
        + '</select>';
    newCell = newRow.insertCell(newRow.cells.length);
    newCell.innerHTML =
        '<input type="text" class="form-control" th:field="*{medicalNoTPD}" style="width: 100px;" placeholder="Number of Time"/>';
    newCell = newRow.insertCell(newRow.cells.length);
    newCell.innerHTML =
        '<input type="text" class="form-control" th:field="*{medicalNoQPD}" style="width: 100px;" placeholder="Number of Quality"/>';

    countMedicine++;
    return newRow;
};
function addRowFoods(tableID) {
    var tableElement = document.getElementById(tableID);
    var newRow = tableElement.insertRow(tableElement.rows.length);
    var newCell;

    newCell = newRow.insertCell(newRow.cells.length);
    newCell.innerHTML = countFoods + "/";
    newCell = newRow.insertCell(newRow.cells.length);
    newCell.innerHTML = '<select class="form-control" th:field="*{food}">'
        + '<option>Rau</option>'
        + '<option>Cơm</option>'
        + '<option>Trái cây</option>'
        + '<option>Bia Rượu</option>'
        + '</select>';
    newCell = newRow.insertCell(newRow.cells.length);
    newCell.innerHTML =
        '<input type="text" class="form-control" th:field="*{foodTime}" style="width: 100px;" />';

    countFoods++;
    return newRow;
};
function addRowPractice(tableID) {
    var tableElement = document.getElementById(tableID);
    var newRow = tableElement.insertRow(tableElement.rows.length);
    var newCell;

    newCell = newRow.insertCell(newRow.cells.length);
    newCell.innerHTML = countPractice + "/";
    newCell = newRow.insertCell(newRow.cells.length);
    newCell.innerHTML ='<select class="form-control" th:field="*{pratice}">'
        + '<option>chạy bộ</option>'
        + '<option>Đi bộ</option>'
        + '<option>Chơi bóng bàn</option>'
        + '</select>';
    newCell = newRow.insertCell(newRow.cells.length);
    newCell.innerHTML =
        '<input type="text" class="form-control" th:field="*{praticeIntensity}" style="width: 100px;"/>';

    countPractice++;
    return newRow;
};