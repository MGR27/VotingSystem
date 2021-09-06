function makeEditableVote(datatableApi) {
    ctx2.datatableApi = datatableApi;

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});
}

const voteAjaxUrl = "profile/votes/";

const ctx2 = {
    ajaxUrl: voteAjaxUrl,
    updateTable: function () {
        $.get(voteAjaxUrl + "today", updateTableByDataVote);
    }
}

function updateTableByDataVote(data) {
    ctx2.datatableApi.clear().rows.add(data).draw();
}

function vote() {
    $.ajax({
        type: "POST",
        url: voteAjaxUrl + restId
    }).done(function () {
        successNoty("voteSuccess", restaurantName);
    });
}

function getVotes() {
    $("#modalTitle").html(i18n["voteTitle"]);
    ctx2.updateTable();
    $("#todayVotes").modal();
}

$(function () {
    makeEditableVote(
        $("#voteTable").DataTable({
            "ajax": {
                "url": voteAjaxUrl + "today",
                "dataSrc": ""
            },
            "paging": false,
            "info": false,
            "filter": false,
            "columns": [
                {
                    "data": "user.name"
                },
                {
                    "data": "user.email"
                },
                {
                    "data": "restaurant.name"
                },
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
