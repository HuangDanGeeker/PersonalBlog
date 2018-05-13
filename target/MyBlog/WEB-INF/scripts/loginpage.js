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

    $.ajax({
        url:"http://localhost:8080/MyBlog/regist/"+registName+"/"+registPasswd+"/"+registQQNum+"/"+registPhone+"/"+registEmail+"/"+registAddress,
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
    var jsonData = {
        "sid":"fbda3ff9e10f4c911680e7595eb61191",
        "token":"6717f98330bd6972f50c66d9423101cb",
        "appid":"bd3bd0259f76467a8b991165dabdcb3b",
        "templateid":"322051",
        "mobile":registPhone,
        'Access-Control-Allow-Origin': '*',
    };

    $.ajax({
        type: "POST",
        url: "https://open.ucpaas.com/ol/sms/sendsms",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(jsonData),
        'Access-Control-Allow-Origin': '*',
        dataType: "json",
        success: function (message) {
            if (message > 0) {
                alert("请求已提交！我们会尽快与您取得联系");
            }
        },
        error: function (message) {
            alert("提交数据失败！");
        }
    });
}


function signin(){
    $('#loginPage').show();
    $('#registPage').hide();
}
function signup(){
    $('#registPage').show();
    $('#loginPage').hide();
}