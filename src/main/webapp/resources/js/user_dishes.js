function makeEditableDish(datatableApi) {
    ctx1.datatableApi = datatableApi;

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}

const dishAjaxUrl = "profile/dishes/menu/";
let restId;
let restaurantName;
// https://stackoverflow.com/a/5064235/548473
const ctx1 = {
    ajaxUrl: dishAjaxUrl
}

function updateTableByDataDish(data) {
    ctx1.datatableApi.clear().rows.add(data).draw();
}

function showMenu(id, restName) {
    $("#restaurantName").html(i18n["restMenu"] + " " + restName);
    restId = id;
    restaurantName = restName;

    $.ajax({
        type: "GET",
        url: dishAjaxUrl + id
    }).done(function (data) {
        updateTableByDataDish(data);
    });
}

$(function () {
    makeEditableDish(
        $("#menuTable").DataTable({
            "ajax": {
                "url": dishAjaxUrl + "emptyList",
                "dataSrc": ""
            },
            "fnDrawCallback": function () {
                if ($(this).find('.dataTables_empty').length === 1) {
                    $('th').hide();
                    $('#votingBtn').hide();
                    $('#showVotes').hide();
                    $('.dataTables_empty').
                        html("<span class='label label-danger'><h5 align='center'>Please, choose restaurant</h5></span>");
                } else {
                    $('th').show();
                    $('#votingBtn').show();
                    $('#showVotes').show();
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
