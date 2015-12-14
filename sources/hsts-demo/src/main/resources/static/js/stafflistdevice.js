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
    document.getElementById("update").style.display = 'none';
    document.getElementById("createNewMeasurement").style.display = 'none';
    var item = document.getElementById("autoCompleteItem");
    $("#table-body").html("");
    item.style.visibility = "hidden";
    item.innerHTML = "";
    if (val != "") {

        if (listdevice.length > 0) {
            for (var i = 0; i < listdevice.length; i++) {
                if (listdevice[i].toLowerCase().indexOf(val.toLowerCase()) > -1) {
                    index = i;
                    item.style.visibility = "visible";
                    var btn = document.createElement("p");
                    var t = document.createTextNode(listdevice[i]);
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
                document.getElementById("update").style.display = '';
                document.getElementById("createNewMeasurement").style.display = 'none';
            }
            else {
                document.getElementById("create").style.display = '';
                document.getElementById("update").style.display = 'none';
            }
        }
    }
}

function fillInput(c) {
    index = c;
    document.getElementById("autoCompleteItem").style.visibility = "hidden";
    document.getElementById("create").style.display = 'none';
    document.getElementById("update").style.display = '';
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

function viewMeasurement(id) {
    document.getElementById("superParent").style.display = "";
    document.getElementById("child").style.display = "";
    if(id == 'createMeasurement'){
        document.getElementById("createMeasurement").style.display = "";
        document.getElementById("updateWristband").style.display = "none";
        document.getElementById("createWristband").style.display = "none";
        document.getElementById("confirmWristband").style.display = "none";
        document.getElementById("mName").value = "";
        document.getElementById("mType").value = "";
        document.getElementById("mPosition").value = "";
        document.getElementById("mUUID").value = "";
    }
    if(id == 'updateWristband'){
        document.getElementById("createMeasurement").style.display = "none";
        document.getElementById("updateWristband").style.display = "";
        document.getElementById("createWristband").style.display = "none";
        document.getElementById("confirmWristband").style.display = "none";
        document.getElementById("wName").value = document.getElementById("device").value;
        $.ajax({
            method: "POST",
            url: "/getDevice",
            data: {"brandName": listint[index]}
        }).done(function (data) {
            document.getElementById("wUUID").value = data.brandUuid;
        });

    }
    if(id == 'createWristband'){
        document.getElementById("createMeasurement").style.display = "none";
        document.getElementById("updateWristband").style.display = "none";
        document.getElementById("createWristband").style.display = "";
        document.getElementById("confirmWristband").style.display = "none";
        document.getElementById("wName2").value = document.getElementById("device").value;
    }
    if(id == 'confirmWristband'){
        var wName2 = document.getElementById("wName2");
        var wUUID2 = document.getElementById("wUUID2");
        checkValidateMeasurement(wName2,'errorwName2');
        checkValidateMeasurement(wUUID2,'errorwUUID2');
        if(wName2.value == ""){
           return;
        }
        if(wUUID2.value == ""){
           return;
        }
        document.getElementById("createMeasurement").style.display = "none";
        document.getElementById("updateWristband").style.display = "none";
        document.getElementById("createWristband").style.display = "none";
        document.getElementById("confirmWristband").style.display = "";
    }

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

function createWristband(){
    var wName2 = document.getElementById("wName2");
    var wUUID2 = document.getElementById("wUUID2");
    var mName2 = document.getElementById("mName2");
    var mUUID2 = document.getElementById("mUUID2");
    var mPosition2 = document.getElementById("mPosition2");
    var mType2 = document.getElementById("mType2");
    checkValidateMeasurement(mName2,'errorName2');
    checkValidateMeasurement(mUUID2,'errorUUID2');
    checkValidateMeasurement(mPosition2,'errorPosition2');
    checkValidateMeasurement(mType2,'errorType2');
    if(mName2.value == ""){
        return;
    }
    if(mUUID2.value == ""){
        return;
    }
    if(mPosition2.value == ""){
        return;
    }
    if(mType2.value == ""){
        return;
    }
    var bytes = [];
    for (var i = 0; i < wName2.value.length; ++i) {
        bytes.push(wName2.value.charCodeAt(i));
    }
    $.ajax({
        method: "POST",
        url: "/createWristband",
        data: {"brandName": bytes,
            "UUID": wUUID2.value,
            "measureName" : mName2.value,
            "measureType" : mType2.value,
            "measurePosition" : mPosition2.value,
            "measureUUID" : mUUID2.value
        }
    }).done(function (data) {
        if(data == "200"){
            alert("New Wristband has been created !")
            location.reload();
        }
        alert("Create Wristband failed!");
    });
}

function updateWristband(){
    var wName = document.getElementById("wName");
    var wUUID = document.getElementById("wUUID");
    checkValidateMeasurement(wName,'errorwName');
    checkValidateMeasurement(wUUID,'errorwUUID');
    if(wName.value == ""){
        return;
    }
    if(wUUID.value == ""){
        return;
    }
    var bytes = [];
    for (var i = 0; i < wName.value.length; ++i) {
        bytes.push(wName.value.charCodeAt(i));
    }
    //
    $.ajax({
        method: "POST",
        url: "/updateWristband",
        data: {"brandName": listint[index],
            "newName": bytes,
            "UUID": wUUID.value
        }
    }).done(function (data) {
        if(data == "200"){
            alert("New Wristband has been created !")
            location.reload();
        }
        alert("Create Wristband failed!");
    });
}