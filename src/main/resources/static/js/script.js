$( document ).ready(function() {
    console.log( "ready!" );

    $('#refreshRequests').click(function() {
        refreshRequests();
    });

    $("#deleteRequests").click(function() {
        console.log("Delete requests");
        deleteRequests();
    });

    $("#testGet").click(function() {
        console.log("Test GET");
        testGet();
    });

    $("#testPost").click(function() {
        console.log("Test POST");
        testPost();
    });
});

function refreshRequests() {
    $.getJSON( "/api/v1/requests", function( data ) {
        console.log("Get requests");

        console.log(data);

        $("#eventList").empty();
        $.each( data, function( key, val ) {
            $("#eventList").append( "<li id='" + key + "'>" + val.url + "</li>" );
        });
    });
}

function deleteRequests() {
    $.ajax({
        url: '/api/v1/requests',
        type: 'DELETE',
        success: function(result) {
            console.log("delete done");
            refreshRequests();
        }
    });
}

function testGet() {
    $.ajax({
        url: '/catch/lala/test-get',
        type: 'GET',
        success: function(result) {
            console.log("test GET done");
            refreshRequests();
        }
    });
}

function testPost() {
    $.ajax({
        url: '/catch/lala/test-post',
        type: 'GET',
        success: function(result) {
            console.log("test POST done");
            refreshRequests();
        }
    });
}