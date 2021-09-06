const adminDishAjaxUrl = "admin/dishes/";
let dishForm;
let restaurantId;
const dishCtx = {
    ajaxUrl: adminDishAjaxUrl,
    updateTable: function () {
        $.get(this.ajaxUrl + "lastMenu/" + restaurantId, updateTableByDataDish);
    }
}

function makeEditableAdminDish(datatableApi) {
    dishCtx.datatableApi = datatableApi;
    dishForm = $('#dishDetailsForm');

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});
}

function updateTableByDataDish(data) {
    dishCtx.datatableApi.clear().rows.add(data).draw();
}

function showLastRestaurantMenu(id, restName) {
    restaurantId = id;
    $.ajax({
        type: "GET",
        url: adminDishAjaxUrl + "lastMenu/" + id
    }).done(function (data) {
        $("#dishRestTitle").html(i18n["restMenu"] + " " + data[0].restaurant.name);
        updateTableByDataDish(data);
    });
}

function dishAdd() {
    $("#dishModalTitle").html(i18n["dishAddTitle"]);
    dishForm.find(":input").val("");
    $("#dishEditRow").modal();
}

function dishSave() {
    $.ajax({
        type: "POST",
        url: dishCtx.ajaxUrl + restaurantId,
        data: dishForm.serialize()
    }).done(function () {
        $("#dishEditRow").modal("hide");
        dishCtx.updateTable();
        successNoty("common.saved", "");
    });
}

function renderEditBtnDish(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRowDish(" + row.id + ")'><span class='fa fa-pencil'></span></a>";
    }
}

function updateRowDish(id) {
    dishForm.find(":input").val("");
    $("#dishModalTitle").html(i18n["dishEditTitle"]);
    $.get(dishCtx.ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            dishForm.find("input[name='" + key + "']").val(value);
        });
        $('#dishEditRow').modal();
    });
}

function deleteRow(id) {
    if (confirm(i18n['common.confirm'])) {
        $.ajax({
            url: dishCtx.ajaxUrl + id,
            type: "DELETE"
        }).done(function () {
            dishCtx.updateTable();
            successNoty("common.deleted", "");
        });
    }
}

$(function () {
    makeEditableAdminDish(
        $("#menuTable").DataTable({
            "ajax": {
                "url": adminDishAjaxUrl + "emptyList",
                "dataSrc": ""
            },
            "fnDrawCallback": function () {
                if ($(this).find('.dataTables_empty').length === 1) {
                    $('#dishHead').hide();
                    $('#dishAdd').hide();
                    $('.dataTables_empty').
                    html("<span class='label label-danger'><h5 align='center'>Please, choose restaurant</h5></span>");
                } else {
                    $('#dishHead').show();
                    $('#dishAdd').show();
                }
            },
            "paging": false,
            "info": false,
            "filter": false,
            "columns": [
                {
                    "data": "name"
                },
                {
                    "data": "price"
                },
                {
                    "data": "date",
                    "orderable": false,
                    "render": function (date, type, row) {
                        if (type === "display") {
                            return date.substring(0, 10);
                        }
                        return date;
                    }
                },
                {
                    "render": renderEditBtnDish,
                    "defaultContent": "",
                    "orderable": false
                },
                {
                    "render": renderDeleteBtn,
                    "defaultContent": "",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        })
    );
});
