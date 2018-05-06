window.onload = function() {
    var location = new String(window.location);
    var userId = new String(location).substr(new String(location).lastIndexOf('/')+1);
    $.ajax({
        url:"http://localhost:8080/MyBlog/queryUserInfo/"+"123",
        dataType:'jsonp',
        processData: true,
        typece:'put',
        sucss:function(){
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            var result = eval("("+XMLHttpRequest.responseText+")");
            if(result.status == "success"){
                $('#user-name').val(result.name);
                $('#user-email').val(result.email);
                $('#user-phone').val(result.phone);
                $('#user-QQ').val(result.qqNum);
                $('#user-intro').val(result.introduction);
            }else{

            }
        }
    });
};

function changeInfo(){
    var name = $('#user-name').val();
    var email = $('#user-email').val(result.email);
    var phone = $('#user-phone').val(result.phone);
    var qqNum = $('#user-QQ').val(result.qqNum);
    var introduction = $('#user-intro').val(result.introduction);

    $.ajax({
        url:"http://localhost:8080/MyBlog/queryUserInfo/"+"123",
        dataType:'jsonp',
        processData: true,
        typece:'put',
        sucss:function(){
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            var result = eval("("+XMLHttpRequest.responseText+")");
            if(result.status == "success"){


            }else{

            }
        }
    });
}