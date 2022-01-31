$(document).ready(function() {
    isLogined();
    isErrorLogin();
});

function isErrorLogin() {
    let href = location.href;
    let queryString = href.substring(href.indexOf("?")+1);

    if(queryString === "error") {
        $("#errorMessage").text("닉네임 또는 비밀번호를 확인해주세요");
    }
    return;
}

//로그인 되어있는 사용자가 로그인 화면으로 이동 시 alert
function isLogined() {
    let message = $("#alreadyLoginedMessage").val();
    console.log(message);
    if(message != "") {
        alert(message);
        document.location.href="/";
        return;
    }
}