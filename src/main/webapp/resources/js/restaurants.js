const restAjaxUrl = "admin/restaurants/";
let form;

const restCtx = {
    ajaxUrl: restAjaxUrl,
    updateTable: function () {
        $.get(this.ajaxUrl, updateTableByDataRest);
    }
}

function makeEditableRest(datatableApi) {
    restCtx.datatableApi = datatableApi;
    form = $('#detailsForm');

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}

function updateTableByDataRest(data) {
    restCtx.datatableApi.clear().rows.add(data).draw();
}

function add() {
    $("#modalTitle").html(i18n["addTitle"]);
    form.find(":input").val("");
    $("#editRow").modal();
}

function save() {
    $.ajax({
        type: "POST",
        url: restCtx.ajaxUrl,
        data: form.serialize()
    }).done(function () {
        $("#editRow").modal("hide");
        restCtx.updateTable();
        successNoty("common.saved", "");
    });
}

function updateRow(id) {
    form.find(":input").val("");
    $("#modalTitle").html(i18n["editTitle"]);
    $.get(restCtx.ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#editRow').modal();
    });
}

function enable(chkbox, id) {
    const enabled = chkbox.is(":checked");
//  https://stackoverflow.com/a/22213543/548473
    $.ajax({
        url: restAjaxUrl + id,
        type: "POST",
        data: "enabled=" + enabled
    }).done(function () {
        chkbox.closest("tr").attr("data-restaurantEnabled", enabled);
        successNoty(enabled ? "common.enabled" : "common.disabled", "");
    }).fail(function () {
        $(chkbox).prop("checked", !enabled);
    });
}

$(function () {
    makeEditableRest(
        $("#restTable").DataTable({
            "ajax": {
                "url": restAjaxUrl,
                "dataSrc": ""
            },
            "paging": false,
            "info": false,
            "filter": false,
            "columns": [
                {
                    "data": "name"
                },
                {
                    "data": "registered",
                    "render": function (date, type, row) {
                        if (type === "display") {
                            return date.substring(0, 10);
                        }
                        return date;
                    }
                },
                {
                    "data": "enabled",
                    "orderable": false,
                    "render": function (data, type, row) {
                        if (type === "display") {
                            return "<input type='checkbox' " + (data ? "checked" : "") + " onclick='enable($(this)," + row.id + ");'/>";
                        }
                        return data;
                    }
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderEditBtn
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderSelectBtn
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ],
            "createdRow": function (row, data, dataIndex) {
                if (!data.enabled) {
                    $(row).attr("data-restaurantEnabled", false);
                }
            }
        })
    );
});