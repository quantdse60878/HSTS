/**
 * Created by QUYHKSE61160 on 10/25/15.
 */

var listValueVariable = document.getElementsByClassName("valueOfVariable");
formulaRight = false;

for (var i = 0; i < listValueVariable.length; i++) {
    var item = listValueVariable.item(i);
    var selectValue = document.createElement("select");
    selectValue.className = "valueOfVariableItem";
    selectValue.setAttribute("name", "valueOfVariable");
    listData = listPrevention + listMedicalRecordData;
    listData = listData.split(",");
    for (var j = 0; j < listData.length; j++) {
        var option = document.createElement("option");
        option.value = listData[j];
        option.text = listData[j];
        selectValue.appendChild(option);
    }
    item.appendChild(selectValue);

    for(var j = 0; j < selectValue.length; j++) {
        if(selectValue.options[j].text == listVariable[i].value) {
            selectValue.selectedIndex = j;
        }
    }

}

function scanVariable() {

    var listFormula = document.getElementsByClassName("formulaInput");
    var listVariableFromFormula = new Array();
    for(var i = 0; i < listFormula.length; i++) {
        var formularInput = listFormula.item(i);
        var formular = formularInput.value;
        var checkExist = true;
        formular = formular.split(" ");
        for(var j = 0; j < formular.length; j++) {
            if(formular[j] <= "z" && formular[j] >= "a") {
                for(var k = 0; k < listVariableFromFormula.length; k++) {
                    if(formular[j] == listVariableFromFormula[k]) {
                        checkExist = false;
                    }
                }
                if(checkExist) {
                    listVariableFromFormula.push(formular[j]);
                    checkExist = true;
                }
            }
        }
    }

    var tableVariable = document.getElementById("tableVariable");
    var tableVariableLength = tableVariable.rows.length;
    for(var i = 0; i < tableVariableLength; i++) {
        tableVariable.deleteRow(0);
    }
    for(var i = 0; i < listVariableFromFormula.length; i++) {
        var rowInsert = tableVariable.insertRow();
//        var inputHidden =
        var cellInsert1 = rowInsert.insertCell();
        var cellInsert3 = rowInsert.insertCell();

        cellInsert1.innerText = listVariableFromFormula[i];
        cellInsert1.style.width = "100px";
        cellInsert1.className = "variable";
        var selectValue = document.createElement("select");
        selectValue.setAttribute("name", "valueOfVariable");
        selectValue.className = "valueOfVariableItem";
        listData = listPrevention + listMedicalRecordData;
        listData = listData.split(",");
        for (var j = 0; j < listData.length; j++) {
            var option = document.createElement("option");
            option.value = listData[j];
            option.text = listData[j];
            selectValue.appendChild(option);
        }
        cellInsert3.appendChild(selectValue);

    }

}

function setupNewFormula() {
    var newFormulaDistance = document.getElementById("formulaDistance").value;
    var newFormulaCalories = document.getElementById("formulaCalories").value;
    var listNewVariableElement = document.getElementsByClassName("variable");
    var listNewValueOfVariableElement = document.getElementsByClassName("valueOfVariableItem");
    var listNewVariable = new Array();
    var listNewValueOfVariable = new Array();
    for(var i = 0; i < listNewVariableElement.length; i++) {
        listNewVariable.push(listNewVariableElement.item(i).textContent);
    }
    for(var i = 0; i < listNewValueOfVariableElement.length; i++) {
        var a = listNewValueOfVariableElement.item(i);
        listNewValueOfVariable.push(a.options[a.selectedIndex].text);
    }
    var checkFormulaDistance = newFormulaDistance;
    var checkFormulaCalories = newFormulaCalories;
    for(var i = 0; i < listNewVariable.length; i++) {
        checkFormulaDistance = checkFormulaDistance.replace(listNewVariable[i], "1000");
    }
    for(var i = 0; i < listNewVariable.length; i++) {
        checkFormulaCalories = checkFormulaCalories.replace(listNewVariable[i], "1000");
    }
    var a = eval(checkFormulaCalories);
    var b = eval(checkFormulaDistance);
    if(a == 0 || b == 0 || a == "Infinity" || b == "Infinity") {
        formulaRight = false;
        document.getElementById("viewButton").className = "hideButton";
        confirm("Please input right formula. Thanks!");
    } else {
        var newVariable = document.getElementById("newVariable");
        var newValue = document.getElementById("newValue");
        var newDistance = document.getElementById("newDistance");
        var newCalories = document.getElementById("newCalories");
        newDistance.value = newFormulaDistance;
        newCalories.value = newFormulaCalories;
        newVariable.value = listNewVariable;
        newValue.value = listNewValueOfVariable;
        formulaRight = true;
        document.getElementById("viewButton").className = "viewButton";
    }
}

function hideButton() {
    document.getElementById("viewButton").className = "hideButton";
}