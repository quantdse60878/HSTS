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
var listdevice = [];
var listint = [];
var index = 0;
window.addEventListener('mouseup', function (event) {
    var item = document.getElementById("autoCompleteItem");
    if (event.target != item) {
        item.style.visibility = "hidden";
    }
});
$(document).ready(function () {
    $('html').bind('keypress', function(e)
    {
        if(e.keyCode == 13)
        {
            return false;
        }
    });
    $.ajax({
        method: "GET",
        url: "/deviceName/listnamedevice",
        data: {}
    }).done(function (data) {

        for (var i = 0; i < data.length; i++) {
            var bytes = [];
            var str = data[i].split(",");
            for (var j = 0; j < str.length; j++) {
                bytes.push(parseInt(str[j]));
            }
            listdevice.push(String.fromCharCode.apply(null, bytes));
            listint.push(data[i]);
        }
        ;
    });
});

function checkExisted() {
    var val = document.getElementById("device").value;
    document.getElementById("create").style.display = 'none';
    document.getElementById("createNewMeasurement").style.display = 'none';
    var item = document.getElementById("autoCompleteItem");
    item.style.visibility = "hidden";
    item.innerHTML = "";
    if (val != "") {

        if (listdevice.length > 0) {
            for (var i = 0; i < listdevice.length; i++) {
                if (listdevice[i].toLowerCase().indexOf(val.toLowerCase()) > -1) {
                    index = i;
                    item.style.visibility = "visible";
                    btn = document.createElement("p");
                    t = document.createTextNode(listdevice[i]);
                    var att = document.createAttribute("onclick");
                    att.value = "fillInput('" + i + "')";
                    btn.appendChild(t);
                    btn.setAttributeNode(att);
                    item.appendChild(btn);
                }
            }
            var found = $.inArray(val, listdevice) > -1;
            if (found) {
                document.getElementById("create").style.display = 'none';
                document.getElementById("createNewMeasurement").style.display = 'none';
            }
            else {
                document.getElementById("create").style.display = 'inline-table';
            }
        }
    }
}

function fillInput(c) {
    index = c;
    document.getElementById("autoCompleteItem").style.visibility = "hidden";
    document.getElementById("create").style.display = 'none';
    document.getElementById("createNewMeasurement").style.display = 'inline-table';
    document.getElementById("device").value = listdevice[c];
    document.getElementById("brandName").value = listint[c];
    $.ajax({
        method: "GET",
        url: "/deviceName/listdevice",
        data: {"brandName": listint[c]}
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
                    '<td>' + element.measurementType + '</td>';
                if(data.length != 1){
                    bodyContent += '<td>' + '<button class="btn btn-danger" value="' + element.uuid +'" onclick="confirmDelete(this)">Delete</button>' + '</td>' ;
                } else {
                    bodyContent += '<td></td>';
                }
                bodyContent += '</tr>';
            });
        }
        $("#table-body").html(bodyContent);
    });
}

function confirmDelete(uuid){
    var r = confirm("Do you want to delete this measurement ?");
    if(r == true){
        var name = document.getElementById("brandName").value;
        var val = uuid.value;
        $.ajax({
            method: "POST",
            url: "/deleteMeasurement",
            data: {"measurementUUID": uuid.value,
                   "brandName" : name}
        }).done(function (data) {
            if(data == "200"){
                alert("Measurement is deleted successfully.");
                fillInput(index);
            }
            else {
                alert("Fail request !");
            }
        });
    }
}

function WP() {
    var val = document.getElementById("device").value;
    var uuid = prompt("Please enter UUID Of Wristband : (Exp: 0000180a) ", "UUID");
    if (uuid != null) {
        document.getElementById("brandUUID").setAttribute("value", uuid);
        var bytes = [];
        for (var i = 0; i < val.length; ++i) {
            bytes.push(val.charCodeAt(i));
        }
        document.getElementById("brandName").setAttribute("value", bytes);
        return true;
    }
    return false;
}

function viewMeasurement() {
    document.getElementById("superParent").style.display = "";
    document.getElementById("child").style.display = "";
    document.getElementById("mName").value = "";
    document.getElementById("mType").value = "";
    document.getElementById("mPosition").value = "";
    document.getElementById("mUUID").value = "";
}

function hideMesurement() {
    document.getElementById("superParent").style.display = "none";
    document.getElementById("child").style.display = "none";
}

function createMeasurement(){
    var mName = document.getElementById("mName").value;
    var mType = document.getElementById("mType").value;
    var mPosition = document.getElementById("mPosition").value;
    var mUUID = document.getElementById("mUUID").value;
    if(mName == ""){
        return;
    }
    if(mType == ""){
        return;
    }
    if(mPosition == ""){
        return;
    }
    if(mUUID == ""){
        return;
    }
    var brandName = document.getElementById("brandName").value;
    $.ajax({
        method: "GET",
        url: "/createNewMeasure",
        data: {"brandName": brandName,
               "measureName": mName,
               "measureType" : mType,
               "measurePosition" : mPosition,
               "measureUUID" : mUUID
        }
    }).done(function (data) {
        if(data == "200"){
            alert("New Measure has been created !")
            fillInput(index);
            hideMesurement();
            return;
        }
        alert("Create failed !")
    });
}

function checkValidateMeasurement (element, errorID){
    if(element.value == ""){
        document.getElementById(errorID).innerHTML = "Field cannot be empty!";
        document.getElementById(errorID).style.display = "";
    } else {
        document.getElementById(errorID).innerHTML = "";
        document.getElementById(errorID).style.display = "none";
    }
}