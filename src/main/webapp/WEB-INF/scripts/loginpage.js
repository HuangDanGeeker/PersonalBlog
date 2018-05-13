window.onload = function () {

}

function login() {
    var userNo = $('#userNo').val();
    var userPasswd = $('#userPasswd').val();
    $.ajax({
        url:"http://localhost:8080/MyBlog/login/"+userNo+"/"+userPasswd,
        dataType:'jsonp',
        processData: true,
        typece:'put',
        sucss:function(){
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            var registResult = eval("("+XMLHttpRequest.responseText+")");
            if(registResult.loginStatus == "success"){
                //redirect
                window.location.href="./profile/"+userNo;
            }else{
                $('#infoModal .modal-body').html('登录失败<br>请检查您的账号和密码');
                $('#infoModal .modal-title').html("Login Error");
                $('#infoModal').modal('show');
            }
        }});
}
function regist() {
    var registName = $('#registName').val();
    var registPasswd = $('#registPasswd').val();
    var registQQNum = $('#registQQNum').val();
    var registAddress = $('#registAddress').val();
    var registPhone = $('#registPhone').val();
    var registEmail= $('#registEmail').val();
    var afirmCode = $('#registAfirmCode').val();

    $.ajax({
        url:"http://localhost:8080/MyBlog/regist/"+registName+"/"+registPasswd+"/"+registQQNum+"/"+registPhone+"/"+registEmail+"/"+registAddress+"/"+afirmCode,
        dataType:'jsonp',
        processData: true,
        type:'get',
        success:function(){
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            var registResult = eval("("+XMLHttpRequest.responseText+")");
            if(registResult.registStatus == "success"){
                signin();
                $('#userName').val(registName);
                $('#userPasswd').val(registPasswd);
                $('#infoModal .modal-body').html('注册成功<br>请检查您的账号是 ' + registResult.registNo);
                $('#infoModal .modal-title').text("Regist Success");
                $('#infoModal').modal('show');
            }else{
                $('#infoModal .modal-body').html(registResult.reason);
                $('#infoModal .modal-title').text("Regist Error");
                $('#infoModal').modal('show');
            }
        }});
}

function requireAfirmCode() {
    var registPhone = $('#registPhone').val();

    $.ajax({
        url:"http://localhost:8080/MyBlog/queryAfirmCode/"+registPhone,
        dataType:'jsonp',
        processData: true,
        type:'get',
        success:function(){
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            var registResult = eval("("+XMLHttpRequest.responseText+")");
            if(registResult.queryAfirmCodeStatus == "success"){
                $('#infoModal .modal-body').html("验证码发送成功");
                $('#infoModal .modal-title').html("query Success<br>请检查您的手机短信");
                $('#infoModal').modal('show');
            }else{
                $('#infoModal .modal-body').html("验证码发送失败");
                $('#infoModal .modal-title').html("query failed<br>请再次尝试");
                $('#infoModal').modal('show');
            }
        }});
}


function signin(){
    $('#loginPage').show();
    $('#registPage').hide();
}
function signup(){
    $('#registPage').show();
    $('#loginPage').hide();
}