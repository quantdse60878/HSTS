/**
 * Created by Man Huynh Khuong on 10/29/2015.
 */
var listdevice;
$("#devices").select2({
    width: "200px",
    ajax: {
        url: "/deviceName/list",
        dataType: 'json',
        delay: 250,
        data: function (params) {
            return {
                searchString: params.term,
                page: params.page,
            };
        },
        processResults: function (data, page) {
            listdevice = data;
            var names = data.map(function (name) {
                return {
                    id: name,
                    text: name
                }
            });
            return {results: names, more: false
            };
        },
        cache: false
    },
    escapeMarkup: function (markup) { return markup; },
    tags: true,
    createTag: function (tag) {
        return {
            id: tag.term,
            text: tag.term,
            tag: true
        };
    }
});

function GG() {
    var x = document.getElementById("devices").value;
    if($.inArray(x, listdevice) > -1){
        document.getElementById("create").style.display = 'none';
        $.ajax({
            method: "GET",
            url: "/deviceName/listdevice",
            data: {"brandName" : x}
        }).done(function (data) {
            console.log(data);
            $("#table-body").html("");
            var bodyContent = "";
            if (data != null) {

                $.each(data, function (key, element) {
                    bodyContent += '<tr>' +
                        '<td>' + (key + 1) + '</td>' +
                        '<td>' + element.measurementName + '</td>' +
                        '<td>' + element.positionHaveValue + '</td>' +
                        '<td>' + element.uuid + '</td>' +
                        '<td>' + element.measurementType + '</td>' +'</tr>';
                });
            }
            $("#table-body").html(bodyContent);
        });
    } else {
        document.getElementById("create").style.display = 'inline-table';
    }
}

function WP(){
    var uuid = prompt("Please Enter UUID Of Wristband : ", "UUID");
    if(uuid != null){
        document.getElementById("uuid").setAttribute("value", uuid);
        return true;
    }
    return false;
}