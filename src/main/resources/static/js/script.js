$( document ).ready(function() {
    console.log( "ready!" );

    $.getJSON( "/api/v1/requests", function( data ) {
       console.log("getJSON");

       console.log(data);

        $.each( data, function( key, val ) {
            $("#eventList").append( "<li id='" + key + "'>" + val.url + "</li>" );
        });
    });


    $("#deleteEvents").click(function() {
        console.log("Delete events.");

        $.ajax({
            url: '/api/v1/requests',
            type: 'DELETE',
            success: function(result) {
                console.log("delete done");
            }
        });
    });

    $('#refreshPage').click(function() {
        location.reload();
    });

});