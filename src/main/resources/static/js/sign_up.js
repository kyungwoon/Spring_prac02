$(document).ready(function() {
    //isRegister();
    isLogined();
});

function isLogined() {
    let message = $("#isLoginedMessage").val();
    console.log(message);
    if (message != "") {
        alert(message);
        document.location.href = "/";
        return;
    }
}