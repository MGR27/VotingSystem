function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRow(" + row.id + ")'><span class='fa fa-pencil'></span></a>";
    }
}

function renderSelectBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='showLastRestaurantMenu(" + row.id + ")'><span class='fa fa-book'></span></a>";
    }
}

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRow(" + row.id + ");'><span class='fa fa-remove'></span></a>";
    }
}

let failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(key, note) {
    new Noty({
        text: "<span class='fa fa-lg fa-check'></span> &nbsp;" + i18n[key] + "&nbsp;" + note,
        type: 'success',
        layout: "bottomRight",
        timeout: 2000
    }).show();
}

function failNoty(jqXHR) {
    closeNoty();
    var errorInfo = jqXHR.responseJSON;
    failedNote = new Noty({
        text: "<span class='fa fa-lg fa-exclamation-circle'></span> &nbsp;" + errorInfo.typeMessage + "<br>" + errorInfo.details.join("<br>"),
        type: "error",
        layout: "bottomRight"
    });
    failedNote.show();
}