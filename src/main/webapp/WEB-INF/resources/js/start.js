$.get("/login/heartbeat", function(data) {
    if(data == "false" && document.URL.search("page=login") < 0) {
        window.location.href += "?page=login";
    }
});

function goToSignupForm() {
    $.get("/view/register", function(data) {
       $("body").html(data); 
    });
}
