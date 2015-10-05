/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/5/2015.
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