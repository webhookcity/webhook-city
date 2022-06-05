$(document).ready(function () {
    console.log("ready!");
    refreshRequests();

    $('#refreshRequests').click(function () {
        refreshRequests();
    });

    $("#deleteRequests").click(function () {
        console.log("Delete requests");
        deleteRequests();
    });

    $("#testGet").click(function () {
        console.log("Test GET");
        testGet();
    });

    $("#testPost").click(function () {
        console.log("Test POST");
        testPost();
    });
});

function refreshRequests() {
    $.getJSON("/api/v1/requests", function (data) {
        console.log("Get requests");

        console.log(data);

        $("#accordion").empty();

        $.each(data, function (key, val) {

            // Accordion
            let accordionItem = `<div class="accordion-item">
                <h2 class="accordion-header" id="heading-$id">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapse-$id">
                        $itemHeader
                    </button>
                </h2>
                <div id="collapse-$id" class="accordion-collapse collapse">
                    <div class="accordion-body">
                        <b>Headers:</b>
                        
                        <ol>
                            $itemBody
                        </ol>
                        
                        <b>Body:</b>
                         <pre><code>$requestBody</code></pre>
                    </div>
                </div>
            </div>`;


            const header = val.id + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + val.method + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + val.createdOn + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + val.url;

            let body = "";
            $.each(val.headers, function (key, val) {
                body += "<li><b>" + key + "</b> : " + val + "</li>"
            });

            let parsed = JSON.parse(val.body);
            let requestBody = JSON.stringify(parsed, null, 2);

            accordionItem = accordionItem.replace("$itemHeader", header);
            accordionItem = accordionItem.replaceAll("$id", val.id)
            accordionItem = accordionItem.replace("$itemBody", body);
            accordionItem = accordionItem.replace("$requestBody", requestBody);
            $("#accordion").append(accordionItem);
        });

        hljs.highlightAll();
    });


}

function deleteRequests() {
    $.ajax({
        url: '/api/v1/requests',
        type: 'DELETE',
        success: function (result) {
            console.log("delete done");
            refreshRequests();
        }
    });
}

function testGet() {
    $.ajax({
        url: '/catch/ui-test/get',
        type: 'GET',
        success: function (result) {
            console.log("test GET done");
            refreshRequests();
        }
    });
}

function testPost() {
    $.ajax({
        url: '/catch/ui-test/post',
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: '{"body": "test"}',
        success: function (result) {
            console.log("test POST done");
            refreshRequests();
        }
    });
}