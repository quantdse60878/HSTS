//$("#devices").select2({
//    width: "200px",
//    ajax: {
//        url: "/deviceName/list",
//        dataType: 'json',
//        delay: 250,
//        data: function (params) {
//            return {
//                searchString: params.term,
//                page: params.page,
//            };
//        },
//        processResults: function (data, page) {
//            listdevice = data;
//            var names = data.map(function (name) {
//                return {
//                    id: name,
//                    text: name
//                }
//            });
//            return {results: names, more: false
//            };
//        },
//        cache: false
//    },
//    escapeMarkup: function (markup) { return markup; },
//    tags: true,
//    createTag: function (tag) {
//        return {
//            id: tag.term,
//            text: tag.term,
//            tag: true
//        };
//    }
//});
/**
 * Created by Man Huynh Khuong on 10/29/2015.
 */
var listdevice;
window.addEventListener('mouseup',function(event){
    var item = document.getElementById("autoCompleteItem");
    if(event.target != item){
        item.style.visibility = "hidden";
    }
});
$(document).ready(function(){
    $.ajax({
        method: "GET",
        url: "/deviceName/listnamedevice",
        data: {}
    }).done(function (data) {
        listdevice = data;
    });
});

function checkExisted(){
    var val = document.getElementById("device").value;
    document.getElementById("create").style.display = 'none';
    var item = document.getElementById("autoCompleteItem");
    item.style.visibility = "hidden";
    item.innerHTML = "";
    if(val!=""){
        if(listdevice.length > 0){
            for(var i=0; i< listdevice.length; i++){
                if(listdevice[i].toLowerCase().indexOf(val.toLowerCase()) > -1){
                    item.style.visibility = "visible";
                    btn = document.createElement("p");
                    t = document.createTextNode(listdevice[i]);
                    var att = document.createAttribute("onclick");
                    att.value = "fillInput('" + listdevice[i] + "')";
                    btn.appendChild(t);
                    btn.setAttributeNode(att);
                    item.appendChild(btn);
                }
            }
            var found = $.inArray(val, listdevice) > -1;
            if(found) document.getElementById("create").style.display = 'none';
            else document.getElementById("create").style.display = 'inline-table';
        }
    }
}

function fillInput(c){
    document.getElementById("autoCompleteItem").style.visibility = "hidden";
    document.getElementById("create").style.display = 'none';
    document.getElementById("device").value = c;
    $.ajax({
        method: "GET",
        url: "/deviceName/listdevice",
        data: {"brandName" : c}
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
}

function WP(){
    var val = document.getElementById("device").value;
    var found = $.inArray(val, listdevice) > -1;
    if(found || val == "") return false;
    else {
        var uuid = prompt("Please Enter UUID Of Wristband : ", "UUID");
        if(uuid != null){
            document.getElementById("uuid").setAttribute("value", uuid);
            return true;
        }
        return false;

    }

}